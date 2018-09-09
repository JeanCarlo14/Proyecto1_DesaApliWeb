$(document).ready(function () {
    listaProductos();
});

function creaCardProducto(prod) {
    var cardHtml = '   <div class="col_1_of_3 span_1_of_3"><a href="single.jsp">' +
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
            '			 <ul class="list">' +
            '			  <li>' +
            '			  	<img src="images/plus.png" alt=""/>' +
            '			  	<ul class="icon1 sub-icon1 profile_img">' +
            '				  <li><a class="active-icon c1" href="single.jsp?idProducto='+prod.id+'">Add To Bag </a>' +
            '					<ul class="sub-icon1 list">' +
            '						<li><h3>sed diam nonummy</h3><a href=""></a></li>' +
            '						<li><p>Lorem ipsum dolor sit amet, consectetuer  <a href="">adipiscing elit, sed diam</a></p></li>' +
            '					</ul>' +
            '				  </li>' +
            '				 </ul>' +
            '			   </li>' +
            '		     </ul>' +
            '   	    <div class="clear"></div>' +
            '  	</a></div>';
    return cardHtml;
}

function listaProductos() {
    console.log("pruebas");
    $.ajax({
        url: 'ArticulosServlet',
        data: {accion: "a"},
        type: 'post',
        dataType: 'json',
        success: function (datos) {
            addProds(datos.productos);
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al cargar los productos');
        },
        complete: function (xhr, status) {
            console.log('Ptroductos cargados exitosamente');
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
        if ((key + 1) % 3 === 0 || key == (prodList.length-1)) {
            prodCards = prodCards.concat('<div class="clear"></div></div>');
        }
    }
    $("#prod-list").html(prodCards);
}

