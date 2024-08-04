/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alejandro
 */
public class Estudiante extends Usuario {
    
    private int idEstudiante;
    private String matricula;
    private int finalizoMantenimiento;
    private Carrera carrera;
    private int nivelCarrera;
    private int idPersona;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String matricula, int finalizoMantenimiento, Carrera carrera, int nivelCarrera, int idPersona, String nombre, String apellido, String correoElectronico, String direccion, String telefono, String cedula, String clave, int rol) {
        super(idPersona, nombre, apellido, correoElectronico, direccion, telefono, cedula, clave, rol);
        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.finalizoMantenimiento = finalizoMantenimiento;
        this.carrera = carrera;
        this.nivelCarrera = nivelCarrera;
        this.idPersona = idPersona;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getFinalizoMantenimiento() {
        return finalizoMantenimiento;
    }

    public void setFinalizoMantenimiento(int finalizoMantenimiento) {
        this.finalizoMantenimiento = finalizoMantenimiento;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public int getNivelCarrera() {
        return nivelCarrera;
    }

    public void setNivelCarrera(int nivelCarrera) {
        this.nivelCarrera = nivelCarrera;
    }
    
    @Override
    public int getIdPersona() {
        return idPersona;
    }

    @Override
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
    public String imprimir(){
        return "Nombre: " + getNombre() + "\n"
                + "Apellido" + getApellido() + "\n"
                + "Correo Electronico" + getCorreoElectronico() + "\n"
                + "Teléfono: " + getTelefono() + "\n"
                + "Direccion" + getDireccion() + "\n"
                + "Cedula" + getCedula() + "\n"
                + "Clave:" + "************\n"
                + "Numero Matricula: "+ getMatricula() + "\n" 
                + "Carrera: " + getCarrera().getNombre();
    }
    
    public void realizarMantenimiento(){
        
    }
    
    public Computadora buscarComputadora(){
        Computadora n = new Computadora();
        return n;
    }
    
    public void registrarComputadora(){
        
    }
    
    public void registrarMantenimiento(){
        
    }
    
    public void editarMantenimiento(){
        
    }
    
    public void editarDatosPersonales(){
        
    }
    
    public void verEstadosComputadoras(){
        
    }
    
    public void finalizarMantenimiento(){
        
    }
}
