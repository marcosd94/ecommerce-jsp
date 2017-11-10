<%@page import="java.text.SimpleDateFormat"%>
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
        <h1>Lista de Compras</h1>
        <table>
            <tr>
                <th>Fecha</th>
                <th>Direccion de envio</th>
                <th>Total</th>
                <th>Medio de Pago</th>
                <th>Usuario</th>
                <th colspan="2" style="text-align: center;">Acciones</th>
            </tr>


            <%
                Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
                ArrayList<TransaccionesCab> productos = (ArrayList<TransaccionesCab>) request.getAttribute("compras");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                for (TransaccionesCab c : productos) {
                    String fecha = formatter.format(c.getFecha());
            %>
            <tr>
                <td><%=fecha%></td>
                <td><%=c.getDireccionDeEnvio()%></td>
                <td><%=c.getTotal()%></td>
                
            <%
                String tipoMedioPago[] =  {"Efectivo","Tarjeta de Credito"};
            %>
                <td><%= tipoMedioPago[c.getIdMedioPago()]  %></td>
                <td><%= usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido()  %></td>
                <td  style="text-align: center;">
                    <form action="/onlineshop/TransaccionesCabServlet">
                        <input type="hidden" name="vaccion" value="Ver"/>
                        <input type="hidden" name="transaccionId" value="<%=c.getIdTransacción()%>"/>
                        <input type="submit" value="Ver detalles"/>
                    </form>
                </td>
            </tr>
            <%
                }
            %>

        </table>
        <form  action="/onlineshop/">
            <input style="margin: 10px" type="submit" value="Seguir comprando"/>
        </form>
        </div>
    </body>
</html>
