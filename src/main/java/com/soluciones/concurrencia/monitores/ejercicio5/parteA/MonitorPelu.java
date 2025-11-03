package com.soluciones.concurrencia.monitores.ejercicio5.parteA;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorPelu {
    private final Lock lock = new ReentrantLock();

    // aca espera el peluquero en caso de no haber clientes.
    private final Condition clientesEsperando = lock.newCondition();
    // aca esperan los clientes si el peluquero esta ocupado.
    private final Condition peluqueroOcupado = lock.newCondition();
    // aca espera el cliente si le estan cortando el pelo.
    private final Condition corteEnProgreso = lock.newCondition();

    // Estado: El peluquero está actualmente atendiendo a alguien.
    private boolean peluqueroEstaOcupado = false;
    // Estado: Cuántos clientes hay en la "sala de espera" (no en la silla).
    private int cantClientesEsperando = 0;

    /**
     * Llamado por el Peluquero.
     * Se bloquea si no hay clientes. Si hay, llama a uno.
     */
    public void empezarCorte() throws InterruptedException {
        lock.lock();
        try {
            // (Peluquero) mientras no haya clientes en la sala de espera...
            while (cantClientesEsperando == 0) {
                System.out.println("PELUQUERO: No hay clientes, a dormir...");
                clientesEsperando.await(); // ...se va a dormir.
            }

            // (Peluquero) Hay clientes. Llama a uno de la sala de espera.
            cantClientesEsperando--;
            System.out.println("PELUQUERO: Pasa el siguiente. Quedan " + cantClientesEsperando + " esperando.");
            peluqueroOcupado.signal(); // Llama a un cliente que esperaba en peluqueroOcupado

        } finally {
            lock.unlock();
        }
    }

    /**
     * Llamado por el Peluquero.
     * Libera al cliente que estaba en la silla.
     */
    public void terminarCorte() {
        lock.lock();
        try {
            System.out.println("PELUQUERO: Corte terminado. Quedó fachero.");
            peluqueroEstaOcupado = false; // El peluquero se desocupa
            corteEnProgreso.signal(); // Avisa al cliente en la silla que puede irse
        } finally {
            lock.unlock();
        }
    }

    /**
     * Llamado por el Cliente.
     * Se bloquea si el peluquero está ocupado.
     * Luego se bloquea mientras le cortan el pelo.
     */
    public void cortarseElPelo() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " llega a la peluquería.");

            // (Cliente) Mientras el peluquero esté ocupado...
            while (peluqueroEstaOcupado) {
                cantClientesEsperando++; // ...se anota en la sala de espera...
                System.out.println(Thread.currentThread().getName() + " espera. (Total en sala: " + cantClientesEsperando + ")");
                peluqueroOcupado.await(); // ...y se va a dormir (en la sala de espera).
            }

            // (Cliente) El peluquero está libre (o lo acaban de despertar).
            peluqueroEstaOcupado = true; // Ocupa al peluquero.
            System.out.println(Thread.currentThread().getName() + " se sienta en la silla.");

            // Despierta al peluquero, que podría estar durmiendo en empezarCorte().
            clientesEsperando.signal();

            // (Cliente) Espera en la silla a que el peluquero termine.
            corteEnProgreso.await();

            System.out.println(">> " + Thread.currentThread().getName() + " se va con el pelo cortado.");

        } finally {
            lock.unlock();
        }
    }
}
