/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuñiga_FAI2235;

/**
 *
 * @author joa
 */
public class Gato extends Animal implements Runnable {

    public Gato(Comedor c) {
        super(c);
    }

    public void run() {
        while (true) {
            super.jugar();
            System.out.println(Thread.currentThread().getName() + ": Me dió hambre, voy a intentar comer");
            while (!comedor.entraGato()) {
                super.darUnaVuelta();
            }
            super.comer();
            comedor.saleGato();
        }
    }
}
