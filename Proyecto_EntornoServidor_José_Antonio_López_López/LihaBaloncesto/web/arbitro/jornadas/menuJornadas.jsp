
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Jornadas</title>
    </head>
    <body>
        <h1>${liga.nombre}</h1>
        <table border="1">
            <tr>
                <th>Número de Jornada</th>
                <th>Fecha</th>
            </tr>
            <c:forEach items="${jornadas}" var="j">
                <tr>
                    <td><c:out value="${j.numero}"/></td>
                    
                    
                    <td>
                    <fmt:formatDate value="${j.fecha}" pattern="dd-MM-yyyy"/>    
                    </td>
                    
                    <td>
                        <form action="MenuPartidos" method="post">
                            <input type="hidden" name="id" value="${j.id}">
                            <input type="submit" value="Ver partidos">
                        </form>
                    </td>
                    <td>
                        <form action="ImportarDatosJornada" method="post">
                            <input type="hidden" name="id" value="${j.id}">
                            <input type="submit" value="Importar resultados jornada">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="/LigaBaloncesto/arbitro/MenuLigas">Volver</a>
    </body>
</html>