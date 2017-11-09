<%-- 
    Document   : header
    Created on : 08/11/2017, 04:09:50 PM
    Author     : mrcpe
--%>

<%@page import="onlineshop.ec.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #232f3e;
    }

    li {
        float: left;
    }

    li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    /* Change the link color to #111 (black) on hover */
    li a:hover {
        background-color: #111;
    }
    .active {
        background-color: #4CAF50;
    }
    .padding-border{ margin-left:8px;
                          margin-right:8px;
    }
    table {
    border-collapse: collapse;
    width: 100%;
    }

    th, td {
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even){background-color: #f2f2f2}

    th {
        background-color: #232f3e;
        color: white;
    }
    .logoutLblPos{
        position:fixed;
        right:10px;
        top:5px;
    }
    #logout {
        
  width:120px;
  height:25px;
  display:block;
  font-weight:bold;
  color:#fff;
  text-decoration:none;
  text-transform:uppercase;
  text-align:center;
  padding-top:6px;
  margin: 7px 0 0 10px;
  cursor:pointer;
  border: none;  
  background-color: #37a69b;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  border-bottom-left-radius:5px;
    }

</style>
<ul>
    <li><a href="/onlineshop/">Inicio</a></li>
    <% Usuario usuario = (Usuario) session.getAttribute("usuario");
    if(usuario != null && usuario.getTipoUsuario() == 0) {  %>
    <li><a href="/onlineshop/UsuarioServlet">Usuarios</a></li>
    <li><a href="/onlineshop/ProductoServlet">Productos</a></li>
    <li><a href="/onlineshop/CategoriaServlet">Categorias</a></li>
         
<% }
    if(usuario != null) {  %>

    <form action="/onlineshop/LogoutServlet">
  <label class="logoutLblPos">
  <input id="logout" name="submit2" type="submit" id="submit2" value="Salir">
  </label>
</form>
<% } else {%> 
    <form action="/onlineshop/LoginServlet">
  <label class="logoutLblPos">
  <input id="logout" name="submit2" type="submit" id="submit2" value="Iniciar Sesión">
  </label>
</form>
    
<% }%> 
</ul>