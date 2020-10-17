/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej13;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Empleado implements Runnable {

    Confiteria confiteria;

    public Empleado(Confiteria c) {
        confiteria = c;
    }

    private void comer() {
        try {
            System.out.println(Thread.currentThread().getName() +": Gracias! Ahora a comer");
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int eleccion() {
        int eleccion;
        Random random = new Random(); //creo un aleatorio para elegir del menú
        System.out.println(Thread.currentThread().getName()+": A ver que puedo elegir...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        eleccion=random.nextInt(3);
        return eleccion;
    }

    public void run() {
        confiteria.ocuparMesa();
        confiteria.recibirMenu();
        confiteria.elegir(eleccion());
        confiteria.esperarComida();
        comer();
        confiteria.desocuparMesa();
    }

}
