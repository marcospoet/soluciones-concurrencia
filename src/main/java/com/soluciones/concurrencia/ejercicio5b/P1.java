package com.soluciones.concurrencia.ejercicio5b;

public class P1 implements Runnable {
    public void run() {
        while (true) {
            Ejercicio5b.semA.acquireUninterruptibly(1);
            System.out.println("A");
            System.out.println("----------------------------------------");
            Ejercicio5b.semB.release();
        }
    }
}