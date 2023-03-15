/*
 * Servlet Controlador MenuDepartamentos.
 */
package controladores.arbitro.jornada;
/**
 *
 * @author Zatonio
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.dao.JornadaJpaController;
import modelo.dao.PartidoJpaController;
import modelo.entidades.Jornada;
import modelo.entidades.Partido;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "ActualizarDatosJornada", urlPatterns = {"/arbitro/ActualizarDatosJornada"})
@MultipartConfig(maxFileSize = 1000000, fileSizeThreshold = 1000000)  //Añadimos la configuracion Multipart 
public class ActualizarDatosJornada extends HttpServlet {

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
        String vista = "/arbitro/jornadas/importarDatosJornada.jsp";
        String error = "";

        HttpSession session = request.getSession(false);
        Long idJornada = (Long) session.getAttribute("idJornada");

        Part parte = request.getPart("fichero");
        if (parte == null) {
            error = "Fichero inválido";
        } else {
            InputStream entrada = parte.getInputStream();
            //.lines -> Stream<String> (Lineas del fichero)
            //.collect(...) -> Stream a List
            List<String> lines = new BufferedReader(new InputStreamReader(entrada)).lines().collect(Collectors.toList());

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
            JornadaJpaController jjc = new JornadaJpaController(emf);
            Jornada jornada = jjc.findJornada(idJornada);

            String jornadaF = lines.get(0);
            if (!jornadaF.equals("Jornada " + jornada.getNumero())) {
                error = "La jornada del fichero no es válida";
            } else if (jornada.getPartidos().size() != lines.size() - 1) {
                error = "El numero de partidos del fichero no es válido";
            } else {
                for (int i = 0; i < jornada.getPartidos().size(); i++) {
                    Partido p = jornada.getPartidos().get(i);
                    String lineP = lines.get(i + 1);

                    String[] teamsLine = lineP.split("-"); //Separamos arrays por separador
                    String[] localTeam = teamsLine[0].trim().split(":"); 
                    String[] visitorTeam = teamsLine[1].trim().split(":");

                    if (!localTeam[0].trim().equals(p.getEquipoLocal().getNombre())) {
                        error = "El equipo local del partido " + (i + 1) + "no es correcto";
                        break;
                    } else if (!visitorTeam[0].trim().equals(p.getEquipoVisitante().getNombre())) {
                        error = "El equipo visitante del partido " + (i + 1) + " no es correcto";
                        break;
                    } else if(localTeam[1].trim().equals(visitorTeam[1].trim())) {
                        error = "Los puntos del partido " + (i + 1) + " no pueden ser iguales";
                        break;
                    }

                    p.setPuntosEquipoLocal(Integer.valueOf(localTeam[1].trim()));
                    p.setPuntosEquipoVisitante(Integer.valueOf(visitorTeam[1].trim()));
                }
            }

            if (error.isEmpty()) {
                try {
                    PartidoJpaController pjc = new PartidoJpaController(emf);
                    for(Partido p: jornada.getPartidos()) {
                        pjc.edit(p);
                    }
                } catch (Exception ex) {
                    error = "Se ha producido un error almacenando los datos en la BBDD";
                }
            }
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            session.setAttribute("idJornada", idJornada);
            getServletContext().getRequestDispatcher(vista).forward(request, response);
        } else {
            response.sendRedirect("./MenuJornadas");

        }

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
