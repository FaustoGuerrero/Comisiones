package com.fegc.modelo;

/**
 *
 * @author fausto
 */
public class TarifasComision {
    private int idComision;
    private double porcentajeComision;
    private int nivelComision;

    public TarifasComision() {
    }

    public TarifasComision(int idComision, double porcentajeComision, int nivelComision) {
        this.idComision = idComision;
        this.porcentajeComision = porcentajeComision;
        this.nivelComision = nivelComision;
    }

    public int getNivelComision() {
        return nivelComision;
    }

    public void setNivelComision(int nivelComision) {
        this.nivelComision = nivelComision;
    }

    public int getIdComision() {
        return idComision;
    }

    public void setIdComision(int idComision) {
        this.idComision = idComision;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public String toString() {
        return "TarifasComision{" + "idComision=" + idComision + ", porcentajeComision=" + porcentajeComision + ", nivelComision=" + nivelComision + '}';
    }
    
    
    
}
