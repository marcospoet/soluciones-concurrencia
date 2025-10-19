package com.soluciones.concurrencia.monitores.ejercicio4.parteB;

public class Ejercicio4 {

    public static void main(String[] args) throws InterruptedException {
        escenario1_LiberadorEspera();
        Thread.sleep(3000); // Pausa entre escenarios
        escenario2_LiberadorLlegaTarde();
        Thread.sleep(3000);
        escenario3_MultiplesLiberadores();
    }

    /**
     * Escenario 1: Un hilo liberador llega primero y se bloquea
     * hasta que lleguen los 3 hilos que necesita.
     */
    public static void escenario1_LiberadorEspera() throws InterruptedException {
        System.out.println("\n--- ESCENARIO 1: Liberador llega primero y espera ---");
        MonitorAtrapador monitor = new MonitorAtrapador();

        Thread liberador = new Thread(new HiloAtrapador(monitor, 3), "LIBERADOR-1");
        liberador.start();

        Thread.sleep(500); // Dar tiempo a que el liberador se bloquee

        System.out.println("\n...Lanzando hilos que esperarán...");
        for (int i = 1; i <= 3; i++) {
            new Thread(new HiloAtrapador(monitor), "ESPERA-" + i).start();
            Thread.sleep(200); // Simula la llegada escalonada
        }
    }

    /**
     * Escenario 2: Varios hilos llegan y esperan. Luego llega un
     * liberador y suelta a un subconjunto de ellos.
     */
    public static void escenario2_LiberadorLlegaTarde() throws InterruptedException {
        System.out.println("\n--- ESCENARIO 2: Liberador llega tarde y libera a 2 de 4 ---");
        MonitorAtrapador monitor = new MonitorAtrapador();

        for (int i = 1; i <= 4; i++) {
            new Thread(new HiloAtrapador(monitor), "ESPERA-" + i).start();
        }

        Thread.sleep(1000); // Dar tiempo a que todos se bloqueen

        System.out.println("\n...Lanzando hilo liberador...");
        new Thread(new HiloAtrapador(monitor, 2), "LIBERADOR-TARDIO").start();
    }

    /**
     * Escenario 3: Un liberador tiene éxito, pero el segundo se bloquea
     * porque ya no hay suficientes hilos esperando.
     */
    public static void escenario3_MultiplesLiberadores() throws InterruptedException {
        System.out.println("\n--- ESCENARIO 3: Dos liberadores, uno tiene éxito y el otro no ---");
        MonitorAtrapador monitor = new MonitorAtrapador();

        for (int i = 1; i <= 5; i++) {
            new Thread(new HiloAtrapador(monitor), "ESPERA-" + i).start();
        }

        Thread.sleep(1000);

        System.out.println("\n...Lanzando liberadores...");
        // Este debería tener éxito
        new Thread(new HiloAtrapador(monitor, 3), "LIBERADOR-EXITOSO").start();
        Thread.sleep(100);
        // Este debería bloquearse, porque solo quedarán 2 hilos esperando
        new Thread(new HiloAtrapador(monitor, 3), "LIBERADOR-BLOQUEADO").start();
    }
}

