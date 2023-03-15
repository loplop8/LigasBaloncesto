/**
 *
 * @author Zatonio
 */
package controladores.arbitro.partido;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.JornadaJpaController;
import modelo.entidades.Jornada;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "MenuPartidosArbitro", urlPatterns = {"/arbitro/MenuPartidos"})
public class MenuPartidos extends HttpServlet {

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
        String vista = "/arbitro/partidos/menuPartidos.jsp";
        
        String idJornadaStr = request.getParameter("id"); 
        Long idJornada;
        HttpSession session = request.getSession(false);

        // Si en la request no encontramos el id, significa que volvemos del listado de partidos
        if (idJornadaStr == null) {
            idJornada = (Long) session.getAttribute("idJornada");
        } else {
            idJornada = Long.valueOf(idJornadaStr);
            session.setAttribute("idJornada", idJornada);

        }
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        JornadaJpaController lc = new JornadaJpaController(emf);

        Jornada jornada = lc.findJornada(idJornada);
        request.setAttribute("jornada", jornada);
        request.setAttribute("partidos", jornada.getPartidos());
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
