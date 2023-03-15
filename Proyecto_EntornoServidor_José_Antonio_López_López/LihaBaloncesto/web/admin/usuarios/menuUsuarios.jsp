

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-11"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-11">
 <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Usuarios</title>
    </head>
    <body>
        <h1>Usuarios</h1>
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Rol</th>
                <th>Nickname</th>
            </tr>
            <c:forEach items="${usuarios}" var="u">
                <tr>
                    <td><c:out value="${u.nombre}"/></td>
                     <td><c:out value="${u.rol}"/></td>
                     <td><c:out value="${u.nickname}"/></td>
                    <td>
                        <form action="EditarUsuario" method="post">
                            <input type="hidden" name="id" value="${u.id}">
                            <input type="submit" value="Editar">
                        </form>
                    </td>
                    <td>
                        <form action="BorrarUsuario" method="post">
                            <input type="hidden" name="id" value="${u.id}">
                            <input type="submit" value="Borrar">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="CrearUsuario">Crear Nuevo Usuario</a>
        <br>
        <br>
        <a href="/LigaBaloncesto/Inicio">Volver</a>
    </body>
</html>
