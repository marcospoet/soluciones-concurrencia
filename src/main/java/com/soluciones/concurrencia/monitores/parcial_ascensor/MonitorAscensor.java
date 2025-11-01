package com.soluciones.concurrencia.monitores.parcial_ascensor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorAscensor {
    private final Lock lock = new ReentrantLock();
    private final int N;
    private int capacidad = 0;
    private final long TIMEOUT_ASCENSOR = 5;
    // Condiciones Personas
    private final Condition[] personaEsperandoSubir = new Condition[2];
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

    public void viajar(int origen, int destino) throws InterruptedException{
        lock.lock();
        try{
           personaEsperandoSubir[origen].await();

           capacidad++;

           ascensorEsperandoCarga.signal();

           personaEsperandoBajar[destino].await();

           capacidad--;

           ascensorEsperandoDescarga.signal();
        } finally {
        lock.unlock();
        }
    }

    public void esperarPersonas(int piso) throws InterruptedException{
        lock.lock();
        try{
            personaEsperandoSubir[piso].signalAll();

            boolean pasoXtiempo = false;

            while(capacidad < N && !pasoXtiempo){

                //    await() devuelve 'true' si fue despertado por un signal()
                //    devuelve 'false' si se despertó porque pasó el tiempo.
                boolean fueDespertadoPorSignal = ascensorEsperandoCarga.await(TIMEOUT_ASCENSOR, TimeUnit.SECONDS);

                if(!fueDespertadoPorSignal){
                    pasoXtiempo = true;
                }
                // Si fueDespertadoPorSignal == true (una persona subió e hizo signal),
                // el bucle 'while' simplemente se repite y vuelve a chequear
                // la condición (capacidad < N).
            }

            // Salió del bucle. Informa la razón.
            if (pasoXtiempo) {
                System.out.println("ASCENSOR: ¡Timeout! Cerrando puertas con " + capacidad + " personas.");
            } else {
                System.out.println("ASCENSOR: ¡Lleno! Cerrando puertas con " + capacidad + " personas.");
            }

        } finally {
            lock.unlock();
        }
    }

    public void llegarAlPiso(int piso) throws InterruptedException{
        lock.lock();
        try{
           personaEsperandoBajar[piso].signalAll();
           while (capacidad>0){
               ascensorEsperandoDescarga.await();
           }
        } finally {
        lock.unlock();
        }

    }

}
