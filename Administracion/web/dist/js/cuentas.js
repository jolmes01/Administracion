$(document).ready(function() {
    $('#saldoText').tooltip({placement: 'right', title: "No es necesario registrarlo"});
    $('#saldoText').tooltip('hide');
    $('#saldoSub').tooltip({placement: 'right', title: "No es necesario registrarlo"});
    $('#saldoSub').tooltip('hide');
    if ($('#ModalResp')) {
        $('#ModalResp').modal('show');
    }
});
function consecutivo() {
    if ($('#automatic').is(':checked')) {
        document.getElementById("idSub").setAttribute('disabled', 'true');
    } else {
        document.getElementById("idSub").removeAttribute('disabled');
    }

}