<%@page import="java.util.ArrayList"%>
<%@page import="onlineshop.ec.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="onlineshop.ec.Producto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        <%
            Producto c = (Producto)request.getAttribute("producto");
         %>
         
        <div class="padding-border">
        <h1>Producto <%=c.getDescripcion()%></h1>
        <form action="/onlineshop/ProductoServlet">
            <table>
                <tr>
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion" value="<%=c.getDescripcion()%>" required="true"/></td>
                </tr>
                <tr>
                    <td>Categoria</td>
                    <td>
                    
                    
                    <select name="idCategoria" required="true">
            <%
                ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
                for (Categoria cat : categorias) {
            %>          
            <% if( cat.getIdCategoria() == c.getIdCategoria()) {  %>
                        <option value="<%=cat.getIdCategoria()%>" selected><%=cat.getDescripcion()%></option>
            <% } else { %>
                        <option value="<%=cat.getIdCategoria()%>"><%=cat.getDescripcion()%></option>
            <% } %>
            <%
                }
            %>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td><input type="number" name="precioUnit" value="<%=c.getPrecioUnit()%>" required="true"/></td>
                </tr>
                <tr>
                    <td>Cantidad</td>
                    <td><input type="number" name="cantidad" value="<%=c.getCantidad()%>" required="true"/></td>
                </tr>
            </table>
            <input type="hidden" name="vid" value="<%=c.getIdProducto()%>"/>    
            <input type="hidden" name="vaccion" value="GrabarModificado"/>
            <input style="margin: 10px" type="submit" value="Grabar"/>
        </form>
        </div>
    </body>
</html>
