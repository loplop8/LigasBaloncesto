
package controladores;
/**
 *
 * @author Zatonio
 */
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.ClasificacionJpaController;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "VerClasificacionLiga", urlPatterns = {"/VerClasificacionLiga"})
public class VerClasificacionLiga extends HttpServlet {

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
        String vista = "/verClasificacionLiga.jsp";
        
        String idLigaStr = request.getParameter("id");
        Long idLiga;
        HttpSession session = request.getSession(false);

        // Si en la request no encontramos el id, significa que volvemos del listado de partidos
        if (idLigaStr == null) {
            idLiga = (Long) session.getAttribute("idLiga");
        } else {
            idLiga = Long.valueOf(idLigaStr);
            session.setAttribute("idLiga", idLiga);

        }
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");

        ClasificacionJpaController cjc = new ClasificacionJpaController(emf);        
        request.setAttribute("equiposClasificacion", cjc.findClasificacionByIdLiga(idLiga));
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
