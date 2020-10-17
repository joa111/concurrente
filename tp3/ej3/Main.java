/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ej3;

/**
 *
 * @author joa
 */
public class Main {

    public static void main(String args[])throws InterruptedException {
        Turno t=new Turno();
        Letra a = new Letra(1,'A',t);
        Letra b = new Letra(2,'B',t);
        Letra c = new Letra(3,'C',t);
        
        Thread t1=new Thread(a);
        Thread t2=new Thread(b);
        Thread t3=new Thread(c);
        
        t1.start();
        t2.start();
        t3.start();

        
    }
}
