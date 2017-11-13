<%-- 
    Document   : header
    Created on : 08/11/2017, 04:09:50 PM
    Author     : mrcpe
--%>

<%@page import="onlineshop.ec.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style><%@include file="../css/style.css"%></style>
<ul>
    <li><a href="/onlineshop/">Inicio</a></li>
    <% Usuario usuario = (Usuario) session.getAttribute("usuario");
    if(usuario != null && usuario.getTipoUsuario() == 0) {  %>
    <li><a href="/onlineshop/UsuarioServlet">Usuarios</a></li>
    <li><a href="/onlineshop/ProductoServlet">Productos</a></li>
    <li><a href="/onlineshop/CategoriaServlet">Categorias</a></li>
         
<% }
    if(usuario != null) {  %>
    <li><a href="/onlineshop/TransaccionesCabServlet">Ver Compras</a></li>

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