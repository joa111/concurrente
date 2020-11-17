/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author joa
 */
public class Comedor {

    private Semaphore semPlatos;
    private int platosTotal;
    private int perrosQueComieron;
    private int gatosQueComieron;
    private int cantidadPerrosTotal;
    private int cantidadGatosTotal;
    private boolean hayPerros;
    private boolean hayGatos;

    public Comedor(int cantidadDeComederos, int perrosTotal, int gatosTotal) {
        platosTotal = cantidadDeComederos;
        semPlatos = new Semaphore(cantidadDeComederos);
        hayPerros = false;
        hayGatos = false;
        gatosQueComieron = 0;
        perrosQueComieron = 0;
        cantidadPerrosTotal = perrosTotal;
        cantidadGatosTotal = gatosTotal;
    }

    public boolean entraPerro() {
        boolean entro = false;
        if (perrosQueComieron == cantidadPerrosTotal && gatosQueComieron == cantidadGatosTotal) {
            perrosQueComieron = 0;
            gatosQueComieron = 0;
        }
        if (!hayGatos && perrosQueComieron < cantidadPerrosTotal && semPlatos.tryAcquire()) {
            System.out.println("--------------------- " + Thread.currentThread().getName() + ": Pude entrar! ahora a comer");
            entro = true;
            hayPerros = true;
            perrosQueComieron++;
        }
        return entro;
    }

    public boolean salePerro() {
        boolean esUltimo = false;
        semPlatos.release();
        if (semPlatos.availablePermits() == platosTotal) {
            hayPerros = false;
            esUltimo = true;
        }
        return esUltimo;
    }

    public boolean entraGato() {
        boolean entro = false;
        if (perrosQueComieron == cantidadPerrosTotal && gatosQueComieron == cantidadGatosTotal) {
            perrosQueComieron = 0;
            gatosQueComieron = 0;
        }
        if (!hayPerros && gatosQueComieron < cantidadGatosTotal && semPlatos.tryAcquire()) {
            System.out.println("--------------------- " + Thread.currentThread().getName() + ": Pude entrar! ahora a comer");
            entro = true;
            hayGatos = true;
            gatosQueComieron++;
        }
        return entro;
    }

    public boolean saleGato() {
        boolean esUltimo = false;
        semPlatos.release();
        if (semPlatos.availablePermits() == platosTotal) {
            hayGatos = false;
            esUltimo = true;
        }
        return esUltimo;
    }

}
