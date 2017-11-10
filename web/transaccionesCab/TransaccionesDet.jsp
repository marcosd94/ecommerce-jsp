<%@page import="onlineshop.ec.Producto"%>
<%@page import="onlineshop.ec.TransaccionesDet"%>
<%@page import="onlineshop.ec.TransaccionesCab"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        
        <div class="padding-border">
        <h1>Detalles de la compra</h1>
        <table>
            <tr>
                <th>Item</th>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Subtotal</th>
            </tr>


            <%
                Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
                ArrayList<TransaccionesDet> productos = (ArrayList<TransaccionesDet>) request.getAttribute("detallesCompras");
                for (TransaccionesDet c : productos) {
            %>
            <tr>
                <td><%=c.getItem()  %></td>
                
                
            <%
                ArrayList<Producto> categorias = (ArrayList<Producto>) request.getAttribute("productos");
                for (Producto cat : categorias) {
            %>          
            <% if( cat.getIdProducto()== c.getIdProducto()) {  %>
                        
                <td><%= cat.getDescripcion()%></td>
            <% } %> 
            <% } %> 
                
                
                <td><%=c.getCantidad()%></td>
                <td><%= c.getPrecio()  %></td>
                <td><%= c.getSubTotal()  %></td>
            </tr>
            <%
                }
            %>

        </table>
        <form  action="/onlineshop/TransaccionesCabServlet">
            <input style="margin: 10px" type="submit" value="Volver"/>
        </form>
        </div>
    </body>
</html>
