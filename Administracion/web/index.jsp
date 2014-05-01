<%-- 
    Document   : index
    Created on : 15/04/2014, 11:58:52 PM
    Author     : JL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- <link rel="shortcut icon" href="../../assets/ico/favicon.ico">  -->

        <title>SisCon - Login</title>

        <!-- Bootstrap core CSS -->
        <link href="dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="dist/css/signin.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy this line! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="aplicacion/principal.jsp" role="form">
                <h2 class="form-signin-heading">Inicia sesi&oacute;n</h2>
                <input type="text" class="form-control" placeholder="Nombre de usuario" required autofocus>
                <input type="password" class="form-control" placeholder="Contrase&ntilde;a" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            </form>
        </div> <!-- /container -->
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
    </body>
</html>

