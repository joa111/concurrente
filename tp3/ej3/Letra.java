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
public class Letra implements Runnable {

    private int numeroDeTurno;
    private char letra;
    private Turno turno;

    public Letra(int i, char l, Turno t) {
        numeroDeTurno = i;
        letra = l;
        turno = t;
    }

    public void imprimir() {
        for (int i = 1; i <= numeroDeTurno; i++) {
            System.out.print(letra);
        }
        System.out.println("");
        int temp = numeroDeTurno + 1;
        turno.setTurno(temp);

    }

    public void run() {
        //puse este while que vi de un compañero ya que de lo contrario no muestra toda la cadena como debería
        while (true) {
            if (numeroDeTurno == turno.getTurno()) {
                this.imprimir();
            }
        }
    }

}
