  <%@page import="Model.Usuario"%>
<div class="header-top">
	 <div class="wrap"> 
		<div class="logo">
			<a href="./"><img src="images/logo.png" alt=""/></a>
	    </div>
	    <div class="cssmenu">
		   <ul>
                       <li class="active"><a id="mySign" name="mySign" href="register.jsp">Sign up & Save</a></li> 
			  <li><a  id="myAcou" name="myAcou" href="login.jsp" >My Account</a></li> 
			 <li><a href="checkout.jsp">CheckOut</a></li> 
                         <li><a id="nameUser" name="nameUser"></a></li>
                         <li><a  id="myLogout" name="myLogout" href="#" onclick="logout()">Logout</a></li>
		   </ul>
		</div>
		
		<div class="clear"></div>
 	</div>
   </div>
   <div class="header-bottom">
   	<div class="wrap">
   		<!-- start header menu -->
		<ul class="megamenu skyblue">
		    <li><a class="color1" href="./">Home</a></li>
			</ul>
		   <div class="clear"></div>
     	</div>
       </div>

 <script type="text/javascript" src="js/metodosUsuarios.js"></script>
 <script type="text/javascript">
 $(document).ready(function () {
            <%
                HttpSession misession= (HttpSession) request.getSession();
                Usuario usuario = new Usuario();
                boolean existe = false;
                if(misession.getAttribute("sUser")!= null){
                    usuario = (Usuario) misession.getAttribute("sUser"); 
                    existe = true;
                }
            %>
  
            if("<%=existe%>" === "true"){    
                 $("#myAcou").hide();
                 $("#mySign").hide(); 
                 $("#nameUser").show();
                 var nombre = "<%=usuario.getNombre()%> "+"<%=usuario.getApellido1()%>";
                 $("#nameUser").text(nombre);
                 $("#myLogout").show(); 
            }
            else{
                 $("#mySign").show(); 
                 $("#myAcou").show();
                 $("#nameUser").hide();
                 $("#myLogout").hide(); 
            }
 
});


 </script>
 