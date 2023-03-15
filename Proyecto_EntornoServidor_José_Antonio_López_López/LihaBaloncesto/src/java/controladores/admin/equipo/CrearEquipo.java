
package controladores.admin.equipo;
/**
 *
 * @author Zatonio
 */
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.EquipoJpaController;
import modelo.dao.LigaJpaController;
import modelo.entidades.Equipo;
import modelo.entidades.Liga;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "CrearEquipo", urlPatterns = {"/admin/CrearEquipo"})
public class CrearEquipo extends HttpServlet {

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
        String vista = "/admin/equipos/crearEquipo.jsp";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        LigaJpaController ljc = new LigaJpaController(emf);
        
        List<Liga> ligas = ljc.findLigaEntities();
        
        String error = "";
        String nombre = "";
        String idLiga = "";


        if (request.getParameter("nombre") != null) {
            nombre = request.getParameter("nombre");
            nombre = nombre.trim();
            
            idLiga = request.getParameter("idLiga");
            idLiga = idLiga.trim();

            if (nombre.isEmpty()) {
                error = "El nombre no puede estar vacío";
            } else if (idLiga.isEmpty()) {
                error = "La liga no puede estar vacía";
            } else {
                
                Equipo u = new Equipo();
                u.setNombre(nombre);
                
                Liga l = ljc.findLiga(Long.valueOf(idLiga));
                u.setLiga(l);
               
                EquipoJpaController ujc = new EquipoJpaController(emf);
                try {
                    ujc.create(u);
                    response.sendRedirect("MenuEquipos");
                    return;
                } catch (RollbackException e) {
                    error = "La liga ya existe";
                }
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
        }
        
        request.setAttribute("ligas", ligas);
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
