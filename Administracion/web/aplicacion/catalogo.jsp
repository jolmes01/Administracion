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
        <title>SisCon - <%= session.getAttribute("Usuario") %></title>
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
            ArrayList<Cuenta> cuentasList = new ArrayList<Cuenta>();
            while (it.hasNext()) {
                Cuenta c = it.next().getValue();
                cuentasList.add(c);
            }
        %>
        <!--    Navbar (Menú barra azul)     -->
        <%@include file="layouts/navbar.html" %>
        <!-- ********************************************************* -->
        <div id="main">
            <div class="container">
                <div class="row main-row">
                    <div class="12u">
                        <section>
                            <% if(Integer.parseInt(session.getAttribute("Tipo").toString()) == 1){ %>
                            <p class="h2">Aquí puedes dar de alta y baja tus cuentas <small>(Solo da click al recuadro para dar de alta o baja)</small></p>
                            <%@include file="../forms/cuentasControl.html" %>
                            <% } %>
                            <p class="h2">Este es tu catálogo de cuentas <small><%= session.getAttribute("nEmpresa") %></small></p>
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
        <!-- CONTROL DE ALERTS -->
        <%
            String mensajeR = "";
            String detalles = "";
            boolean mostrar = false;
            if (session.getAttribute("Respuesta") != null) {
                mostrar = true;
                boolean respuesta = Boolean.parseBoolean(session.getAttribute("Respuesta").toString());
                session.removeAttribute("Respuesta");
                mensajeR = respuesta ? "Acción Correcta :¬D" : "Hubo un problema D:";
                detalles = respuesta ? "Se acaba de agregar la cuenta a tu catálogo" : "Se presento un falló en tu conexión a internet. Intenta más tarde :'(";
            } else if (session.getAttribute("RespuestaE") != null) {
                mostrar = true;
                boolean respuesta = Boolean.parseBoolean(session.getAttribute("RespuestaE").toString());
                session.removeAttribute("RespuestaE");
                mensajeR = respuesta ? "Acción Correcta :¬D" : "Hubo un problema D:";
                detalles = respuesta ? "Se acaba de borrar la cuenta de tu catalogo" : "Se presento un falló en tu conexión a internet. Intenta más tarde :'(";
            }
            if (mostrar) {
        %>
        <div class="modal fade" id="ModalResp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title"><%= mensajeR%></h4>
                    </div>
                    <div class="modal-body">
                        <p class="lead"><%= detalles%></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" data-dismiss="modal">Aceptar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <% } %>
        <!-- ********************************************************* -->
        <!-- script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script -->
        <script src="../dist/js/bootstrap.min.js"></script>
        <script src="../dist/js/docs.min.js"></script>
        <script src="../dist/js/cuentas.js"></script>

    </body>
</html>