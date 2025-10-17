package com.soluciones.concurrencia.monitores.ejercicio2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SecuenciadorMonitor {
    private final Lock lock;
    private final Condition condicionPrimero;
    private final Condition condicionSegundo;
    private final Condition condicionTercera;
    private int turno;

    public SecuenciadorMonitor(){
        this.lock = new ReentrantLock();
        condicionPrimero = lock.newCondition();
        condicionSegundo = lock.newCondition();
        condicionTercera = lock.newCondition();
        turno = 1;
    }

    public void primero(int id){
        lock.lock(); //tomo el lock
        try{
            while(turno!=1){
                condicionPrimero.await();
            }
            System.out.println(">>Hilo " + id + " ejecuta: PRIMERO");
            Thread.sleep(2500);

            turno = 2; // cambio de turno

            condicionSegundo.signal(); //levanto al que este esperando para ejecutar segundo
        } catch (InterruptedException e){
            System.out.println("uppssss: "+e.getMessage());
        } finally {
        lock.unlock();
        }
    }

    public void segundo(int id){
        lock.lock();
        try{
            while(turno!=2){
                condicionSegundo.await();
            }

            System.out.println(">>Hilo " + id + " ejecuta: SEGUNDO");
            Thread.sleep(2500);

            turno = 3;

            condicionTercera.signal();
        } catch (InterruptedException e) {
            System.out.println("uppps: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void tercero(int id) {
        lock.lock();
        try {
            while (turno!=3){
                condicionTercera.await();
            }

            System.out.println(">>Hilo " + id + " ejecuta: TERCERO");
            Thread.sleep(2500);
            System.out.println("--------------------------------CICLO COMPLETADO---------------------------");

            turno = 1; //reinicio el ciclo

            condicionPrimero.signal();
        } catch (InterruptedException e) {
            System.out.println("uppps: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
