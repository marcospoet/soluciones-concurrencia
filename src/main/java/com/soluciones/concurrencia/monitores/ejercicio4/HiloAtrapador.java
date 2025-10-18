package com.soluciones.concurrencia.monitores.ejercicio4;

public class HiloAtrapador implements Runnable {
    private final MonitorAtrapador monitor;
    private final boolean esLiberador;
    private final int nLiberar;

    // Constructor para un hilo que va a esperar
    public HiloAtrapador(MonitorAtrapador monitor) {
        this.monitor = monitor;
        this.esLiberador = false;
        this.nLiberar = 0;
    }

    // Constructor para un hilo que va a liberar a N hilos
    public HiloAtrapador(MonitorAtrapador monitor, int n) {
        this.monitor = monitor;
        this.esLiberador = true;
        this.nLiberar = n;
    }

    @Override
    public void run() {
        try {
            if (esLiberador) {
                // Da tiempo a que otros hilos se bloqueen primero
                Thread.sleep(1000);
                monitor.liberar(nLiberar);
            } else {
                monitor.esperar();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
