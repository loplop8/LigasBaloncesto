/*
 * Servlet Controlador CrearDepartamento.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.admin.usuario;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
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
@WebServlet(name = "CrearUsuario", urlPatterns = {"/admin/CrearUsuario"})
public class CrearUsuario extends HttpServlet {

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
        String vista = "/admin/usuarios/crearUsuario.jsp";

        String error = "";
        String nombre = "";
        String rol = "admin";
        String nickname = "";
        String password = "";

        if (request.getParameter("nombre") != null) { //Si no es null es cuando se crea el usuario
            nombre = request.getParameter("nombre");
            nombre = nombre.trim();

            rol = request.getParameter("rol");
            rol = rol.trim(); 

            nickname = request.getParameter("nickname");
            nickname = nickname.trim();

            password = request.getParameter("password");
            password = password.trim();

            if (nombre.isEmpty()) { //Comprobamos y mostramos mensajes
                error = "El nombre no puede estar vacío";
            } else if (rol.isEmpty()) {
                error = "El rol no puede estar vacío";
            } else if (nickname.isEmpty()) {
                error = "El nickname no puede estar vacío";
            } else if (password.isEmpty()) {
                error = "La contraseña no puede estar vacía";
            } else { //Si no hay error creamos un nuevo usuario
                Usuario u = new Usuario();
                u.setNombre(nombre);
                u.setRol(rol);
                u.setNickname(nickname);
                u.setPassword(password);
                
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
                UsuarioJpaController ujc = new UsuarioJpaController(emf); //Llamamos controlador JPA USUARIO
                try { //Persistimos los datos en la base de datos
                    ujc.create(u); 
                    response.sendRedirect("MenuUsuarios"); // 
                    return;
                } catch (RollbackException e) {
                    error = "El usuario ya existe"; 
                }
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
            request.setAttribute("rol", rol);
            request.setAttribute("nickname", nickname);
            request.setAttribute("password", password);
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
