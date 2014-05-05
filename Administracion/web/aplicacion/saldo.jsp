<%-- 
    Document   : principal
    Created on : 25/04/2014, 02:14:50 PM
    Author     : JL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="../dist/js/bootstrap.min.js"></script>
        <noscript>
        <link rel="stylesheet" href="../dist/css/skel-noscript.css" />
        <link rel="stylesheet" href="../dist/css/style.css" />
        <link rel="stylesheet" href="../dist/css/style-desktop.css" />
        </noscript>
        <link rel="stylesheet" href="../dist/css/bootstrap.min.css" />
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
        <script type="text/javascript">
            $(document).ready(function(){
                $('#activar').click(function(){
                    if($(this).prop('checked'))
                        $('#cuentaEspecifica').attr('disabled',false);
                    else
                        $('#cuentaEspecifica').attr('disabled',true);
                });
                
                $('#btnBuscar').click(function(){
                    /*
                     *ajax({
                     success:function(a){*/
                    $('#resultadoCuenta').show();
                    //                        $('#resultadoCuenta .panel-body').text('El saldo de la cuenta #### es <b>$' + a.resultado + '</b>')
                    $('#resultadoCuenta .panel-body').text('El saldo de la cuenta #### es <b>$115,000.00</b>')
                    /*})
                     */
                    
                });
            });
        </script>
    </head>
    <body>
        <!-- ********************************************************* -->
        <div id="header-wrapper">
            <div class="container">
                <div class="row">
                    <div class="12u">
                        <header id="header">
                            <h1><a href="principal.jsp" id="logo">SisCon - Usuario</a></h1>
                            <nav id="nav">
                                <a href="catalogo.jsp" id="catalog">Catálogo de cuentas</a>
                                <a href="poliza.jsp" id="poliz">Captura de polizas</a>
                                <a href="#" id="saldo" class="current-page-item">Saldos</a>
                                <a href="estado.jsp" id="state">Generar Estados</a>
                            </nav>
                        </header>
                    </div>
                </div>
            </div>
        </div>
        <div id="main">
            <div class="container ">
                <div class="row main-row">
                    <div class="7u">
                        <section>
                            <h2>Saldos</h2>
                            <form role="form">
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
                                <button type="button" id="btnBuscar" class="btn btn-primary">Buscar</button>
                            </form>
                            <br/><br/>
                            <div class="panel panel-default" id="resultadoCuenta" style="display:none;">
                                <div class="panel-body">

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
