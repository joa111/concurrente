/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp0.ej2;

import java.util.Date;
import java.text.SimpleDateFormat;
import tp1.ej9.ExcepcionDias;

/**
 *
 * @author J0A
 */
public class Alquiler {

    private Cliente cliente;
    private Date fechaInicial;
    private Date fechaFinal;
    private int amarre;
    private Barco barco;
    private int valorFijo;

    public Alquiler(Cliente c, String fi, String ff, int posAmarre, Barco b) throws Exception, ExcepcionDias {
        //para la fecha, se ingresa como String y se pasa a Date
        fechaInicial = new SimpleDateFormat("dd/MM/yyyy").parse(fi);
        fechaFinal = new SimpleDateFormat("dd/MM/yyyy").parse(ff);
        try{
        Date diferenciaDias = new Date(fechaFinal.getTime() - fechaInicial.getTime());
        int dias = DateToDays(diferenciaDias);
        if (dias>5) {
            throw new ExcepcionDias();
        }
        }catch(ExcepcionDias e){
            System.err.println(e);
        }
        cliente = c;
        amarre = posAmarre;
        barco = b;
        valorFijo = 200;
        
    }

    public void actualizarValor(int i) {
        valorFijo = i;
    }

    public int calcularAlquiler() {
        //creo una Date 
        Date diferenciaDias = new Date(fechaFinal.getTime() - fechaInicial.getTime());
        int dias = DateToDays(diferenciaDias);
        return (10 * this.barco.getEslora() + valorFijo + this.barco.getModulo() + dias);
    }

    public int DateToDays(Date fecha) {
        //milisec*sec*min*hours=1000*60*60*24=86400000
        long dias = fecha.getTime();
        dias = dias / 86400000;
        return (int) dias;
    }
}
