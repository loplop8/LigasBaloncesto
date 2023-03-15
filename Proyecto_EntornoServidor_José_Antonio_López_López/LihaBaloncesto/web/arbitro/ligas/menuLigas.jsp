

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-11"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-11">
      <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Ligas</title>
    </head>
    <body>
        <h1>Ligas</h1>
        <table border="1">
            <tr><th>Nombre</th></tr>
                    <c:forEach items="${ligas}" var="l">
                <tr>
                    <td><c:out value="${l.nombre}"/></td>
                    <c:if test="${l.jornadas.size() > 0}">
                        <td>
                            <form action="MenuJornadas" method="post">
                                <input type="hidden" name="id" value="${l.id}">
                                <input type="submit" value="Ver jornadas">
                            </form>
                        </td>
                    </c:if>
                        
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="/LigaBaloncesto/Inicio">Volver</a>
    </body>
</html>