/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej12;

/**
 *
 * @author joa
 */
public class Main {

    public static void main(String[] args) {
        Jaula jaula = new Jaula();
        Thread[] t = new Thread[4];
        for (int i = 0; i < 3; i++) {
            t[i] = new Thread(new Hamster(jaula), "Hamster" + i);
            t[i].start();            
        }
    }
}
