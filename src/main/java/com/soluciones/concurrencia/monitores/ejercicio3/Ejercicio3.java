package com.soluciones.concurrencia.monitores.ejercicio3;

public class Ejercicio3 {
    public static void main(String[] args){
        final int CantHilos = 5;
        BarreraMonitor miBarrera = new BarreraMonitor(CantHilos);

        System.out.println("Iniciando " + CantHilos + " hilos...");

        for (int i = 1; i <= CantHilos; i++) {
            String antes = "LETRA " + (char)('A' + i - 1);
            String despues = "numero: " + i;
            Thread t = new Thread(new HiloDeBarrera(i, miBarrera, antes, despues));
            t.start();
        }
    }
}
