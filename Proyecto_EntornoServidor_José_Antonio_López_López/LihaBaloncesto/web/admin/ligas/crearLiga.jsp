

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Crear Liga</title>
    </head>
    <body>
        <h1>Crear Liga</h1>
        <br>
        <form method="post">
            <label>Nombre</label>
            <input type="text" name="nombre" value="${nombre}" required>
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
