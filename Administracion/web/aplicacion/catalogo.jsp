<%-- 
    Document   : principal
    Created on : 25/04/2014, 02:14:50 PM
    Author     : JL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="modelo.AccesBD"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.SubCuenta"%>
<%@page import="modelo.Cuenta"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="modelo.BeanCuentas"%>
<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Crea una instancia de BeanProducto --%>
<jsp:useBean id="cuentasE" scope="session" class="modelo.BeanCuentas" />
<html lang="es">
    <head>
        <title>SisCon - Usuario</title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <link href="http://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet">
        <script src="../dist/js/jquery.min.js"></script>
        <script src="../dist/js/config.js"></script>
        <script src="../dist/js/skel.min.js"></script>
        <script src="../dist/js/skel-panels.min.js"></script>
        <noscript>
        <link rel="stylesheet" href="../dist/css/skel-noscript.css" />
        <link rel="stylesheet" href="../dist/css/style.css" />
        <link rel="stylesheet" href="../dist/css/style-desktop.css" />
        </noscript>
        <link rel="stylesheet" href="../dist/css/bootstrap.min.css" />
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
    </head>
    <body onload="">
        <%
            DecimalFormat df = new DecimalFormat("###,###,###,###.00");
            BeanCuentas cuenta = (BeanCuentas) session.getAttribute("cuentasE");
            Map<Integer, Cuenta> map = cuenta.getCuenta();
            Iterator<Map.Entry<Integer, Cuenta>> it = map.entrySet().iterator();
        %>
        <!--    Navbar (Menú barra azul)     -->
        <%@include file="layouts/navbar.html" %>
        <!-- ********************************************************* -->
        <div id="main">
            <div class="container">
                <div class="row main-row">
                    <div class="12u">
                        <section>
                            <h2>Cátalogo de cuentas de: </h2>
                            <div class="panel-group" id="accordion">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                                Alta de cuentas
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseOne" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <form class="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="cuentaN" class="col-sm-2 control-label">Cuenta</label>
                                                    <div class="col-sm-10">
                                                        <select id="cuentaN" class="form-control">
                                                            <%
                                                                Connection con = new AccesBD().conexion();
                                                                PreparedStatement ps = con.prepareStatement("SELECT idcuenta,descripcion FROM cuenta ORDER BY descripcion");
                                                                ps.execute();
                                                                ResultSet rs = ps.getResultSet();
                                                                while (rs.next()) {
                                                                    if (!map.containsKey(rs.getInt("idcuenta"))) {
                                                            %>
                                                            <option value="<%= rs.getString("idcuenta")%>"><%= rs.getString("descripcion")%></option>
                                                            <% }
                                                                }
                                                                con.close(); %>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="saldo" class="col-sm-2 control-label">Saldo inicial</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="saldo" placeholder="Tu saldo" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-sm-10">
                                                        <button type="submit" class="btn btn-success">Enviar</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                                Alta de subcuenta
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseTwo" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <form class="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="cuentaSubN" class="col-sm-2 control-label">Cuenta</label>
                                                    <div class="col-sm-10">
                                                        <select id="cuentaSubN" class="form-control">
                                                            <%
                                                                con = new AccesBD().conexion();
                                                                ps = con.prepareStatement("SELECT idCuentaC,descripcion FROM cuentas WHERE idEmpresaC LIKE 1 AND idSubCuenta IS null ORDER BY descripcion");
                                                                ps.execute();
                                                                rs = ps.getResultSet();
                                                                while (rs.next()) {
                                                            %>
                                                            <option value="<%= rs.getString("idcuentaC")%>"><%= rs.getString("idcuentaC") + " - " + rs.getString("descripcion")%></option>
                                                            <% }
                                                                con.close();%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="idSub" class="col-sm-2 control-label">ID SubCuenta</label>
                                                    <div class="col-sm-10">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <input id="automatic" type="checkbox" checked/>Calcular Automático
                                                            </span>
                                                            <input id="idSub" type="text" class="form-control" placeholder="ID Consecutivo 1,2,3,...">
                                                        </div><!-- /input-group -->
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="descripcionSub" class="col-sm-2 control-label">Descripción</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="descripcionSub" placeholder="Descripción de la subcuenta" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="saldoSub" class="col-sm-2 control-label">Saldo inicial</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="saldoSub" placeholder="Tu saldo inicial de subcuenta" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-sm-10">
                                                        <button type="submit" class="btn btn-success">Enviar</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                                Baja de cuentas
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseThree" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <form class="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="bajaCuenta" class="col-sm-2 control-label">Cuenta</label>
                                                    <div class="col-sm-10">
                                                        <select id="bajaCuenta" class="form-control">
                                                            <%
                                                                con = new AccesBD().conexion();
                                                                ps = con.prepareStatement("SELECT idCuentaC,idSubCuenta, descripcion, descripcionSub FROM cuentas WHERE idEmpresaC LIKE 1 ORDER BY idCuentaC");
                                                                ps.execute();
                                                                rs = ps.getResultSet();
                                                                while (rs.next()) {
                                                                    if (rs.getString("idSubCuenta") == null || rs.getString("idSubCuenta") == "null") {
                                                            %>
                                                            <option value="<%= rs.getString("idcuentaC")%>"><%= rs.getString("idcuentaC") + " - " + rs.getString("descripcion")%></option>
                                                            <% } else {%>
                                                            <option value="<%= rs.getString("idcuentaC")%>.<%= rs.getString("idSubCuenta")%>"><%= rs.getString("idcuentaC") + "." + rs.getString("idSubCuenta") + " - " + rs.getString("descripcionSub")%></option>
                                                            <% }
                                                                }
                                                                con.close();%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-sm-10">
                                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <ul class="nav nav-pills">
                                <li class="active"><a href="#Activos" data-toggle="tab">Activos</a></li>
                                <li><a href="#Pasivos" data-toggle="tab">Pasivos</a></li>
                                <li><a href="#Capital" data-toggle="tab">Capital Contable</a></li>
                                <li><a href="#Complementaria" data-toggle="tab">Complementarias de activo</a></li>
                                <li><a href="#Deudoras" data-toggle="tab">Resultados Deudoras</a></li>
                                <li><a href="#Acreedoras" data-toggle="tab">Resultados Acreedoras</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane  fade in active" id="Activos"><%@include file="../forms/activos.html" %></div>
                                <div class="tab-pane fade" id="Pasivos"><%@include file="../forms/pasivos.html" %></div>
                                <div class="tab-pane fade" id="Capital"><%@include file="../forms/capital.html" %></div>
                                <div class="tab-pane fade" id="Complementaria"><%@include file="../forms/complementaria.html" %></div>
                                <div class="tab-pane fade" id="Deudoras"><%@include file="../forms/deudoras.html" %></div>
                                <div class="tab-pane fade" id="Acreedoras"><%@include file="../forms/acreedoras.html" %></div>
                            </div>
                            <form action="cuentas" method="post" class=""
                    </section>
                </div>
            </div>
        </div>
    </div>
    <div id="footer-wrapper">
        <div class="container">
            <div class="row">
                <div class="12u">
                    <div id="copyright">
                        &copy; Sistema Contable (SisCon). All rights reserved.
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ********************************************************* -->
    <!-- script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script -->
    <script src="../dist/js/bootstrap.min.js"></script>
    <script src="../dist/js/docs.min.js"></script>

</body>
</html>

