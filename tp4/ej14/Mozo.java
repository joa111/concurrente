/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej14;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Mozo implements Runnable {

    Confiteria confiteria;

    public Mozo(Confiteria c) {
        confiteria = c;
    }

    public void servir() {
        try {
            System.out.println("*" + Thread.currentThread().getName() + " sirve la bebida*");
            Thread.sleep(1500);
            System.out.println(Thread.currentThread().getName() + ": Aquí está su bebida");
        } catch (InterruptedException ex) {
            Logger.getLogger(Mozo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            confiteria.atenderBebida();
            servir();
            confiteria.bebidaServida();
            confiteria.pedidoListo();
        }
    }
}
