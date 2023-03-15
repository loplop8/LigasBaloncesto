
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Editar liga</title>
    </head>
    <body>
        <h1>Editar liga</h1>
        <br>
        <form method="post">
            <input type="hidden" name="id" value="${liga.id}">
            <label>Nombre</label>
            <input type="text" name="nombre" value="${liga.nombre}" required>
            <br>
            
            <input type="submit" value="Editar liga">
        </form>
        <c:if test="${! empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
