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
public class Main {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Thread[] t1 = new Thread[5];
        Thread[] t2 = new Thread[5];
        for (int i = 0; i < 5; i++) {
            t1[i] = new Thread(new Productor(buffer), "productor " + i);
            t2[i] = new Thread(new Consumidor(buffer), "consumidor " + i);
            t1[i].start();
            t2[i].start();
        }
    }
}
