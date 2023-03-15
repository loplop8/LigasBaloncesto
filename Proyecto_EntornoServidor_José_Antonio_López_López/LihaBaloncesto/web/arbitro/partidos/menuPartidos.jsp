

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Partidos</title>
    </head>
    <body>
        <h1>Jornada ${jornada.numero}</h1>
        <p>Fecha: <fmt:formatDate value="${jornada.fecha}" pattern="dd-MM-yyyy"/></p>
        <table border="1">
            <tr>
                <th>Equipo Local</th>
                <th>Equipo Visitante</th>
                <th>Puntos Equipo Local</th>
                <th>Puntos Equipo Visitante</th>
            </tr>
            <c:forEach items="${partidos}" var="p">
                <tr>
                    <td><c:out value="${p.equipoLocal.nombre}"/></td>
                    <td><c:out value="${p.equipoVisitante.nombre}"/></td>
                    <td>
                        <c:if test="${p.puntosEquipoVisitante == 0 && p.puntosEquipoLocal == 0}">
                            Por determinar
                        </c:if>
                        <c:if test="${p.puntosEquipoVisitante != 0 || p.puntosEquipoLocal != 0}">
                            <c:out value="${p.puntosEquipoLocal}"/>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${p.puntosEquipoVisitante == 0 && p.puntosEquipoLocal == 0}">
                            Por determinar
                        </c:if>
                        <c:if test="${p.puntosEquipoVisitante != 0 || p.puntosEquipoLocal != 0}">
                           <c:out value="${p.puntosEquipoVisitante}"/>
                        </c:if>
                    </td>
                    <td>
                        <form action="EditarPuntos" method="post">
                            <input type="hidden" name="id" value="${p.id}">
                            <input type="submit" value="Modificar puntos">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="/LigaBaloncesto/arbitro/MenuJornadas">Volver</a>
    </body>
</html>