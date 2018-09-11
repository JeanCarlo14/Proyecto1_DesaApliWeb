<%-- 
    Document   : register.jsp
    Created on : 09-sep-2018, 9:31:06
    Author     : Jean Carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/principal.js"></script>
        <!-- start menu -->     
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
        <!-- end menu -->
        <!-- top scrolling -->
        <script type="text/javascript" src="js/move-top.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>
        <script type="text/javascript" src="js/metodosUsuarios.js"></script>
        <title>Register</title>
    </head>
    <body>
       <%@ include file="menu.jsp" %> 

       <div class="register_account">
          	<div class="wrap">
    	      <h4 class="title">Create an Account</h4>
    		   <form action="login.jsp" method="post">
    			 <div class="col_1_of_2 span_1_of_2">
		   			<div><input id="uNombre" name="uNombre" type="text" value="Name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Name';}"></div>
		    			<div><input id="uApellido1" name="uApellido1" type="text" value="First Last Name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'First Last Name';}"></div>
		    			<div><input id="uApellido2" name="uApellido2" type="text" value="Second Last Name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Second Last Name';}"></div>
                                        <div><input id="uEmail" name="uEmail" type="text" value="E-Mail" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'E-Mail';}"></div>
		    			<div><input id="uPass" name="uPass" type="text" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}"></div>
		    	 
                         <button class="grey" onclick="crearUsuario()">Create</button>
	         
                         </div>
                          <div class="clear"></div>
		    </form>
    	  </div> 
        </div>
     
       <%@ include file="footer.jsp" %> 
       <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>
    </body>
</html>
