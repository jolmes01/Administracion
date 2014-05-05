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

    public HashMap<Integer, Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(HashMap<Integer, Poliza> polizas) {
        this.polizas = polizas;
    }

    public String addPoliza(String polizaId, String cuentaP, String fechaMov, String tipo, String pago) {
        int idCuenta = 0;
        int idSubC = 0;
        if (cuentaP.length() > 4) {
            String cuentasList[] = cuentaP.split(".");
            idCuenta = Integer.parseInt(cuentasList[0]);
            idSubC = Integer.parseInt(cuentasList[1]);
        } else {
            idCuenta = Integer.parseInt(cuentaP);
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
    
    public String delPoliza(String idPoliza,String index){
        System.err.println(idPoliza+"-"+index);
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
            partida = pol.getPartidaDoble() ? 0:1;
            datosNull = pol.getIdCuenta().size() > 0 ? 1:0;
            abonos += pol.getTotAbono();
            cargos += pol.getTotCargo();
        }
        String botonSend = "<tr>\n"
                + "<th colspan=\"7\" class=\"text-center\"><p class=\"lead\"><button class=\"btn btn-success\">Registrar Poliza</button></p></th> \n"
                + "</tr>";
        String retorno = "<table class=\"table table-responsive\">\n"
                + "                                    <thead>\n";
        retorno += (((cargos-abonos == 0)&&polizas.size()>0)&&(partida == 0 && datosNull == 1)) ? botonSend : "";
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
                retorno += " <th>" + pCount.getPoliza() + "</th>"
                        + " <th>" + pCount.getIdCuentaByIndex(i) + "</th>"
                        + " <th>" + getDescripcionCuenta(pCount.getIdCuentaByIndex(i)) + "</th>"
                        + " <th>" + pCount.getFechaByIndex(i) + "</th>";
                retorno += (pCount.getCargoByIndex(i) > 0) ? "<th>" + pCount.getCargoByIndex(i) + "</th>" : "<th></th>";
                retorno += (pCount.getAbonoByIndex(i) > 0) ? "<th>" + pCount.getAbonoByIndex(i) + "</th>" : "<th></th>";
                retorno += "<th><button class=\"btn btn-danger\" onclick=\"eliminar('" + pCount.getPoliza() + "."+i+"')\">Eliminar</button></th>";
            }
        }
        retorno += "</tbody></table>";
        return retorno;
    }

    private String getDescripcionCuenta(int idCuentaByIndex) {
        String descripcion = "";
        try {
            Connection con = new AccesBD().conexion();
            PreparedStatement ps = con.prepareStatement("SELECT descripcion FROM cuenta WHERE idcuenta LIKE "+idCuentaByIndex);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                descripcion = rs.getString("descripcion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BeanPoliza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return descripcion;
    }

}
