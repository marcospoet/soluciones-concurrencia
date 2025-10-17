package com.soluciones.concurrencia.semaforos.ejercicio5;

import java.util.concurrent.Semaphore;

public class Ejercicio5a {
    // Semáforos compartidos por los hilos
    static Semaphore semA = new Semaphore(1);
    static Semaphore semB = new Semaphore(1);

    public static void main(String[] args) {
        System.out.println("Iniciando hilos para el Ejercicio 5a...");

        Thread hilo1 = new Thread(new P1());
        Thread hilo2 = new Thread(new P2());

        hilo1.start();
        hilo2.start();
    }
}
