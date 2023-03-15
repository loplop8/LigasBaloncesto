
package controladores.admin.liga;
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
import modelo.dao.LigaJpaController;
import modelo.entidades.Liga;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "EditarLiga", urlPatterns = {"/admin/EditarLiga"})
public class EditarLiga extends HttpServlet {

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
        String vista = "/admin/ligas/editarLiga.jsp";
        Long id = Long.valueOf(request.getParameter("id"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        
        
        LigaJpaController ujc = new LigaJpaController(emf);
        Liga liga = ujc.findLiga(id);
        String error = "";

        if (request.getParameter("nombre") != null) {
            // Editando
            String nombre = request.getParameter("nombre");
            nombre = nombre.trim();

            liga.setNombre(nombre);

            if (nombre.isEmpty()) {
                error = "El nombre no puede estar vacío";
            } else {
                try {
                    ujc.edit(liga);
                    response.sendRedirect("MenuLigas");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error al actualizar");
                    request.setAttribute("liga", liga);
                }
            }

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.setAttribute("liga", liga);

            }
        } else {
            // Rescatar departamento para editar
            request.setAttribute("liga", liga);
        }
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
