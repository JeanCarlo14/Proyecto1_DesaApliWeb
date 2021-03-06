<%-- 
    Document   : single
    Created on : 02/09/2018, 11:01:20 AM
    Author     : nacho
--%>

<%@page import="Model.Carrito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <title>OPticas</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/form.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/single.js"></script>
        <script language="javascript">

            function datosPagina() {
            <%
                int idCarrito;
                if (session.getAttribute("carrito") != null) {
                    idCarrito = ((Carrito) session.getAttribute("carrito")).getId();
                } else {
                    idCarrito = 1;
                }
            %>

            <%int idProducto = Integer.parseInt(request.getParameter("idProducto"));%>
                var idProducto = "<%=idProducto%>";
                cargarProducto(idProducto);
            }

        </script> 

        <!-- start menu -->     
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () {
        $(".megamenu").megamenu();
    });</script>
        <!-- end menu -->
        <script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
        <script type="text/javascript" id="sourcecode">
$(function ()
{
$('.scroll-pane').jScrollPane();
});
        </script>



        <!----details-product-slider--->
        <!-- Include the Etalage files -->
        <link rel="stylesheet" href="css/etalage.css">
        <script src="js/jquery.etalage.min.js"></script>
        <!-- Include the Etalage files -->

        <!----//details-product-slider--->	
        <!-- top scrolling -->
        <script type="text/javascript" src="js/move-top.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>

    </head>
    <body onload="datosPagina()">
        <%@ include file="menu.jsp" %> 
        <div class="single">
            <input id="idProducto" name="idProducto" type="hidden" value="<%=idProducto%>">
            <div class="wrap">
                <div class="cont span_2_of_3">
                    <div class="labout span_1_of_a1">
                        <!-- start product_slider -->
                        <ul id="etalage">
                            <li>
                                <a href="optionallink.html">
                                    <img class="etalage_source_image" src="" id="imagenProducto" />
                                </a>
                            </li>

                        </ul>


                        <!-- end product_slider -->
                    </div>
                    <div class="cont1 span_2_of_a1">
                        <h3 class="m_3" id="nombreProducto"></h3>

                        <div class="price_single">
                            <span class="actual" id="precioProducto"></span><a href="#">click for offer</a>
                        </div>
                        <ul class="options">
                            <h4 class="m_9">Select a Size</h4>
                            <li><a href="#">6</a></li>
                            <li><a href="#">7</a></li>
                            <li><a href="#">8</a></li>
                            <li><a href="#">9</a></li>
                            <div class="clear"></div>
                        </ul>
                        <div class="btn_form">

                            <button onclick="agregarItem(<%= idCarrito%>)">Buy Now</button> 
                        </div>
                        <ul class="add-to-links">
                            <li><img src="images/wish.png" alt=""/><a href="#">Add to wishlist</a></li>
                        </ul>
                        <p class="m_desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>

                        <div class="social_single">	
                            <ul>	
                                <li class="fb"><a href="#"><span> </span></a></li>
                                <li class="tw"><a href="#"><span> </span></a></li>
                                <li class="g_plus"><a href="#"><span> </span></a></li>
                                <li class="rss"><a href="#"><span> </span></a></li>		
                            </ul>
                        </div>
                    </div>
                    <div class="clear"></div>


                    <ul id="flexiselDemo3">
                        <li><img src="images/pic11.jpg" /><div class="grid-flex"><a href="#">Bloch</a><p>Rs 850</p></div></li>
                        <li><img src="images/pic10.jpg" /><div class="grid-flex"><a href="#">Capzio</a><p>Rs 850</p></div></li>
                        <li><img src="images/pic9.jpg" /><div class="grid-flex"><a href="#">Zumba</a><p>Rs 850</p></div></li>
                        <li><img src="images/pic8.jpg" /><div class="grid-flex"><a href="#">Bloch</a><p>Rs 850</p></div></li>
                        <li><img src="images/pic7.jpg" /><div class="grid-flex"><a href="#">Capzio</a><p>Rs 850</p></div></li>
                    </ul>

                    <script type="text/javascript" src="js/jquery.flexisel.js"></script>
                    <div class="toogle">
                        <h3 class="m_3">Product Details</h3>
                        <p class="m_text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum.</p>
                    </div>					
                    <div class="toogle">
                        <h3 class="m_3">Product Reviews</h3>
                        <p class="m_text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum.</p>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <%@ include file="footer.jsp" %> 
        <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>
    </body>

</html>
