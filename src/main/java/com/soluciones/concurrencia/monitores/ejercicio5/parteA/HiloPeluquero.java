package com.soluciones.concurrencia.monitores.ejercicio5.parteA;

public class HiloPeluquero implements Runnable {
    private final MonitorPelu monitor;

    public HiloPeluquero(MonitorPelu monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {
        while (true){
            try{
                monitor.empezarCorte();

                System.out.println("...Peluquero cortando el pelo...");
                Thread.sleep((long) (Math.random() * 2000 + 1000));

                monitor.terminarCorte();
                Thread.sleep((long) (Math.random() * 2000 + 1000));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
