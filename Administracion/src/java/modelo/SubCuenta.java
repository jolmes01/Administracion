/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author JL
 */
public class SubCuenta {

    private int idSubCuenta;
    private String descripcion;
    private double saldoSub;

    public SubCuenta() {
    }

    public SubCuenta(int idSubCuenta, String descripcion, double saldoSub) {
        this.idSubCuenta = idSubCuenta;
        this.descripcion = descripcion;
        this.saldoSub = saldoSub;
    }

    public int getIdSubCuenta() {
        return idSubCuenta;
    }

    public void setIdSubCuenta(int idSubCuenta) {
        this.idSubCuenta = idSubCuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSaldoSub() {
        return saldoSub;
    }

    public void setSaldoSub(double saldoSub) {
        this.saldoSub = saldoSub;
    }
}
