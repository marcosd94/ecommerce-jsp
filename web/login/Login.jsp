<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    <style><%@include file="../css/login.css"%></style>
    
        <title>Categorias</title>
    </head>
    <body style="margin:0px">
        <%@ include file="../include/header.jsp" %>
        <div class="login">
            <tools:img path="../image/login.png"/>  
                    <form action="/onlineshop/LoginServlet">
            <input type="text" placeholder="Usuario" name="nombreUsuario">  
            <input type="password" placeholder="Contraseña" name="pass">
            <input type="submit" value="Iniciar Sesión">
            <input type="hidden" name="vaccion" value="Iniciar"/>
                    </form>
        </div>

    </body>
</html>
