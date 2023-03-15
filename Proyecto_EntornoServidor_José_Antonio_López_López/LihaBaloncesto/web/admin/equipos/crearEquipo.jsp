

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Crear Equipo</title>
    </head>
    <body>
        <h1>Crear Equipo</h1>
        <br>
        <form method="post">
            <label>Nombre</label>
            <input type="text" name="nombre" value="${nombre}" required>
            <br>            
            <label>Liga</label>
            <select name="idLiga">
                <option value=""></option>
                <c:forEach var="l" items="${ligas}">
                    <c:if test="${l.jornadas.size()==0}">
                        
                    <option value="${l.id}" 
                        <c:if test="${l.id == idLiga}">
                            selected
                        </c:if>
                        >
                        <c:out value="${l.nombre}"/>
                    </option>
                    </c:if>
                </c:forEach>
            </select>
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
