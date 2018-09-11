$(document).ready(function () {
    listaProductos('');
    $( "#search" ).keyup(function() {
  listaProductos($( "#search" ).val());
});
});

function creaCardProducto(prod) {
    var cardHtml = '   <div class="col_1_of_3 span_1_of_3"><a href="single.jsp?idProducto='+prod.id+'">' +
            '<div class="view view-fifth">' +
            '<div class="top_box">' +
            '<h3 class="m_1">' + prod.descripcion + '</h3>' +
            '<p class="m_2">' + prod.marca + '</p>' +
            '<div class="grid_img">' +
            '<div class="css3"><img src="images/'+prod.imagen+'" alt=""/></div>' +
            '<div class="mask">' +
            '<div class="info">Quick View</div>' +
            '  </div>' +
            '</div>' +
            '<div class="price">$' + prod.precio + '</div>' +
            '		   </div>' +
            '		    </div>' +
            '		   <span class="rating">' +
            '	        <input type="radio" class="rating-input" id="rating-input-1-5" name="rating-input-1">' +
            '	        <label for="rating-input-1-5" class="rating-star1"></label>' +
            '	        <input type="radio" class="rating-input" id="rating-input-1-4" name="rating-input-1">' +
            '	        <label for="rating-input-1-4" class="rating-star1"></label>' +
            '	        <input type="radio" class="rating-input" id="rating-input-1-3" name="rating-input-1">' +
            '	        <label for="rating-input-1-3" class="rating-star1"></label>' +
            '	        <input type="radio" class="rating-input" id="rating-input-1-2" name="rating-input-1">' +
            '	        <label for="rating-input-1-2" class="rating-star"></label>' +
            '	        <input type="radio" class="rating-input" id="rating-input-1-1" name="rating-input-1">' +
            '	        <label for="rating-input-1-1" class="rating-star"></label>&nbsp;' +
            '	  (45)' +
            '     </span>' +
            '   	    <div class="clear"></div>' +
            '  	</a></div>';
    return cardHtml;
}

function listaProductos(filtro) {
    console.log("pruebas");
    $.ajax({
        url: 'ArticulosServlet',
        data: {accion: "a",filtro:filtro},
        type: 'post',
        dataType: 'json',
        success: function (datos) {
            addProds(datos.productos);
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al cargar los productos');
        },
        complete: function (xhr, status) {
            console.log('Productos cargados exitosamente');
        }
    });
}

function addProds(prodList) {
    
    var prodCards = '<div class="content group">';
    for (var key in prodList) {
        if (key % 3 === 0) {
            prodCards = prodCards.concat('<div class="content group">');
        }
        var prod = prodList[key];
        prodCards = prodCards.concat(creaCardProducto(prod));
        if ((key + 1) % 3 === 0 || parseInt(key) === (prodList.length-1)) {
            prodCards = prodCards.concat('<div class="clear"></div></div>');
        }
    }
    $("#prod-list").html(prodCards);
}

