package com.soluciones.concurrencia.monitores.ejercicio5.parteA;

public class Ejercicio5 {
    public static void main(String[] args){
        MonitorPelu monitor = new MonitorPelu();

        //Creo el hilo peluquero
        Thread peluquero = new Thread(new HiloPeluquero(monitor), "PELUQUERO");
        peluquero.start();

        //creo los clientes y los mando a la pelu
        int cantClientes = 5;
        System.out.println("Abriendo la peluqueria..." + cantClientes + " clientes en camino...");

        for (int i = 1; i <=cantClientes; i++) {
           Thread cliente = new Thread(new HiloCliente(monitor),"Cliente-" + i);
           cliente.start();
            try {
                // Simula que los clientes llegan en momentos distintos
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
