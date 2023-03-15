/*
 * Servlet Controlador EditarDepartamento.
 */
package controladores.admin.usuario;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Usuario;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "EditarUsuario", urlPatterns = {"/admin/EditarUsuario"})
public class EditarUsuario extends HttpServlet {

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
        String vista = "/admin/usuarios/editarUsuario.jsp";
        Long id = Long.valueOf(request.getParameter("id"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario usuario = ujc.findUsuario(id);
        String error = "";

        if (request.getParameter("nombre") != null) {
            // Editando
            String nombre = request.getParameter("nombre");
            nombre = nombre.trim();

            String rol = request.getParameter("rol");
            rol = rol.trim();

            String nickname = request.getParameter("nickname");
            nickname = nickname.trim();

            String password = request.getParameter("password");
            password = password.trim();

            usuario.setNombre(nombre);
            usuario.setNickname(nickname);
            usuario.setRol(rol);
            usuario.setPassword(password);

            if (nombre.isEmpty()) {
                error = "El nombre no puede estar vacío";
            } else if (rol.isEmpty()) {
                error = "El rol no puede estar vacío";
            } else if (nickname.isEmpty()) {
                error = "El nickname no puede estar vacío";
            } else if (password.isEmpty()) {
                error = "La contraseña no puede estar vacía";
            } else {
                try {
                    ujc.edit(usuario);
                    response.sendRedirect("MenuUsuarios");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando el usuario");
                    request.setAttribute("usuario", usuario);
                }
            }

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.setAttribute("usuario", usuario);

            }
        } else { //Si hay algun error editando dejamos el usuario como estaba
            
            request.setAttribute("usuario", usuario);
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
