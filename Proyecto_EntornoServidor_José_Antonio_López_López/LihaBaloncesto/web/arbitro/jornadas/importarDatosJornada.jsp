

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Importar datos jornada</title>
    </head>
    <body>
        <h1>Importar datos jornada</h1>
        <br>
        <form action="ActualizarDatosJornada" method="post" enctype="multipart/form-data">
            <label>Fichero</label>
            <input type="file" name="fichero">
            <br>
            <input type="submit" value="Importar datos">
        </form>
        <c:if test="${! empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
