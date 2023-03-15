/*
 * Servlet Controlador MenuDepartamentos.
 */
package controladores.arbitro.liga;
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

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "MenuLigasArbitro", urlPatterns = {"/arbitro/MenuLigas"})
public class MenuLigas extends HttpServlet {

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
            throws ServletException, IOException { //Nos redirige a al menu d
        String vista = "/arbitro/ligas/menuLigas.jsp";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        LigaJpaController djc = new LigaJpaController(emf);
        request.setAttribute("ligas", djc.findLigaEntities()); //Pasamos las ligas a la vista
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
