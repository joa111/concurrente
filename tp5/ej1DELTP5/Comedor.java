/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5.ej1DELTP5;

import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joa
 */
public class Comedor {

    private Semaphore semPlatos;    
    private Semaphore semEspecies;       //semEspecies: si entra una especie, la/s otras no puedan entrar y queden esperando a obtener este sem
    private HashMap<String, Integer> animalesQueComieron;
    private HashMap<String, Integer> cantidadAnimalesTotal;
   
    private int platosTotal;
    private ReentrantLock lockPlatosTotal;
    
    
    private HashMap<String, Boolean> hay;
    private ReentrantLock lockHay;
    
    
    private Boolean esperando;
    private ReentrantLock lockEsperando;  

    
    public Comedor(int cantidadDeComederos, int perrosTotal, int gatosTotal) {
        platosTotal = cantidadDeComederos;
        lockPlatosTotal= new ReentrantLock(true);
        semPlatos = new Semaphore(cantidadDeComederos, true);
        semEspecies = new Semaphore(1, true);
        animalesQueComieron = new HashMap<>();
        animalesQueComieron.put("perro", 0);
        animalesQueComieron.put("gato", 0);
        hay = new HashMap<>();
        hay.put("perro", false);
        hay.put("gato", false);
        lockHay= new ReentrantLock(true);
        cantidadAnimalesTotal = new HashMap<>();
        cantidadAnimalesTotal.put("perro", perrosTotal);
        cantidadAnimalesTotal.put("gato", perrosTotal);
        esperando = false;
        lockEsperando= new ReentrantLock(true);
    }

    public boolean permisoDeEntrada(String especie) {
        boolean permiso = true;
        if (noHayNadie()) {
            try {
                semEspecies.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            lockHay.lock();
            try {
                hay.put(especie, true);
            } finally {
                lockHay.unlock();
            }
        } else {
            //si no hay alguno de su especie adentro, adquiero el semEspecies para esperar a que salga la otra especie
            if (!hayDeSuEspecie(especie)) {
                    esperando = true;              
                lockEsperando.lock();
                try {
                    semEspecies.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    lockEsperando.unlock();
                }
            }
        }
        if(!comieronTodos()){
        if (tieneQueEsperar(especie)) {
            System.out.println("+++++++++++++++++++++++++++"+ Thread.currentThread().getName()  +" no puede entrar porque hay que dejarle entrar a la otra especie");
            permiso=false;
            semEspecies.release();
            }
            
        }       
        return permiso;
    }


    private boolean noHayNadie() {
        lockPlatosTotal.lock();
        try {
            return (semPlatos.availablePermits() == platosTotal);
        } finally {
            lockPlatosTotal.unlock();
        }
    }

    private boolean hayDeSuEspecie(String especie) {
        lockHay.lock();
        try {
            return (hay.get(especie));
        } finally {
            lockHay.unlock();
        }
    }

    private synchronized boolean tieneQueEsperar(String especie) {
        return (animalesQueComieron.get(especie) >= cantidadAnimalesTotal.get(especie) && esperando);
    }
    
    private synchronized boolean comieronTodos() {
        return (animalesQueComieron.get("perro") == cantidadAnimalesTotal.get("perro") && animalesQueComieron.get("gato") == cantidadAnimalesTotal.get("gato"));
    }

    public void entrar(String especie) {
        try {
            //aca espero a poder entrar
            semPlatos.acquire();
            System.out.println("--------------------- " + Thread.currentThread().getName() + ": Pude entrar! ahora a comer");
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized (this) {
            animalesQueComieron.put(especie, animalesQueComieron.get(especie) + 1);
        }
    }

    public boolean salir(String especie) {
        boolean esUltimo = false;
        System.out.println("--------------------- " + Thread.currentThread().getName() + ": Ya comí, me voy a hacer otra cosa");
        semPlatos.release();
        if (noHayNadie()) {
            esUltimo = true;
            lockPlatosTotal.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ": Soy el último en salir!\n\n *no queda nadie*\n");
            } finally {
                lockPlatosTotal.unlock();
            }
            semEspecies.release();
            lockHay.lock();
            try {
                hay.put(especie, false);
            } finally {
                lockHay.unlock();
            }     
        }
        return esUltimo;
    }

}
