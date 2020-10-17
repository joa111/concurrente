/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej14;

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

    private void trabajar() {
        System.out.println(Thread.currentThread().getName() + ": Estoy trabajando");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vuelvoEnUnRato() {
        try {
            Random random=new Random();
            Thread.sleep(1000*random.nextInt(6));
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comerBeberOAmbos() {
        boolean bebida = true;
        boolean ambas = false;
        Random random = new Random();
        int eleccion = random.nextInt(3);
        System.out.println(Thread.currentThread().getName() + ": A ver que puedo elegir...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        eleccion=2;
        switch (eleccion) {
            case 0:
                System.out.println(Thread.currentThread().getName() + ": voy a pedir una bebida");
                eleccion = random.nextInt(3);
                confiteria.elegirDelMenu(eleccion, bebida,ambas);
                break;
            case 1:
                System.out.println(Thread.currentThread().getName() + ": voy a pedir un plato");
                bebida = false;
                eleccion = random.nextInt(3);
                confiteria.elegirDelMenu(eleccion, bebida,ambas);
                break;
            case 2:
                System.out.println(Thread.currentThread().getName() + ": voy a pedir para comer y beber");
                ambas=true;
                eleccion = random.nextInt(3);
                confiteria.elegirDelMenu(eleccion, bebida,ambas);
                bebida = false;
                eleccion = random.nextInt(3);
                confiteria.elegirDelMenu(eleccion, bebida,ambas);
                break;
            default:
                System.err.println("Opción inválida");
                break;
        }
    }

    private void consumir() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Gracias!");
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            trabajar();
            while (!confiteria.buscarSilla()) {   //este while podria ser con un tryUnlock
                vuelvoEnUnRato();
            }
            comerBeberOAmbos();
            confiteria.esperarPedido();
            consumir();
            confiteria.desocuparSilla();
        }
    }

}
