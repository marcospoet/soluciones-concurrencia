package com.soluciones.concurrencia.ejercicio5b;

public class P2 implements Runnable {
    public void run() {
        while (true) {
            Ejercicio5b.semB.acquireUninterruptibly(1);
            System.out.println("B");
            Ejercicio5b.semA.release();
        }
    }
}