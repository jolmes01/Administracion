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
 * @author AlejandraMonserrat
 */

public class BalanceGeneral {
  
    private ArrayList<Double> val = new ArrayList<Double>();
    private ArrayList<String> descripcion = new ArrayList<String>();
    private ArrayList<Integer> id = new ArrayList<Integer>();
    
    private ArrayList<Double> valcircu = new ArrayList<Double>();
    private ArrayList<String> descripcircu = new ArrayList<String>();
    private ArrayList<Double> valinmu = new ArrayList<Double>();
    private ArrayList<String> descripinmu = new ArrayList<String>();
    private ArrayList<Double> valinta = new ArrayList<Double>();
    private ArrayList<String> descripinta = new ArrayList<String>();
    private ArrayList<Double> valotros = new ArrayList<Double>();
    private ArrayList<String> descripotros = new ArrayList<String>();
    private ArrayList<Double> valpasivocp = new ArrayList<Double>();
    private ArrayList<String> descripasivocp = new ArrayList<String>();
    private ArrayList<Double> valpasivolp = new ArrayList<Double>();
    private ArrayList<String> descripasivolp = new ArrayList<String>();
    private ArrayList<Double> valcapitalcon = new ArrayList<Double>();
    private ArrayList<String> descripcapitalcon = new ArrayList<String>();
    private ArrayList<Double> valcapitalgan = new ArrayList<Double>();
    private ArrayList<String> descripcapitalgan = new ArrayList<String>();

    public BalanceGeneral() {
    }
    
    public void calcula(int idEmpresa) throws SQLException{
        Connection con= new AccesBD().conexion();
        PreparedStatement ps= con.prepareStatement("SELECT idCuentaC,descripcion,saldo FROM cuentas WHERE idEmpresaC LIKE ? AND idSubCuenta LIKE 0 ORDER BY idCuentaC");
        ps.setInt(1, idEmpresa);
        ResultSet rs= ps.executeQuery();
        while(rs.next())
        {
            id.add(rs.getInt("idCuentaC"));
            descripcion.add(rs.getString("descripcion"));
            val.add(rs.getDouble("saldo"));
        }
        con.close();
        
        for(int i=0;i<id.size();i++)
        {
            if(id.get(i)>=1101 && id.get(i)<=1114)
            {
                valcircu.add(val.get(i));
                descripcircu.add(descripcion.get(i));
            }
            else
            {
                if(id.get(i)>=1201 && id.get(i)<=1205)
                {
                    valinmu.add(val.get(i));
                    descripinmu.add(descripcion.get(i));
                }
                else if(id.get(i)>=1301 && id.get(i)<=1303)
                {
                    valinta.add(val.get(i));
                    descripinta.add(descripcion.get(i));
                }
                else if(id.get(i)>=1401 && id.get(i)<=1403)
                {
                    valotros.add(val.get(i));
                    descripotros.add(descripcion.get(i));
                }
                else if(id.get(i)>=2101 && id.get(i)<=2109)
                {
                    valpasivocp.add(val.get(i));
                    descripasivocp.add(descripcion.get(i));
                }
                else if(id.get(i)>=2201 && id.get(i)<=2204)
                {
                    valpasivolp.add(val.get(i));
                    descripasivolp.add(descripcion.get(i));
                }
                else if(id.get(i)>=3101 && id.get(i)<=3104)
                {
                    valcapitalcon.add(val.get(i));
                    descripcapitalcon.add(descripcion.get(i));
                }
                else if(id.get(i)>=3201 && id.get(i)<=3205)
                {
                    valcapitalgan.add(val.get(i));
                    descripcapitalgan.add(descripcion.get(i));
                }
            }
        }
    }

    public ArrayList<String> getDescripcapitalcon() {
        return descripcapitalcon;
    }

    public void setDescripcapitalcon(ArrayList<String> descripcapitalcon) {
        this.descripcapitalcon = descripcapitalcon;
    }

    public ArrayList<Double> getValcapitalcon() {
        return valcapitalcon;
    }
    
    public void setValcapitalcon(ArrayList<Double> valcapitalcon) {
        this.valcapitalcon = valcapitalcon;
    }

    public ArrayList<String> getDescripcapitalgan() {
        return descripcapitalgan;
    }

    public void setDescripcapitalgan(ArrayList<String> descripcapitalgan) {
        this.descripcapitalgan = descripcapitalgan;
    }

    public ArrayList<Double> getValcapitalgan() {
        return valcapitalgan;
    }

    public void setValcapitalgan(ArrayList<Double> valcapitalgan) {
        this.valcapitalgan = valcapitalgan;
    }
    
    public ArrayList<String> getDescripasivocp() {
        return descripasivocp;
    }

    public void setDescripasivocp(ArrayList<String> descripasivocp) {
        this.descripasivocp = descripasivocp;
    }

    public ArrayList<String> getDescripasivolp() {
        return descripasivolp;
    }

    public void setDescripasivolp(ArrayList<String> descripasivolp) {
        this.descripasivolp = descripasivolp;
    }

    public ArrayList<Double> getValpasivocp() {
        return valpasivocp;
    }

    public void setValpasivocp(ArrayList<Double> valpasivocp) {
        this.valpasivocp = valpasivocp;
    }

    public ArrayList<Double> getValpasivolp() {
        return valpasivolp;
    }

    public void setValpasivolp(ArrayList<Double> valpasivolp) {
        this.valpasivolp = valpasivolp;
    }
    

    public ArrayList<String> getDescripotros() {
        return descripotros;
    }

    public void setDescripotros(ArrayList<String> descripotros) {
        this.descripotros = descripotros;
    }

    public ArrayList<Double> getValotros() {
        return valotros;
    }

    public void setValotros(ArrayList<Double> valotros) {
        this.valotros = valotros;
    }

    public ArrayList<String> getDescripinta() {
        return descripinta;
    }

    public void setDescripinta(ArrayList<String> descripinta) {
        this.descripinta = descripinta;
    }

    public ArrayList<Double> getValinta() {
        return valinta;
    }

    public void setValinta(ArrayList<Double> valinta) {
        this.valinta = valinta;
    }

    public ArrayList<Double> getValinmu() {
        return valinmu;
    }

    public void setValinmu(ArrayList<Double> valinmu) {
        this.valinmu = valinmu;
    }

    public ArrayList<String> getDescripinmu() {
        return descripinmu;
    }

    public void setDescripinmu(ArrayList<String> descripinmu) {
        this.descripinmu = descripinmu;
    }
    
    public ArrayList<String> getDescripcircu() {
        return descripcircu;
    }

    public void setDescripcircu(ArrayList<String> descripcircu) {
        this.descripcircu = descripcircu;
    }

    public ArrayList<Double> getValcircu() {
        return valcircu;
    }

    public void setValcircu(ArrayList<Double> valcircu) {
        this.valcircu = valcircu;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public void setId(ArrayList<Integer> id) {
        this.id = id;
    }
    

    public ArrayList<Double> getVal() {
        return val;
    }

    public void setVal(ArrayList<Double> val) {
        this.val = val;
    }
    
    public ArrayList<String> getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(ArrayList<String> descripcion) {
        this.descripcion = descripcion;
    }
    
}
