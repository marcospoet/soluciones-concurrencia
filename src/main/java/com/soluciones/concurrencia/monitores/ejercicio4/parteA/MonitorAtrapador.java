package com.soluciones.concurrencia.monitores.ejercicio4.parteA;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Solución para la Parte (a) del ejercicio del Atrapador.
 */
public class MonitorAtrapador {
    private final Lock lock = new ReentrantLock();
    private final Condition hilosAtrapados = lock.newCondition();
    private int cantHilosEsperando = 0;

    public void esperar() throws InterruptedException {
        lock.lock();
        try {
            cantHilosEsperando++;
            System.out.println(Thread.currentThread().getName() + " comienza a esperar. Total esperando: " + cantHilosEsperando);
            hilosAtrapados.await(); // El hilo duerme y libera el lock.

            // El hilo se desperto y lo resto
            System.out.println(">> " + Thread.currentThread().getName() + " FUE LIBERADO. Quedan esperando: " + cantHilosEsperando);

        } finally {
            lock.unlock();
        }
    }

    public void liberar(int N) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " intenta liberar a " + N + " hilos.");
            if (cantHilosEsperando >= N) {
                cantHilosEsperando-=N;
                System.out.println(">> Hay suficientes hilos. Liberando a " + N + "...");
                for (int i = 0; i < N; i++) {
                    hilosAtrapados.signal(); // Despierta a un hilo de la cola de espera.
                }
            } else {
                System.out.println(">> No hay suficientes hilos (" + cantHilosEsperando + "). No se libera a nadie.");
            }
        } finally {
            lock.unlock();
        }
    }
}