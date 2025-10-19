package com.soluciones.concurrencia.monitores.ejercicio4.parteB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Solución para la Parte (b) del ejercicio del Atrapador.
 * El metodo liberar(N) se bloquea si no hay suficientes hilos hasta tanto se puedan liberar N threads. (por eso le meto el while para poder comprobar eso)
 */
public class MonitorAtrapador {
    private final Lock lock = new ReentrantLock();
    private final Condition hilosAtrapados = lock.newCondition();
    private final Condition liberadoEsperando = lock.newCondition();
    private int cantHilosEsperando = 0;

    public void esperar() throws InterruptedException {
        lock.lock();
        try {
            cantHilosEsperando++;
            System.out.println(Thread.currentThread().getName() + " comienza a esperar. Total esperando: " + cantHilosEsperando);

            liberadoEsperando.signal(); // levanto a uno para ver si se cumplio la condicion de liberar N threads

            hilosAtrapados.await(); // El hilo duerme y libera el lock.

            // El hilo se desperto y lo resto
            System.out.println(">> " + Thread.currentThread().getName() + " FUE LIBERADO.");

        } finally {
            lock.unlock();
        }
    }

    public void liberar(int N) throws InterruptedException{
        lock.lock();
        try {
           while(cantHilosEsperando<N){
               System.out.println(Thread.currentThread().getName() + " se bloquea. Necesita " + N + ", solo hay " + cantHilosEsperando);
               liberadoEsperando.await(); // mientras no cumpla la condicion lo mando a dormir
           }
           // Si sale del bucle, la condición se cumple.
           System.out.println(">> " + Thread.currentThread().getName() + " va a liberar " + N + " de " + cantHilosEsperando + " hilos.");
           cantHilosEsperando -= N;
           System.out.println("   (Quedarán " + cantHilosEsperando + " hilos esperando después de esta operación)");
           for (int i = 0; i < N; i++) {
               hilosAtrapados.signal();
           }
        } finally {
            lock.unlock();
        }
    }
}