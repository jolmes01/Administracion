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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="polizaE" scope="session" class="modelo.BeanPoliza" />
<!DOCTYPE HTML>
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
        <script language="javascript" type="text/javascript">

            function get_XmlHttp()
            {
                // Crea la variable que contendrá la instancia del objeto  XMLHttpRequest (inicialmente con un valor nulo)
                var xmlHttp = null;

                if (window.XMLHttpRequest)
                {       // para Forefox, IE7+, Opera, Safari, ...
                    xmlHttp = new XMLHttpRequest();
                }
                else if (window.ActiveXObject)
                {  // para Internet Explorer 5 or 6
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }

                return xmlHttp;
            }

            function agregar()
            {
                var request = get_XmlHttp();
                var envio = true;
                var poliza = document.getElementById("poliza").value;
                var cuentaP = document.getElementById("cuentaP").value;
                var fechaDeMov = document.getElementById("fechaDeMov").value;
                var automatic = "cargo";
                if ($('#automatic').is(':checked')) {
                    automatic = "cargo";
                } else {
                    automatic = "abono";
                }
                var tipoCA = document.getElementById("tipoCA").value;
                var params = "poliza=" + poliza + "&cuentaP=" + cuentaP + "&fechaDeMov=" + fechaDeMov + "&tipo=" + automatic + "&pago=" + tipoCA;
                var mensajeTipo = "";
                $('#poliza').tooltip('destroy');
                $('#fechaDeMov').tooltip('destroy');
                $('#tipoCA').tooltip('destroy');
                if (poliza == "") {
                    $('#poliza').tooltip({placement: 'right', title: "Debes seleccionar un dato"});
                    $('#poliza').tooltip('show');
                    envio = false;
                }
                if (fechaDeMov == "") {
                    $('#fechaDeMov').tooltip({placement: 'right', title: "Debes establecer una fecha"});
                    $('#fechaDeMov').tooltip('show');
                    envio = false;
                }
                if (tipoCA == "") {
                    mensajeTipo = "Debes poner el monto a registrar";
                    $('#tipoCA').tooltip({placement: 'right', title: mensajeTipo});
                    $('#tipoCA').tooltip('show');
                    envio = false;
                }
                if (isNaN(tipoCA)) {
                    mensajeTipo = "Deben der ser solo números";
                    $('#tipoCA').tooltip({placement: 'right', title: mensajeTipo});
                    $('#tipoCA').tooltip('show');
                    envio = false;
                }
                var parametros = "comando=1" + "&" + params;
                if (envio) {
                    request.open("POST", "../poliza", true);
                    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    request.send(parametros);
                    request.onreadystatechange = function()
                    {
                        var ex_ajsn = request.responseText;
                        document.getElementById("tablaPolizas").innerHTML = ex_ajsn;
                    }
                }
            }

            function cargar() {
                var request = get_XmlHttp();

                var parametros = "comando=0";
                request.open("POST", "../poliza", true);

                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(parametros);
                request.onreadystatechange = function()
                {
                    var ex_ajsn = request.responseText;
                    document.getElementById("tablaPolizas").innerHTML = ex_ajsn;
                }
            }

            function registrarPoliza() {
                var request = get_XmlHttp();

                var parametros = "comando=3";
                request.open("POST", "../poliza", true);

                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(parametros);
                request.onreadystatechange = function()
                {
                    var ex_ajsn = request.responseText;
                    document.getElementById("tablaPolizas").innerHTML = ex_ajsn;
                }
            }

            function eliminar(vItem) {
                var request = get_XmlHttp();
                var pItem = "item=" + vItem;

                var parametros = "comando=2" + "&" + pItem;
                request.open("POST", "../poliza", true);

                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(parametros);
                request.onreadystatechange = function()
                {

                    var ex_ajsn = request.responseText;
                    document.getElementById("tablaPolizas").innerHTML = ex_ajsn;
                }
            }
        </script>
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
    </head>
    <body onload="cargar()">
        <%
            BeanCuentas cuenta = (BeanCuentas) session.getAttribute("cuentasE");
            if (cuenta == null) {
                cuenta = new BeanCuentas();
                session.setAttribute("cuentasE", cuenta);
            }
            Map<Integer, Cuenta> map = cuenta.getCuentasSeparadas();
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
            <div class="container ">
                <div class="row main-row">
                    <div class="12u">
                        <section>
                            <h2>Captura de poliza</h2>
                            <%@include file="../forms/altapoliza.html" %>
                            <div id="tablaPolizas">
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
        <!-- ********************************************************* -->
        <script src="../dist/js/bootstrap.min.js"></script>
    </body>
</html>