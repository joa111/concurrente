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
public class Perro extends Animal implements Runnable {

    public Perro(Comedor c) {
        super(c);
    }

    public void run() {
        while (true) {
            jugar();
            System.out.println(Thread.currentThread().getName() + ": Me dió hambre, voy a intentar comer");
            while (!super.comedor.entraPerro()) {
                super.darUnaVuelta();
            }
            super.comer();
            super.comedor.salePerro();
        }
    }
}
