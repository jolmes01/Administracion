<%-- 
    Document   : principal
    Created on : 25/04/2014, 02:14:50 PM
    Author     : JL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="es">
    <head>
        <title>SisCon - <%= session.getAttribute("Usuario")%></title>
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
        <script language="javascript" type="text/javascript">
            function ResultadoIntegral(url) {
                //window.frames['marco-desplegado'].location = url;
                window.open(url)
            }
            function resizeIframe(obj) {
                obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
            }
        </script>
    </head>
    <body>
        <!--    Navbar (Menú barra azul)     -->
        <%@include file="layouts/navbar.html" %>
        <!-- ********************************************************* -->
        <div id="main">
            <div class="container ">
                <div class="row main-row">
                    <div class="12u">
                        <section>
                            <h2>Reportes de Resultado Integral y Estado de Situación Financiera</h2>
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    Reportes
                                </div>
                                <div id="polizaPanel" class="panel-collapse">
                                    <div id="contenidoImpreso" class="panel-body">
                                        <p class="lead"><strong>¿Necesitas el reporte de tu empresa?</strong> No te preocupes solo da click al botón
                                            del reporte que quieres y se abrira una pestaña con tu reporte generado</p>
                                        <button class="btn btn-primary" onclick="ResultadoIntegral('../resultadoIntegral')">Reporte de Resultado Integral</button>
                                        <button class="btn btn-info">Estado de Situación Financiera</button>
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
        <script src="../dist/js/bootstrap.min.js"></script>
    </body>
</html>