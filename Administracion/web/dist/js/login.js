function valida() {
    var envio = true;
    var usuario = document.getElementById("uName").value;
    var pass = document.getElementById("uPass").value;
    if (usuario.length < 6 || usuario.length > 15) {
        $('#uName').tooltip({placement: 'right', title: "Tu usuario debe de estar entre 6-15 letras"});
        $('#uName').tooltip('show');
        envio = false;
    }
    if (pass.length < 6 || pass.length > 16) {
        $('#uPass').tooltip({placement: 'right', title: "Tu contraseña debe de estar entre 6-16 letras"});
        $('#uPass').tooltip('show');
        envio = false;
    }
    return envio;
}