package com.soluciones.concurrencia.monitores.parcial_ascensor;

public class ParcialAscensor {

    public static void main(String[] args) {
        // --- Configuración de la Simulación ---
        int CAPACIDAD_ASCENSOR = 5;
        int TOTAL_PERSONAS = 15;
        // -------------------------------------

        System.out.println("Iniciando simulación de Ascensor Kosher.");
        System.out.println("Capacidad: " + CAPACIDAD_ASCENSOR + " | Personas: " + TOTAL_PERSONAS);

        // 1. Crear el Monitor
        MonitorAscensor monitor = new MonitorAscensor(CAPACIDAD_ASCENSOR);

        // 2. Crear y arrancar el Hilo del Ascensor
        Thread hiloAscensor = new Thread(new HiloAscensor(monitor), "ASCENSOR-Thread");
        hiloAscensor.start();

        // 3. Crear y arrancar los Hilos de las Personas
        for (int i = 1; i <= TOTAL_PERSONAS; i++) {
            // Asigna un origen y destino aleatorio (0 o 1)
            int origen = (int) (Math.random() * 2); // 0 o 1
            int destino = 1 - origen; // El piso opuesto

            String nombre = "Persona-" + i + " (" + origen + "->" + destino + ")";
            Thread hiloPersona = new Thread(new HiloPersona(monitor, origen, destino), nombre);
            hiloPersona.start();
        }
    }
}