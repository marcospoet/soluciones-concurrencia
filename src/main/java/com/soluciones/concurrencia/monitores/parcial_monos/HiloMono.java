package com.soluciones.concurrencia.monitores.parcial_monos;

public class HiloMono implements Runnable {
    private final MonitorCuerda monitor;
    private final Destination destino;
    private final int id;

    public HiloMono(int id, MonitorCuerda monitor, Destination destino) {
        this.id = id;
        this.monitor = monitor;
        this.destino = destino;
    }

    /**
     * Simulación de la función CrossRavine()
     * Esto ocurre FUERA del monitor.
     */
    private void crossRavine() throws InterruptedException {
        System.out.println("... Mono " + id + " (-> " + destino + ") está CRUZANDO ...");
        Thread.sleep((long) (Math.random() * 1000 + 500)); // Cruza entre 0.5 y 1.5 seg
    }

    @Override
    public void run() {
        try {
            // Simula que los monos llegan en momentos distintos
            Thread.sleep((long) (Math.random() * 5000));
            System.out.println("Mono " + id + " (-> " + destino + ") llega al barranco.");

            // 1. Espera a que sea seguro cruzar (entra al monitor)
            monitor.WaitUntilSafeToCross(destino);

            // 2. Cruza (fuera del monitor)
            crossRavine();

            // 3. Avisa que terminó de cruzar (vuelve a entrar al monitor)
            monitor.DoneWithCrossing(destino);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
