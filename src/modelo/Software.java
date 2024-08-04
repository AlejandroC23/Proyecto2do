/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alejandro
 */
public class Software {
    private int idSoftware;
    private String nombre;
    private String descripcion;
    private String version;
    private TipoSoftware tipo;

    public Software() {
    }

    public Software(int idSoftware, String nombre, String descripcion, String version, TipoSoftware tipo) {
        this.idSoftware = idSoftware;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.version = version;
        this.tipo = tipo;
    }

    public int getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(int idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public TipoSoftware getTipo() {
        return tipo;
    }

    public void setTipo(TipoSoftware tipo) {
        this.tipo = tipo;
    }
}
