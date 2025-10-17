package com.soluciones.concurrencia.monitores.ejercicio2;

public class HiloEjecutor implements Runnable{
    private final SecuenciadorMonitor monitor;
    private final int id;
    private final int operacionACorrer;

    public HiloEjecutor(SecuenciadorMonitor mon, int id, int operacion){
        this.monitor=mon;
        this.id=id;
        this.operacionACorrer=operacion;
    }

    @Override
    public void run(){
       while(true){
           switch (operacionACorrer){
               case 1:
                   monitor.primero(id);
                   break;
               case 2:
                   monitor.segundo(id);
                   break;
               case 3:
                   monitor.tercero(id);
                   break;
           }
           try {
               Thread.sleep(2500); //para que los hilos se puedan ir ejecutando
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
    }
}
