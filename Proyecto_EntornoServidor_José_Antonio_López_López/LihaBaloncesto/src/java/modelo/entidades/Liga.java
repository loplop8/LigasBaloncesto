/*
 * Clase Empleado.
 * Entidad JPA
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author Zatonio
 */
@Entity
@Table(name = "liga")
@Cacheable(false) //La etiqueta cacheable hace que no se guarde en la cache de java para que las vistas se recarguen como es debido
public class Liga implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id")
    private Long id;
    
    @Column(name="nombre", unique = true)
    private String nombre;
    
    @OneToMany(mappedBy = "liga")
    List<Equipo> equipos;
    
    @OneToMany(mappedBy = "liga")
    List<Jornada> jornadas;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    @Override
    public String toString() {
        return "Liga{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
}
