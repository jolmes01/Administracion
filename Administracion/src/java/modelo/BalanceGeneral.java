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
    private ArrayList<Double> saldos = new ArrayList<Double>();
    private final int lonDescriptores = 50;
    
    private double caja = 0.0;
    private double bancos = 0.0;
    private double clientes = 0.0;
    private double docpcobrar= 0.0;
    private double deudiversos= 0.0;
    private double almacen = 0.0;
    private double ivacreditable = 0.0;
    private double ivapacreditar = 0.0;
    private double renppanticipado = 0.0;
    private double primasdeseguro = 0.0;
    private double intppanticipado = 0.0;
    private double papyutilies = 0.0;
    private double propypubli = 0.0;
    private double terrenos = 0.0;
    private double edificio = 0.0;
    private double mobyequipo = 0.0;
    private double edereparto = 0.0;
    private double edecomputo = 0.0;
    private double creditom = 0.0;
    private double patentes = 0.0;
    private double marcas = 0.0;
    private double depgarantia = 0.0;
    private double gdeorg = 0.0;
    private double gdeins = 0.0;
    private double proveedores = 0.0;
    private double acreedores = 0.0;
    private double dppagar = 0.0;
    private double itrasladado = 0.0;
    private double iptrasladar = 0.0;
    private double impppagar = 0.0;
    private double intcpanticipadocp = 0.0;
    private double rentcpanticipado = 0.0;
    private double docpplargo = 0.0;
    private double ahipotecarios = 0.0;
    private double capitalsocial = 0.0;
    private double utanteriores = 0.0;
    private double reservalegar = 0.0;
    private double utejercicio = 0.0;
    private double depedificio = 0.0;
    private double depequipo = 0.0;
    private double deptransporte = 0.0;
    private double depcomputo = 0.0;
    private double intcobrados = 0.0;
    private double intcpanticipadolp = 0.0;
    private double creditofrac = 0.0;
    private double aportaciones = 0.0;
    private double donaciones = 0.0;
    private double primaenventa = 0.0;
    private double inversiones = 0.0;
    private double ptu = 0.0;
    
    private ArrayList<Double> val = new ArrayList<Double>();
    private ArrayList<String> descripcion = new ArrayList<String>();

    public BalanceGeneral() {
    }
    
    public void calcula(int idEmpresa) throws SQLException{
        Connection con= new AccesBD().conexion();
        PreparedStatement ps= con.prepareStatement("SELECT descripcion,saldo FROM cuentas WHERE idEmpresaC LIKE ? AND idCuentaC > 5000 AND idSubCuenta LIKE 0 ORDER BY idCuentaC");
        ps.setInt(1, idEmpresa);
        ResultSet rs= ps.executeQuery();
        while(rs.next())
        {
            descripcion.add(rs.getString("descripcion"));
            val.add(rs.getDouble("saldo"));
        }
        con.close();
        if(!descripcion.isEmpty())
        {
            this.caja = this.val.get(descripcion.indexOf("Caja"));
            saldos.add(this.caja);
            this.bancos = this.val.get(descripcion.indexOf("Bancos"));
            saldos.add(this.bancos);
            this.clientes = this.val.get(descripcion.indexOf("Clientes"));
            saldos.add(this.clientes);
            this.deudiversos = this.val.get(descripcion.indexOf("Deudores diversos"));
            saldos.add(this.deudiversos);
            this.docpcobrar = this.val.get(descripcion.indexOf("Documentos por cobrar"));
            saldos.add(this.docpcobrar);
            this.almacen = this.val.get(descripcion.indexOf("Almacén"));
            saldos.add(this.almacen);
            this.ivacreditable = this.val.get(descripcion.indexOf("IVA acreditable"));
            saldos.add(this.ivacreditable);
            this.ivapacreditar = this.val.get(descripcion.indexOf("IVA por acreditar"));
            saldos.add(this.ivapacreditar);
            this.renppanticipado = this.val.get(descripcion.indexOf("Rentas pagadas por anticipado"));
            saldos.add(this.renppanticipado);
            this.primasdeseguro = this.val.get(descripcion.indexOf("Primas de seguro"));
            saldos.add(this.primasdeseguro);
            this.intppanticipado = this.val.get(descripcion.indexOf("Intereses pagados por anticipado"));
            saldos.add(this.intppanticipado);
            this.papyutilies = this.val.get(descripcion.indexOf("Papelería y útiles"));
            saldos.add(this.papyutilies);
            this.propypubli = this.val.get(descripcion.indexOf("Propaganda y publicidad"));
            saldos.add(this.propypubli);
            this.terrenos = this.val.get(descripcion.indexOf("Terrenos"));
            saldos.add(this.terrenos);
            this.edificio = this.val.get(descripcion.indexOf("Edificio"));
            saldos.add(this.edificio);
            this.mobyequipo = this.val.get(descripcion.indexOf("Mobiliario y equipo de oficina"));
            saldos.add(this.mobyequipo);
            this.edereparto = this.val.get(descripcion.indexOf("Equipo de reparto"));
            saldos.add(this.edereparto);
            this.edecomputo = this.val.get(descripcion.indexOf("Equipo de Cómputo"));
            saldos.add(this.edecomputo);
            this.creditom = this.val.get(descripcion.indexOf("Crédito mercantil"));
            saldos.add(this.creditom);
            this.patentes = this.val.get(descripcion.indexOf("Patentes"));
            saldos.add(this.patentes);
            this.marcas = this.val.get(descripcion.indexOf("Marcas"));
            saldos.add(this.marcas);
            this.depgarantia = this.val.get(descripcion.indexOf("Depósitos en garantía"));
            saldos.add(this.depgarantia);
            this.gdeorg = this.val.get(descripcion.indexOf("Gastos de organización"));
            saldos.add(this.gdeorg);
            this.gdeins = this.val.get(descripcion.indexOf("Gastos de instalación"));
            saldos.add(this.gdeins);
            this.proveedores = this.val.get(descripcion.indexOf("Proveedores"));
            saldos.add(this.proveedores);
            this.acreedores = this.val.get(descripcion.indexOf("Acreedores diversos"));
            saldos.add(this.acreedores);
            this.dppagar = this.val.get(descripcion.indexOf("Documentos por pagar"));
            saldos.add(this.dppagar);
            this.itrasladado = this.val.get(descripcion.indexOf("IVA trasladado"));
            saldos.add(this.itrasladado);
        }
    }
    
    
    
    
}
