/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej1DELTP5;



/**
 *
 * @author joa
 */
public class Main {

    public static void main(String[] args) {
        /* 6 perros y 4 gatos, 2 comederos. los animales juegan y les da hambre, por lo que intentan entrar a comer
        pero solo pueden entrar de la misma especie a la vez, y si una especie ya entró la misma cantidad de veces
        que cantidad total de animales de esa especie, dejan entrar a la otra especie.
        */
        int cantidadPerros = 6;
        int cantidadGatos = 4;
        int cantidadComederos=2;

        Comedor comedor = new Comedor(cantidadComederos, cantidadPerros, cantidadGatos);
        Thread[] t = new Thread[cantidadPerros];
        for (int i = 0; i < cantidadPerros; i++) {
            t[i] = new Thread(new Perro(comedor), "Perro " + i);
            t[i].start();
        }
        Thread[] h = new Thread[cantidadGatos];
        for (int i = 0; i < cantidadGatos; i++) {
            h[i] = new Thread(new Gato(comedor), "Gato " + i);
            h[i].start();
        }
    }
}
