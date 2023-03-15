package controladores.admin.liga;
/**
 *
 * @author Zatonio
 */
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.PartidoJpaController;
import modelo.entidades.Equipo;
import modelo.entidades.Jornada;
import modelo.entidades.Liga;
import modelo.entidades.Partido;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "GenerarPartidosLiga", urlPatterns = {"/admin/GenerarPartidosLiga"})
public class GenerarPartidosLiga extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el ID de la liga
        HttpSession session = request.getSession(false);

        Long id = (Long) session.getAttribute("idLiga"); //Obtenemos de la sesion el id de la liga 

        // Crear el controlador de las entidades necesarias
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        PartidoJpaController pjpa = new PartidoJpaController(emf);

        // Encontrar la liga correspondiente al ID
        Liga liga = emf.createEntityManager().find(Liga.class, id);
        if (liga == null) { //Si hubiera algun tipo de fallo aunque no deberia por venir de la sesion 
            response.sendRedirect("./MenuLigas"); 
            return;
        }

        // Obtener los equipos de la liga
        List<Equipo> equipos = liga.getEquipos(); //Obtenemos los equipos de la liga

        // Generar los partidos de ida y vuelta para cada jornada con el bucle de todos contra todos
        //Se genera el partido de ida y de vuelta para cada emparejamiento
        int numJornadas = liga.getJornadas().size(); 
        for (int i = 0; i < numJornadas / 2; i++) {
            Jornada jornadaIda = liga.getJornadas().get(i);
            Jornada jornadaVuelta = liga.getJornadas().get(numJornadas - 1 - i);

            for (int k = 0; k < equipos.size() / 2; k++) {
                Partido partidoIda = new Partido();
                partidoIda.setEquipoLocal(equipos.get(k));
                partidoIda.setEquipoVisitante(equipos.get(equipos.size() - 1 - k));
                partidoIda.setJornada(jornadaIda);
                partidoIda.setPuntosEquipoLocal(0);
                partidoIda.setPuntosEquipoVisitante(0);
                try {
                    pjpa.create(partidoIda); 
                } catch (Exception e) {
                    
                    String error = "Error generando partidos: " + e.getMessage();
                    request.setAttribute("error", error);
                }
                
                Partido partidoVuelta = new Partido();
                partidoVuelta.setEquipoLocal(equipos.get(equipos.size() - 1 - k));
                partidoVuelta.setEquipoVisitante(equipos.get(k));
                partidoVuelta.setJornada(jornadaVuelta);
                partidoVuelta.setPuntosEquipoLocal(0);
                partidoVuelta.setPuntosEquipoVisitante(0);
                try {
                    pjpa.create(partidoVuelta);
                } catch (Exception e) {
                    
                    String error = "Error generando partidos: " + e.getMessage();
                    request.setAttribute("error", error);
                }
            }
            Collections.rotate(equipos.subList(1, equipos.size()), 1);
        }

        response.sendRedirect("./MenuLigas");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
