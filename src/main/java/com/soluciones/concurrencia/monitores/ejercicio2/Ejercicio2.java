package com.soluciones.concurrencia.monitores.ejercicio2;

public class Ejercicio2 {

    public static void main(String[] args){
        SecuenciadorMonitor monitor = new SecuenciadorMonitor();
        int contador = 1;

        for (int i = 0; i < 3; i++) {
            Thread hilo = new Thread(new HiloEjecutor(monitor,contador++,1));
            hilo.start();
        }
        for (int i = 0; i < 3; i++) {
            Thread hilo = new Thread(new HiloEjecutor(monitor,contador++,2));
            hilo.start();
        }
        for (int i = 0; i < 3; i++) {
            Thread hilo = new Thread(new HiloEjecutor(monitor,contador++,3));
            hilo.start();
        }
    }

}
