jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
			});
                        
                        var defaults = {
		  			containerID: 'toTop', // fading element id
					containerHoverID: 'toTopHover', // fading element hover id
					scrollSpeed: 1200,
					easingType: 'linear' 
		 		};
				
				
				$().UItoTop({ easingType: 'easeOutQuart' });
				
cargarCarrito();		
});
function cargarTabla(item){
var fila = '<tr>'+
'<td data-th="'+item.descripcion+'">'+
'<div class="row">'+
'<div class="col-sm-2 hidden-xs"><img src="images/'+item.imagen+'" alt="..." class="img-responsive"/></div>'+
'<div class="col-sm-10">'+
'<h4 class="nomargin">'+item.descripcion+'</h4>'+
'<p>'+item.descripcion+'</p>'+
'</div>'+
'</div>'+
'</td>'+
'<td data-th="Price">'+item.precio+'</td>'+
'<td data-th="Quantity">'+
'<input type="number" class="form-control text-center" value="'+item.cantidad+'">'+
'</td>'+
'<td data-th="Subtotal" class="text-center">'+item.precio+'</td>'+
'<td class="actions" data-th="">'+
'<button class="btn btn-info btn-sm"><i class="fa fa-refresh"></i></button>'+
'<button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>'+								
'</td>'+
'</tr>';
return fila;
}


function cargarCarrito() { //agrega item al carrito
    //console.log("single");
    var valores = {};
    valores.accion ="d";
    valores.idCarrito = 1; //si se mete en la session se tiene q quitar deaqui y meterlo desde el servlet
       console.log(valores);
    $.ajax({
        url: 'ArticulosServlet',
        data: valores,
        type: 'post',
        dataType: 'json',
        success: function (datos) {
           var tabla ='';
         $.each(datos.ItemsCarrito, function( index, value ) {
            tabla+= cargarTabla(datos.ItemsCarrito[index]);
  
});
$("#carrito").html(tabla);   
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al guardar');
        },
        complete: function (xhr, status) {
            console.log('Item agregado exitosamente');
        }
    });
}
