<%-- 
    Document   : principal
    Created on : 25/04/2014, 02:14:50 PM
    Author     : JL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
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
        <script src="../dist/js/bootstrap.min.js"></script>
        <noscript>
        <link rel="stylesheet" href="../dist/css/skel-noscript.css" />
        <link rel="stylesheet" href="../dist/css/style.css" />
        <link rel="stylesheet" href="../dist/css/style-desktop.css" />
        </noscript>
        <link rel="stylesheet" href="../dist/css/bootstrap.min.css" />
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
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
            function consulta()
            {
                $('#resultadoCuenta').hide();
                var envio = true;
                var request = get_XmlHttp();
                var cuenta = document.getElementById("cuenta").value;
                var cuentaE = document.getElementById("cuentaEspecifica").value;
                $('#cuenta').tooltip('destroy');
                $('#cuentaEspecifica').tooltip('destroy');
                if (cuenta == "") {
                    $('#cuenta').tooltip({placement: 'right', title: "Debes ingresar el número de la cuenta"});
                    $('#cuenta').tooltip('show');
                    envio = false;
                }
                if ($('#activar').is(':checked') && (cuentaE == "" || isNaN(cuentaE))) {
                    $('#cuentaEspecifica').tooltip({placement: 'right', title: "Debes ingresar el número de la cuenta"});
                    $('#cuentaEspecifica').tooltip('show');
                    envio = false;
                }
                if (envio) {
                    var params = "cuenta=" + cuenta;
                    if (cuentaE != "") {
                        params += "&cuentaE=" + cuentaE;
                    }
                    var parametros = "submit=consultaS" + "&" + params;
                    request.open("POST", "./cuentas", true);

                    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    request.send(parametros);
                    request.onreadystatechange = function()
                    {
                        var ex_ajsn = request.responseText;
                        $('#resultadoCuenta').show();
                        document.getElementById("contenido").innerHTML = ex_ajsn;
                    }
                }
            }
            $(document).ready(function() {
                $('#activar').click(function() {
                    if ($(this).prop('checked')) {
                        $('#cuentaEspecifica').attr('disabled', false);
                    }
                    else {
                        $('#cuentaEspecifica').attr('disabled', true);
                    }
                });
            });
        </script>
    </head>
    <body>
        <!-- ********************************************************* -->
        <%@include file="layouts/navbar.html" %>
        <div id="main">
            <div class="container ">
                <div class="row main-row">
                    <div class="12u">
                        <section>
                            <h2>Saldos</h2>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="cuenta">Ingrese número de la cuenta general:</label>
                                        <input class="form-control" id="cuenta" placeholder="Cuenta">
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" id="activar"> Búsqueda por cuenta específica
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label for="cuentaEspecifica">Ingrese número de la cuenta Específica:</label>
                                        <input  class="form-control" id="cuentaEspecifica" placeholder="Cuenta específica" disabled="true">
                                    </div>
                                    <button type="button" id="btnBuscar" onclick="consulta()" class="btn btn-primary">Buscar</button>
                                </div>
                                <div class="col-md-6">
                                    <div class="panel panel-default" id="resultadoCuenta" style="display:none;">
                                        <div class="panel-body" id="contenido">
                                        </div>
                                    </div>
                                </div>
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
    </body>
</html>