/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej14;

/**
 *
 * @author joa
 */
public class Main {
    
    public static void main(String []args){
        Confiteria confiteria = new Confiteria();
        Thread mozo=new Thread(new Mozo(confiteria),"Mozo");
        Thread cocinero=new Thread(new Cocinero(confiteria),"Cocinero");
        Thread[] t = new Thread[5];
        mozo.start();
        cocinero.start();
        for (int i = 0; i < 5; i++) {
            t[i] = new Thread(new Empleado(confiteria), "Empleado" + i);
            t[i].start();
        }
    }
}
