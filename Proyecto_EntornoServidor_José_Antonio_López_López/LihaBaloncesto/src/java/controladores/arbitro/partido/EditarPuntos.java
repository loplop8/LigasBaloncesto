
package controladores.arbitro.partido;
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
import modelo.dao.PartidoJpaController;
import modelo.entidades.Partido;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "EditarPuntos", urlPatterns = {"/arbitro/EditarPuntos"})
public class EditarPuntos extends HttpServlet {

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
        String vista = "/arbitro/partidos/editarPuntos.jsp";

        Long idPartido = Long.valueOf(request.getParameter("id"));
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        PartidoJpaController pjc = new PartidoJpaController(emf);
        
        Partido partido= pjc.findPartido(idPartido);
        
                
        String error = "";
        Integer puntosLocal;
        Integer puntosVisitante;

        if (request.getParameter("puntosLocal") != null) {
            puntosLocal = Integer.valueOf(request.getParameter("puntosLocal"));
            puntosVisitante = Integer.valueOf(request.getParameter("puntosVisitante"));

            if (puntosLocal.equals(puntosVisitante)) {
                error = "El partido no puede quedar en empate";
            } else {
                partido.setPuntosEquipoLocal(puntosLocal);
                partido.setPuntosEquipoVisitante(puntosVisitante);
               
                try {
                    pjc.edit(partido);
                    response.sendRedirect("MenuPartidos");
                    return;
                } catch (Exception e) {
                    error = "Error modificando los puntos";
                } 
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("partido", partido);
        }
               
        request.setAttribute("partido", partido);
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
