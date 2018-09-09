/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function crearUsuario() { //Crear Usuario
    //console.log("single");
    var valores = {};
    
    valores.accion ="createUser";
    valores.nombre = $("#uNombre").val();
    valores.apellido1 = $("#uApellido1").val();
    valores.apellido2 = $("#uApellido2").val();
    valores.email = $("#uEmail").val();
    valores.pass = $("#uPass").val();
    console.log(valores);
    $.ajax({
        url: 'ArticulosServlet',
        data: valores,
        type: 'post',
        dataType: 'json',
        success: function (datos) {
             location.href ="login.jsp";            
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al crear el usuario');
        },
        complete: function (xhr, status) {
            console.log('Usuario creado exitosamente');
        }
    });
}

