

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="bundle.mensajes" var="mensajes" scope="application"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://cdn.jsdelivr.net/npm/chart.js@latest/dist/Chart.min.js"></script>
         <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Detalles del equipo ()</title>
    </head>
    <body>
        <h1>${equipo.nombre} <fmt:message key="Detallesdelequipo" bundle="${mensajes}"/></h1>
        
        <table border="1">
            <thead>
            <tr>
                
                
                <th><fmt:message key="Jornada" bundle="${mensajes}"/></th>
                <th><fmt:message key="Fechajornada" bundle="${mensajes}"/></th>
                <th><fmt:message key="EquipoLocal" bundle="${mensajes}"/></th>
                <th><fmt:message key="EquipoVisitante" bundle="${mensajes}"/></th>
                <th><fmt:message key="PuntosEquipoLocal" bundle="${mensajes}"/></th>
                <th><fmt:message key="PuntosEquipoVisitante" bundle="${mensajes}"/></th>
                <th><fmt:message key="Diferencialdepuntos" bundle="${mensajes}"/></th>
            </tr>    
            </thead>
            <tbody>
            <c:forEach items="${partidos}" varStatus="status" var="p">
                
                <tr>
                    <td>${p.jornada.numero}</td>
                    <td><fmt:formatDate value="${p.jornada.fecha}" pattern="dd-MM-yyyy"/></td>
                    <td><c:out value="${p.equipoLocal.nombre}"/></td>
                    <td><c:out value="${p.equipoVisitante.nombre}"/></td>
                    <td><c:out value="${p.puntosEquipoLocal}"/></td>
                    <td><c:out value="${p.puntosEquipoVisitante}"/></td>
                    <td>
                        <c:if test="${p.equipoLocal.nombre==equipo.nombre}">
                            <c:out value="${p.puntosEquipoLocal-p.puntosEquipoVisitante}"/>
                        </c:if>
                        <c:if test="${p.equipoVisitante.nombre==equipo.nombre}">
                            <c:out value="${p.puntosEquipoVisitante-p.puntosEquipoLocal}"/>
                        </c:if>
                        
                    </td>
                    


                </tr>
                
            </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="6"><fmt:message key="PuntosTotales" bundle="${mensajes}"/></td>
                    <td> <c:out value="${puntosTotales}"></c:out></td>
                </tr>
                </tfoot>
        </table>
        <br>
        <a href="/LigaBaloncesto/VerClasificacionLiga"><fmt:message key="Volver" bundle="${mensajes}"/></a>
         
                    
                    <div style="display:flex;">
                        <div style="width: 100%;">
                            <canvas id="myChart"></canvas>
                        </div>    
                        <div style="width: 100%;">
                   <canvas id="PorcentajeGanadosPerdidos"></canvas>
                        </div>
                    </div>
                    
    
                     
                    
    
    
 <script>
// Obtener los puntos de cada partido en un array
var puntos = [];
<c:forEach items="${partidos}" var="p">
    <c:if test="${p.equipoLocal.nombre==equipo.nombre}">
        puntos.push(${p.puntosEquipoLocal-p.puntosEquipoVisitante});
    </c:if>
    <c:if test="${p.equipoVisitante.nombre==equipo.nombre}">
        puntos.push(${p.puntosEquipoVisitante-p.puntosEquipoLocal});
    </c:if>
</c:forEach>

// Obtener los números de jornada en una array
var jornadas = [];
<c:forEach items="${partidos}" var="p">
    jornadas.push(${p.jornada.numero});
</c:forEach>

// Crear una nueva gráfica de barras
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: jornadas, // Etiquetas de las barras
        datasets: [{
            label: ' <fmt:message key="Diferencialdepuntos" bundle="${mensajes}"/> ',
            data: puntos, // Datos de la altura de las barras
            backgroundColor: 'rgba(255,0,0,0.5)', // Color de fondo de las barras
            borderColor: 'rgba(54, 162, 235, 1)', // Color del borde de las barras
            borderWidth: 1 // Ancho del borde de las barras
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true // Escala del eje y comienza en cero
                }
            }]
        }
    }
});
</script>
<script>
// Obtener los resultados de los partidos
var partidosGanados = 0;
var partidosPerdidos = 0;
<c:forEach items="${partidos}" var="p">
    <c:if test="${p.equipoLocal.nombre==equipo.nombre}">
        <c:if test="${p.puntosEquipoLocal > p.puntosEquipoVisitante}">
            partidosGanados++;
        </c:if>
        <c:if test="${p.puntosEquipoLocal < p.puntosEquipoVisitante}">
            partidosPerdidos++;
        </c:if>
    </c:if>
    <c:if test="${p.equipoVisitante.nombre==equipo.nombre}">
        <c:if test="${p.puntosEquipoVisitante > p.puntosEquipoLocal}">
            partidosGanados++;
        </c:if>
        <c:if test="${p.puntosEquipoVisitante < p.puntosEquipoLocal}">
            partidosPerdidos++;
        </c:if>
    </c:if>
</c:forEach>

// Calcular el porcentaje de partidos ganados y perdidos
var totalPartidos = partidosGanados + partidosPerdidos;
var porcentajeGanados = partidosGanados / totalPartidos * 100;
var porcentajePerdidos = partidosPerdidos / totalPartidos * 100;

// Crear una nueva gráfica de pastel
var ctx = document.getElementById('PorcentajeGanadosPerdidos').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: ['<fmt:message key="Partidosganados" bundle="${mensajes}"/>', '<fmt:message key="Partidosperdidos" bundle="${mensajes}"/>'], // Etiquetas de las secciones del pastel
        datasets: [{
            label: '<fmt:message key="Porcentajedepartidosganadosyperdidos" bundle="${mensajes}"/>',
            data: [porcentajeGanados, porcentajePerdidos], // Datos de las secciones del pastel
            backgroundColor: [
                'rgba(0,128,0,0.2)', // Color de fondo de la sección de partidos ganados
                'rgba(255, 99, 132, 0.2)' // Color de fondo de la sección de partidos perdidos
            ],
            borderColor: [
                'rgba(0,128,0,1)', // Color del borde de la sección de partidos ganados
                'rgba(255, 99, 132, 1)' // Color del borde de la sección de partidos perdidos
            ],
            borderWidth: 1 // Ancho del borde de las secciones del pastel
        }]
    },
    options: {
        responsive: true,
        legend: {
            position: 'top'
        },
        title: {
            display: true,
            text: '<fmt:message key="Porcentajedepartidosganadosyperdidos" bundle="${mensajes}"/>'
        },
        animation: {
            animateScale: true,
            animateRotate: true
        }
    }
});

</script>
    
    </body>
</html>