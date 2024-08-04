/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alejandro
 */
public class Encargado extends Usuario {

    private int idEncargado;
    private String titulo;
    private String cargo;
    private int idPersona;

    public Encargado() {
    }

    public Encargado(int idEncargado, String titulo, String cargo, int idPersona, String nombre, String apellido, String correoElectronico, String direccion, String telefono, String cedula, String clave, int rol) {
        super(idPersona, nombre, apellido, correoElectronico, direccion, telefono, cedula, clave, rol);
        this.idEncargado = idEncargado;
        this.titulo = titulo;
        this.cargo = cargo;
        this.idPersona = idPersona;
    }

    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public int getIdPersona() {
        return idPersona;
    }

    @Override
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\n"
                + "Apellido" + getApellido() + "\n"
                + "Cargo: " + getCargo() + "\n"
                + "Correo Electronico" + getCorreoElectronico() + "\n"
                + "Teléfono: " + getTelefono() + "\n"
                + "Direccion" + getDireccion() + "\n"
                + "Cedula" + getCedula() + "\n"
                + "Clave:" + "************\n";
    }

    public void realizarMantenimiento(){
        
    }
    
    public void finalizarMantenimiento(){
        
    }
    
    public void verificarMantenimiento(){
        
    }
    
    public void editarDatosPersonales(){
        
    }
}
