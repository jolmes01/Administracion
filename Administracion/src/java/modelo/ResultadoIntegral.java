/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JL
 */
public class ResultadoIntegral {

    private ArrayList<Double> saldos = new ArrayList<Double>();
    private final int tamanioDescriptores = 19;
    private double ventas = 0.0;
    private double costo = 0.0;
    private double utilidad = 0.0;
    private double gastosV = 0.0;
    private double gastosA = 0.0;
    private double gastosI = 0.0;
    private double otrosIngresos = 0.0;
    private double otrosGastos = 0.0;
    private double EBIT = 0.0;
    private double interesesDev = 0.0;
    private double fluctuacion = 0.0;
    private double cambiosValor = 0.0;
    private double antesImpuestos = 0.0;
    private double impuestosUtilidad = 0.0;
    private double operacionesCon = 0.0;
    private double operacionesDis = 0.0;
    private double utilneta = 0.0;
    private double ORI = 0.0;
    private double resultadoIntegral = 0.0;

    private ArrayList<Double> valores = new ArrayList<Double>();
    private ArrayList<String> descripcion = new ArrayList<String>();

    public ResultadoIntegral() {
    }

    public void calculaResultado(int idEmpresa) throws SQLException {
        Connection con = new AccesBD().conexion();
        PreparedStatement ps = con.prepareStatement("SELECT descripcion,saldo FROM cuentas WHERE idEmpresaC LIKE ? AND idCuentaC > 5000 AND idSubCuenta LIKE 0 ORDER BY idCuentaC");
        ps.setInt(1, idEmpresa);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            descripcion.add(rs.getString("descripcion"));
            valores.add(rs.getDouble("saldo"));
        }
        con.close();
        if (!descripcion.isEmpty()) {
            this.ventas = this.valores.get(descripcion.indexOf("Costo de ventas"));
            saldos.add(this.ventas);
            this.costo = this.valores.get(descripcion.indexOf("Ventas"));
            saldos.add(this.costo);
            this.utilidad = this.ventas - this.costo;
            saldos.add(this.utilidad);
            this.gastosV = this.valores.get(descripcion.indexOf("Gastos de venta"));;
            saldos.add(this.gastosV);
            this.gastosA = this.valores.get(descripcion.indexOf("Gastos de Administración"));;
            saldos.add(this.gastosA);
            this.gastosI = this.valores.get(descripcion.indexOf("Gastos por intereses"));;
            saldos.add(this.gastosI);
            this.otrosIngresos = this.valores.get(descripcion.indexOf("Otros ingresos"));;
            saldos.add(this.otrosIngresos);
            this.otrosGastos = this.valores.get(descripcion.indexOf("Otros gastos"));;
            saldos.add(this.otrosGastos);
            this.EBIT = this.utilidad + this.otrosIngresos - this.otrosGastos - this.gastosV - this.gastosA - this.gastosI;
            saldos.add(this.EBIT);
            this.interesesDev = this.valores.get(descripcion.indexOf("Intereses cobrados"));
            saldos.add(this.interesesDev);
            this.fluctuacion = 0.0;
            saldos.add(this.fluctuacion);
            this.cambiosValor = 0.0;
            saldos.add(this.cambiosValor);
            this.antesImpuestos = this.EBIT - this.interesesDev;
            saldos.add(this.antesImpuestos);
            this.impuestosUtilidad = (this.utilidad < 0) && (this.EBIT < 0) ? this.antesImpuestos * .3 : 0.0;
            saldos.add(this.impuestosUtilidad);
            this.operacionesCon = this.antesImpuestos - this.impuestosUtilidad;
            saldos.add(this.operacionesCon);
            this.operacionesDis = 0.0;
            saldos.add(this.operacionesDis);
            this.utilneta = this.operacionesCon - this.operacionesDis;
            saldos.add(this.utilneta);
            this.ORI = 0.0;
            saldos.add(this.ORI);
            this.resultadoIntegral = this.utilneta - this.ORI;
            saldos.add(this.resultadoIntegral);
        }else{
            saldos.add(this.ventas);
            saldos.add(this.costo);
            saldos.add(this.utilidad);
            saldos.add(this.gastosV);
            saldos.add(this.gastosA);
            saldos.add(this.gastosI);
            saldos.add(this.otrosIngresos);
            saldos.add(this.otrosGastos);
            saldos.add(this.EBIT);
            saldos.add(this.interesesDev);
            saldos.add(this.fluctuacion);
            saldos.add(this.cambiosValor);
            saldos.add(this.antesImpuestos);
            saldos.add(this.impuestosUtilidad);
            saldos.add(this.operacionesCon);
            saldos.add(this.operacionesDis);
            saldos.add(this.utilneta);
            saldos.add(this.ORI);
            saldos.add(this.resultadoIntegral);
        }
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(double utilidad) {
        this.utilidad = utilidad;
    }

    public double getGastosV() {
        return gastosV;
    }

    public void setGastosV(double gastosV) {
        this.gastosV = gastosV;
    }

    public double getGastosA() {
        return gastosA;
    }

    public void setGastosA(double gastosA) {
        this.gastosA = gastosA;
    }

    public double getGastosI() {
        return gastosI;
    }

    public void setGastosI(double gastosI) {
        this.gastosI = gastosI;
    }

    public double getOtrosIngresos() {
        return otrosIngresos;
    }

    public void setOtrosIngresos(double otrosIngresos) {
        this.otrosIngresos = otrosIngresos;
    }

    public double getOtrosGastos() {
        return otrosGastos;
    }

    public void setOtrosGastos(double otrosGastos) {
        this.otrosGastos = otrosGastos;
    }

    public double getEBIT() {
        return EBIT;
    }

    public void setEBIT(double EBIT) {
        this.EBIT = EBIT;
    }

    public double getInteresesDev() {
        return interesesDev;
    }

    public void setInteresesDev(double interesesDev) {
        this.interesesDev = interesesDev;
    }

    public double getFluctuacion() {
        return fluctuacion;
    }

    public void setFluctuacion(double fluctuacion) {
        this.fluctuacion = fluctuacion;
    }

    public double getCambiosValor() {
        return cambiosValor;
    }

    public void setCambiosValor(double cambiosValor) {
        this.cambiosValor = cambiosValor;
    }

    public double getAntesImpuestos() {
        return antesImpuestos;
    }

    public void setAntesImpuestos(double antesImpuestos) {
        this.antesImpuestos = antesImpuestos;
    }

    public double getImpuestosUtilidad() {
        return impuestosUtilidad;
    }

    public void setImpuestosUtilidad(double impuestosUtilidad) {
        this.impuestosUtilidad = impuestosUtilidad;
    }

    public double getOperacionesCon() {
        return operacionesCon;
    }

    public void setOperacionesCon(double operacionesCon) {
        this.operacionesCon = operacionesCon;
    }

    public double getOperacionesDis() {
        return operacionesDis;
    }

    public void setOperacionesDis(double operacionesDis) {
        this.operacionesDis = operacionesDis;
    }

    public double getUtilneta() {
        return utilneta;
    }

    public void setUtilneta(double utilneta) {
        this.utilneta = utilneta;
    }

    public double getORI() {
        return ORI;
    }

    public void setORI(double ORI) {
        this.ORI = ORI;
    }

    public double getResultadoIntegral() {
        return resultadoIntegral;
    }

    public void setResultadoIntegral(double resultadoIntegral) {
        this.resultadoIntegral = resultadoIntegral;
    }

    public ArrayList<Double> getValores() {
        return valores;
    }

    public void setValores(ArrayList<Double> valores) {
        this.valores = valores;
    }

    public ArrayList<String> getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(ArrayList<String> descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Double> getSaldos() {
        return saldos;
    }

    public void setSaldos(ArrayList<Double> saldos) {
        this.saldos = saldos;
    }

}
