/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.EstudianteControlador;
import controlador.Funciones;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void asignarEstudiante() {
        Scanner s = new Scanner(System.in);

        EstudianteControlador estC = new EstudianteControlador();
        Estudiante est;
        Usuario usu = new Usuario();

        String opcYN;

        do {
            Funciones.cls();
            System.out.println("""
                                                --------------------------------------------------
                                                                 Asignar Estudiante
                                                --------------------------------------------------
                                               Para poder asignar al estudiante al mantenimiento de
                                                  laboratorio, ingrese la cédula del estudiante.
                               """);
            System.out.print("  Ingrese la cédula (Ingrese 'r' para regresar): ");
            String cedula = s.next();
            if (Funciones.isValidNumeric(cedula)) {
                if (!usu.validarCedula(cedula)) {
                    Funciones.cls();
                    System.out.println("""
                                                ¡ERROR! Ingrese una cédula correcta.
                                           """);
                    Funciones.pause();
                } else {
                    est = estC.buscarEstudiante(cedula);
                    if (!"0".equals(est.getCedula())) {
                        if(estC.existeEstudianteMantenimiento(est.getMatricula())){
                            Funciones.cls();
                            System.out.println("¡ERROR! El estudiante ya esta asignado a un mantenimiento en curso. Ingrese otro estudiante.");
                            Funciones.pause();
                        } else {
                            do {
                                Funciones.cls();
                                System.out.println("El estudiante es: "
                                        + est.getNombre()
                                        + " "
                                        + est.getApellido());
                                System.out.print("¿Es correcto? [Y/n] ");
                                opcYN = s.next();
                                switch (opcYN) {
                                    case "Y" -> {
                                        int idEstudiante = estC.buscarIdEstudiante(est.getMatricula());
                                        estC.asignarEstudianteMantenimiento(idEstudiante, getIdMantenimiento());
                                    }
                                    case "n" -> {
                                        break;
                                    }
                                    default -> {
                                        Funciones.cls();
                                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                                        Funciones.pause();
                                        continue;
                                    }
                                }
                            } while ("n".equals(opcYN) && "Y".equals(opcYN));

                            if ("Y".equals(opcYN)) {
                                break;
                            }
                        }
                    } else {
                        Funciones.cls();
                        System.out.println("¡ERROR! No existe el estudiante, compruebe la cédula o revise que no sea docente.");
                        Funciones.pause();
                    }
                }
            } else if ("r".equals(cedula)) {
                break;
            } else {
                System.out.println("""
                                            ¡ERROR! Opción o cédula ingresa incorrecta.
                                       """);
            }
        } while (true);
    }

    public void quitarEstudiante() {
        Scanner s = new Scanner(System.in);

        EstudianteControlador estC = new EstudianteControlador();
        Estudiante est;
        Usuario usu = new Usuario();

        String opcYN;

        do {
            Funciones.cls();
            System.out.println("""
                                                --------------------------------------------------
                                                                  Quitar Estudiante
                                                --------------------------------------------------
                                               Para poder quitar al estudiante al mantenimiento de
                                                  laboratorio, ingrese la cédula del estudiante.
                               """);
            System.out.print("  Ingrese la cédula (Ingrese 'r' para regresar): ");
            String cedula = s.next();
            if (Funciones.isValidNumeric(cedula)) {
                if (!usu.validarCedula(cedula)) {
                    Funciones.cls();
                    System.out.println("""
                                                ¡ERROR! Ingrese una cédula correcta.
                                           """);
                    Funciones.pause();
                } else {
                    est = estC.buscarEstudiante(cedula);
                    if (!"0".equals(est.getCedula())) {
                        do {
                            Funciones.cls();
                            System.out.println("El estudiante es: "
                                    + est.getNombre()
                                    + " "
                                    + est.getApellido());
                            System.out.print("¿Es correcto? [Y/n] ");
                            opcYN = s.next();
                            switch (opcYN) {
                                case "Y" -> {
                                    Funciones.cls();
                                    int idEstudiante = estC.buscarIdEstudiante(est.getMatricula());
                                    estC.quitarEstudianteMantenimiento(idEstudiante, getIdMantenimiento());

                                    System.out.println("""
                                                                     La acción se ha realizado con éxito.
                                                        Si eliminaste al estudiante por error puedes volver asignarlo en
                                                                              cualquier momento.
                                                        """);
                                    Funciones.pause();
                                }
                                case "n" -> {
                                    break;
                                }
                                default -> {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                                    Funciones.pause();
                                    continue;
                                }
                            }
                        } while ("n".equals(opcYN) && "Y".equals(opcYN));

                        if ("Y".equals(opcYN)) {
                            break;
                        }
                    } else {
                        Funciones.cls();
                        System.out.println("¡ERROR! No existe el estudiante, compruebe la cédula o revise que no sea docente.");
                        Funciones.pause();
                    }
                }
            } else if ("r".equals(cedula)) {
                break;
            } else {
                System.out.println("""
                                            ¡ERROR! Opción o cédula ingresa incorrecta.
                                       """);
            }
        } while (true);
    }
}
