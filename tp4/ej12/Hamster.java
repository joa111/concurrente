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
public class Hamster implements Runnable{
    
    private Jaula jaula;
    
    public Hamster(Jaula j){
        jaula=j;
    }
    
    
    public void run(){
        while(true){
            jaula.comer();
            jaula.correrEnRueda();
            jaula.descansarEnHamaca();
        }
    }
}
