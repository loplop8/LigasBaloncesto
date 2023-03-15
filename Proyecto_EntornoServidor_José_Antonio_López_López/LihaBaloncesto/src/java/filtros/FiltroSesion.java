/*
 * Filtro de sesi�n de Empleado.
 * Comprueba que el usuario se ha registrado antes de entrar en cualquier p�gina
 * salvo la p�gina de login y asociadas.
 */
package filtros;

/**
 *
 * @author Zatonio
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.entidades.Usuario;

/**
 *
 * @author jose
 */
public class FiltroSesion implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Usuario u = (Usuario) req.getSession().getAttribute("usuario");
        String uri = req.getRequestURI();
      
        if(uri.endsWith(".css")){
           
            chain.doFilter(request, response);
            
        }else
        // No hay login y estoy intentando acceder a una vista protegida -> redirigimos a login
        if (u == null && !isPublicUrl(uri)) { //Si ul usuario no us null y la uri no es publica aplicamos segun el rol
            res.sendRedirect(req.getContextPath() + "/Login");
        } else if (u != null && "admin".equals(u.getRol()) && uri.contains("arbitro") ) {
            res.sendRedirect(req.getContextPath() + "/Inicio");
        } else if (u != null && "arbitro".equals(u.getRol()) && uri.contains("admin")) {
            res.sendRedirect(req.getContextPath() + "/Inicio");
        } else {
            chain.doFilter(request, response);
        }

    }

    private boolean isPublicUrl(String uri) {
        return !uri.contains("admin") && !uri.contains("arbitro");
    }

    //Url admin /ligaBaloncesto/admin/....
    //Url arbitro /ligaBaloncesto/arbitro/....
    //Url publica /ligaBaloncesto/....
}
