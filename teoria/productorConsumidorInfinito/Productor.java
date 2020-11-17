/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoria.productorConsumidorInfinito;

/**
 *
 * @author joa
 */
public class Productor implements Runnable {

    private Buffer buffer;

    public Productor(Buffer b) {
        buffer = b;
    }

    private void entregar() {
        buffer.almacenar();
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " quiere entregar");
        entregar();
        System.out.println(Thread.currentThread().getName() + " entregó correctamente");
    }
}
