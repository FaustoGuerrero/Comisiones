package com.fegc.modelo;

import java.util.Date;

/**
 *
 * @author fausto
 */
public class Ventas {
    private int idVenta;
    private int idEmpleadoVentas;
    private double monto;
    private Date fechaVenta;

    public Ventas() {
    }

    public Ventas(int idVenta, int idEmpleadoVentas, double monto, Date fechaVenta) {
        this.idVenta = idVenta;
        this.idEmpleadoVentas = idEmpleadoVentas;
        this.monto = monto;
        this.fechaVenta = fechaVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdEmpleadoVentas() {
        return idEmpleadoVentas;
    }

    public void setIdEmpleadoVentas(int idEmpleadoVentas) {
        this.idEmpleadoVentas = idEmpleadoVentas;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Ventas{" + "idVenta=" + idVenta + ", idEmpleadoVentas=" + idEmpleadoVentas + ", monto=" + monto + ", fechaVenta=" + fechaVenta + '}';
    }
    
    
}
