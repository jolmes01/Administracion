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
    ArrayList<Integer> idCuenta = new ArrayList<Integer>();
    ArrayList<Integer> idSubC = new ArrayList<Integer>();
    ArrayList<String> fecha = new ArrayList<String>();
    ArrayList<Integer> cargo = new ArrayList<Integer>();
    ArrayList<Integer> abono = new ArrayList<Integer>();
    int totAbono;
    int totCargo;

    public Poliza() {
    }

    public Poliza(int poliza, ArrayList<Integer> idCuenta, ArrayList<String> fecha, ArrayList<Integer> cargo, ArrayList<Integer> abono) {
        this.poliza = poliza;
        this.idCuenta = idCuenta;
        this.fecha = fecha;
        this.cargo = cargo;
        this.abono = abono;
    }
    
    public boolean getPartidaDoble(){
        boolean partidaDoble = false;
        if((totAbono-totCargo) == 0)
            partidaDoble = true;
        return partidaDoble;
    }

    public int getTotAbono() {
        return totAbono;
    }

    public void setTotAbono(int totAbono) {
        this.totAbono = totAbono;
    }

    public int getTotCargo() {
        return totCargo;
    }
    
    public void removeData(int index){
        idCuenta.remove(index);
        idSubC.remove(index);
        fecha.remove(index);
        int cargoVal = cargo.get(index);
        cargo.remove(index);
        int abonoVal = abono.get(index);
        abono.remove(index);
        totAbono = totAbono-abonoVal;
        totCargo = totCargo - cargoVal;
    }

    public void setTotCargo(int totCargo) {
        this.totCargo = totCargo;
    }
    
    public void addTotAbono(int abono){
        this.totAbono += abono;
    }
    
    public void addTotCargo(int cargo){
        this.totCargo += cargo;
    }

    public void addidCuenta(int id) {
        idCuenta.add(id);
    }
    
    public void addidSub(int id){
        idSubC.add(id);
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
    
    public ArrayList<Integer> getIdSub() {
        return idSubC;
    }
    
    public void setIdSub(ArrayList<Integer> idSub) {
        this.idSubC = idSub;
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

    public int getIdCuentaByIndex(int i) {
        return idCuenta.get(i);
    }
    
    public String getFechaByIndex(int i){
        return fecha.get(i);
    }
    
    public int getCargoByIndex(int i){
        return cargo.get(i);
    }
    
    public int getAbonoByIndex(int i){
        return abono.get(i);
    }

}
