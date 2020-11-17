/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoria.productorConsumidorFinito;

/**
 *
 * @author joa
 */
public class Consumidor implements Runnable {

    private Buffer buffer;

    public Consumidor(Buffer b) {
        buffer = b;
    }

    private void buscar() {
        buffer.retirar();
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " quiere retirar");
        buscar();
        System.out.println(Thread.currentThread().getName() + " retira con éxito");
    }

}
