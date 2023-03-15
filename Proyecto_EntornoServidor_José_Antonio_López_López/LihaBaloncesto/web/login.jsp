
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Inicio de Sesi�n</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <h1>Inicio de sesi�n</h1>
        <form method="post">
            <label>Usuario</label>
            <input type="text" name="login" value="${login}" required>
            <br>
            <label>Contrase�a</label>
            <input type="password" name="password" value="${password}" required>
            <br>
            <input type="submit" value="Iniciar Sesi�n">
        </form>
        <br>
        <c:if test="${not empty error}">
            <div class="error">
                ${error}
            </div>
        </c:if>
        <a href="/LigaBaloncesto/Inicio">Volver</a>
    </body>
</html>
