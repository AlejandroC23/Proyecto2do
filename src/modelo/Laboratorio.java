/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Laboratorio {
    
    private int idLaboratorio;
    private String nombre;
    private int edificio;
    private String ciudad;
    private ArrayList<Computadora> computadoras;
    private Encargado encargado;

    public Laboratorio() {
    }

    public Laboratorio(int idLaboratorio, String nombre, int edificio, String ciudad, ArrayList<Computadora> computadoras, Encargado encargado) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.edificio = edificio;
        this.ciudad = ciudad;
        this.computadoras = computadoras;
        this.encargado = encargado;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdificio() {
        return edificio;
    }

    public void setEdificio(int edificio) {
        this.edificio = edificio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Computadora> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(ArrayList<Computadora> computadoras) {
        this.computadoras = computadoras;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }

    @Override
    public String toString() {
        return "Laboratorio \n" 
                + "Nombre: " + getNombre() + "\n" 
                + "Edificio: " + getEdificio() + "\n"
                + "Ciudad: " + getCiudad() + "\n"
                + "Cantidad de computadoras: " + getComputadoras().size() + "\n"
                + "Encargado: " + getEncargado().getNombre() 
                + " " + getEncargado().getApellido();
    }

    public void buscarInformeMantenimiento(){
        
    }
    
    public void imprimirInformeComputadoras(){
        
    }
    
    public void imprimirInforme(){
        
    }
}
