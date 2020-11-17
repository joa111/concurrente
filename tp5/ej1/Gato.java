/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej1;

/**
 *
 * @author joa
 */
public class Gato extends Animal implements Runnable {

    public Gato(Comedor c) {
        super(c);
    }

    public void run() {
        while (true) {
            jugar();
            System.out.println(Thread.currentThread().getName() + ": Me dió hambre, voy a intentar comer");
            while (!comedor.entraGato()) {
                System.out.println(Thread.currentThread().getName() + ": No puedo entrar, me voy a dar una vuelta");
                darUnaVuelta();
            }
            comer();
            System.out.println("--------------------- " + Thread.currentThread().getName() + ": Ya comí, me voy a hacer otra cosa");
            if (comedor.saleGato()) {
                System.out.println(Thread.currentThread().getName() + ": Soy el último en salir!");
            }
        }
    }
}
