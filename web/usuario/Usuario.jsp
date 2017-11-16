<%-- 
    Document   : Categoria
    Created on : 21/10/2014, 07:55:38 PM
    Author     : Mauricio
--%>

<%@page import="onlineshop.ec.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList, onlineshop.ec.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        <div class="padding-border">
        <h1>Lista de Usuarios</h1>
        <table>
            <tr>
                <th>Id Usuario</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Login Name</th>
                <th>Contraseña</th>
                <th>Tipo Usuario</th>
                <th colspan="2" style="text-align: center;">Acciones</th>
            </tr>

            <%
                ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
                for (Usuario c : usuarios) {
            %>
            <tr>
                <td><%=c.getIdUsuario()%></td>
                <td><%=c.getNombre()%></td>
                <td><%=c.getApellido()%></td>
                <td><%=c.getLoginName()%></td>
                <td><%=c.getContrasenha()%></td>
                
            <%
            String tipoUsuario[] =  {"Administrador","Cliente"};
               for (int i=0; i<(tipoUsuario.length); i++ ) {
                   if(i == c.getTipoUsuario()){ %>
                        <td><%=tipoUsuario[c.getTipoUsuario()]%></td>
            <% }
                }
            %>
                <td>
                    <form action="/onlineshop/UsuarioServlet">
                        <input type="hidden" name="vaccion" value="Editar"/>
                        <input type="hidden" name="vid" value="<%=c.getIdUsuario()%>"/>
                        <input type="submit" value="Editar"/>
                    </form>
                </td>

                <td>
                    <form action="/onlineshop/UsuarioServlet">
                        <input type="hidden" name="vaccion" value="Eliminar"/>
                        <input type="hidden" name="vid" value="<%=c.getIdUsuario()%>"/>
                        <input type="submit" value="Eliminar"/>
                    </form>
                </td>
            </tr>
            <%
                }
            %>

        </table>
        <form action="/onlineshop/usuario/UsuarioNew.jsp">
            <input style="margin: 10px" type="submit" value="Crear"/>
        </form>
        </div>
    </body>
</html>
