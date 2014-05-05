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
public class Poliza {

    int poliza;
    ArrayList<Integer> idCuenta;
    ArrayList<String> fecha;
    ArrayList<Integer> cargo;
    ArrayList<Integer> abono;

    public Poliza() {
    }

    public Poliza(int poliza, ArrayList<Integer> idCuenta, ArrayList<String> fecha, ArrayList<Integer> cargo, ArrayList<Integer> abono) {
        this.poliza = poliza;
        this.idCuenta = idCuenta;
        this.fecha = fecha;
        this.cargo = cargo;
        this.abono = abono;
    }

    public void addidCuenta(int id) {
        idCuenta.add(id);
    }

    public void addFecha(String fecha) {
        this.fecha.add(fecha);
    }

    public void addCargo(int cargo) {
        this.cargo.add(cargo);
    }

    public void addAbono(int abono) {
        this.abono.add(abono);
    }

    public int getPoliza() {
        return poliza;
    }

    public void setPoliza(int poliza) {
        this.poliza = poliza;
    }

    public ArrayList<Integer> getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(ArrayList<Integer> idCuenta) {
        this.idCuenta = idCuenta;
    }

    public ArrayList<String> getFecha() {
        return fecha;
    }

    public void setFecha(ArrayList<String> fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Integer> getCargo() {
        return cargo;
    }

    public void setCargo(ArrayList<Integer> cargo) {
        this.cargo = cargo;
    }

    public ArrayList<Integer> getAbono() {
        return abono;
    }

    public void setAbono(ArrayList<Integer> abono) {
        this.abono = abono;
    }

}
