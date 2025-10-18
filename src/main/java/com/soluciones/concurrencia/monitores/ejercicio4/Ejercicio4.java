package com.soluciones.concurrencia.monitores.ejercicio4;

public class Ejercicio4 {
    public static void main(String[] args) {
        MonitorAtrapador monitor = new MonitorAtrapador();

        System.out.println("--- Escenario de prueba para Atrapador (Parte A) ---");

        // Creamos 5 hilos que se van a quedar esperando
        for (int i = 1; i <= 5; i++) {
            Thread hiloEspera = new Thread(new HiloAtrapador(monitor), "HILO-ESPERA-" + i);
            hiloEspera.start();
        }

        // Creamos un hilo que intentará liberar a 3
        Thread hiloLiberador = new Thread(new HiloAtrapador(monitor, 3), "LIBERADOR");
        hiloLiberador.start();

        // Creamos otro hilo que intentará liberar a 4, pero fallará (para probar esa lógica)
        Thread hiloLiberadorFallido = new Thread(new HiloAtrapador(monitor, 4), "LIBERADOR-FALLIDO");
        Thread hiloLiberadorExitoso = new Thread(new HiloAtrapador(monitor, 1), "LIBERADOR-EXITOSO");

        hiloLiberadorFallido.start();
        hiloLiberadorExitoso.start();
    }
}
