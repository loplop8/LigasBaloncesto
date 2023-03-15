

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
         <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Iniciar Liga</title>
    </head>
    <body>
        <h1>Iniciar Liga ${liga.nombre}</h1>
        <br>
        <form method="post">
            <input type="hidden" name="id" value="${liga.id}">
            <label>Fecha Inicio</label>
            <input type="date" name="fechaInicio" value="${fechaInicio}" required>
            <br>
            
            <c:if test="${not empty error}">
                <div class="error">
                    ${error}
                </div>
            </c:if>
            
            <input type="submit" value="Iniciar">
        </form>
    </body>
</html>
