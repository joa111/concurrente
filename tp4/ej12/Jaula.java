/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej12;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Jaula {

    private ReentrantLock lockPlato;
    private ReentrantLock lockRueda;
    private ReentrantLock lockHamaca;

    public Jaula() {
        lockPlato = new ReentrantLock();
        lockRueda = new ReentrantLock();
        lockHamaca = new ReentrantLock();
    }

    public void comer(){
        lockPlato.lock();
        System.out.println(Thread.currentThread().getName() + " está comiendo\n");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println(Thread.currentThread().getName() + " se llenó\n");
            lockPlato.unlock();
        }
    }
    
    public void correrEnRueda() {
        lockRueda.lock();
        System.out.println(Thread.currentThread().getName() + " está corriendo en la rueda\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println(Thread.currentThread().getName() + " se cansó de correr y se baja\n");
            lockRueda.unlock();
        }
    }
    
    public void descansarEnHamaca() {
        lockRueda.lock();
        System.out.println(Thread.currentThread().getName() + " se fue a dormir\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println(Thread.currentThread().getName() + " se levantó\n");
            lockRueda.unlock();
        }
    }
    

}
