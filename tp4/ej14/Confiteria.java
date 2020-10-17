/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.ej14;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author joa
 */
public class Confiteria {

    private Semaphore semMozo;
    private Semaphore semCocinero;
//    private ReentrantLock lockSilla1;
//    private ReentrantLock lockSilla2;
    private ReentrantLock lockOrden;
    private ReentrantLock lockMozo;
    private ReentrantLock lockCocinero;
    private Semaphore semPedido;
    private Semaphore semConsumir;
    private String[] comidas;
    private String[] bebidas;
    private int cantSillas;

    public Confiteria() {
        semMozo = new Semaphore(0);
        semCocinero = new Semaphore(0);
        cantSillas=2;
//        semMenu = new Semaphore(0);
        semPedido = new Semaphore(0);
        semConsumir = new Semaphore(0);
        lockOrden = new ReentrantLock();
//        lockSilla1 = new ReentrantLock();
//        lockSilla2 = new ReentrantLock();
        comidas = new String[]{"un pollo","una ensalada","una burger"};
        bebidas = new String[]{"agua","una coca","un jugo"};
    }
    
    
    //del empleado
public synchronized boolean buscarSilla(){
    boolean seSienta=false;
    if(cantSillas>0){
        seSienta=true;
        cantSillas--;
        //tryacquire de las 2 sillas?
        System.out.println(Thread.currentThread().getName() +": Buen día!");   
    }else{
        System.out.println(Thread.currentThread().getName() +": No hay lugar, en un rato vuelvo");
    }
    return seSienta;
}
    
    public void elegirDelMenu(int eleccion, boolean bebida,boolean ambas){
        if(bebida){
        System.out.println(Thread.currentThread().getName() + ": Me gustaría " + bebidas[eleccion]+". Espero a que me atiendan");
        semMozo.release();  //locks para que mozo/cocinero sea adquirido por el hilo y no lo suelte hasta que termine (con sem release devuelvo 2 permisos)
//        lockMozo.unlock();
        if(ambas){
            lockOrden.lock();
        }
        }else{
        if(ambas){
            lockOrden.lock();
        }
        System.out.println(Thread.currentThread().getName() + ": Me gustaría " + comidas[eleccion]);
        semCocinero.release();
        }
     }
    

    public void esperarPedido() {
        try {
        semConsumir.acquire();
        lockOrden.unlock();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void desocuparSilla() {
        System.out.println(Thread.currentThread().getName() + ": Estuvo muy rico! Ahora de vuelta al trabajo");
        //semMesa.release();
        cantSillas++;
    }

    
    public void bebidaServida(){
        //este método forzará el orden entre la bebida y la comida

    }
    
    
    
    
    //del mozo    
    public String atenderBebida(){
        System.out.println(Thread.currentThread().getName() + ": mientras no me necesiten, me dedico a idear nuevas recetas");
        try {
            semMozo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Thread.currentThread().getName();
    }
    
     
    
    
    
    //del cocinero
    public String atenderComida(){
                try {
                    System.out.println(Thread.currentThread().getName() +": Mientras no me necesiten, estaré ordenando la cocina");
            semCocinero.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Thread.currentThread().getName();
    }


    public void pedidoListo(){
        semConsumir.release();
    }
}