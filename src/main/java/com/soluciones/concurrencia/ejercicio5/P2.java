package com.soluciones.concurrencia.ejercicio5;

public class P2 implements Runnable {
    public void run() {
        while (true) {
            Ejercicio5a.semB.acquireUninterruptibly();
            System.out.println("B");
            Ejercicio5a.semA.release();
        }
    }
}