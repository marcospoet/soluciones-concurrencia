package com.soluciones.concurrencia.monitores.ejercicio3;

public class HiloDeBarrera implements Runnable {
    private final BarreraMonitor barrera;
    private final String mensajeAntes;
    private final String mensajeDespues;
    private final int id;

    public HiloDeBarrera(int id, BarreraMonitor barrera, String antes, String despues) {
        this.id = id;
        this.barrera = barrera;
        this.mensajeAntes = antes;
        this.mensajeDespues = despues;
    }

    @Override
    public void run() {
        try {
            System.out.println("Hilo " + id + ": Ejecuta ANTES de la barrera -> " + mensajeAntes);
            // Simula un trabajo de duración variable antes de llegar a la barrera.
            Thread.sleep((long) (Math.random() * 1000));

            System.out.println("Hilo " + id + ": LLEGÓ a la barrera y va a esperar.");
            barrera.esperar(); // Aquí todos los hilos se sincronizan.

            System.out.println("Hilo " + id + ": Pasó la barrera. Ejecuta DESPUÉS -> " + mensajeDespues);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}