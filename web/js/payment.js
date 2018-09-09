jQuery(document).ready(function ($) {
    $(".scroll").click(function (event) {
        event.preventDefault();
        $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
    });

    var defaults = {
        containerID: 'toTop', // fading element id
        containerHoverID: 'toTopHover', // fading element hover id
        scrollSpeed: 1200,
        easingType: 'linear'
    };


    $().UItoTop({easingType: 'easeOutQuart'});

    cargarTarjetas();
});


function cargarTarjetas() {
    var valores = {};
    valores.accion = "l";
    valores.idUsuario = 'hshhs'; 
    console.log(valores);
    $.ajax({
        url: 'ArticulosServlet',
        data: valores,
        type: 'post',
        dataType: 'json',
        success: function (datos) {
            console.log(datos);
            var tabla = '';
            $.each(datos.tarjetas, function (index, value) {
                tabla += '<option>' + value.numero + '</option>';
                console.log(value.numero);
            });
            $("#card").html(tabla);
        },
        error: function (xhr, status) {
            console.log(status);
            console.log('Disculpe, existió un problema al recoger informacion de las tarjetas');
        },
        complete: function (xhr, status) {
            console.log('No hay mas detalles');
        }
    });
}