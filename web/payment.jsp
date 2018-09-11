<%-- 
    Document   : checkout
    Created on : 02/09/2018, 10:19:13 AM
    Author     : nacho
--%>

<%@page import="Model.Carrito"%>
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
              String idUsuario = null;
              int idCarrito = 0;
              if (session.getAttribute("carrito") != null) {
                  idCarrito = ((Carrito) session.getAttribute("carrito")).getId();
                  idUsuario = ((Carrito) session.getAttribute("carrito")).getUsuario().getEmail();
              } 
              
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
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Existing Card</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">+ Other Card</a>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
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
                        </table>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <table id="payment" class="table table-hover table-condensed">
                            <tbody>
                                <tr>
                                    <td>Card Number:</td>
                                    <td>
                                        <input id="cardnumber" type="number" name="cardnumber" maxlength="16" class="form-control" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Expiration Date:</td>
                                    <td>
                                        <select name="month" id="month" class="form-control" value="">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                        </select>/<select name="year" id="year" class="form-control" value="">
                                            <option>2018</option>
                                            <option>2019</option>
                                            <option>2020</option>
                                            <option>2021</option>
                                            <option>2022</option>
                                            <option>2023</option>
                                            <option>2024</option>
                                            <option>2025</option>
                                            <option>2026</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>CCV:</td>
                                    <td>
                                        <input id="ccv" type="number" name="ccv" maxlength="4" class="form-control" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <table class="table table-hover table-condensed">
                        <tbody>
                            <tr>
                                <td><a href="billing.jsp?idCarrito=<%= idCarrito%>" class="btn btn-warning"><i class="fa fa-angle-left"></i> Billing Info</a></td>
                                <td><a href="#" class="btn btn-success pull-right" data-toggle="modal" data-target="#myModal" onClick="completePurchase(<%= idCarrito%>)">Complete Payment <i class="fa fa-angle-right"></i></a></td>
                            </tr>
                        </tbody>
                    </table>           
                </div>
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
