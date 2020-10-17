/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej13;

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

    private void atender() {
        System.out.println(Thread.currentThread().getName() + ": Buen día! aquí está el menú");
        confiteria.entregarMenu();
    }

    public void run() {
        while (true) {
            confiteria.intentarAtender();
            atender();
            confiteria.realizarPedido();
        }
    }
}
