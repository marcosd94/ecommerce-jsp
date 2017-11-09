<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList, onlineshop.ec.Categoria"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        <div class="padding-border">
        <h1>Lista de Categorias</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th colspan="2" style="text-align: center;">Acciones</th>
            </tr>

            <%
                ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
                for (Categoria c : categorias) {
            %>
            <tr>
                <td><%=c.getIdCategoria()%></td>
                <td><%=c.getDescripcion()%></td>
                <td>
                    <form action="/onlineshop/CategoriaServlet">
                        <input type="hidden" name="vaccion" value="Editar"/>
                        <input type="hidden" name="vid" value="<%=c.getIdCategoria()%>"/>
                        <input type="submit" value="Editar"/>
                    </form>
                </td>

                <td>
                    <form action="/onlineshop/CategoriaServlet">
                        <input type="hidden" name="vaccion" value="Eliminar"/>
                        <input type="hidden" name="vid" value="<%=c.getIdCategoria()%>"/>
                        <input type="submit" value="Eliminar"/>
                    </form>
                </td>
            </tr>
            <%
                }
            %>

        </table>
        <form action="/onlineshop/categoria/CategoriaNew.jsp">
            <input style="margin: 10px" type="submit" value="Crear"/>
        </form>
        </div>
    </body>
</html>
