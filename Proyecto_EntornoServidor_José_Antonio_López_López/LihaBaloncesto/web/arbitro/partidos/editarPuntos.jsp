

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
       <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Editar puntos</title>
    </head>
    <body>
        <h1>Editar puntos</h1>
        <br>
        <form method="post">
            <input type="hidden" name="id" value="${partido.id}">
            <label>Puntos equipo local</label>
            <input type="number" min="0" step="1" name="puntosLocal" value="${partido.puntosEquipoLocal}" required>
            <br>
            <label>Puntos equipo visitante</label>
            <input type="number" step="1" min="0" name="puntosVisitante" value="${partido.puntosEquipoVisitante}" required>
            <br>
            <input type="submit" value="Editar puntos">
        </form>
        <c:if test="${! empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
