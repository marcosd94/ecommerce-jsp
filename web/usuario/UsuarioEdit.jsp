<%@page contentType="text/html" pageEncoding="UTF-8" import="onlineshop.ec.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        <%
            Usuario c = (Usuario)request.getAttribute("usuario");
         %>
        <div class="padding-border">
        <h1>Usuario <%=c.getNombre()%>  <%=c.getApellido()%></h1>
        <form action="/onlineshop/UsuarioServlet">
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value="<%=c.getNombre()%>" required="true"/></td>
                </tr>
                <tr>
                    <td>Apellido</td>
                    <td><input type="text" name="apellido" value="<%=c.getApellido()%>" required="true"/></td>
                </tr>
                <tr>
                    <td>Login Name</td>
                    <td><input type="text" name="loginName" value="<%=c.getLoginName()%>" required="true"/></td>
                </tr>
                <tr>
                    <td>Contraseña</td>
                    <td><input type="text" name="contrasenha" value="<%=c.getContrasenha()%>" required="true"/></td>
                </tr>
                <tr>
                    <td>Tipo Usuario</td>
                    <td>
                    
                    <select name="tipoUsuario"  required="true">
            <%
            String tipoUsuario[] =  {"Administrador","Cliente"};
               for (int i=0; i<(tipoUsuario.length); i++ ) {
                   if(i == c.getTipoUsuario()){ %>
                        <option value="<%=c.getTipoUsuario()%>" selected><%=tipoUsuario[c.getTipoUsuario()]%></option>
                        
                   <% } else { %>                  
                   
                        <option value="i" ><%=tipoUsuario[i]%></option>
            <% }
                }
            %>
                    </select>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="vid" value="<%=c.getIdUsuario()%>"/>    
            <input type="hidden" name="vaccion" value="GrabarModificado"/>
            <input style="margin: 10px" type="submit" value="Grabar"/>
        </form>
        </div>
    </body>
</html>
