/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ej4;

/**
 *
 * @author joa
 */
public class Vehiculo {
    
    private String patente;
    private String color;
    private int kmAutonomia;
    private int kmActual;
    private String marca;
       
    public Vehiculo(String pat,String col,String mark,int kmAut){
        patente=pat;
        marca=mark;
        color=col;
        kmActual=0;
        kmAutonomia=kmAut;
    }   
    
    public int getKmAutonomia(){
        return kmAutonomia;
    }
    
    
}
