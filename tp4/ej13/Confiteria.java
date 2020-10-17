/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej13;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Confiteria {

    private Semaphore semMozo;
    private Semaphore semMesa;
    private Semaphore semMenu;
    private Semaphore semPedido;
    private Semaphore semComer;
    private String[] menu;

    public Confiteria() {
        semMozo = new Semaphore(0);
        semMenu = new Semaphore(0);
        semPedido = new Semaphore(0);
        semComer = new Semaphore(0);
        semMesa = new Semaphore(1);
        menu = new String[]{"un chori de cancha con coca","un cafe con tostados","una porción de pizza con fanta"};
    }
    
    

    //del empleado
    public void ocuparMesa() {
        try {
            semMesa.acquire();
            System.out.println(Thread.currentThread().getName() + ": Buen día!");
            semMozo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recibirMenu() {
        try {
            semMenu.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void elegir(int eleccion) {
        System.out.println(Thread.currentThread().getName() + ": Me gustaría " + menu[eleccion]);
        semMenu.release();
        semPedido.release();
    }

    public void esperarComida() {
        try {
            semComer.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desocuparMesa() {
        System.out.println(Thread.currentThread().getName() + ": Estuvo muy rico! Ahora de vuelta al trabajo");
        semMesa.release();

    }

    
    //del mozo
    public void intentarAtender() {
        //en este método el mozo intenta atender a un empleado (cuando llegue uno, liberará el semMozo y el mozo podrá atenderlo).
        System.out.println(Thread.currentThread().getName() + ": mientras no me necesiten, me dedico a idear nuevas recetas.");
        try {
            semMozo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void entregarMenu() {
        semMenu.release();
    }

    public void realizarPedido() {
        try {
            semPedido.acquire();
            semMenu.acquire();
            System.out.println(Thread.currentThread().getName() + ": Marchando!");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + ": Aquí está su comida, buen provecho");
            semComer.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
