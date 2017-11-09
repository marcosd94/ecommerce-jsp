<%@page import="onlineshop.ec.Categoria"%>
<%@page import="onlineshop.ec.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList, onlineshop.ec.Producto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        
        <div class="padding-border">
        <h1>Lista de Productos</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th colspan="2" style="text-align: center;">Acciones</th>
            </tr>

            <%
                ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
                for (Producto c : productos) {
            %>
            <tr>
                <td><%=c.getIdProducto()%></td>
                <td><%=c.getDescripcion()%></td>
                
            <%
                ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
                for (Categoria cat : categorias) {
            %>          
            <% if( cat.getIdCategoria() == c.getIdCategoria()) {  %>
                        
                <td><%= cat.getDescripcion()%></td>
            <% } %> 
            <% } %> 
                <td><%=c.getCantidad()%></td>
                <td><%=c.getPrecioUnit()%></td>
                <td  style="text-align: center;">
                    <form action="/onlineshop/ProductoServlet">
                        <input type="hidden" name="vaccion" value="Editar"/>
                        <input type="hidden" name="vid" value="<%=c.getIdProducto()%>"/>
                        <input type="submit" value="Editar"/>
                    </form>
                </td>

                <td style="text-align: center;">
                    <form action="/onlineshop/ProductoServlet">
                        <input type="hidden" name="vaccion" value="Eliminar"/>
                        <input type="hidden" name="vid" value="<%=c.getIdProducto()%>"/>
                        <input type="submit" value="Eliminar"/>
                    </form>
                </td>
            </tr>
            <%
                }
            %>

        </table>
        <form  action="/onlineshop/ProductoServlet">
            <input type="hidden" name="vaccion" value="Crear"/>
            <input style="margin: 10px" type="submit" value="Crear"/>
        </form>
        </div>
    </body>
</html>
