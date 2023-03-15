/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Zatonio
 */
@Entity
@Table(name = "partido")
@Cacheable(false)
@NamedQueries({
    
    @NamedQuery(name = "findPartidoJugadosporEquipoTerminados", query = "SELECT p FROM Partido p WHERE (p.equipoLocal.id = :id OR p.equipoVisitante.id = :id) AND (p.puntosEquipoLocal != 0 OR  p.puntosEquipoVisitante != 0)")
        
})


public class Partido implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_equipoLocal")
    private Equipo equipoLocal;
    
    @ManyToOne
    @JoinColumn(name = "id_equipoVisitante")
    private Equipo equipoVisitante;
    
    
    @Column(name="puntos_equipoLocal")
    private int puntosEquipoLocal;
    
    @Column(name="puntos_equipoVisitante")
    private int puntosEquipoVisitante;
    
    @ManyToOne
    @JoinColumn(name = "id_jornada")
    private Jornada jornada;

    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getPuntosEquipoLocal() {
        return puntosEquipoLocal;
    }

    public void setPuntosEquipoLocal(int puntosEquipoLocal) {
        this.puntosEquipoLocal = puntosEquipoLocal;
    }

    public int getPuntosEquipoVisitante() {
        return puntosEquipoVisitante;
    }

    public void setPuntosEquipoVisitante(int puntosEquipoVisitante) {
        this.puntosEquipoVisitante = puntosEquipoVisitante;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        return "Partido{" + "id=" + id + ", equipoLocal=" + equipoLocal + ", equipoVisitante=" + equipoVisitante + ", puntosEquipoLocal=" + puntosEquipoLocal + ", puntosEquipoVisitante=" + puntosEquipoVisitante + ", jornada=" + jornada + '}';
    }
    
}
