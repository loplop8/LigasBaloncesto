
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Zatonio
 */
@Entity
@Table(name = "jornada")
@Cacheable(false)
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id")
    private Long id;
    
    @Column(name="numero")
    private int numero;
    
    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    
    @ManyToOne
    @JoinColumn(name="id_liga")
    private Liga liga;
    
    @OneToMany(mappedBy = "jornada")
    private List<Partido> partidos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    @Override
    public String toString() {
        return "Jornada{" + "id=" + id + ", numero=" + numero + ", fecha=" + fecha + ", liga=" + liga + '}';
    }
    
    
   
    
    
    
    
   
}
