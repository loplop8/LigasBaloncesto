
package controladores.rest;
/**
 *
 * @author Zatonio
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.DetalleEquipo;
import modelo.DetallePartido;
import modelo.EquipoClasificacion;
import modelo.dao.ClasificacionJpaController;
import modelo.dao.EquipoJpaController;
import modelo.dao.PartidoJpaController;
import modelo.entidades.Equipo;
import modelo.entidades.Partido;

/**
 *
 * @author Zatonio
 */
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class RestController {

    @GET
    @Path("ClasificacionLiga/{id}")
    public Response getClasificacionLiga(@PathParam("id") Long idLiga) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        ClasificacionJpaController cjc = new ClasificacionJpaController(emf);
        List<EquipoClasificacion> res;
        String jsonRes;
        try {
            res = cjc.findClasificacionByIdLiga(idLiga);

            // Pasamos de objeto a String con el JSON
            ObjectMapper mapper = new ObjectMapper();
            jsonRes = mapper.writeValueAsString(res);

        } catch (JsonProcessingException e) {
            return Response.status(500).entity("Error obteniendo el JSON").build();
        } catch (Exception e) {
            return Response.status(404).entity("No se ha encontrado la liga indicada").build();
        }

        return Response.ok(jsonRes, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("DetalleEquipo/{id}")
    public Response getDetalleEquipo(@PathParam("id") Long idEquipo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LigaBaloncesto");
        EquipoJpaController ejc = new EquipoJpaController(emf);
        PartidoJpaController pjc = new PartidoJpaController(emf);
        String jsonRes;

        try {
            Equipo equipo = ejc.findEquipo(idEquipo);
            List<Partido> partidos = pjc.findPartidoJugadosporEquipoTerminados(idEquipo);
            List<DetallePartido> detallePartidos = new ArrayList<>();
            
            Integer puntosTotales = 0;

            for (Partido partido : partidos) {
                DetallePartido dp = new DetallePartido();
                
                dp.setNombreEquipoLocal(partido.getEquipoLocal().getNombre());
                dp.setNombreEquipoVisitante(partido.getEquipoVisitante().getNombre());
                dp.setPuntosEquipoLocal(partido.getPuntosEquipoLocal());
                dp.setPuntosEquipoVisitante(partido.getPuntosEquipoVisitante());
                
                
                if (partido.getEquipoLocal().getId().equals(idEquipo)) {
                    puntosTotales += partido.getPuntosEquipoLocal();
                } else {
                    puntosTotales += partido.getPuntosEquipoVisitante();
                }
                
                detallePartidos.add(dp);
            }

            DetalleEquipo res = new DetalleEquipo();
            res.setIdEquipo(idEquipo);
            res.setNombreEquipo(equipo.getNombre());
            res.setNombreLiga(equipo.getLiga().getNombre());
            res.setPartidos(detallePartidos);
            res.setPuntosTotales(puntosTotales);

            // Pasamos de objeto a String con el JSON
            ObjectMapper mapper = new ObjectMapper();
            jsonRes = mapper.writeValueAsString(res);

        } catch (JsonProcessingException e) {
            return Response.status(500).entity("Error obteniendo el JSON").build();
        } catch (Exception e) {
            return Response.status(404).entity("No se ha encontrado el equipo").build();
        }

        return Response.ok(jsonRes, MediaType.APPLICATION_JSON).build();
    }

}
