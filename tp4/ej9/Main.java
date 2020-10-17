/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej9;

import java.util.concurrent.Semaphore;

/**
 *
 * @author joa
 */
public class Main {

    public static void main(String args[]) {

        Semaphore semPasajero = new Semaphore(1);
        Semaphore semTaxista = new Semaphore(0);

        Taxi taxi = new Taxi(semTaxista, semPasajero);

        Taxista taxista = new Taxista(taxi);
        Thread tTaxista = new Thread(taxista);

        Thread[] t = new Thread[4];
        Pasajero[] pasajeros=new Pasajero[4];
        for (int i = 0; i <3; i++) {
            pasajeros[i] = new Pasajero(taxi);
            t[i] = new Thread(pasajeros[i],"Pasajero"+i);
            t[i].start();
        }
        tTaxista.start();
    }

}
