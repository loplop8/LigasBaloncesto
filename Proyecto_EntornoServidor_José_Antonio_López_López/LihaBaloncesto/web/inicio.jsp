

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="css/style.css">
        <title>Página de Inicio</title>
        
    </head>
    <body>
        <c:if test="${usuario != null}">
            <div>
                <c:out value="${usuario.nombre}"/>
                <a href="CerrarSesion">Cerrar Sesión</a>
            </div>
        </c:if>
        <c:if test="${usuario == null}">
            <div>
                <a href="Login">Iniciar Sesión</a>
            </div>
        </c:if>

        <h1>Ligas Baloncesto</h1>
        <br>
        <c:if test="${usuario.rol == 'admin'}">
            <a href="admin/MenuUsuarios">Gestionar Usuarios</a>
            <br>
            <a href="admin/MenuLigas">Gestionar Ligas</a>
            <br>
            <a href="admin/MenuEquipos">Gestionar Equipos</a>
            <br>
        </c:if>

        <c:if test="${usuario.rol == 'arbitro'}">
            <a href="arbitro/MenuLigas" > Consultar ligas y modificación de jornadas</a>
            <br>
        </c:if>
            
            
        <h3>Ligas</h3>
        <table >
            <tr><th>Nombre</th></tr>
                    <c:forEach items="${ligas}" var="l">
                <tr>
                    <td><c:out value="${l.nombre}"/></td>
                    <td>
                        <form action="VerClasificacionLiga" method="post">
                            <input type="hidden" name="id" value="${l.id}">
                            <input type="submit" value="Ver clasificación">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
