package com.soluciones.concurrencia.semaforos.ejercicio6;

public class P1  implements Runnable{

    public void run(){
        while(Ejercicio6.N>0){
            Ejercicio6.semN2.release();
            Ejercicio6.semN1.acquireUninterruptibly(1);
            Ejercicio6.N = Ejercicio6.N-1;

        }
        System.out.println("------"+Ejercicio6.N2+"------");
    }
}
