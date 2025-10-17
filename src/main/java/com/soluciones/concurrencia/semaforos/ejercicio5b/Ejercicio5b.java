package com.soluciones.concurrencia.semaforos.ejercicio5b;

import java.util.concurrent.Semaphore;

public class Ejercicio5b {

   static Semaphore semA = new Semaphore(1);
   static Semaphore semB = new Semaphore(0);

   public static void main(String[] args){
       System.out.println("------------Iniciando Ejercicio5b--------------");
       Thread hilo1 = new Thread(new P1());
       Thread hilo2 = new Thread(new P2());

       hilo1.start();
       hilo2.start();
   }


}
