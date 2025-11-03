package com.soluciones.concurrencia.monitores.parcial_ascensor;

public class HiloAscensor implements Runnable {

    private final MonitorAscensor monitor;
    // Esta es la ÚNICA fuente de verdad sobre dónde está el ascensor.
    private int pisoActual = 0;

    public HiloAscensor(MonitorAscensor monitor) {
        this.monitor = monitor;
    }

    /**
     * Esta es la función moverse().
     * Simula el tiempo que tarda el ascensor en viajar entre pisos.
     * Se llama SIN tener el lock del monitor.
     */
    private void moverse() throws InterruptedException {
        System.out.println("-------------------------------------------------");
        System.out.println("ASCENSOR: Viajando desde piso " + pisoActual + "...");

        // Simula el viaje (2 segundos)
        Thread.sleep(2000);

        // Actualiza su propio estado
        pisoActual = 1 - pisoActual;

        System.out.println("ASCENSOR: ...ha llegado al piso " + pisoActual);
        System.out.println("-------------------------------------------------");
    }


    @Override
    public void run() {
        try {
            while (true) {
                // 1. Llama al monitor con su estado actual
                monitor.esperarPersonas(pisoActual);

                // 2. Se mueve (sin lock) y actualiza su estado
                moverse();

                // 3. Llama al monitor con su NUEVO estado
                monitor.llegarAlPiso(pisoActual);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

