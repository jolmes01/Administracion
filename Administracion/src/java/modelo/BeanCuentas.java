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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JL
 * 
 * Bean que se encargará de mantener integra la información de las cuentas propias de la empresa
 * y permitirá la administración de las cuentas
 * 
 */
public class BeanCuentas {
    
    private HashMap<Integer,Cuenta> cuenta = new HashMap<Integer, Cuenta>();

    public HashMap<Integer, Cuenta> getCuenta() {
        return cuenta;
    }

    public void setCuenta(HashMap<Integer, Cuenta> cuenta) {
        this.cuenta = cuenta;
    }
    
    
    public void getCuentas(){
        try {
            cuenta.clear();
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cuentas WHERE idEmpresaC LIKE 1");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                //Obtiene los parametros resultantes de la consulta de la vista
                int id = Integer.parseInt(rs.getString("idCuentaC"));
                String descripcion = rs.getString("descripcion");
                String idSub = rs.getString("idSubCuenta");
                String descripcionSub = rs.getString("descripcionSub");
                String saldo = rs.getString("saldo");
                //Verifica que el id de la cuenta no exista ya en la lista
                if(!cuenta.containsKey(id)){
                    Cuenta c = new Cuenta();
                    c.setIdCuenta(id);
                    c.setDescripcionCuenta(descripcion);
                    //Si el dato obtenido tiene subcuenta lo agrega a la cuenta como una subdivisión de la misma
                    if(idSub != null && descripcionSub != null){
                        SubCuenta sub = new SubCuenta(Integer.parseInt(idSub), descripcionSub, Double.parseDouble(saldo));
                        c.setSubCuenta(sub);
                    }else{ //Si no la presenta guarda el valor total del saldo en saldo total
                        c.setSaldoTotal(Double.valueOf(saldo));
                    }
                    cuenta.put(id, c);
                }else{ //Si contiene el id la lista entonces solo se encargara de actualizar los valores
                    Cuenta c = cuenta.get(id);
                    cuenta.remove(id);
                    //Si el dato obtenido tiene subcuenta lo agrega a la cuenta como una subdivisión de la misma
                    if(idSub != null && descripcionSub != null){
                        SubCuenta sub = new SubCuenta(Integer.parseInt(idSub), descripcionSub, Double.parseDouble(saldo));
                        c.setSubCuenta(sub);
                    }else{ //Si no la presenta guarda el valor total del saldo en saldo total
                        c.setSaldoTotal(Double.valueOf(saldo));
                    }
                    cuenta.put(id, c);
                }
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
<<<<<<< HEAD

    public boolean addCuenta(int idCuenta, double saldo) {
        boolean resultado = false;
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cuenta_empresa "
                    + "VALUES(1," + idCuenta + ",null,null," + saldo + ")");
            boolean result = ps.execute();
            if (!result) {
                ps.execute("SELECT descripcion FROM cuenta WHERE idcuenta = " + idCuenta);
                ResultSet rs = ps.getResultSet();
                if (rs.next()) {
                    Cuenta c = new Cuenta();
                    c.setDescripcionCuenta(rs.getString("descripcion"));
                    c.setIdCuenta(idCuenta);
                    c.setSaldoTotal(saldo);
                    cuenta.put(idCuenta, c);
                    resultado = true;
                }
                con.close();
            }
        } catch (SQLException ex) {
            resultado = false;
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public boolean addCuenta(int idCuenta, int idSubD, String descripcion, double saldo) {
        boolean resultado = false;
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cuenta_empresa "
                    + "VALUES(1," + idCuenta + "," + idSubD + ",'" + descripcion + "'," + saldo + ")");
            boolean result = ps.execute();
            con.close();
            if (!result) {
                updateDinero(idCuenta, saldo);
                Cuenta c = cuenta.get(idCuenta);
                c.setSubCuenta(idSubD, descripcion, saldo);
                c.setSaldoTotal(saldo + c.getSaldoTotal());
                cuenta.remove(idCuenta);
                cuenta.put(idCuenta, c);
                resultado = true;
            }
        } catch (SQLException ex) {
            resultado = false;
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public boolean dropCuenta(int idCuenta, int idSubCuenta) {
        boolean resultado = false;
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM cuenta_empresa "
                    + "WHERE idCuenta LIKE " + idCuenta + " AND idSubCuenta LIKE " + idSubCuenta);
            boolean result = ps.execute();
            con.close();
            if (!result) {
                Cuenta c = cuenta.get(idCuenta);
                SubCuenta sub = c.getSubCuenta(idSubCuenta);
                c.removeSubCuenta(sub);
                cuenta.remove(idCuenta);
                cuenta.put(idCuenta, c);
                resultado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    /*public boolean dropCuenta(int parseInt) {
        boolean resultado = false;
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM cuenta_empresa "
                    + "WHERE idCuenta LIKE " + idCuenta + " AND idSubCuenta IS");
            boolean result = ps.execute();
            con.close();
            if (!result) {
                Cuenta c = cuenta.get(idCuenta);
                SubCuenta sub = c.getSubCuenta(idSubCuenta);
                c.removeSubCuenta(sub);
                cuenta.remove(idCuenta);
                cuenta.put(idCuenta, c);
                resultado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }*/

    private void updateDinero(int idCuenta, double saldo) {
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("UPDATE cuenta_empresa "
                    + "SET saldo = (saldo+" + saldo + ") WHERE idCuentaC = " + idCuenta + " AND idSubCuenta IS NULL;");
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

=======
    
>>>>>>> parent of f01a746... Catalogo al 90
}
