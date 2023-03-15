/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Zatonio
 */
public class DetalleEquipo implements Serializable {
    
    private Long idEquipo;
    
    private String nombreEquipo;
    
    private String nombreLiga;
    
    private List<DetallePartido> partidos;
    
    private Integer puntosTotales;

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    

    public List<DetallePartido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<DetallePartido> partidos) {
        this.partidos = partidos;
    }

    public Integer getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(Integer puntosTotales) {
        this.puntosTotales = puntosTotales;
    }
    
}
