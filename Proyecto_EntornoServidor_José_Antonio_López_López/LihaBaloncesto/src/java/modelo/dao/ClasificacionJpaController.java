/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.EquipoClasificacion;
import modelo.entidades.Liga;
import modelo.entidades.Equipo;
import modelo.entidades.Partido;
/**
 *
 * @author Zatonio
 */
public class ClasificacionJpaController implements Serializable {

    public ClasificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<EquipoClasificacion> findClasificacionByIdLiga(Long idLiga) {  //Recorre los equipos de la liga y nos devuelve un objeto con la clasificacion de la liga
        EntityManager em = null; //Esta implementado aqui en JPA por la namedQuery

        List<EquipoClasificacion> res = new ArrayList<>();
        try {
            em = getEntityManager();
            Liga liga = em.find(Liga.class, idLiga);

            PartidoJpaController pjc = new PartidoJpaController(emf);

            for (Equipo e : liga.getEquipos()) {  //Obtenemos equipos de la liga
                EquipoClasificacion ec = new EquipoClasificacion(); 
                ec.setIdEquipo(e.getId());
                ec.setNombreEquipo(e.getNombre());

                Integer partidosGanados = 0;
                Integer partidosPerdidos = 0;

                Integer puntosPartidosFavorables = 0;
                Integer puntosPartidosEnContra = 0;

                List<Partido> partidosJugados = pjc.findPartidoJugadosporEquipoTerminados(e.getId());  //Encontramos los partidos necesarios con Query
                ec.setPartidosJugados(partidosJugados.size());

                for (Partido p : partidosJugados) {
                    // Si soy el equipo local
                    if (p.getEquipoLocal().getId().equals(e.getId())) {
                        puntosPartidosFavorables += p.getPuntosEquipoLocal();
                        puntosPartidosEnContra += p.getPuntosEquipoVisitante();
                        
                        if(p.getPuntosEquipoLocal() > p.getPuntosEquipoVisitante()) {
                            partidosGanados++;
                        } else {
                            partidosPerdidos++;
                        }
                    } else {
                        puntosPartidosFavorables += p.getPuntosEquipoVisitante();
                        puntosPartidosEnContra += p.getPuntosEquipoLocal();
                        if(p.getPuntosEquipoVisitante() > p.getPuntosEquipoLocal()) {
                            partidosGanados++;
                        } else {
                            partidosPerdidos++;
                        }
                    }
                }
                
                ec.setPuntosTotales(2 * partidosGanados + partidosPerdidos);
                ec.setPartidosGanados(partidosGanados);
                ec.setPartidosPerdidos(partidosPerdidos);
                ec.setPuntosPartidosFavorables(puntosPartidosFavorables);
                ec.setPuntosPartidosEnContra(puntosPartidosEnContra);
                ec.setDiferenciaPuntosPartidos(puntosPartidosFavorables - puntosPartidosEnContra);

                res.add(ec);
            }
            
            Collections.sort(res);
            Collections.reverse(res); //Hacemos reverse por la 

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return res;

    }
}
