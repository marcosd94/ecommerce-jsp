<%@page import="onlineshop.ec.ProductosCargados"%>
<%@page import="onlineshop.ec.Categoria"%>
<%@page import="onlineshop.ec.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList, onlineshop.ec.Producto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head >
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
                    <th>Precio por Unidad</th>
                    <th>Seleccionar</th>
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
                    <td>
                    <form action="/onlineshop/TiendaServlet">
                                        <input type="hidden" name="vaccion" value="Cargar"/>                                        
                                        <input type="hidden" name="cantidad" value="1"/>
                                        <input type="hidden" name="descripcion" value="<%=c.getDescripcion()%>"/>
                                        <input type="hidden" name="idCategoria" value="<%=c.getIdCategoria()%>"/>
                                        <input type="hidden" name="precioUnit" value="<%=c.getPrecioUnit()%>"/>
                                        <input type="hidden" name="idProducto" value="<%=c.getIdProducto()%>"/>
                                        <input type="submit" value="Cargar"/>
                    </form>
                    </td>
                </tr>
                <%
                    }
                %>

            </table>
                <h1>Productos seleccionados</h1>
                
            <form action="/onlineshop/TiendaServlet">
            <table>
                <tr>
                    <th>Id</th>
                    <th>Descripcion</th>
                    <th>Categoria</th>
                    <th>Cantidad</th>
                    <th>Precio por Unidad</th>
                    <th>Eliminar</th>
                </tr>

                <%
                    ArrayList<ProductosCargados> productosCargados = (ArrayList<ProductosCargados>) request.getAttribute("productosCargados");
                    request.setAttribute("data", productosCargados);
                    session.setAttribute( "data", productosCargados );
                    for (ProductosCargados c : productosCargados) {
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
                    <td>
                            <input type="hidden" name="vaccion" value="Eliminar"/>
                            <input type="hidden" name="idProducto" value="<%=c.getIdProducto()%>"/>
                            <input type="submit" value="Eliminar"/>
                    </td>
                </tr>
                <%
                    }
                %>

            </table>
            </form>
            <form action="/onlineshop/TiendaServlet">
                <input type="hidden" name="vaccion" value="Comprar"/>
                <% if( productosCargados.size() > 0 ) {  %>
                <input style="margin: 10px" type="submit" value="Comprar"/>
                
                <%
                    }else{
                %>
                <input style="margin: 10px" type="submit" value="Comprar" disabled/>
                <%
                    }
                %>
            </form>
        </div>
    </body>
</html>