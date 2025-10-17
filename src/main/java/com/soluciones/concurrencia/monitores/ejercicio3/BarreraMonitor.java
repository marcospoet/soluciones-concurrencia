package com.soluciones.concurrencia.monitores.ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarreraMonitor {
    private final int cantHilosParaLargar;
    private int cantHillosEsperando=0;

    private final Lock lock = new ReentrantLock();
    private final Condition barreraAlcanzada = lock.newCondition();

    public BarreraMonitor(int totalHilos){
        this.cantHilosParaLargar=totalHilos;
    }

    public void esperar() throws InterruptedException{
        lock.lock();
        try{
            cantHillosEsperando++;

            if(cantHillosEsperando==cantHilosParaLargar){
                System.out.println(">> El ultimo hilo llego, liberando a todos los hilos...");
                cantHillosEsperando=0;
                barreraAlcanzada.signalAll();
            } else {
                    barreraAlcanzada.await();
            }
        }finally {
            lock.unlock();
        }
    }

}
