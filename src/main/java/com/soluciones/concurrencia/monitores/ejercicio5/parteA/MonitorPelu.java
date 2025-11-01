package com.soluciones.concurrencia.monitores.ejercicio5.parteA;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorPelu {
    private final Lock lock = new ReentrantLock();

    //aca espera el peluquero en caso de no haber clientes.
    private final Condition clientesEsperando = lock.newCondition();
    //aca esperan los clientes si el peluquero esta ocupado.
    private final Condition peluqeroOcupado = lock.newCondition();
    //aca espera el cliente si le estan cortando el pelo.
    private final Condition corteEnProgreso = lock.newCondition();

    private boolean peluqueroOcuparo = false;
    private int cantClientesEsperando = 0;

    private void empezarCorte() throws InterruptedException{
       lock.lock();
       try{
           //mientras no haya clientes lo duermo al peluquero
           while (cantClientesEsperando==0){
               System.out.println("PELUQUERO: No hay clientes, a dormir...");
               clientesEsperando.await();
           }
       } finally {
           lock.unlock();
       }
    }
}
