/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alejandro
 */
public class TipoSoftware {
    private int idTipoSoftware;
    private String descripcion;

    public TipoSoftware() {
    }

    public TipoSoftware(int idTipoSoftware, String descripcion) {
        this.idTipoSoftware = idTipoSoftware;
        this.descripcion = descripcion;
    }

    public int getIdTipoSoftware() {
        return idTipoSoftware;
    }

    public void setIdTipoSoftware(int idTipoSoftware) {
        this.idTipoSoftware = idTipoSoftware;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
