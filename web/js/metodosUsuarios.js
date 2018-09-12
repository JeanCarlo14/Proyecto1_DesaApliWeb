/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function logout() {
    var valores = {};
    valores.accion = "logout";

    console.log(valores);
    $.ajax({
        url: 'ArticulosServlet',
        data: valores,
        type: 'post',
        dataType: 'json',
        success: function (datos) {

            if (datos.estado) {
                location.reload();
            }
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al logearse');
        },
        complete: function (xhr, status) {
            console.log('Login exitoso');
        }
    });

}


function usuarioLogin() {
    var valores = {};

    valores.accion = "userLogin";
    valores.uEmail = $("#uEmail").val();
    valores.uPass = $("#pass").val();

    console.log(valores);
    if (validar()) {
        $.ajax({
            url: 'ArticulosServlet',
            data: valores,
            type: 'post',
            dataType: 'json',
            success: function (datos) {
                if (datos.estado) {
                    location.href = "./";
                }

            },
            error: function (xhr, status) {
                console.log('Disculpe, existió un problema al logearse');
            },
            complete: function (xhr, status) {
                console.log('Login exitoso');
            }
        });
    }

}



function crearUsuario() { //Crear Usuario
    //console.log("single");
    var valores = {};

    valores.accion = "createUser";
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
            location.href = "login.jsp";
        },
        error: function (xhr, status) {
            console.log('Disculpe, existió un problema al crear el usuario');
        },
        complete: function (xhr, status) {
            console.log('Usuario creado exitosamente');
        }
    });
}

function validar() {
    var valid = true;
    if ($("#uEmail").val() === '') {
        document.getElementById('uEmail').style.borderColor = '#FF0000';
        valid = false;
    } else {
        document.getElementById('uEmail').style.borderColor = '#000000';
    }
    if ($("#pass").val() === '') {
        document.getElementById('pass').style.borderColor = '#FF0000';
        valid = false;
    } else {
        document.getElementById('pass').style.borderColor = '#000000';
    }
    return valid;
}
function validarCU() {
    var valid = true;
    if ($("#uNombre").val() === '') {
        document.getElementById('uNombre').style.borderColor = '#FF0000';
        valid = false;
    } else {
        document.getElementById('uNombre').style.borderColor = '#000000';
    }
    if ($("#uApellido1").val() === '') {
        document.getElementById('uApellido1').style.borderColor = '#FF0000';
        valid = false;
    } else {
        document.getElementById('uApellido1').style.borderColor = '#000000';
    }
    if ($("#uApellido2").val() === '') {
        document.getElementById('uApellido2').style.borderColor = '#FF0000';
        valid = false;
    } else {
        document.getElementById('uApellido2').style.borderColor = '#000000';
    }
    if ($("#uEmail").val() === '') {
        document.getElementById('uEmail').style.borderColor = '#FF0000';
        valid = false;
    } else {
        document.getElementById('uEmail').style.borderColor = '#000000';
    }
    if ($("#uPass").val() === '') {
        document.getElementById('uPass').style.borderColor = '#FF0000';
        valid = false;
    } else {
        document.getElementById('uPass').style.borderColor = '#000000';
    }
    return valid;
}