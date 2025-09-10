package com.soluciones.concurrencia.ejercicio5;

public class P1 implements Runnable {
    public void run() {
        while (true) {
            Ejercicio5a.semA.acquireUninterruptibly(1);
            System.out.println("A");
            Ejercicio5a.semB.release();
        }
    }
}