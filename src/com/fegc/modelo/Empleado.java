package com.fegc.modelo;

/**
 *
 * @author fausto
 */
public class Empleado {
    private int idEmpleado;
    private String nombres;

    public Empleado() {
    }

    
    public Empleado(int idEmpleado, String nombres) {
        this.idEmpleado = idEmpleado;
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", nombres=" + nombres + '}';
    }
    
            
}
