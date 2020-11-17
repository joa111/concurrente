/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej1DELTP5;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Animal implements Runnable {

    Comedor comedor;
    String especie;

    public Animal(Comedor c) {
        comedor = c;
    }

    public void comer() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Zuñiga_FAI2235.Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void jugar() {
        try {
            Random random = new Random();
            Thread.sleep(5000 * random.nextInt(2));
        } catch (InterruptedException ex) {
            Logger.getLogger(Zuñiga_FAI2235.Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            jugar();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++"+Thread.currentThread().getName() + ": Me dió hambre, voy a intentar comer");
            if (!comedor.permisoDeEntrada(especie)) {
                comedor.permisoDeEntrada(especie);
            }
            comedor.entrar(especie);
            comer();
            comedor.salir(especie);
        }
    }
    
}
