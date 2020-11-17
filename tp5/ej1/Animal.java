/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Animal {

    Comedor comedor;

    public Animal(Comedor c) {
        comedor = c;
    }

    public void darUnaVuelta() {
        Random random = new Random();
        try {
            Thread.sleep(1500 * random.nextInt(2));
        } catch (InterruptedException ex) {
            Logger.getLogger(Zuñiga_FAI2235.Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comer() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Zuñiga_FAI2235.Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void jugar() {
        System.out.println(Thread.currentThread().getName() + ": Estoy jugando");
        try {
            Random random = new Random();
            Thread.sleep(3500 * random.nextInt(3));
        } catch (InterruptedException ex) {
            Logger.getLogger(Zuñiga_FAI2235.Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
