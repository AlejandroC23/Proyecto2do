/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alejandro
 */
public class TipoHardware {
    private int idTipoHardware;
    private String descripcion;

    public TipoHardware() {
    }

    public TipoHardware(int idTipoHardware, String descripcion) {
        this.idTipoHardware = idTipoHardware;
        this.descripcion = descripcion;
    }

    public int getIdTipoHardware() {
        return idTipoHardware;
    }

    public void setIdTipoHardware(int idTipoHardware) {
        this.idTipoHardware = idTipoHardware;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
