/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alejandro
 */
public class Administrador extends Persona {
    
    private int  idAdministrador;

    public Administrador() {
    }

    public Administrador(int idAdministrador, int idPersona, String nombre, String apellido, String correoElectronico, String direccion, String telefono, String cedula, String clave) {
        super(idPersona, nombre, apellido, correoElectronico, direccion, telefono, cedula, clave);
        this.idAdministrador = idAdministrador;
    }
    
    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    
    public String imprimir(){
        return "Nombre: " + getNombre() + "\n"
                + "Apellido" + getApellido() + "\n"
                + "Correo Electronico" + getCorreoElectronico() + "\n"
                + "Teléfono: " + getTelefono() + "\n"
                + "Direccion" + getDireccion() + "\n"
                + "Cedula" + getCedula() + "\n"
                + "Clave:" + "************\n";
                
    }
}
