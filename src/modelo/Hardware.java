/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alejandro
 */
public class Hardware {
    private int idHarware;
    private String descripcion;
    private String nroSerie;
    private Estado estado;
    private TipoHardware tipo;

    public Hardware() {
    }

    public Hardware(int idHarware, String descripcion, String nroSerie, Estado estado, TipoHardware tipo) {
        this.idHarware = idHarware;
        this.descripcion = descripcion;
        this.nroSerie = nroSerie;
        this.estado = estado;
        this.tipo = tipo;
    }

    public int getIdHarware() {
        return idHarware;
    }

    public void setIdHarware(int idHarware) {
        this.idHarware = idHarware;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoHardware getTipo() {
        return tipo;
    }

    public void setTipo(TipoHardware tipo) {
        this.tipo = tipo;
    }
}
