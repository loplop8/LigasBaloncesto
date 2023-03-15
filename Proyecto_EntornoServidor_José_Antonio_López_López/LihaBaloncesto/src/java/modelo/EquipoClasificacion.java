/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Zatonio
 */
public class EquipoClasificacion implements Comparable<EquipoClasificacion>{
    
    private Long idEquipo;
    
    private String nombreEquipo;
    
    private Integer puntosTotales;
    
    private Integer partidosJugados;
    
    private Integer partidosGanados;
    
    private Integer partidosPerdidos;
    
    private Integer puntosPartidosFavorables;
    
    private Integer puntosPartidosEnContra;
    
    private Integer diferenciaPuntosPartidos;

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

    public Integer getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(Integer puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public Integer getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(Integer partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public Integer getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(Integer partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public Integer getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(Integer partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public Integer getPuntosPartidosFavorables() {
        return puntosPartidosFavorables;
    }

    public void setPuntosPartidosFavorables(Integer puntosPartidosFavorables) {
        this.puntosPartidosFavorables = puntosPartidosFavorables;
    }

    public Integer getPuntosPartidosEnContra() {
        return puntosPartidosEnContra;
    }

    public void setPuntosPartidosEnContra(Integer puntosPartidosEnContra) {
        this.puntosPartidosEnContra = puntosPartidosEnContra;
    }

    public Integer getDiferenciaPuntosPartidos() {
        return diferenciaPuntosPartidos;
    }

    public void setDiferenciaPuntosPartidos(Integer diferenciaPuntosPartidos) {
        this.diferenciaPuntosPartidos = diferenciaPuntosPartidos;
    }

    @Override
    public int compareTo(EquipoClasificacion o) {
        int res = this.getPuntosTotales().compareTo(o.getPuntosTotales());
        
        if(res == 0) {
            res = this.getDiferenciaPuntosPartidos().compareTo(o.getDiferenciaPuntosPartidos());
        }
        
        return res;
    }
    
    
    
}
