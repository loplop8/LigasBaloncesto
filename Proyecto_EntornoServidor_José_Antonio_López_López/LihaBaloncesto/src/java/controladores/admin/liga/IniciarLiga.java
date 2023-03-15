
package controladores.admin.liga;
/**
 *
 * @author Zatonio
 */
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.EquipoJpaController;
import modelo.dao.LigaJpaController;
import modelo.dao.JornadaJpaController;
import modelo.dao.PartidoJpaController;
import modelo.entidades.Equipo;
import modelo.entidades.Jornada;
import modelo.entidades.Liga;
import modelo.entidades.Partido;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "IniciarLiga", urlPatterns = {"/admin/IniciarLiga"})
public class IniciarLiga extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/admin/ligas/iniciarLiga.jsp";
        Long id = Long.valueOf(request.getParameter("id"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");

        LigaJpaController ljc = new LigaJpaController(emf);
        Liga liga = ljc.findLiga(id); // Encontramos las ligas disponibles

        EquipoJpaController ejc = new EquipoJpaController(emf);
        List<Equipo> equipos = liga.getEquipos(); //Encontramos los equipos de esta liga en concreto

        String error = "";
        Date fechaInicio = null; // Inicializamos un objeto de tipo fecha a null

        if (request.getParameter("fechaInicio") != null) { // Si lo que nos llegue por el parametro no es null

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // Cambiamos el formato de la fecha

            try {
                fechaInicio = format.parse(request.getParameter("fechaInicio")); //
            } catch (ParseException ex) {
                Logger.getLogger(IniciarLiga.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (fechaInicio == null) {
                error = "La fecha no puede estar vacía";
            } else {

                // Hay que generar 2*n-1
                Integer numJornadas = 2 * (liga.getEquipos().size() - 1); 
                Calendar calendar = Calendar.getInstance(); // Inicializamos un objeto calendar para tratar la fecha
                calendar.setTime(fechaInicio); // Le pasamos la fecha con el formato

                JornadaJpaController jcp = new JornadaJpaController(emf); // Inicializamos el Controlador de Jpa de Jornada
                PartidoJpaController pjpa = new PartidoJpaController(emf);
                try { // Intentamos crear una jornada en función a la fecha que ha escogido el usuario y le añadimos al final del bucle 7 dias al objeto calendar para que las jornadas se creen con la diferencia de una semana

                    Collections.shuffle(equipos); //Mezclamos los equipos de manera aleatoria

                    List<Jornada> jornadaAux = new ArrayList<Jornada>();
                    for (int i = 1; i <= numJornadas; i++) {
                        Jornada j = new Jornada(); //Creamos tantas jornadas como el numero de jornadas dependiendo del numero de equipos
                        j.setLiga(liga);
                        j.setNumero(i);
                        j.setFecha(calendar.getTime());
                        try {
                            jcp.create(j);
                        } catch (Exception e) {
                            error = "No se ha podido generar la jornada-->" + e.getMessage();
                        }

                        jornadaAux.add(j);
                        calendar.add(Calendar.DATE, 7);

                    }

                    HttpSession session = request.getSession(false);
                    session.setAttribute("idLiga", id); //Guardamos en la sesion el id de la liga
                    response.sendRedirect("./GenerarPartidosLiga"); //Redirigimos al siguiente controlador

                    return;
                } catch (RollbackException e) {
                    error = "Se ha producido un error creando las jornadas";
                }
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
        }

        request.setAttribute("liga", liga);

        //Recogemos el dato de la liga la primera vez que se pulse el boton 
        getServletContext().getRequestDispatcher(vista).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
