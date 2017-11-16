<%-- 
    Document   : Categoria
    Created on : 21/10/2014, 07:55:38 PM
    Author     : Mauricio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        
        <div class="padding-border">
        <h1>Nueva Categoria</h1>
        <form action="/onlineshop/CategoriaServlet">
            <table>
                <tr>
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion" required="true"/></td>
                </tr>
            </table>
            <input type="hidden" name="vaccion" value="GrabarNuevo"/>
            <input style="margin: 10px" type="submit" value="Grabar"/>
        </form>
        </div>
    </body>
</html>
