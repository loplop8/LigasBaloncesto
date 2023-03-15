

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
         <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Crear Usuario</title>
    </head>
    <body>
        <h1>Crear Usuario</h1>
        <br>
        <form method="post">
            <label>Nombre</label>
            <input type="text" name="nombre" value="${nombre}" required>
            <br>
            
            <label>Rol</label>
            <select name="rol">
                    <option value="admin"
                        <c:if test="${rol == 'admin'}">
                            selected
                        </c:if>>
                       Administrador
                    </option>
                    <option value="arbitro">
                       Árbitro
                    </option>
                    
            </select>
            <br>
            
            <label>Nickname</label>
            <input type="text" name="nickname" value="${nickname}" required>
            <br>
            
            <label>Contraseña</label>
            <input type="password" name="password" value="${password}" required>
            <br>
            
            <c:if test="${not empty error}">
                <div class="error">
                    ${error}
                </div>
            </c:if>
            
            <input type="submit" value="Crear">
        </form>
    </body>
</html>
