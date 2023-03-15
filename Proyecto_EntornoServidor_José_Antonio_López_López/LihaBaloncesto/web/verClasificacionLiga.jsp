

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="bundle.mensajes" var="mensajes" scope="application"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title><fmt:message key="Clasificacion" bundle="${mensajes}"/></title>
    </head>
    <body>
        <h1><fmt:message key="Clasificacion" bundle="${mensajes}"/></h1>
        <table border="1">
            <tr>
                
                
                <th><fmt:message key="Posicion" bundle="${mensajes}"/></th>
                
                <th><fmt:message key="Nombre" bundle="${mensajes}"/></th>
                <th><fmt:message key="Puntostotales" bundle="${mensajes}"/></th>
                <th><fmt:message key="Partidosjugados" bundle="${mensajes}"/></th>
                <th><fmt:message key="Partidosganados" bundle="${mensajes}"/></th>
                <th><fmt:message key="Partidosperdidos" bundle="${mensajes}"/></th>
                <th><fmt:message key="Puntospartidosfavorables" bundle="${mensajes}"/></th>
                <th><fmt:message key="Puntospartidosencontra" bundle="${mensajes}"/></th>
                <th><fmt:message key="Diferencialdepuntos" bundle="${mensajes}"/></th>
            </tr>
            <c:forEach items="${equiposClasificacion}" varStatus="status" var="e">
                <tr>
                    <td>${status.getIndex() + 1}</td>
                    <td><c:out value="${e.nombreEquipo}"/></td>
                    <td><c:out value="${e.puntosTotales}"/></td>
                    <td><c:out value="${e.partidosJugados}"/></td>
                    <td><c:out value="${e.partidosGanados}"/></td>
                    <td><c:out value="${e.partidosPerdidos}"/></td>
                    <td><c:out value="${e.puntosPartidosFavorables}"/></td>
                    <td><c:out value="${e.puntosPartidosEnContra}"/></td>
                    <td><c:out value="${e.diferenciaPuntosPartidos}"/></td>


                    <td>
                        <form action="VerDetalleEquipo" method="post">
                            <input type="hidden" name="id" value="${e.idEquipo}">
                            <input type="submit" value= <fmt:message key="Verequipo" bundle="${mensajes}"/>>
                        </form>
                    </td>


                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="/LigaBaloncesto/Inicio"><fmt:message key="Volver" bundle="${mensajes}"/></a>
    </body>
</html>