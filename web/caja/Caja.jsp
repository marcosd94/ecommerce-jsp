<%@page import="onlineshop.ec.ProductosCargados"%>
<%@page import="onlineshop.ec.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head style="margin:0px">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <%@ include file="../include/header.jsp" %>
        <div class="padding-border">
        <h1>Confirmar pedido</h1>
        </table>
                <h1>Productos seleccionados</h1>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Descripcion</th>
                    <th>Categoria</th>
                    <th>Cantidad</th>
                    <th>Precio por Unidad</th>
                </tr>

                <%
                    ArrayList<ProductosCargados> productosCargados = (ArrayList<ProductosCargados>) session.getAttribute("productosCargados");
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
                </tr>
                <%
                    }
                %>

        </table>
             
        <h1>Datos del cobro</h1>   
        <form action="/onlineshop/CajaServlet">
            <table>
                <tr>
                <th  colspan="2" style="text-align: center;">
                    Datos del cliente
                </th>
                <th   style="background: white; color: white">
                    Datos del cliente
                </th>
                <th   style="background: white; color: white">
                    Datos del cliente
                </th>
                <th   style="background: white; color: white">
                    Datos del cliente
                </th>
                <th   style="background: white; color: white">
                    Datos del cliente
                </th>
                </tr>
                <tr>
                    <td>Medio de Pago</td>
                   
                    <td>
                    
                    
                    <select name="idMedio">
                        <option value="" selected>Seleccione</option>
                        <option value="1" >Tarjeta de Crédito</option>
                        <option value="2" >Giros</option>
                        <option value="3" >Cuenta Bancaria</option>
                    </select>
                </td>

                    
                </tr>
                <tr>
                    <td>Dato del medio de pago</td>
                    <td><input type="text" name="datosMedio"/></td>
                </tr>
                <tr>
                    <td>Dirección de Envio</td>
                    <td><input type="text" name="direccion"/></td>
                </tr>
            </table>
            <input type="hidden" name="vaccion" value="Pagar"/>
            <input style="margin: 10px"  type="submit" value="Grabar"/>
        </form>
        </div>
    </body>
</html>
