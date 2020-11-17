/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoria.filosofos;

/**
 *
 * @author joa
 */
public class Main {

    /*
     * los filósofos pares agarran primero el tenedor de la derecha, los impares
     * primero los de la izquierda (no siempre comen los mismos pares, pero todos
     * comen en algún momento por mas coman 2 veces seguidas)
     */
    public static void main(String[] args) {
        int cantFilosofos = 5;
        Mesa mesa = new Mesa(cantFilosofos);
        Thread[] t = new Thread[cantFilosofos];
        for (int i = 0; i < cantFilosofos; i++) {
            t[i] = new Thread(new Filosofo(mesa, i), "filosofo " + i);
            t[i].start();
        }
    }

}
