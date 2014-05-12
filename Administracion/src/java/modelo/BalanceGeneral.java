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
    private ArrayList<Double> sumas = new ArrayList<Double>();
    private final int lonDescriptores = 68;
    
    private double activo=0.0;
    private double circulante=0.0;
    private double nocirculante = 0.0;
    private double pasivo= 0.0;
    private double pcorto= 0.0;
    private double plargo= 0.0;
    private double contable = 0.0;
    private double ganado = 0.0;
    private double contribuido= 0.0;
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
   // private double intcpanticipadocp = 0.0;
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
    //private double intcpanticipadolp = 0.0;
    private double creditofrac = 0.0;
    private double aportaciones = 0.0;
    private double donaciones = 0.0;
    private double primaenventa = 0.0;
    private double inversiones = 0.0;
    private double ptu = 0.0;
    private double panteriores= 0.0;
    private double pejercicio = 0.0;
    private double sumarac= 0.0;
    private double sumarpac=0.0;
    
    private double sumactivoc= 0.0;
    private double sumactivonc= 0.0;
    private double sumactivo= 0.0;
    private double sumacp = 0.0;
    private double sumapcp = 0.0;
    private double sumaplp = 0.0;
    private double sumacc = 0.0;
    private double sumacg = 0.0;
    
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
            this.activo=0.0;
            saldos.add(this.caja);
            this.circulante=0.0;
            saldos.add(this.circulante);
            this.caja = this.val.get(descripcion.indexOf("Caja"));
            saldos.add(this.caja);
            this.bancos = this.val.get(descripcion.indexOf("Bancos"));
            saldos.add(this.bancos);
            this.inversiones = this.val.get(descripcion.indexOf("Inversiones Temporales"));
            saldos.add(this.inversiones);
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
            this.sumactivoc=this.propypubli+this.papyutilies+this.intppanticipado+this.primasdeseguro+this.renppanticipado+this.ivapacreditar+this.ivacreditable+this.almacen
                    +this.docpcobrar+this.deudiversos+this.clientes+this.inversiones+this.bancos+this.caja;
            sumas.add(this.sumactivoc);
            this.nocirculante=0.0;
            saldos.add(this.nocirculante);
            this.terrenos = this.val.get(descripcion.indexOf("Terrenos"));
            saldos.add(this.terrenos);
            this.edificio = this.val.get(descripcion.indexOf("Edificio"));
            saldos.add(this.edificio);
            this.depedificio = this.val.get(descripcion.indexOf("Depreciación acumulada de edificio"));
            saldos.add(this.depedificio);
            this.mobyequipo = this.val.get(descripcion.indexOf("Mobiliario y equipo de oficina"));
            saldos.add(this.mobyequipo);
            this.depequipo = this.val.get(descripcion.indexOf("Depreciación acumulada de mobiliario y equipo"));
            saldos.add(this.depequipo);
            this.edereparto = this.val.get(descripcion.indexOf("Equipo de reparto"));
            saldos.add(this.edereparto);
            this.deptransporte = this.val.get(descripcion.indexOf("Depreciación acumulada de equipo de transporte"));
            saldos.add(this.deptransporte);
            this.edecomputo = this.val.get(descripcion.indexOf("Equipo de Cómputo"));
            saldos.add(this.edecomputo);
            this.depcomputo = this.val.get(descripcion.indexOf("Depreciación acumulada de equipo de cómputo"));
            saldos.add(this.depcomputo);
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
            this.sumactivonc=this.terrenos+this.edificio+this.depedificio+this.mobyequipo+this.depequipo+this.edereparto+this.deptransporte+this.edecomputo+this.depcomputo
                    +this.creditom+this.patentes+this.marcas+this.depgarantia+this.gdeorg+this.gdeins;
            sumas.add(this.sumactivoc);
            this.sumarac=0.0;
            saldos.add(this.sumarac);
            this.sumactivo=this.sumactivoc+this.sumactivonc;
            sumas.add(this.sumactivo);
            this.pasivo=0.0;
            saldos.add(this.pasivo);
            this.pcorto=0.0;
            saldos.add(this.pcorto);
            this.proveedores = this.val.get(descripcion.indexOf("Proveedores"));
            saldos.add(this.proveedores);
            this.acreedores = this.val.get(descripcion.indexOf("Acreedores diversos"));
            saldos.add(this.acreedores);
            this.dppagar = this.val.get(descripcion.indexOf("Documentos por pagar"));
            saldos.add(this.dppagar);
            this.itrasladado = this.val.get(descripcion.indexOf("IVA trasladado"));
            saldos.add(this.itrasladado);
            this.iptrasladar = this.val.get(descripcion.indexOf("IVA por trasladar"));
            saldos.add(this.iptrasladar);
            //this.itrasladado = this.val.get(descripcion.indexOf("Intereses cobrados por anticipado"));
            //saldos.add(this.itrasladado);
            this.impppagar = this.val.get(descripcion.indexOf("Impuestos por pagar"));
            saldos.add(this.impppagar);
            this.rentcpanticipado = this.val.get(descripcion.indexOf("Rentas cobradas por anticipado"));
            saldos.add(this.rentcpanticipado);
            this.ptu = this.val.get(descripcion.indexOf("PTU por pagar"));
            saldos.add(this.ptu);
            this.sumapcp=this.proveedores+this.acreedores+this.dppagar+this.itrasladado+this.iptrasladar+this.impppagar+this.rentcpanticipado+this.ptu;
            sumas.add(this.sumapcp);
            this.plargo=0.0;
            saldos.add(plargo);
            this.docpplargo = this.val.get(descripcion.indexOf("Docuemtnos por pagar a largo plazo"));
            saldos.add(this.docpplargo);
            this.ahipotecarios = this.val.get(descripcion.indexOf("Acreedores hipotecarios"));
            saldos.add(this.ahipotecarios);
            this.creditofrac = this.val.get(descripcion.indexOf("Crédito refraccionario"));
            saldos.add(this.creditofrac);
            this.sumaplp=this.docpplargo+this.ahipotecarios+this.creditofrac;
            sumas.add(this.sumaplp);
            this.circulante=0.0;
            saldos.add(this.circulante);
            this.contable=0.0;
            saldos.add(this.contable);
            this.capitalsocial = this.val.get(descripcion.indexOf("Capital Social"));
            saldos.add(this.capitalsocial);
            this.aportaciones = this.val.get(descripcion.indexOf("Aportaciones para futuros aumentos de capital"));
            saldos.add(this.aportaciones);
            this.donaciones = this.val.get(descripcion.indexOf("Donaciones"));
            saldos.add(this.donaciones);
            this.primaenventa = this.val.get(descripcion.indexOf("Prima en venta de acciones"));
            saldos.add(this.primaenventa);
            this.sumacc=this.capitalsocial+this.aportaciones+this.donaciones+this.primaenventa;
            sumas.add(this.sumacc);
            this.contribuido=0.0;
            saldos.add(this.contribuido);
            this.utanteriores = this.val.get(descripcion.indexOf("Utilidades de ejercicios anteriores"));
            saldos.add(this.utanteriores);
            this.panteriores = this.val.get(descripcion.indexOf("Pérdidas de ejercicios anteriores"));
            saldos.add(this.panteriores);
            this.reservalegar = this.val.get(descripcion.indexOf("Reserva legal"));
            saldos.add(this.reservalegar);
            this.utejercicio = this.val.get(descripcion.indexOf("Utilidad del ejercicio"));
            saldos.add(this.utejercicio);
            this.pejercicio = this.val.get(descripcion.indexOf("Pérdida del ejercicio"));
            saldos.add(this.pejercicio);
            this.sumacg=this.utanteriores+this.panteriores+this.reservalegar+this.utejercicio+this.pejercicio;
            sumas.add(this.sumacg);
            this.sumarpac=0.0;
            saldos.add(this.sumarpac);
            this.sumacp=this.sumapcp+this.sumaplp+this.sumacc+this.sumacg;
            sumas.add(this.sumacp);
            
        }
        else{
            saldos.add(this.caja);
            saldos.add(this.bancos);
            saldos.add(this.inversiones);
            saldos.add(this.clientes);
            saldos.add(this.deudiversos);
            saldos.add(this.docpcobrar);
            saldos.add(this.almacen);
            saldos.add(this.ivacreditable);
            saldos.add(this.ivapacreditar);
            saldos.add(this.renppanticipado);
            saldos.add(this.primasdeseguro);
            saldos.add(this.intppanticipado);
            saldos.add(this.papyutilies);
            saldos.add(this.propypubli);
            saldos.add(this.terrenos);
            saldos.add(this.edificio);
            saldos.add(this.depedificio);
            saldos.add(this.mobyequipo);
            saldos.add(this.depequipo);
            saldos.add(this.edereparto);
            saldos.add(this.deptransporte);
            saldos.add(this.edecomputo);
            saldos.add(this.depcomputo);
            saldos.add(this.creditom);
            saldos.add(this.patentes);
            saldos.add(this.marcas);
            saldos.add(this.depgarantia);
            saldos.add(this.gdeorg);
            saldos.add(this.gdeins);
            saldos.add(this.proveedores);
            saldos.add(this.acreedores);
            saldos.add(this.dppagar);
            saldos.add(this.itrasladado);
            saldos.add(this.iptrasladar);
            saldos.add(this.impppagar);
            saldos.add(this.rentcpanticipado);
            saldos.add(this.ptu);
            saldos.add(this.docpplargo);
            saldos.add(this.ahipotecarios);
            saldos.add(this.creditofrac);
            saldos.add(this.capitalsocial);
            saldos.add(this.aportaciones);
            saldos.add(this.donaciones);
            saldos.add(this.primaenventa);
            saldos.add(this.utanteriores);
            saldos.add(this.panteriores);
            saldos.add(this.reservalegar);
            saldos.add(this.utejercicio);
            saldos.add(this.pejercicio);
        }
    }

    public double getAcreedores() {
        return acreedores;
    }

    public void setAcreedores(double acreedores) {
        this.acreedores = acreedores;
    }

    public double getAhipotecarios() {
        return ahipotecarios;
    }

    public void setAhipotecarios(double ahipotecarios) {
        this.ahipotecarios = ahipotecarios;
    }

    public double getAlmacen() {
        return almacen;
    }

    public void setAlmacen(double almacen) {
        this.almacen = almacen;
    }

    public double getAportaciones() {
        return aportaciones;
    }

    public void setAportaciones(double aportaciones) {
        this.aportaciones = aportaciones;
    }

    public double getBancos() {
        return bancos;
    }

    public void setBancos(double bancos) {
        this.bancos = bancos;
    }

    public double getCaja() {
        return caja;
    }

    public void setCaja(double caja) {
        this.caja = caja;
    }

    public double getCapitalsocial() {
        return capitalsocial;
    }

    public void setCapitalsocial(double capitalsocial) {
        this.capitalsocial = capitalsocial;
    }

    public double getClientes() {
        return clientes;
    }

    public void setClientes(double clientes) {
        this.clientes = clientes;
    }

    public double getCreditofrac() {
        return creditofrac;
    }

    public void setCreditofrac(double creditofrac) {
        this.creditofrac = creditofrac;
    }

    public double getCreditom() {
        return creditom;
    }

    public void setCreditom(double creditom) {
        this.creditom = creditom;
    }

    public double getDepcomputo() {
        return depcomputo;
    }

    public void setDepcomputo(double depcomputo) {
        this.depcomputo = depcomputo;
    }

    public double getDepedificio() {
        return depedificio;
    }

    public void setDepedificio(double depedificio) {
        this.depedificio = depedificio;
    }

    public double getDepequipo() {
        return depequipo;
    }

    public void setDepequipo(double depequipo) {
        this.depequipo = depequipo;
    }

    public double getDepgarantia() {
        return depgarantia;
    }

    public void setDepgarantia(double depgarantia) {
        this.depgarantia = depgarantia;
    }

    public double getDeptransporte() {
        return deptransporte;
    }

    public void setDeptransporte(double deptransporte) {
        this.deptransporte = deptransporte;
    }

    public double getDeudiversos() {
        return deudiversos;
    }

    public void setDeudiversos(double deudiversos) {
        this.deudiversos = deudiversos;
    }

    public double getDocpcobrar() {
        return docpcobrar;
    }

    public void setDocpcobrar(double docpcobrar) {
        this.docpcobrar = docpcobrar;
    }

    public double getDocpplargo() {
        return docpplargo;
    }

    public void setDocpplargo(double docpplargo) {
        this.docpplargo = docpplargo;
    }

    public double getDonaciones() {
        return donaciones;
    }

    public void setDonaciones(double donaciones) {
        this.donaciones = donaciones;
    }

    public double getDppagar() {
        return dppagar;
    }

    public void setDppagar(double dppagar) {
        this.dppagar = dppagar;
    }

    public double getEdecomputo() {
        return edecomputo;
    }

    public void setEdecomputo(double edecomputo) {
        this.edecomputo = edecomputo;
    }

    public double getEdereparto() {
        return edereparto;
    }

    public void setEdereparto(double edereparto) {
        this.edereparto = edereparto;
    }

    public double getEdificio() {
        return edificio;
    }

    public void setEdificio(double edificio) {
        this.edificio = edificio;
    }

    public double getGdeins() {
        return gdeins;
    }

    public void setGdeins(double gdeins) {
        this.gdeins = gdeins;
    }

    public double getGdeorg() {
        return gdeorg;
    }

    public void setGdeorg(double gdeorg) {
        this.gdeorg = gdeorg;
    }

    public double getImpppagar() {
        return impppagar;
    }

    public void setImpppagar(double impppagar) {
        this.impppagar = impppagar;
    }

    public double getIntppanticipado() {
        return intppanticipado;
    }

    public void setIntppanticipado(double intppanticipado) {
        this.intppanticipado = intppanticipado;
    }

    public double getInversiones() {
        return inversiones;
    }

    public void setInversiones(double inversiones) {
        this.inversiones = inversiones;
    }

    public double getIptrasladar() {
        return iptrasladar;
    }

    public void setIptrasladar(double iptrasladar) {
        this.iptrasladar = iptrasladar;
    }

    public double getItrasladado() {
        return itrasladado;
    }

    public void setItrasladado(double itrasladado) {
        this.itrasladado = itrasladado;
    }

    public double getIvacreditable() {
        return ivacreditable;
    }

    public void setIvacreditable(double ivacreditable) {
        this.ivacreditable = ivacreditable;
    }

    public double getIvapacreditar() {
        return ivapacreditar;
    }

    public void setIvapacreditar(double ivapacreditar) {
        this.ivapacreditar = ivapacreditar;
    }

    public double getMarcas() {
        return marcas;
    }

    public void setMarcas(double marcas) {
        this.marcas = marcas;
    }

    public double getMobyequipo() {
        return mobyequipo;
    }

    public void setMobyequipo(double mobyequipo) {
        this.mobyequipo = mobyequipo;
    }

    public double getPapyutilies() {
        return papyutilies;
    }

    public void setPapyutilies(double papyutilies) {
        this.papyutilies = papyutilies;
    }

    public double getPatentes() {
        return patentes;
    }

    public void setPatentes(double patentes) {
        this.patentes = patentes;
    }

    public double getPrimaenventa() {
        return primaenventa;
    }

    public void setPrimaenventa(double primaenventa) {
        this.primaenventa = primaenventa;
    }

    public double getPrimasdeseguro() {
        return primasdeseguro;
    }

    public void setPrimasdeseguro(double primasdeseguro) {
        this.primasdeseguro = primasdeseguro;
    }

    public double getPropypubli() {
        return propypubli;
    }

    public void setPropypubli(double propypubli) {
        this.propypubli = propypubli;
    }

    public double getProveedores() {
        return proveedores;
    }

    public void setProveedores(double proveedores) {
        this.proveedores = proveedores;
    }

    public double getPtu() {
        return ptu;
    }

    public void setPtu(double ptu) {
        this.ptu = ptu;
    }

    public double getRenppanticipado() {
        return renppanticipado;
    }

    public void setRenppanticipado(double renppanticipado) {
        this.renppanticipado = renppanticipado;
    }

    public double getRentcpanticipado() {
        return rentcpanticipado;
    }

    public void setRentcpanticipado(double rentcpanticipado) {
        this.rentcpanticipado = rentcpanticipado;
    }

    public double getReservalegar() {
        return reservalegar;
    }

    public void setReservalegar(double reservalegar) {
        this.reservalegar = reservalegar;
    }

    public double getTerrenos() {
        return terrenos;
    }

    public void setTerrenos(double terrenos) {
        this.terrenos = terrenos;
    }

    public double getUtanteriores() {
        return utanteriores;
    }

    public void setUtanteriores(double utanteriores) {
        this.utanteriores = utanteriores;
    }

    public double getUtejercicio() {
        return utejercicio;
    }

    public void setUtejercicio(double utejercicio) {
        this.utejercicio = utejercicio;
    }

    public ArrayList<Double> getSaldos() {
        return saldos;
    }

    public void setSaldos(ArrayList<Double> saldos) {
        this.saldos = saldos;
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
