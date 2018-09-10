<%-- 
    Document   : checkout
    Created on : 02/09/2018, 10:19:13 AM
    Author     : nacho
--%>

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
        <script type="text/javascript" src="js/payment.js"></script>
        <script language="javascript">

            function datosUsuario() {
            <%
                int idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
                String idUsuario = (String) session.getAttribute("idUsuario");
                if (idUsuario == null) {
                    idUsuario = "hshhs";
                }
            %>
                var idUsuario = "<%=idUsuario%>";
                cargarTarjetas(idUsuario);
            }

        </script>

    </head>
    <body onload="datosUsuario()">
        <%@ include file="menu.jsp" %> 
        <div class="login">
            <!--<div class="wrap">
                <h4 class="title">Shopping cart is empty</h4>
                <p class="cart">You have no items in your shopping cart.<br>Click<a href="index.html"> here</a> to continue shopping</p>
              </div>-->
            <!-- tabla boostrap de carrito -->   
            <div class="container">
                <h1>Payment</h1>
                <table id="payment" class="table table-hover table-condensed">
                    <tbody>
                        <tr>
                            <td>Card:</td>
                            <td>
                                <select name="card" id="card" class="form-control" value="">
                                    <option></option>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td><a href="billing.jsp?idCarrito=<%= idCarrito%>" class="btn btn-warning"><i class="fa fa-angle-left"></i> Billing Info</a></td>
                            <td><a href="#" class="btn btn-success pull-right" data-toggle="modal" data-target="#myModal" onClick="completePurchase(<%= idCarrito%>)">Complete Payment <i class="fa fa-angle-right"></i></a></td>
                        </tr>
                    </tfoot>
                </table>
            </div>

        </div>
        <%@ include file="footer.jsp" %> 
        <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>  
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <!--button type="button" class="close" data-dismiss="modal">&times;</button-->
                        <h4 id="modalMessageTitle" class="modal-title"></h4>
                    </div>
                    <div id="modalMessage" class="modal-body">

                    </div>
                    <div class="modal-footer">
                        <button href="index.jsp" type="button" class="btn btn-success btn-block" data-dismiss="modal">OK!</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
