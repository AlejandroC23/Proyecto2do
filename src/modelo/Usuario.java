/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Funciones;

/**
 *
 * @author Alejandro
 */
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String direccion;
    private String telefono;
    private String cedula;
    private String clave;
    private int rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, String correoElectronico, String direccion, String telefono, String cedula, String clave, int rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cedula = cedula;
        this.clave = clave;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\n"
                + "Apellido" + getApellido() + "\n"
                + "Correo Electronico" + getCorreoElectronico() + "\n"
                + "Teléfono: " + getTelefono() + "\n"
                + "Direccion" + getDireccion() + "\n"
                + "Cedula" + getCedula() + "\n"
                + "Clave:" + "************\n";
    }
    
    public boolean validarCedula(String c){
        if (c == null || c.length() != 10) {
            return false;
        }

        if (!c.matches("\\d{10}")) {
            return false;
        }

        int codigoProvincia = Integer.parseInt(c.substring(0, 2));
        int tercerDigito = Integer.parseInt(c.substring(2, 3));

        if (codigoProvincia < 1 || codigoProvincia > 24 || tercerDigito >= 6) {
            return false;
        }

        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(c.charAt(i)) * coeficientes[i];
            sum += digito > 9 ? digito - 9 : digito;
        }

        int comprobadorDigito = (10 - (sum % 10)) % 10;

        return comprobadorDigito == Character.getNumericValue(c.charAt(9));
    }
}
