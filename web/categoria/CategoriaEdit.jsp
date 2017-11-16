<%-- 
    Document   : Categoria
    Created on : 21/10/2014, 07:55:38 PM
    Author     : Mauricio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="onlineshop.ec.Categoria"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        <%
            Categoria c = (Categoria)request.getAttribute("categoria");
         %>
         
        <div class="padding-border">
        <h1>Categoria <%=c.getDescripcion()%></h1>
        <form action="/onlineshop/CategoriaServlet">
            <table>
                <tr>
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion" value="<%=c.getDescripcion()%>" required="true"/></td>
                </tr>
            </table>
            <input type="hidden" name="vid" value="<%=c.getIdCategoria()%>"/>    
            <input type="hidden" name="vaccion" value="GrabarModificado"/>
            <input type="submit" style="margin: 10px" value="Grabar"/>
        </form>
        </div>
    </body>
</html>
