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

                //var request = get_XmlHttp();
                var poliza = document.getElementById("poliza").value;
                var cuentaP = document.getElementById("cuentaP").value;
                var fechaDeMov = document.getElementById("fechaDeMov").value;
                var automatic = "cargo";
                if($('#automatic').is(':checked')){
                    automatic = "cargo";
                }else{
                    automatic = "abono";
                }
                var tipoCA = document.getElementById("tipoCA").value;
                var params = "poliza="+poliza+"&cuentaP="+cuentaP+"&fechaDeMov="+fechaDeMov+"&tipo="+automatic+"&pago="+tipoCA;

                var parametros = "comando=1" + "&"+ params;
                alert(parametros);
                /*request.open("POST", "../controlador", true);

                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(parametros);
                request.onreadystatechange = function()
                {

                    var ex_ajsn = request.responseText;
                    document.getElementById("carrito").innerHTML = ex_ajsn;
                }*/

            }
        </script>
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
    </head>
    <body>
        <%
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
            <div class="container ">
                <div class="row main-row">
                    <div class="12u">
                        <section>
                            <h2>Captura de poliza</h2>
                            <%@include file="../forms/altapoliza.html" %>
                            <div id="tablaPolizas">
                                <table class="table table-responsive">
                                    <thead>
                                        <tr>
                                            <th><p>No. de Poliza</p></th>
                                    <th><p>Cuenta</p></th>
                                    <th><p>Descripción</p></th>
                                    <th><p>Fecha del movimiento</p></th>
                                    <th><p>Cargos</p></th>
                                    <th><p>Abonos</p></th>
                                    <th><p>Borrar</p></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th><p>1</p></th>
                                    <th><p>1113</p></th>
                                    <th><p>Papelería y útiles</p></th>
                                    <th><p>02-03-14</p></th>
                                    <th><p>100</p></th>
                                    <th><p></p></th>
                                    <th><button class="btn btn-danger">Eliminar</button></th>
                                    </tr>
                                    <tr>
                                        <th><p>1</p></th>
                                    <th><p>1108</p></th>
                                    <th><p>IVA acreditable</p></th>
                                    <th><p>02-03-14</p></th>
                                    <th><p>10</p></th>
                                    <th><p></p></th>
                                    <th><button class="btn btn-danger">Eliminar</button></th>
                                    </tr>
                                    <tr>
                                        <th><p>1</p></th>
                                    <th><p>1102.01</p></th>
                                    <th><p>Bancomer</p></th>
                                    <th><p>02-03-14</p></th>
                                    <th><p></p></th>
                                    <th><p>110</p></th>
                                    <th><button class="btn btn-danger">Eliminar</button></th>
                                    </tr>
                                    <tr class="alert alert-danger">
                                        <th><p>2</p></th>
                                    <th><p>1113</p></th>
                                    <th><p>Papelería y útiles</p></th>
                                    <th><p>02-03-14</p></th>
                                    <th><p>100</p></th>
                                    <th><p></p></th>
                                    <th><button class="btn btn-danger">Eliminar</button></th>
                                    </tr>
                                    <tr class="alert alert-danger">
                                        <th><p>2</p></th>
                                    <th><p>1108</p></th>
                                    <th><p>IVA acreditable</p></th>
                                    <th><p>02-03-14</p></th>
                                    <th><p>10</p></th>
                                    <th><p></p></th>
                                    <th><button class="btn btn-danger">Eliminar</button></th>
                                    </tr>
                                    <tr class="alert alert-danger">
                                        <th><p>2</p></th>
                                    <th><p>1102.01</p></th>
                                    <th><p>Bancomer</p></th>
                                    <th><p>02-03-14</p></th>
                                    <th><p></p></th>
                                    <th><p>150</p></th>
                                    <th><button class="btn btn-danger">Eliminar</button></th>
                                    </tr>
                                    </tbody>
                                </table>
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

