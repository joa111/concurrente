/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoria.filosofos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Mesa {

    private Semaphore semTenedores[];
    private int contFilosofosIniciales;
    private int cantTenedores;
    private boolean primeros;

    public Mesa(int cantTenedores) {
        contFilosofosIniciales = 0;
        primeros = true;
        this.cantTenedores = cantTenedores;
        semTenedores = new Semaphore[] { new Semaphore(1, true), new Semaphore(1, true), new Semaphore(1, true),
                new Semaphore(1, true), new Semaphore(1, true) };
    }

    public void esperarTenedores(int t) {
        try {
            if (t % 2 == 1) {
                semTenedores[t].acquire();
                semTenedores[(t + 1) % cantTenedores].acquire();
            } else {
                semTenedores[(t + 1) % cantTenedores].acquire();
                semTenedores[t].acquire();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarDeComer(int t) {
        semTenedores[t].release();
        semTenedores[(t + 1) % cantTenedores].release();
    }

}