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
            '<td data-th="Quantity">' +
            '<input type="number" id="cantidadItem' + item.id + '" class="form-control text-center" value="' + item.cantidad + '">' +
            '</td>' +
            '<td data-th="Subtotal" class="text-center">' + item.subtotal + '</td>' +
            '<td class="actions" data-th="">' +
            '<button class="btn btn-info btn-sm" onclick="actualizarItem(' + item.id + ')"><i class="fa fa-refresh"></i></button>' +
            '<button class="btn btn-danger btn-sm" onclick="eliminarItem()"><i class="fa fa-trash-o"></i></button>' +
            '</td>' +
            '</tr>';
    return fila;
}


function cargarCarrito() { //agrega item al carrito
//console.log("single");
    var valores = {};
    valores.accion = "d";
    valores.idCarrito = $("#idCarrito").val(); //si se mete en la session se tiene q quitar deaqui y meterlo desde el servlet
    console.log(valores);
    $.ajax({
        url: 'ArticulosServlet',
        data: valores,
        type: 'post',
        dataType: 'json',
        success: function (datos) {

            if (datos.ItemsCarrito.length == 0) {
                $('#nextbtn').addClass('disabled');
            }
            var tabla = '';
            $.each(datos.ItemsCarrito, function (index, value) {
                tabla += cargarTabla(datos.ItemsCarrito[index]);
            });
            $("#carrito").html(tabla);
            $("#totalCarrito").html(datos.total);
            $("#totalCarrito1").html(datos.total);
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al guardar');
        },
        complete: function (xhr, status) {
            console.log('Item agregado exitosamente');
        }
    });
}

function eliminarItem() { //agrega item al carrito
//console.log("single");
    var valores = {};
    valores.accion = "e";
    valores.idItem = $("#idItem").val(); //si se mete en la session se tiene q quitar deaqui y meterlo desde el servlet
    console.log(valores);
    $.ajax({
        url: 'ArticulosServlet',
        data: valores,
        type: 'post',
        dataType: 'json',
        success: function (datos) {
            if (datos.estado)
                cargarCarrito();
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al eliminar');
        },
        complete: function (xhr, status) {
            console.log('Item eliminado exitosamente');
        }
    });
}



function actualizarItem(id) { //agrega item al carrito
//console.log("single");
    var valores = {};
    valores.accion = "f";
    valores.idItem = id; //si se mete en la session se tiene q quitar deaqui y meterlo desde el servlet
    var cantidad = "#cantidadItem" + id;
    console.log(cantidad);
    valores.cantidad = $(cantidad).val();
    console.log(valores);
    $.ajax({
        url: 'ArticulosServlet',
        data: valores,
        type: 'post',
        dataType: 'json',
        success: function (datos) {
            if (datos.estado)
                cargarCarrito();
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al actualizar');
        },
        complete: function (xhr, status) {
            console.log('Item actualizado exitosamente');
        }
    });
}