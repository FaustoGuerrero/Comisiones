package com.fegc.modelo;

/**
 *
 * @author fausto
 */
public class DatosEmpleado {
    private int id;
    private int nivel;
    private double monto;

    public DatosEmpleado(int id, int nivel, double monto) {
        this.id = id;
        this.nivel = nivel;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "DatosEmpleado{" + "id=" + id + ", nivel=" + nivel + ", monto=" + monto + '}';
    }
    
    
}
