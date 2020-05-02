package com.fegc.modelo;

/**
 *
 * @author fausto
 */
public class Jerarquia {
    private int idEmpleado;
    private int idPadreEmpleado;

    public Jerarquia() {
    }

    public Jerarquia(int idEmpleado, int idPadreEmpleado) {
        this.idEmpleado = idEmpleado;
        this.idPadreEmpleado = idPadreEmpleado;
    }

    public int getIdPadreEmpleado() {
        return idPadreEmpleado;
    }

    public void setIdPadreEmpleado(int idPadreEmpleado) {
        this.idPadreEmpleado = idPadreEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Jerarquia{" + "idEmpleado=" + idEmpleado + ", idPadreEmpleado=" + idPadreEmpleado + '}';
    }
    
}
