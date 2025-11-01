package com.soluciones.concurrencia.monitores.ejercicio5.parteA;

public class HiloCliente implements Runnable {
    private final MonitorPelu monitor;

    public HiloCliente(MonitorPelu monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            monitor.cortarseElPelo();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
