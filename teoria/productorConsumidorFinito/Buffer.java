/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoria.productorConsumidorFinito;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Buffer {

    private Semaphore semBuffer;
    private Semaphore semProductor;

    public Buffer(int tamaño) {
        /* el semProductor para este ejemplo es inicializado en 5, dando a entender que el buffer tiene una capacidad 
        total de 5 y, si un productor quiere entregar y ya hay 5, debe esperar hasta que haya espacio*/
        semBuffer = new Semaphore(0);
        semProductor = new Semaphore(tamaño);
    }

    public void almacenar() {
        try {
            semBuffer.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        semBuffer.release();
    }

    public void retirar() {
        try {
            semBuffer.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
        semProductor.release();
    }
}
