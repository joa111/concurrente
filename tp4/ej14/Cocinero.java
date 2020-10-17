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
public class Cocinero implements Runnable {

    Confiteria confiteria;

    public Cocinero(Confiteria c) {
        confiteria = c;
    }

    private void cocinar() {
        try {
            System.out.println("*" + Thread.currentThread().getName() + " cocina*");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + ": Aquí está su plato");
        } catch (InterruptedException ex) {
            Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run() {
        while (true) {
            confiteria.atenderComida();
            cocinar();
            confiteria.pedidoListo();
        }
    }
}
