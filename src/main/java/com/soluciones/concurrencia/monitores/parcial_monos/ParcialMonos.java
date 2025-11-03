package com.soluciones.concurrencia.monitores.parcial_monos;

public class ParcialMonos {

    public static void main(String[] args) {
        // --- Configuración de la Simulación ---
        int CAPACIDAD_CUERDA = 5; // Máximo 5 monos en la cuerda
        int TOTAL_MONOS = 15;
        // -------------------------------------

        System.out.println("Iniciando simulación de Monos en Barranco.");
        System.out.println("Capacidad Cuerda: " + CAPACIDAD_CUERDA + " | Total Monos: " + TOTAL_MONOS);

        // 1. Crear el Monitor
        MonitorCuerda monitor = new MonitorCuerda(CAPACIDAD_CUERDA);

        // 2. Crear y arrancar los Hilos de los Monos
        for (int i = 1; i <= TOTAL_MONOS; i++) {
            // Asigna una dirección aleatoria
            Destination dir = (Math.random() < 0.5) ? Destination.EAST : Destination.WEST;

            // Usamos el nombre del Hilo para pasar el ID
            String nombre = "Mono-" + i;
            Thread hiloMono = new Thread(new HiloMono(i, monitor, dir), nombre);
            hiloMono.start();
        }
    }
}
