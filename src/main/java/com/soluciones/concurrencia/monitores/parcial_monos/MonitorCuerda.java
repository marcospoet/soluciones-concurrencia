package com.soluciones.concurrencia.monitores.parcial_monos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorCuerda {
    private final Lock lock = new ReentrantLock();
    private final int N; //Capacidad maxima de la cuerda
    private int capacidad = 0;
    private int direccionCuerda = -1; // si esta vacia es menos 1, no tiene direccion
    //Condiciones de los monos
    private final Condition[] monosEsperando = new Condition[2];

    public MonitorCuerda(int capacidadMaxima){
        this.N = capacidadMaxima;
        monosEsperando[0] = lock.newCondition();
        monosEsperando[1] = lock.newCondition();
    }

    public void WaitUntilSafeToCross(Destination destino) throws InterruptedException{
        lock.lock();
        try{
            int direccionMono = destino.ordinal();

            // Bucle de espera
            while( capacidad >= N || (direccionMono != direccionCuerda && direccionCuerda != -1)){

                System.out.println("--- " + Thread.currentThread().getName() + " (-> " + destino + ") ESPERA. Cuerda: " + capacidad + "/"+N + ", Dir: " + (direccionCuerda == 0 ? "EAST":"WEST") );
                monosEsperando[direccionMono].await();
            }

            // El mono puede subir
            capacidad++;
            direccionCuerda = direccionMono;

            System.out.println(Thread.currentThread().getName() + " (-> " + destino + ") SE SUBE. Capacidad: " + capacidad + "/" + N);

        } finally {
            lock.unlock();
        }
    }

    public void DoneWithCrossing(Destination destino) throws InterruptedException{
        lock.lock();
        try{
            capacidad--;

            System.out.println(Thread.currentThread().getName() + " SE BAJA. Capacidad: " + capacidad + "/" + N);

            if(capacidad == 0) {
                direccionCuerda = -1; // Cuerda vacía
                int dirContraria = 1 - destino.ordinal();

                System.out.println("...Cuerda vacía. Despertando al otro lado (Dir " + (dirContraria == 0 ? "EAST" : "WEST") + ")");
                monosEsperando[dirContraria].signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
