

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-11"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-11">
 <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Equipos</title>
    </head>
    <body>
        <h1>Equipos</h1>
        <table border="1">
            <tr>
                <th>Nombre Equipo</th>
                <th>Liga</th>
            </tr>
            <c:forEach items="${equipos}" var="e">
                <tr>
                    <td><c:out value="${e.nombre}"/></td>
                    <td><c:out value="${e.liga.nombre}"/></td>
                    <td>
                        <form action="EditarEquipo" method="post">
                            <input type="hidden" name="id" value="${e.id}">
                            <input type="submit" value="Editar">
                        </form>
                    </td>
                    
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="CrearEquipo">Crear Nuevo Equipo</a>
        <br>
        <br>
        <a href="/LigaBaloncesto/Inicio">Volver</a>
    </body>
</html>
