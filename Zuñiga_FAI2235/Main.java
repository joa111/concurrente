/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuñiga_FAI2235;

/**
 *
 * @author joa
 */
public class Main {

    public static void main(String[] args) {
        /*El comedor tiene 2 comederos y hay 4 gatos y 6 perros. Solo 3 gatos/perros pueden comer de corrido, 
        luego del tercero se deja pasar a la otra especie.
        para agregarle algo de variación, la vuelta de los perros cuando no puedieron entrar es más corta, esto da lugar a
        situaciones como que salen 2 gatos y dejan los comederos vacios, pero el resto de gatos no tiene hambre, 
        por lo que deja a los perros entrar
        
        pasé el comportamiento compartido (como comer, jugar, etc) a una superclase Animal
         */
        Comedor comedor = new Comedor();
        Thread[] t = new Thread[6];
        for (int i = 0; i < 6; i++) {
            t[i] = new Thread(new Perro(comedor), "Perro " + i);
            t[i].start();
        }
        Thread[] h = new Thread[4];
        for (int i = 0; i < 4; i++) {
            h[i] = new Thread(new Gato(comedor), "Gato " + i);
            h[i].start();
        }
    }
}
