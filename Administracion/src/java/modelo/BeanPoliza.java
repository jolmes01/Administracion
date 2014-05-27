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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JL
 */
public class BeanPoliza {

    private HashMap<Integer, Poliza> polizas = new HashMap<Integer, Poliza>();
    private int idEmpresa = 0;

    public HashMap<Integer, Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(HashMap<Integer, Poliza> polizas) {
        this.polizas = polizas;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String addPoliza(String polizaId, String cuentaP, String fechaMov, String tipo, String pago) {
        int idCuenta = 0;
        int idSubC = 0;
        //int valorDeSobrePaso = 0;
        if (cuentaP.length() > 4) {
            idCuenta = Integer.parseInt(cuentaP.substring(0, 4));
            idSubC = Integer.parseInt(cuentaP.substring(cuentaP.indexOf(".") + 1));
            //valorDeSobrePaso = getSobrePaso(idCuenta,idSubC);
        } else {
            idCuenta = Integer.parseInt(cuentaP);
            //valorDeSobrePaso = getSobrePaso(idCuenta);
        }
        int polID = Integer.parseInt(polizaId);
        Poliza p;
        if (polizas.containsKey(polID)) {
            p = polizas.get(polID);
            polizas.remove(polID);
        } else {
            p = new Poliza();
        }
        p.setPoliza(polID);
        p.addidCuenta(idCuenta);
        p.addidSub(idSubC);
        if ("cargo".equals(tipo.toLowerCase())) {
            p.addTotCargo(Integer.parseInt(pago));
            p.addCargo(Integer.parseInt(pago));
            p.addAbono(0);
        } else {
            p.addTotAbono(Integer.parseInt(pago));
            p.addAbono(Integer.parseInt(pago));
            p.addCargo(0);
        }
        p.addFecha(fechaMov);
        polizas.put(polID, p);
        String retorno = getHistory();
        return retorno;
    }

    public String delPoliza(String idPoliza, String index) {
        Poliza p = polizas.get(Integer.parseInt(idPoliza));
        p.removeData(Integer.parseInt(index));
        return getHistory();
    }

    public String getHistory() {
        Map<Integer, Poliza> map = polizas;
        Iterator<Map.Entry<Integer, Poliza>> it = map.entrySet().iterator();
        ArrayList<Poliza> polizaList = new ArrayList<Poliza>();
        int cargos = 0;
        int abonos = 0;
        int partida = 0;
        int datosNull = 0;
        while (it.hasNext()) {
            Poliza pol = it.next().getValue();
            polizaList.add(pol);
            partida = pol.getPartidaDoble() ? 0 : 1;
            datosNull = pol.getIdCuenta().size() > 0 ? 1 : 0;
            abonos += pol.getTotAbono();
            cargos += pol.getTotCargo();
        }
        String botonSend = "<tr>\n"
                + "<th colspan=\"7\" class=\"text-center\"><p class=\"lead\"><button class=\"btn btn-success\" onClick=\"registrarPoliza()\">Registrar Poliza</button></p></th> \n"
                + "</tr>";
        String retorno = "<table class=\"table table-responsive\">\n"
                + "                                    <thead>\n";
        String msg = (polizas.size() > 0) ? "<p class=\"h2\">Aún no cuadran los cargos y abonos, por tal motivo estan en rojo y por eso no puedes registrar la poliza</p>" : "";
        retorno += (((cargos - abonos == 0) && polizas.size() > 0) && (partida == 0 && datosNull == 1)) ? botonSend : msg;
        retorno += "                                        <tr>\n"
                + "                                            <th><p>No. de Poliza</p></th>\n"
                + "                                    <th><p>Cuenta</p></th>\n"
                + "                                    <th><p>Descripción</p></th>\n"
                + "                                    <th><p>Fecha del movimiento</p></th>\n"
                + "                                    <th><p>Cargos</p></th>\n"
                + "                                    <th><p>Abonos</p></th>\n"
                + "                                    <th><p>Borrar</p></th>\n"
                + "                                    </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n";
        for (Poliza pCount : polizaList) {
            for (int i = 0; i < pCount.getIdCuenta().size(); i++) {
                retorno += pCount.getPartidaDoble() ? "<tr class=\"alert alert-success\">" : "<tr class=\"alert alert-danger\">";
                retorno += " <th>" + pCount.getPoliza() + "</th>";
                retorno += (pCount.getIdSubCuentaByIndex(i) == 0) ? "<th>" + pCount.getIdCuentaByIndex(i) + "</th>" : " <th>" + pCount.getIdCuentaByIndex(i) + "." + pCount.getIdSubCuentaByIndex(i) + "</th>";
                retorno += (pCount.getIdSubCuentaByIndex(i) == 0) ? "<th>" + getDescripcionCuenta(pCount.getIdCuentaByIndex(i)) + "</th>"
                        : "<th>" + getDescripcionCuenta(pCount.getIdCuentaByIndex(i)) + "-" + getDescripcionCuenta(pCount.getIdCuentaByIndex(i), pCount.getIdSubCuentaByIndex(i)) + "</th>";
                retorno += "<th>" + pCount.getFechaByIndex(i) + "</th>";
                retorno += (pCount.getCargoByIndex(i) > 0) ? "<th>" + pCount.getCargoByIndex(i) + "</th>" : "<th></th>";
                retorno += (pCount.getAbonoByIndex(i) > 0) ? "<th>" + pCount.getAbonoByIndex(i) + "</th>" : "<th></th>";
                retorno += "<th><button class=\"btn btn-danger\" onclick=\"eliminar('" + pCount.getPoliza() + "." + i + "')\">Eliminar</button></th>";
            }
        }
        retorno += "</tbody></table>";
        return retorno;
    }

    public String loadFail(boolean check) {
        Map<Integer, Poliza> map = polizas;
        Iterator<Map.Entry<Integer, Poliza>> it = map.entrySet().iterator();
        ArrayList<Poliza> polizaList = new ArrayList<Poliza>();
        int cargos = 0;
        int abonos = 0;
        int partida = 0;
        int datosNull = 0;
        while (it.hasNext()) {
            Poliza pol = it.next().getValue();
            polizaList.add(pol);
            partida = pol.getPartidaDoble() ? 0 : 1;
            datosNull = pol.getIdCuenta().size() > 0 ? 1 : 0;
            abonos += pol.getTotAbono();
            cargos += pol.getTotCargo();
        }
        String botonSend = "<tr>\n"
                + "<th colspan=\"7\" class=\"text-center\"><p class=\"lead\"><button class=\"btn btn-success\" onClick=\"registrarPoliza()\">Registrar Poliza</button></p></th> \n"
                + "</tr>";
        String retorno = "<table class=\"table table-responsive\">\n"
                + "                                    <thead>\n";
        retorno += (((cargos - abonos == 0) && polizas.size() > 0) && (partida == 0 && datosNull == 1) && check) ? botonSend : "";
        retorno += "                                        <tr>\n"
                + "                                            <th><p>No. de Poliza</p></th>\n"
                + "                                    <th><p>Cuenta</p></th>\n"
                + "                                    <th><p>Descripción</p></th>\n"
                + "                                    <th><p>Fecha del movimiento</p></th>\n"
                + "                                    <th><p>Cargos</p></th>\n"
                + "                                    <th><p>Abonos</p></th>\n"
                + "                                    <th><p>Borrar</p></th>\n"
                + "                                    </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n";
        for (Poliza pCount : polizaList) {
            for (int i = 0; i < pCount.getIdCuenta().size(); i++) {
                retorno += pCount.getPartidaDoble() ? "<tr class=\"alert alert-success\">" : "<tr class=\"alert alert-danger\">";
                retorno += " <th>" + pCount.getPoliza() + "</th>";
                retorno += (pCount.getIdSubCuentaByIndex(i) == 0) ? "<th>" + pCount.getIdCuentaByIndex(i) + "</th>" : " <th>" + pCount.getIdCuentaByIndex(i) + "." + pCount.getIdSubCuentaByIndex(i) + "</th>";
                retorno += (pCount.getIdSubCuentaByIndex(i) == 0) ? "<th>" + getDescripcionCuenta(pCount.getIdCuentaByIndex(i)) + "</th>"
                        : "<th>" + getDescripcionCuenta(pCount.getIdCuentaByIndex(i)) + "-" + getDescripcionCuenta(pCount.getIdCuentaByIndex(i), pCount.getIdSubCuentaByIndex(i)) + "</th>";
                retorno += "<th>" + pCount.getFechaByIndex(i) + "</th>";
                retorno += (pCount.getCargoByIndex(i) > 0) ? "<th>" + pCount.getCargoByIndex(i) + "</th>" : "<th></th>";
                retorno += (pCount.getAbonoByIndex(i) > 0) ? "<th>" + pCount.getAbonoByIndex(i) + "</th>" : "<th></th>";
                retorno += "<th><button class=\"btn btn-danger\" onclick=\"eliminar('" + pCount.getPoliza() + "." + i + "')\">Eliminar</button></th>";
            }
        }
        retorno += "</tbody></table>";
        return retorno;
    }

    private String getDescripcionCuenta(int idCuentaByIndex) {
        String descripcion = "";
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT descripcion FROM cuenta WHERE idcuenta LIKE " + idCuentaByIndex);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                descripcion = rs.getString("descripcion");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanPoliza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return descripcion;
    }

    private String getDescripcionCuenta(int idCuentaByIndex, int idSub) {
        String descripcion = "";
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT descripcionSub FROM cuentas "
                    + "WHERE idcuentaC LIKE " + idCuentaByIndex + " AND idSubCuenta LIKE " + idSub + " AND idEmpresaC LIKE "+getIdEmpresa());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                descripcion = rs.getString("descripcionSub");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanPoliza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return descripcion;
    }

    private int getSobrePaso(int idCuenta, int idSubC, int cargo) {
        int valorDeSobrePaso = 0;
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM cuenta_empresa WHERE idEmpresaC LIKE ? AND idCuentaC LIKE ? AND idSubCuenta LIKE ? AND saldo >= ");
            ps.setInt(1, getIdEmpresa());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                valorDeSobrePaso = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanPoliza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valorDeSobrePaso;
    }

    private int getSobrePaso(int idCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String registroPoliza() {
        String resultado = "";
        Map<Integer, Poliza> map = polizas;
        Iterator<Map.Entry<Integer, Poliza>> it = map.entrySet().iterator();
        try {
            Connection con = new AccesBD().conexion();
            int valorFirtsQuery = 0;
            while (it.hasNext()) {
                /* idEmpresaP - idCuentaP - idSubCuentaP
                 NoPoliza - fecha - Cargo - Abonos*/
                Poliza pol = it.next().getValue();
                int idPoliza = pol.getPoliza();
                for (int i = 0; i < pol.getIdCuenta().size(); i++) {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO poliza VALUES(?,?,?,?,?,?,?)");
                    ps.setInt(1, getIdEmpresa());
                    ps.setInt(2, pol.getIdCuentaByIndex(i));
                    ps.setInt(3, pol.getIdSubCuentaByIndex(i));
                    ps.setInt(4, idPoliza);
                    ps.setString(5, pol.getFechaByIndex(i));
                    ps.setInt(6, pol.getCargoByIndex(i));
                    ps.setInt(7, pol.getAbonoByIndex(i));
                    Connection cambio = new AccesBD().conexion();
                    String operador = "";
                    String subCuenta = " AND idSubCuenta = 0";
                    int saldo = 0;
                    if (pol.getIdSubCuentaByIndex(i) != 0) {
                        subCuenta = " AND idSubCuenta LIKE " + pol.getIdSubCuentaByIndex(i);
                    }
                    if (pol.getIdCuentaByIndex(i) < 2000) {
                        if (pol.getCargoByIndex(i) != 0) {
                            operador = "+";
                            saldo = pol.getCargoByIndex(i);
                        } else {
                            operador = "-";
                            saldo = pol.getAbonoByIndex(i);
                        }
                    } else {
                        if (pol.getCargoByIndex(i) != 0) {
                            operador = "-";
                            saldo = pol.getCargoByIndex(i);
                        } else {
                            operador = "+";
                            saldo = pol.getAbonoByIndex(i);
                        }
                    }
                    String validaMayor = (operador == "-") ? "saldo >= " + saldo + " AND" : "";
                    String sql = "UPDATE cuenta_empresa "
                            + "SET saldo = (saldo" + operador + "" + saldo + ") WHERE " + validaMayor + " idCuentaC = " + pol.getIdCuentaByIndex(i) + "" + subCuenta + " AND idEmpresaC LIKE "+getIdEmpresa();
                    System.out.println(sql);
                    PreparedStatement psc = cambio.prepareStatement(sql);
                    valorFirtsQuery = psc.executeUpdate();
                    if (pol.getIdSubCuentaByIndex(i) != 0 && valorFirtsQuery == 1) {
                        sql = "UPDATE cuenta_empresa "
                                + "SET saldo = (saldo" + operador + "" + saldo + ") "
                                + "WHERE " + validaMayor + " idCuentaC = " + pol.getIdCuentaByIndex(i) + " AND idSubCuenta = 0"
                                + " AND idEmpresaC LIKE "+getIdEmpresa();
                        System.out.println(sql);
                        valorFirtsQuery = psc.executeUpdate(sql);
                    }
                    cambio.close();
                    if (valorFirtsQuery == 1) {
                        ps.execute();
                    }
                }
            }
            con.close();
            if (valorFirtsQuery == 1) {
                resultado = "<div class=\"alert alert-success alert-dismissable\">\n"
                        + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
                        + "  <p class=\"lead\"><strong>Correcto!</strong> Tu poliza se ha registrado correctamente en tu cuenta.</p>"
                        + "</div>";
                polizas.clear();
            } else {
                resultado = "<div class=\"alert alert-danger alert-dismissable\">\n"
                        + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
                        + "  <p class=\"lead\"> <strong>OJO!</strong> El movimiento de tus saldos ingresados en la poliza, exceden de tu saldo, por seguridad, te pedimos verifiques los datos ya que por ahora no se guardo tu poliza en el Sistema. </p>"
                        + "</div>" + loadFail(false);
            }
        } catch (SQLException ex) {
            resultado = "<div class=\"alert alert-danger alert-dismissable\">\n"
                    + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
                    + "  <p class=\"lead\"><strong>Ups!</strong> Hubo un error en tu conexión y no se pudo registrar tu poliza.</p>"
                    + "</div>";
            Logger.getLogger(BeanPoliza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

}
