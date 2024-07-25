/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class DetalleMantenimiento {
    private int idDetMant;
    private Computadora computadora;
    private String descripcion;

    public DetalleMantenimiento() {
    }

    public DetalleMantenimiento(int idDetMant, Computadora computadora, String descripcion) {
        this.idDetMant = idDetMant;
        this.computadora = computadora;
        this.descripcion = descripcion;
    }

    public int getIdDetMant() {
        return idDetMant;
    }

    public void setIdDetMant(int idDetMant) {
        this.idDetMant = idDetMant;
    }

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
