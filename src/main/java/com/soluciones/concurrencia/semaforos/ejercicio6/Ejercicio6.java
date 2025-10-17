package com.soluciones.concurrencia.semaforos.ejercicio6;

import java.util.concurrent.Semaphore;

public class Ejercicio6 {

    static Semaphore semN1 = new Semaphore(0);
    static Semaphore semN2 = new Semaphore(0);
    static volatile int N=50;
    static volatile int N2=0;

    public static void main(String[] args) {
        Thread t1 = new Thread(new P1());
        Thread t2 = new Thread(new P2());
        t1.start();
        t2.start();
    }
}
