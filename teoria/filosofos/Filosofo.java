/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoria.filosofos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Filosofo implements Runnable {

    private Mesa mesa;
    private int numero; // cada filósofo se identifica con un número

    public Filosofo(Mesa m, int n) {
        mesa = m;
        numero = n;
    }

    private void comer() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            mesa.esperarTenedores(numero);
            System.out.println(Thread.currentThread().getName() + ": voy a comer");
            comer();
            System.out.println(Thread.currentThread().getName() + ": terminé de comer");
            mesa.terminarDeComer(numero);
        }
    }
}
