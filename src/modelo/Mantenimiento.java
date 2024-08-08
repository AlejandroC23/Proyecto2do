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
public class Mantenimiento {
     
    private int idMantenimiento;
    private Laboratorio laboratorio;
    private String fechaInicio;
    private String fechaFin;
    private ArrayList<DetalleMantenimiento> detMantenimientos;
    private ArrayList<Estudiante> estudiantes;

    public Mantenimiento() {
    }

    public Mantenimiento(int idMantenimiento, Laboratorio laboratorio, String fechaInicio, String fechaFin, ArrayList<DetalleMantenimiento> detMantenimientos, ArrayList<Estudiante> estudiantes) {
        this.idMantenimiento = idMantenimiento;
        this.laboratorio = laboratorio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.detMantenimientos = detMantenimientos;
        this.estudiantes = estudiantes;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<DetalleMantenimiento> getDetMantenimientos() {
        return detMantenimientos;
    }

    public void setDetMantenimientos(ArrayList<DetalleMantenimiento> detMantenimientos) {
        this.detMantenimientos = detMantenimientos;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
