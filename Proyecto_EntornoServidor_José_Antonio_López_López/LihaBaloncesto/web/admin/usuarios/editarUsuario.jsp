

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editar usuario</title>
    </head>
    <body>
        <h1>Editar usuario</h1>
        <br>
        <form method="post">
            <input type="hidden" name="id" value="${usuario.id}">
            <label>Nombre</label>
            <input type="text" name="nombre" value="${usuario.nombre}" required>
            <br>
            
            <label>Rol</label>
            <select name="rol">
                    <option value="admin"
                        <c:if test="${usuario.rol == 'admin'}">
                            selected
                        </c:if>>
                       Aministrador
                    </option>
                    <option value="arbitro"
                            <c:if test="${usuario.rol == 'arbitro'}">
                            selected
                        </c:if>
                            >
                       Árbitro
                    </option>
            </select>
            <br>
            
            <label>Nickname</label>
            <input type="text" name="nickname" value="${usuario.nickname}" required>
            <br>
            
            <label>Contraseña</label>
            <input type="password" name="password" value="${usuario.password}" required>
            <br>
            
            <input type="submit" value="Editar usuario">
        </form>
        <c:if test="${! empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
