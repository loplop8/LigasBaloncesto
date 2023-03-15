

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Editar equipo</title>
    </head>
    <body>
        <h1>Editar equipo</h1>
        <br>
        <form method="post">
            <input type="hidden" name="id" value="${equipo.id}">
            <label>Nombre</label>
            <input type="text" name="nombre" value="${equipo.nombre}" required>
            <br>
            <label for="liga">Liga</label>
            <select name="liga" value="${liga.id}">
                <option value=""></option>
            <c:forEach items="${ligas}" var="l">
                <option value="${l.id}" <c:if test="${l.id == equipo.liga.id}">
                            selected
                        </c:if>>
                    <c:out value="${l.nombre}"/>
                </option>>    
                    </c:forEach>
                </select>
            </br>
            <input type="submit" value="Editar equipo">
        </form>
        <c:if test="${! empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
