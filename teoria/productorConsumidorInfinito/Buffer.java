/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoria.productorConsumidorInfinito;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Buffer {

    private Semaphore semBuffer;

    public Buffer() {
        semBuffer = new Semaphore(0);
    }

    public void almacenar() {
        semBuffer.release();
    }

    public void retirar() {
        try {
            semBuffer.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
