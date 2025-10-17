package com.soluciones.concurrencia.semaforos.ejercicio6;

public class P2  implements Runnable{

    public void run() {
        while(true){
            Ejercicio6.semN2.acquireUninterruptibly(1);
            Ejercicio6.N2= Ejercicio6.N2 + 2*Ejercicio6.N-1;
            Ejercicio6.semN1.release();
        }
    }
}
