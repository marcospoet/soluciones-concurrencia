package com.soluciones.concurrencia.semaforos.ejercicio5;

public class P2 implements Runnable {
    public void run() {
        while (true) {
            Ejercicio5a.semB.acquireUninterruptibly(1);
            System.out.println("B");
            Ejercicio5a.semA.release();
        }
    }
}