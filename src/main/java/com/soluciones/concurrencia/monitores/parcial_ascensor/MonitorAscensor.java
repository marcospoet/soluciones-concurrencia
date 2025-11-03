package com.soluciones.concurrencia.monitores.parcial_ascensor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorAscensor {
    private final Lock lock = new ReentrantLock();
    private final int N;
    private int capacidad = 0;
    private final long TIMEOUT_ASCENSOR_SEGUNDOS = 5; // 5 segundos de timeout
    private boolean puertasAbiertas = false;
    private int pisoActual = 0;
    // Condiciones Personas
    // [0]: Personas esperando SUBIR en Planta Baja
    // [1]: Personas esperando SUBIR en Primer Piso
    private final Condition[] personaEsperandoSubir = new Condition[2];

    // [0]: Personas DENTRO esperando BAJAR en Planta Baja
    // [1]: Personas DENTRO esperando BAJAR en Primer Piso
    private final Condition[] personaEsperandoBajar = new Condition[2];

    //Condiciones Ascensor
    private final Condition ascensorEsperandoCarga = lock.newCondition();
    private final Condition ascensorEsperandoDescarga = lock.newCondition();

    public MonitorAscensor(int capacidadMaxima){
        this.N = capacidadMaxima;
        this.personaEsperandoSubir[0] = lock.newCondition();
        this.personaEsperandoSubir[1] = lock.newCondition();
        this.personaEsperandoBajar[0] = lock.newCondition();
        this.personaEsperandoBajar[1] = lock.newCondition();
    }

    /**
     * Llamado por HiloPersona.
     */
    public void viajar(int origen, int destino) throws InterruptedException{
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " espera en pisoActual " + origen);

            // 1. Espera MIENTRAS las puertas estén cerradas O el ascensor esté lleno
            while (!puertasAbiertas || capacidad >= N || origen != pisoActual) {
                personaEsperandoSubir[origen].await();
            }

            // 2. Fue despertado Y las puertas están abiertas Y hay espacio. Se sube.
            capacidad++;
            System.out.println(Thread.currentThread().getName() + " SE SUBE. Capacidad: " + capacidad + "/" + N);

            // 3. Avisa al ascensor que se subió.
            ascensorEsperandoCarga.signal();

            // 4. Espera dentro del ascensor hasta llegar al destino.
            personaEsperandoBajar[destino].await();

            // 5. Fue despertado. Se baja.
            capacidad--;
            System.out.println(">> " + Thread.currentThread().getName() + " SE BAJA en pisoActual " + destino + ". Capacidad: " + capacidad);

            // 6. Avisa al ascensor que se bajó.
            ascensorEsperandoDescarga.signal();

        } finally {
            lock.unlock();
        }
    }

    /**
     * Llamado por HiloAscensor.
     */
   public void esperarPersonas(int piso) throws InterruptedException{
        lock.lock();
        try{
            System.out.println("ASCENSOR: Abriendo puertas en pisoActual " + piso + " para SUBIR.");

            // 1. Abrir las puertas
            puertasAbiertas = true;

            // 2. Despertar a todos
            personaEsperandoSubir[piso].signalAll();


            boolean pasoXtiempo = false;
            while(capacidad < N && !pasoXtiempo){
                // ... (tu lógica de await con timeout es perfecta) ...
                boolean fueDespertadoPorSignal = ascensorEsperandoCarga.await(TIMEOUT_ASCENSOR_SEGUNDOS, TimeUnit.SECONDS);
                if(!fueDespertadoPorSignal){
                    pasoXtiempo = true;
                }
            }

            // 3. Cerrar las puertas
            puertasAbiertas = false;

            if (pasoXtiempo) {
                System.out.println("ASCENSOR: ¡Timeout! Cerrando puertas con " + capacidad + " personas.");
            } else {
                System.out.println("ASCENSOR: ¡Lleno! Cerrando puertas con " + capacidad + " personas.");
            }

            // 4. (Opcional pero recomendado) Despertar a los hilos colados
            // para que vean que las puertas se cerraron y vuelvan a dormir.
            personaEsperandoSubir[piso].signalAll();

        } finally {
            lock.unlock();
        }
    }

    /**
     * Llamado por HiloAscensor.
     */
    public void llegarAlPiso(int piso) throws InterruptedException{
        lock.lock();
        try{
            pisoActual = piso;
            puertasAbiertas = false;
            System.out.println("ASCENSOR: Abriendo puertas en pisoActual " + piso + " para BAJAR.");
            // 1. Avisa a las personas que quieren BAJAR en este pisoActual.
            personaEsperandoBajar[piso].signalAll();

            // 2. Espera MIENTRAS haya gente bajándose.
            while (capacidad > 0){
                ascensorEsperandoDescarga.await();
            }
            System.out.println("ASCENSOR: Todos bajaron en pisoActual " + piso + ". Listo.");
        } finally {
            lock.unlock();
        }

    }

}
