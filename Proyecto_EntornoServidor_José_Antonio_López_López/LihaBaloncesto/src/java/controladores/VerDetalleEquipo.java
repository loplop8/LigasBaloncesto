
package controladores;
/**
 *
 * @author Zatonio
 */
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.EquipoJpaController;
import modelo.dao.PartidoJpaController;
import modelo.entidades.Equipo;
import modelo.entidades.Partido;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "VerDetalleEquipo", urlPatterns = {"/VerDetalleEquipo"})
public class VerDetalleEquipo extends HttpServlet {

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
        String vista = "/verDetalleEquipo.jsp";

        Long id = Long.valueOf(request.getParameter("id"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        EquipoJpaController ejc = new EquipoJpaController(emf);
        PartidoJpaController pjc = new PartidoJpaController(emf);
        Equipo equipo = ejc.findEquipo(id);
        List<Partido> partidos = pjc.findPartidoJugadosporEquipoTerminados(id);
        Integer puntosTotales = 0;

        for (Partido partido : partidos) {
            if (partido.getEquipoLocal().getId().equals(id)) {
                puntosTotales += partido.getPuntosEquipoLocal();
            } else {
                puntosTotales += partido.getPuntosEquipoVisitante();
            }
        }

        request.setAttribute("equipo", equipo);
        request.setAttribute("partidos", partidos);
        request.setAttribute("puntosTotales", puntosTotales);
        getServletContext().getRequestDispatcher(vista).forward(request, response);
    }

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
