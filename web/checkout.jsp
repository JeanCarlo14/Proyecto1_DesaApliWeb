<%-- 
    Document   : checkout
    Created on : 02/09/2018, 10:19:13 AM
    Author     : nacho
--%>

<%@page import="Model.Carrito"%>
<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Checkout</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <!-- start menu -->     
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () {
        $(".megamenu").megamenu();
    });</script>
        <!-- end menu -->
        <!-- top scrolling -->
        <script type="text/javascript" src="js/move-top.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>
        <script type="text/javascript" src="js/checkout.js"></script>
        <script language="javascript">

            function datosCarrito() {
            <%
              int idCarrito;
              if (session.getAttribute("carrito") != null) {
                  idCarrito = ((Carrito) session.getAttribute("carrito")).getId();
              } else {
                  idCarrito = 1;
              }
            %>
                var idCarrito = <%= idCarrito%>;
                console.log(idCarrito);
                cargarCarrito(idCarrito);
            }

        </script>

    </head>
    <body onload="datosCarrito()">
        <%@ include file="menu.jsp" %> 
        <div class="login">
            <!--<div class="wrap">
                <h4 class="title">Shopping cart is empty</h4>
                <p class="cart">You have no items in your shopping cart.<br>Click<a href="index.html"> here</a> to continue shopping</p>
              </div>-->
            <!-- tabla boostrap de carrito -->   
            <div class="container">
                <input type="hidden" id="idCarrito" value="<%= idCarrito%>" >
                <table id="cart" class="table table-hover table-condensed">
                    <thead>
                        <tr>
                            <th style="width:50%">Product</th>
                            <th style="width:10%">Price</th>
                            <th style="width:8%">Quantity</th>
                            <th style="width:22%" class="text-center">Subtotal</th>
                            <th style="width:10%"></th>
                        </tr>
                    </thead>
                    <tbody id='carrito'>

                    </tbody>
                    <tfoot>
                        <tr class="visible-xs">
                            <td colspan='2'></td>
                            <td class="text-center" ><strong>Total:</strong></td>
                            <td class="text-center" ><strong id="totalCarrito"></strong></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a href="index.jsp" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
                            <td class="visible-xs" colspan="3"></td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs text-center" ><strong>Total:</strong></td>
                            <td class="hidden-xs text-center" ><strong id="totalCarrito1"></strong></td>
                            <td><a href="billing.jsp?idCarrito=<%= idCarrito%>" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
                        </tr>
                    </tfoot>
                </table>
            </div>

        </div>
        <%@ include file="footer.jsp" %> 
        <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>       
    </body>
</html>
