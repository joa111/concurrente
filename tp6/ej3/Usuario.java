/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp6.ej3;

import java.util.Random;

/**
 *
 * @author joa
 */
public class Usuario implements Runnable {

    private CentroImpresion centroImpresion;

    public Usuario(CentroImpresion c) {
        centroImpresion = c;
    }

    public void run() {
        Random r = new Random();
        int tipo;
        while (true) {
            tipo = r.nextInt(3);    //tipo 1: A, tipo 1: B, tipo 2: cualquiera
            centroImpresion.solicitarImpresion();
        }
    }
}
