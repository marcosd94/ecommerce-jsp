<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        <div class="padding-border">
        <h1>Nuevo Usuario</h1>
        <form action="/onlineshop/UsuarioServlet">
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" required="true"/></td>
                </tr>
                <tr>
                    <td>Apellido</td>
                    <td><input type="text" name="apellido" required="true"/></td>
                </tr>
                <tr>
                    <td>Login Name</td>
                    <td><input type="text" name="loginName" required="true"/></td>
                </tr>
                <tr>
                    <td>Contraseña</td>
                    <td><input type="text" name="contrasenha" required="true"/></td>
                </tr>
                <tr>
                    <td>Tipo Usuario</td>
                    <td>
                    
                    
                    <select name="tipoUsuario"  required="true">
                        <option value="" selected>Seleccione</option>
                        <option value="1" >Cliente</option>
                        <option value="0" >Administrador</option>
                    </select>
                </td>
                </tr>
            </table>
            <input type="hidden" name="vaccion" value="GrabarNuevo"/>
            <input style="margin: 10px" type="submit" value="Grabar"/>
        </form>
        </div>
    </body>
</html>
