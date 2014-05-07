/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author JL
 */
public class Cuenta {

    private int idCuenta;
    private String descripcionCuenta;
    private double saldoTotal;
    private ArrayList<SubCuenta> subcuentas;

    public Cuenta() {
        this.idCuenta = 0;
        this.descripcionCuenta = "";
        this.saldoTotal = 0.0;
        this.subcuentas = new ArrayList<SubCuenta>();
    }

    public Cuenta(String descripcionCuenta, double saldoTotal, ArrayList<SubCuenta> subcuentas) {
        this.idCuenta = 0;
        this.descripcionCuenta = descripcionCuenta;
        this.saldoTotal = saldoTotal;
        this.subcuentas = subcuentas;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getDescripcionCuenta() {
        return descripcionCuenta;
    }

    public void setDescripcionCuenta(String descripcionCuenta) {
        this.descripcionCuenta = descripcionCuenta;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public void setSubCuenta(int idSubcuenta, String descripcion, double saldo) {
        SubCuenta sub = new SubCuenta(idSubcuenta, descripcion, saldo);
        subcuentas.add(sub);
    }

    public SubCuenta getSubCuenta(int idSubCuenta) {
        SubCuenta s = new SubCuenta();
        for (SubCuenta sub : subcuentas) {
            if (sub.getIdSubCuenta() == idSubCuenta) {
                s = sub;
                break;
            }
        }
        return s;
    }

    public void removeSubCuenta(SubCuenta sub) {
        subcuentas.remove(sub);
    }

    public void setSubCuenta(SubCuenta sub) {
        subcuentas.add(sub);
    }

    public ArrayList<SubCuenta> getSubcuentas() {
        return subcuentas;
    }
}
