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
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JL
 *
 * Bean que se encargará de mantener integra la información de las cuentas
 * propias de la empresa y permitirá la administración de las cuentas
 *
 */
public class BeanCuentas {

    private HashMap<Integer, Cuenta> cuenta = new HashMap<Integer, Cuenta>();
    private int idEmpresa = 0;

    public HashMap<Integer, Cuenta> getCuenta() {
        return cuenta;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getTotalSubCuentas(int idCuenta) {
        Cuenta c = cuenta.get(idCuenta);
        return c.getSubcuentas().size();
    }

    public void setCuenta(HashMap<Integer, Cuenta> cuenta) {
        this.cuenta = cuenta;
    }

    public int getTotPol() {
        int valor = 0;
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(DISTINCT NoPoliza) FROM poliza WHERE idEmpresaP LIKE "+this.idEmpresa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    public void getCuentas() {
        try {
            cuenta.clear();
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cuentas WHERE idEmpresaC LIKE " + getIdEmpresa());
            ps.execute();
            System.out.println(ps.toString());
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                //Obtiene los parametros resultantes de la consulta de la vista
                int id = Integer.parseInt(rs.getString("idCuentaC"));
                String descripcion = rs.getString("descripcion");
                String idSub = rs.getString("idSubCuenta");
                String descripcionSub = rs.getString("descripcionSub");
                String saldo = rs.getString("saldo");
                System.out.println(id + "-" + descripcion + " idSub " + idSub + " Desc " + descripcionSub);
                //Verifica que el id de la cuenta no exista ya en la lista
                if (!cuenta.containsKey(id)) {
                    Cuenta c = new Cuenta();
                    c.setIdCuenta(id);
                    c.setDescripcionCuenta(descripcion);
                    //Si el dato obtenido tiene subcuenta lo agrega a la cuenta como una subdivisión de la misma
                    if (idSub != null && descripcionSub != null) {
                        SubCuenta sub = new SubCuenta(Integer.parseInt(idSub), descripcionSub, Double.parseDouble(saldo));
                        c.setSubCuenta(sub);
                    } else { //Si no la presenta guarda el valor total del saldo en saldo total
                        c.setSaldoTotal(Double.valueOf(saldo));
                    }
                    cuenta.put(id, c);
                } else { //Si contiene el id la lista entonces solo se encargara de actualizar los valores
                    Cuenta c = cuenta.get(id);
                    cuenta.remove(id);
                    //Si el dato obtenido tiene subcuenta lo agrega a la cuenta como una subdivisión de la misma
                    if (idSub != null && descripcionSub != null) {
                        SubCuenta sub = new SubCuenta(Integer.parseInt(idSub), descripcionSub, Double.parseDouble(saldo));
                        c.setSubCuenta(sub);
                    } else { //Si no la presenta guarda el valor total del saldo en saldo total
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

    public HashMap<Integer, Cuenta> getCuentasSeparadas() {
        HashMap<Integer, Cuenta> cuentasSeparadas = new HashMap<Integer, Cuenta>();
        //SELECT idCuentaC,idSubCuenta, descripcion, descripcionSub FROM cuentas WHERE idEmpresaC LIKE 1 ORDER BY idCuentaC,idSubCuenta
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT "
                    + "idCuentaC,idSubCuenta, descripcion, descripcionSub, saldo "
                    + "FROM cuentas "
                    + "WHERE idEmpresaC LIKE " + getIdEmpresa() + " "
                    + "ORDER BY idCuentaC,idSubCuenta");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                //Obtiene los parametros resultantes de la consulta de la vista
                int id = Integer.parseInt(rs.getString("idCuentaC"));
                System.out.println(id);
                String descripcion = rs.getString("descripcion");
                String idSub = rs.getString("idSubCuenta");
                String descripcionSub = rs.getString("descripcionSub");
                String saldo = rs.getString("saldo");
                //System.out.println(id+"-"+descripcion+" idSub "+idSub+" Desc "+descripcionSub);
                //Verifica que el id de la cuenta no exista ya en la lista
                if (!cuentasSeparadas.containsKey(id)) {
                    Cuenta c = new Cuenta();
                    c.setIdCuenta(id);
                    c.setDescripcionCuenta(descripcion);
                    //Si el dato obtenido tiene subcuenta lo agrega a la cuenta como una subdivisión de la misma
                    if (idSub != null && descripcionSub != null) {
                        SubCuenta sub = new SubCuenta(Integer.parseInt(idSub), descripcionSub, Double.parseDouble(saldo));
                        //System.out.println(idSub+"-"+descripcionSub+"-"+saldo);
                        c.setSubCuenta(sub);
                    } else { //Si no la presenta guarda el valor total del saldo en saldo total
                        c.setSaldoTotal(Double.valueOf(saldo));
                    }
                    cuentasSeparadas.put(id, c);
                } else { //Si contiene el id la lista entonces solo se encargara de actualizar los valores
                    Cuenta c = cuentasSeparadas.get(id);
                    cuentasSeparadas.remove(id);
                    //Si el dato obtenido tiene subcuenta lo agrega a la cuenta como una subdivisión de la misma
                    if (idSub != null && descripcionSub != null) {
                        SubCuenta sub = new SubCuenta(Integer.parseInt(idSub), descripcionSub, Double.parseDouble(saldo));
                        c.setSubCuenta(sub);
                    } else { //Si no la presenta guarda el valor total del saldo en saldo total
                        c.setSaldoTotal(Double.valueOf(saldo));
                    }
                    cuentasSeparadas.put(id, c);
                }
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuentasSeparadas;
    }

    public String getSaldo(int idCuenta) {
        String descripcion = "";
        String saldo = "";
        String mensaje = "";
        DecimalFormat df = new DecimalFormat("###,###,###,###.00");
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT descripcion,saldo FROM cuentas WHERE idCuentaC LIKE " + idCuenta + " AND idEmpresaC LIKE " + getIdEmpresa());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                descripcion = rs.getString("descripcion");
                saldo = rs.getString("saldo");
                mensaje = "<div class=\"alert alert-success\">"
                        + "<p class=\"lead\">El saldo de la cuenta <strong>" + descripcion + "</strong> "
                        + "es <strong>$" + df.format(Double.valueOf(saldo)) + "</strong></p></div>";
            } else {
                mensaje = "<div class=\"alert alert-warning\">"
                        + "<p class=\"lead\">La cuenta no existe en tu catalogo</p></div>";
            }
            con.close();
        } catch (SQLException ex) {
            mensaje = "<div class=\"alert alert-danger\">No se pudo consultar tu saldo en estos momentos</div>";
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

    public String getSaldoSub(int idCuenta, int idSubCuenta) {
        String descripcion = "";
        String descripSub = "";
        String saldo = "";
        String mensaje = "";
        DecimalFormat df = new DecimalFormat("###,###,###,###.00");
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT descripcion,descripcionSub,saldo FROM cuentas "
                    + "WHERE idCuentaC LIKE " + idCuenta + " AND idSubCuenta LIKE " + idSubCuenta + " AND idEmpresaC LIKE " + getIdEmpresa());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                descripcion = rs.getString("descripcion");
                descripSub = rs.getString("descripcionSub");
                saldo = rs.getString("saldo");
                mensaje = "<div class=\"alert alert-success\">"
                        + "<p class=\"lead\">El saldo de la subcuenta <strong>" + descripSub + "</strong> "
                        + "es <strong>$" + df.format(Double.valueOf(saldo)) + "</strong></p></div>";
            } else {
                mensaje = "<div class=\"alert alert-warning\">"
                        + "<p class=\"lead\">La cuenta no existe en tu catalogo</p></div>";
            }
            con.close();
        } catch (SQLException ex) {
            mensaje = "<div class=\"alert alert-danger\">No se pudo consultar tu saldo en estos momentos</div>";
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

    public boolean addCuenta(int idCuenta, double saldo) {
        boolean resultado = false;
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cuenta_empresa "
                    + "VALUES(" + getIdEmpresa() + "," + idCuenta + ",0,null," + saldo + ")");
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
            }
            con.close();
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
                    + "VALUES(" + getIdEmpresa() + "," + idCuenta + "," + idSubD + ",'" + descripcion + "'," + saldo + ")");
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
                    + "WHERE idCuentaC LIKE " + idCuenta + " AND idSubCuenta LIKE " + idSubCuenta + " AND idEmpresaC LIKE " + getIdEmpresa());
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

    public boolean dropCuenta(int idCuenta) {
        boolean resultado = false;
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM cuenta_empresa "
                    + "WHERE idCuentaC LIKE " + idCuenta + " AND idSubCuenta LIKE 0 AND idEmpresaC LIKE " + getIdEmpresa() + " AND saldo LIKE 0");
            boolean result = ps.execute();
            con.close();
            if (!result) {
                Cuenta c = cuenta.get(idCuenta);
                cuenta.remove(idCuenta);
                resultado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    private void updateDinero(int idCuenta, double saldo) {
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("UPDATE cuenta_empresa "
                    + "SET saldo = (saldo+" + saldo + ") WHERE idCuentaC = " + idCuenta + " AND idSubCuenta LIKE 0;");
            ps.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanCuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
