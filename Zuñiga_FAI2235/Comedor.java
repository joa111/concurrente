/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuñiga_FAI2235;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Comedor {

    private boolean hayPerros;
    private boolean hayGatos;
    private int perrosQueComieron;
    private int gatosQueComieron;
    private int comederosDisponibles;

    public Comedor() {
        comederosDisponibles = 2;
        hayPerros = false;
        hayGatos = false;
        perrosQueComieron = 0;
        gatosQueComieron = 0;
    }

    public synchronized boolean entraPerro() {
        //si entran mas de 3 perros seguidos, se le da el paso a los gatos
        boolean entro = false;
        if (!hayGatos && perrosQueComieron < 3 && comederosDisponibles > 0) {
            System.out.println("--------------------- " + Thread.currentThread().getName() + ": Pude entrar! estoy en el comedero " + comederosDisponibles);
            entro = true;
            hayPerros = true;
            comederosDisponibles--;
            perrosQueComieron++;
            gatosQueComieron = 0;
        } else {
            System.out.println(Thread.currentThread().getName() + ": No puedo entrar. Pruebo más rato");

        }
        return entro;
    }

    public synchronized boolean entraGato() {
        //si entran mas de 3 gatos seguidos, se le da el paso a los perros
        boolean entro = false;
        if (!hayPerros && gatosQueComieron < 3 && comederosDisponibles > 0) {
            System.out.println("--------------------- " + Thread.currentThread().getName() + ": Pude entrar! estoy en el comedero " + comederosDisponibles);
            entro = true;
            hayGatos = true;
            comederosDisponibles--;
            gatosQueComieron++;
            perrosQueComieron = 0;
        } else {
            System.out.println(Thread.currentThread().getName() + ": No puedo entrar. Pruebo más rato");

        }
        return entro;
    }

    public synchronized void salePerro() {
        comederosDisponibles++;
        System.out.println("--------------------- " + Thread.currentThread().getName() + ": Ya comí, me voy a hacer otra cosa");
        if (comederosDisponibles == 2) {    //si el perro en irse es el último, deja la posibilidad de que entren gatos (podrian entrar perros de nuevo)
            System.out.println(Thread.currentThread().getName() + ": Soy el ultimo perro en irme!!!!\n");
            hayPerros = false;
        }
    }

    public synchronized void saleGato() {
        comederosDisponibles++;
        System.out.println("--------------------- " + Thread.currentThread().getName() + ": Ya comí, me voy a hacer otra cosa");
        if (comederosDisponibles == 2) {    //si el gato en irse es el último, deja la posibilidad de que entren perros (podrían entrar gatos de nuevo)
            System.out.println(Thread.currentThread().getName() + ": Soy el último gato en irme!!!!\n");
            hayGatos = false;
        }
    }

}
