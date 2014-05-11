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
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="login" method="post" onSubmit="return valida()" role="form">
                <h2 class="form-signin-heading">Inicia sesi&oacute;n</h2>
                <input type="text" id="uName" name="uName" class="form-control" placeholder="Nombre de usuario" required autofocus>
                <input type="password" id="uPass" name="uPass" class="form-control" placeholder="Contrase&ntilde;a" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
                <button class="btn btn-lg btn-primary btn-block" type="button">Registrar</button>
            </form>
            <% if (session.getAttribute("Error") != null && Boolean.valueOf(session.getAttribute("Error").toString())) { 
                session.removeAttribute("Error");
            %>
            <div class="alert alert-warning">
                <p class="lead">El usuario y/o contraseña son incorrectos</p>
            </div>
            <% }%>
        </div> <!-- /container -->
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>
        <script src="dist/js/docs.min.js"></script>
        <script src="dist/js/login.js"></script>
        <!-- Placed at the end of the document so the pages load faster -->
    </body>
</html>

