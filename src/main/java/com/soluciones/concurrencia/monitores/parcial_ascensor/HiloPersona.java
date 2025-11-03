package com.soluciones.concurrencia.monitores.parcial_ascensor;

public class HiloPersona implements Runnable {

    private final MonitorAscensor monitor;
    private final int pisoOrigen;
    private final int pisoDestino;

    public HiloPersona(MonitorAscensor monitor, int origen, int destino) {
        this.monitor = monitor;
        this.pisoOrigen = origen;
        this.pisoDestino = destino;
    }

    @Override
    public void run() {
        try {
            // Simula que la persona tarda un tiempo aleatorio en llegar a la parada
            Thread.sleep((long) (Math.random() * 10000)); // Tarda entre 0 y 10 seg en llegar

            // 1. Llama al monitor para viajar
            monitor.viajar(pisoOrigen, pisoDestino);

            // 2. La persona se va...

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}