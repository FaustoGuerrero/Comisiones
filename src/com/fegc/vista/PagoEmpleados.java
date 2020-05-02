package com.fegc.vista;

import com.fegc.modelo.Metodos;

/**
 *
 * @author fausto
 */
public class PagoEmpleados {

    public static void main(String[] args) {
            Metodos metodos = new Metodos();
            metodos.asignarComisionVentaPropia(9, 1);
            metodos.asignarBonoLiderazgoMensual();
            metodos.asignarBonoLiderazgoTrimestral();
    }
}
