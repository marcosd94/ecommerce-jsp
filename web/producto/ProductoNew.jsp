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
        <h1>Nuevo Producto</h1>
        <form action="/onlineshop/ProductoServlet">
            <table>
                <tr>
                <th  colspan="2" style="text-align: center;">
                    Datos del Producto
                </th>
                </tr>
                <tr>
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion"/></td>
                </tr>
                <tr>
                    <td>Categoria Id</td>
                   
                                    <td>
                    
                    
                    <select name="idCategoria">
                        <option value="" selected>Seleccione</option>
            <%
                ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
                for (Categoria c : categorias) {
            %>
                        <option value="<%=c.getIdCategoria()%>"><%=c.getDescripcion()%></option>
            <%
                }
            %>
                    </select>
                </td>

                    
                </tr>
                <tr>
                    <td>Precio</td>
                    <td><input type="text" name="precioUnit"/></td>
                </tr>
                <tr>
                    <td>Cantidad</td>
                    <td><input type="text" name="cantidad"/></td>
                </tr>
            </table>
            <input type="hidden" name="vaccion" value="GrabarNuevo"/>
            <input style="margin: 10px"  type="submit" value="Grabar"/>
        </form>
        </div>
    </body>
</html>
