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
});
function cargarTabla(item) {
    var fila = '<tr>' +
            '<input id="idItem" name="idItem" type="hidden" value="' + item.id + '">' +
            '<td data-th="' + item.descripcion + '">' +
            '<div class="row">' +
            '<div class="col-sm-2 hidden-xs"><img src="images/' + item.imagen + '" alt="..." class="img-responsive"/></div>' +
            '<div class="col-sm-10">' +
            '<h4 class="nomargin">' + item.descripcion + '</h4>' +
            '<p>' + item.descripcion + '</p>' +
            '</div>' +
            '</div>' +
            '</td>' +
            '<td data-th="Price">' + item.precio + '</td>' +
            '<td data-th="Quantity">' + item.cantidad + '</td>' +
            '<td data-th="Subtotal" class="text-center">' + item.subtotal + '</td>' +
            '</tr>';
    return fila;
}


function cargarCarrito(id) {
    var valores = {};
    valores.accion = "d";
    valores.idCarrito = id;
    console.log(valores);
    $.ajax({
        url: 'ArticulosServlet',
        data: valores,
        type: 'post',
        dataType: 'json',
        success: function (datos) {
            var tabla = '';
            $.each(datos.ItemsCarrito, function (index, value) {
                tabla += cargarTabla(datos.ItemsCarrito[index]);
            });
            $("#carrito").html(tabla);

            $("#totalCarrito").html(datos.total);
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al guardar');
        },
        complete: function (xhr, status) {
            console.log('Item agregado exitosamente');
        }
    });
}