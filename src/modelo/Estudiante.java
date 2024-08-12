/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.CarreraControlador;
import controlador.ComputadoraControlador;
import controlador.ConexionBDD;
import controlador.DetalleMantenimientoControlador;
import controlador.EstudianteControlador;
import controlador.Funciones;
import controlador.HardwareControlador;
import controlador.MantenimientoControlador;
import controlador.SoftwareControlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import vista.MainEstudiante;

/**
 *
 * @author Alejandro
 */
public class Estudiante extends Usuario {

    private int idEstudiante;
    private String matricula;
    private boolean finalizoMantenimiento;
    private Carrera carrera;
    private int nivelCarrera;
    private int idUsuario;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String matricula, boolean finalizoMantenimiento, Carrera carrera, int nivelCarrera, int idUsuario, String nombre, String apellido, String correoElectronico, String direccion, String telefono, String cedula, String clave, int rol) {
        super(idUsuario, nombre, apellido, correoElectronico, direccion, telefono, cedula, clave, rol);
        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.finalizoMantenimiento = finalizoMantenimiento;
        this.carrera = carrera;
        this.nivelCarrera = nivelCarrera;
        this.idUsuario = idUsuario;
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

    public boolean isFinalizoMantenimiento() {
        return finalizoMantenimiento;
    }

    public void setFinalizoMantenimiento(boolean finalizoMantenimiento) {
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
    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "                Cedula: " + getCedula() + "\n"
                + "                Nombres: " + getNombre() + "\n"
                + "                Apellidos: " + getApellido() + "\n"
                + "                Teléfono: " + getTelefono() + "\n"
                + "                Email: " + getCorreoElectronico() + "\n"
                + "                Dirección: " + getDireccion() + "\n"
                + "                Matrícula: " + getMatricula() + "\n"
                + "                Carrera: " + getCarrera().getNombre() + "\n"
                + "                Nivel de Carrera: " + getNivelCarrera() + "\n";
    }

    public void realizarMantenimiento(Computadora comp, Mantenimiento mant) {
        DetalleMantenimientoControlador detMantC = new DetalleMantenimientoControlador();
        DetalleMantenimiento detMant = new DetalleMantenimiento();
        String opcMant;
        Scanner s = new Scanner(System.in);
        do {
            Funciones.cls();
            System.out.println(String.format("""
                                                            --------------------------------------------
                                                               Mantenimiento Computadora - %s            
                                                            --------------------------------------------
                                                              Tipo de mantenimiento:
                                                                1. Mantenimiento Preventivo
                                                                2. Mantenimiento Correctivo
                                             """, comp.getCodigo()));
            System.out.print("            Opción: ");
            opcMant = s.next();
            switch (opcMant) {
                //Mantenimiento Preventivo
                case "1" -> {
                    do{
                        String opcMantP;
                        Funciones.cls();
                        System.out.println(String.format("""
                                                            -----------------------------------------------------
                                                               Mantenimiento Preventivo Computadora - %s            
                                                            -----------------------------------------------------
                                                              ¿Hubo algún inconveniente?
                                                                1. Sin problemas
                                                                2. Problemas con Hardware
                                                                3. Problemas con Software
                                             """, comp.getCodigo()));
                        System.out.print("            Opción: ");
                        opcMantP = s.next();
                        switch (opcMantP){
                            case "1" -> {
                                detMant.setDescripcion(getCedula() + "/-/" 
                                        + comp.getCodigo() + "/-/" 
                                        + "MANTENIMIENTO PREVENTIVO/-/SIN PROBLEMAS/-/" 
                                        + comp.getIdComputadora());
                                detMant.setComputadora(comp);
                                detMantC.crearDetalleMantenimiento(detMant, mant.getIdMantenimiento());
                                break;
                            }
                            case "2" -> {
                                do{
                                    String opcPH;
                                    Funciones.cls();
                                    System.out.println(String.format("""
                                                                        -----------------------------------------------------
                                                                           Mantenimiento Preventivo Computadora - %s            
                                                                        -----------------------------------------------------
                                                                          Seleccione el Hardware con problemas:
                                                         """, comp.getCodigo()));

                                    for (int i = 1; i <= comp.getHardware().size(); i++) {
                                        String[] hard = comp.getHardware().get(i-1).getDescripcion().split("/-/");

                                        String nombre;
                                        nombre = switch (hard.length) {
                                            case 1 -> hard[0];
                                            case 2 -> hard[0] + " - " + hard[1];
                                            default -> hard[0] + " - " + hard[1] + "GB";
                                        };

                                        System.out.println("                   " + i + ". " 
                                                + comp.getHardware().get(i-1).getTipo().getDescripcion() 
                                                + " - " +nombre);
                                    }
                                    
                                    System.out.print("            Opción: ");
                                    opcPH = s.next();
                                    int idHardware;

                                    if(Funciones.isValidNumeric(opcPH)){
                                        int x = Integer.parseInt(opcPH);
                                        if(x <= comp.getHardware().size()){
                                            for (int i = 1; i <= comp.getHardware().size(); i++) {
                                                if(x == i){
                                                    idHardware = comp.getHardware().get(i-1).getIdHarware();
                                                    String opcPHC;
                                                    do{
                                                        System.out.println(String.format("""
                                                                                            -----------------------------------------------------
                                                                                               Mantenimiento Preventivo Computadora - %s            
                                                                                            -----------------------------------------------------
                                                                                              ¿Qué problemas tuvo?
                                                                                                1. El componente no funciona
                                                                                                2. El componente necesita reparación
                                                                                                3. Regresar
                                                                             """, comp.getCodigo()));
                                                        System.out.print("            Opción: ");
                                                        opcPHC = s.next();
                                                        switch (opcPHC) {
                                                            case "1" -> {
                                                                detMant.setDescripcion(getCedula() + "/-/" 
                                                                        + comp.getCodigo() + "/-/" 
                                                                        + "MANTENIMIENTO PREVENTIVO/-/PROBLEMAS HARDWARE" + "/-/" 
                                                                        + idHardware);
                                                                HardwareControlador hadC = new HardwareControlador();
                                                                hadC.editarHardwareEstado(idHardware, 3);
                                                                
                                                                int idTH = comp.getHardware().get(i-1).getTipo().getIdTipoHardware();
                                                                if(idTH == 3 || idTH == 4 || idTH == 6 || idTH == 7){
                                                                    ComputadoraControlador compC = new ComputadoraControlador();
                                                                    compC.editarComputadoraEstado(comp.getIdComputadora(), 4);
                                                                }
                                                                break;
                                                            }
                                                            case "2" -> {
                                                                detMant.setDescripcion(getCedula() + "/-/" 
                                                                        + comp.getCodigo() + "/-/" 
                                                                        + "MANTENIMIENTO PREVENTIVO/-/PROBLEMAS HARDWARE" + "/-/" 
                                                                        + idHardware);
                                                                HardwareControlador hadC = new HardwareControlador();
                                                                hadC.editarHardwareEstado(idHardware, 2);
                                                                
                                                                int idTH = comp.getHardware().get(i-1).getTipo().getIdTipoHardware();
                                                                if(idTH == 3 || idTH == 4 || idTH == 6 || idTH == 7){
                                                                    ComputadoraControlador compC = new ComputadoraControlador();
                                                                    compC.editarComputadoraEstado(comp.getIdComputadora(), 4);
                                                                }
                                                                break;
                                                            }
                                                            case "3" -> {
                                                                Funciones.cls();
                                                                System.out.println("Regresando...");
                                                                Funciones.pause();
                                                                break;
                                                            }
                                                            default -> {
                                                                Funciones.cls();
                                                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                                                Funciones.pause();
                                                                continue;
                                                            }
                                                        }
                                                    }while(!"1".equals(opcPHC) && !"2".equals(opcPHC) && !"3".equals(opcPHC));
                                                }
                                            }
                                        }else{
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                            continue;
                                        }
                                    }else{
                                        Funciones.cls();
                                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                                        Funciones.pause();
                                        continue;
                                    }

                                    detMant.setComputadora(comp);
                                    detMantC.crearDetalleMantenimiento(detMant, mant.getIdMantenimiento());
                                    
                                    String opcYN;
                                    do{
                                        Funciones.cls();
                                        System.out.print("¿Tuvo otro problema con el hardware? [Y/n] ");
                                        opcYN = s.next();
                                        switch (opcYN) {
                                            case "Y" -> {
                                                break;
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
                                    }while("Y".equals(opcYN) && "n".equals(opcYN));
                                    
                                    if("n".equals(opcYN)){
                                        break;
                                    }
                                }while(true);
                                break;
                            }
                            case "3" -> {
                                do{
                                    String opcPS;
                                    Funciones.cls();
                                    System.out.println(String.format("""
                                                                        -----------------------------------------------------
                                                                           Mantenimiento Preventivo Computadora - %s            
                                                                        -----------------------------------------------------
                                                                          Seleccione el Software con problemas:
                                                         """, comp.getCodigo()));

                                    for (int i = 1; i <= comp.getSoftware().size(); i++) {
                                        System.out.println("                   " + i + ". " 
                                                + comp.getSoftware().get(i-1).getTipo().getDescripcion() 
                                                + " - " + comp.getSoftware().get(i-1).getNombre());
                                    }
                                    
                                    System.out.print("            Opción: ");
                                    opcPS = s.next();
                                    int idSoftware = 0;

                                    if(Funciones.isValidNumeric(opcPS)){
                                        int x = Integer.parseInt(opcPS);
                                        if(x <= comp.getSoftware().size()){
                                            for (int i = 1; i <= comp.getSoftware().size(); i++) {
                                                if(x == i){
                                                    idSoftware = comp.getSoftware().get(i-1).getIdSoftware();
                                                    String opcPSC;
                                                    do{
                                                        System.out.println(String.format("""
                                                                                            -----------------------------------------------------
                                                                                               Mantenimiento Preventivo Computadora - %s            
                                                                                            -----------------------------------------------------
                                                                                              ¿Qué problemas tuvo?
                                                                                                1. El software no funciona
                                                                                                2. El software necesita reparación
                                                                                                3. Regresar
                                                                             """, comp.getCodigo()));
                                                        System.out.print("            Opción: ");
                                                        opcPSC = s.next();
                                                        switch (opcPSC) {
                                                            case "1" -> {
                                                                detMant.setDescripcion(getCedula() + "/-/" 
                                                                        + comp.getCodigo() + "/-/" 
                                                                        + "MANTENIMIENTO PREVENTIVO/-/PROBLEMAS SOFTWARE" + "/-/" 
                                                                        + idSoftware);
                                                                SoftwareControlador softC = new SoftwareControlador();
                                                                softC.editarSoftwareEstado(idSoftware, 3);
                                                                
                                                                int idTS = comp.getSoftware().get(i-1).getTipo().getIdTipoSoftware();
                                                                if(idTS == 1){
                                                                    ComputadoraControlador compC = new ComputadoraControlador();
                                                                    compC.editarComputadoraEstado(comp.getIdComputadora(), 4);
                                                                }
                                                                break;
                                                            }
                                                            case "2" -> {
                                                                detMant.setDescripcion(getCedula() + "/-/" 
                                                                        + comp.getCodigo() + "/-/" 
                                                                        + "MANTENIMIENTO PREVENTIVO/-/PROBLEMAS SOFTWARE" + "/-/" 
                                                                        + idSoftware);
                                                                SoftwareControlador softC = new SoftwareControlador();
                                                                softC.editarSoftwareEstado(idSoftware, 2);
                                                                
                                                                int idTS = comp.getSoftware().get(i-1).getTipo().getIdTipoSoftware();
                                                                if(idTS == 1){
                                                                    ComputadoraControlador compC = new ComputadoraControlador();
                                                                    compC.editarComputadoraEstado(comp.getIdComputadora(), 4);
                                                                }
                                                                break;
                                                            }
                                                            case "3" -> {
                                                                Funciones.cls();
                                                                System.out.println("Regresando...");
                                                                Funciones.pause();
                                                                break;
                                                            }
                                                            default -> {
                                                                Funciones.cls();
                                                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                                                Funciones.pause();
                                                                continue;
                                                            }
                                                        }
                                                        break;
                                                    }while(!"1".equals(opcPSC) && !"2".equals(opcPSC) && !"3".equals(opcPSC));
                                                }
                                            }
                                        }else{
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                            continue;
                                        }
                                    }else{
                                        Funciones.cls();
                                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                                        Funciones.pause();
                                        continue;
                                    }

                                    detMant.setComputadora(comp);
                                    detMantC.crearDetalleMantenimiento(detMant, mant.getIdMantenimiento());
                                    
                                    String opcYN;
                                    do{
                                        Funciones.cls();
                                        System.out.print("¿Tuvo otro problema con el software? [Y/n] ");
                                        opcYN = s.next();
                                        switch (opcYN) {
                                            case "Y" -> {
                                                break;
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
                                    }while("Y".equals(opcYN) && "n".equals(opcYN));
                                    
                                    if("n".equals(opcYN)){
                                        break;
                                    }
                                }while(true);
                                break;
                            }
                            default -> {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        }
                        break;
                    }while(true);
                    break;
                }
                //Mantenimiento Correctivo
                case "2" -> {
                    do{
                        String opcMantC;
                        Funciones.cls();
                        System.out.println(String.format("""
                                                            -----------------------------------------------------
                                                               Mantenimiento Correctivo Computadora - %s            
                                                            -----------------------------------------------------
                                                              Qué se tuvo que corregir:
                                                                1. Hardware
                                                                2. Software
                                             """, comp.getCodigo()));
                        System.out.print("            Opción: ");
                        opcMantC = s.next();
                        switch (opcMantC){

                            case "1" -> {
                                do{
                                    String opcPH;
                                    Funciones.cls();
                                    System.out.println(String.format("""
                                                                        -----------------------------------------------------
                                                                           Mantenimiento Correctivo Computadora - %s            
                                                                        -----------------------------------------------------
                                                                          Seleccione el Hardware que corrigió:
                                                         """, comp.getCodigo()));

                                    for (int i = 1; i <= comp.getHardware().size(); i++) {
                                        String[] hard = comp.getHardware().get(i-1).getDescripcion().split("/-/");
                                        
                                        String nombre;
                                        nombre = switch (hard.length) {
                                            case 1 -> hard[0];
                                            case 2 -> hard[0] + " - " + hard[1];
                                            default -> hard[0] + " - " + hard[1] + "GB";
                                        };

                                        System.out.println("                   " + i + ". " 
                                                + comp.getHardware().get(i-1).getTipo().getDescripcion() 
                                                + " - " + nombre + " - " + comp.getHardware().get(i-1).getEstado().getDescripcion());
                                    }
                                    
                                    System.out.print("            Opción: ");
                                    opcPH = s.next();
                                    int idHardware;

                                    if(Funciones.isValidNumeric(opcPH)){
                                        int x = Integer.parseInt(opcPH);
                                        if(x <= comp.getHardware().size()){
                                            for (int i = 1; i <= comp.getHardware().size(); i++) {
                                                if(x == i){
                                                    idHardware = comp.getHardware().get(i-1).getIdHarware();
                                                    detMant.setDescripcion(getCedula() + "/-/" 
                                                                + comp.getCodigo() + "/-/" 
                                                                + "MANTENIMIENTO CORRECTIVO/-/CORREGIR HARDWARE" + "/-/" 
                                                                + idHardware);
                                                    HardwareControlador hadC = new HardwareControlador();
                                                    hadC.editarHardwareEstado(idHardware, 1);
                                                }
                                                int idTH = comp.getHardware().get(i-1).getTipo().getIdTipoHardware();
                                                if(idTH == 3 || idTH == 4 || idTH == 6 || idTH == 7){
                                                    ComputadoraControlador compC = new ComputadoraControlador();
                                                    if(comp.getHardware().get(i-1).getEstado().getIdEstado() == 2 || comp.getHardware().get(i-1).getEstado().getIdEstado() == 3){
                                                        compC.editarComputadoraEstado(comp.getIdComputadora(), 4);
                                                    }else{
                                                        compC.editarComputadoraEstado(comp.getIdComputadora(), 1);
                                                    }
                                                }
                                            }
                                        }else{
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                            continue;
                                        }
                                    }else{
                                        Funciones.cls();
                                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                                        Funciones.pause();
                                        continue;
                                    }

                                    detMant.setComputadora(comp);
                                    detMantC.crearDetalleMantenimiento(detMant, mant.getIdMantenimiento());
                                    
                                    String opcYN;
                                    do{
                                        Funciones.cls();
                                        System.out.print("¿Corrigió otro hardware? [Y/n] ");
                                        opcYN = s.next();
                                        switch (opcYN) {
                                            case "Y" -> {
                                                break;
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
                                    }while("Y".equals(opcYN) && "n".equals(opcYN));
                                    
                                    if("n".equals(opcYN)){
                                        break;
                                    }
                                }while(true);
                                break;
                            }
                            case "2" -> {
                                do{
                                    String opcPS;
                                    Funciones.cls();
                                    System.out.println(String.format("""
                                                                        -----------------------------------------------------
                                                                           Mantenimiento Preventivo Computadora - %s            
                                                                        -----------------------------------------------------
                                                                          Seleccione el Software que corrigió:
                                                         """, comp.getCodigo()));

                                    for (int i = 1; i <= comp.getSoftware().size(); i++) {
                                        System.out.println("                   " + i + ". " 
                                                + comp.getSoftware().get(i-1).getTipo().getDescripcion() 
                                                + " - " + comp.getSoftware().get(i-1).getNombre()
                                                + " - " + comp.getSoftware().get(i-1).getEstado().getDescripcion());
                                    }
                                    
                                    System.out.print("            Opción: ");
                                    opcPS = s.next();
                                    int idSoftware = 0;

                                    if(Funciones.isValidNumeric(opcPS)){
                                        int x = Integer.parseInt(opcPS);
                                        if(x <= comp.getSoftware().size()){
                                            for (int i = 1; i <= comp.getSoftware().size(); i++) {
                                                if(x == i){
                                                    idSoftware = comp.getSoftware().get(i-1).getIdSoftware();
                                                    detMant.setDescripcion(getCedula() + "/-/" 
                                                                + comp.getCodigo() + "/-/" 
                                                                + "MANTENIMIENTO CORRECTIVO/-/CORREGIR SOFTWARE" + "/-/" 
                                                                + idSoftware);
                                                    SoftwareControlador softC = new SoftwareControlador();
                                                    softC.editarSoftwareEstado(idSoftware, 1);
                                                }
                                                int idTS = comp.getSoftware().get(i-1).getTipo().getIdTipoSoftware();
                                                if(idTS == 3 || idTS == 4 || idTS == 6 || idTS == 7){
                                                    ComputadoraControlador compC = new ComputadoraControlador();
                                                    if(comp.getSoftware().get(i-1).getEstado().getIdEstado() == 2 || comp.getSoftware().get(i-1).getEstado().getIdEstado() == 3){
                                                        compC.editarComputadoraEstado(comp.getIdComputadora(), 4);
                                                    }else{
                                                        compC.editarComputadoraEstado(comp.getIdComputadora(), 1);
                                                    }
                                                }
                                            }
                                        }else{
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                            continue;
                                        }
                                    }else{
                                        Funciones.cls();
                                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                                        Funciones.pause();
                                        continue;
                                    }

                                    detMant.setComputadora(comp);
                                    detMantC.crearDetalleMantenimiento(detMant, mant.getIdMantenimiento());
                                    
                                    String opcYN;
                                    do{
                                        Funciones.cls();
                                        System.out.print("¿Tuvo otro problema con el software? [Y/n] ");
                                        opcYN = s.next();
                                        switch (opcYN) {
                                            case "Y" -> {
                                                break;
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
                                    }while("Y".equals(opcYN) && "n".equals(opcYN));
                                    
                                    if("n".equals(opcYN)){
                                        break;
                                    }
                                }while(true);
                                break;
                            }
                            default -> {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        }
                        break;
                    }while(true);
                    break;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                }
            }
        } while (!"1".equals(opcMant) && !"2".equals(opcMant) && !"3".equals(opcMant));
    }

    public void registrarComputadora(Laboratorio lab) throws IOException {

        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        ComputadoraControlador compC = new ComputadoraControlador();
        Computadora comp = new Computadora();

        String titulo = """
                                --------------------------------------------
                                           Registro de computadora            
                                --------------------------------------------
                        """;

        //Campo - Código
        do {
            Funciones.cls();
            System.out.println(titulo);
            System.out.print("          Ingrese el código de la computadora: ");
            String codigo = s.next();
            if (compC.existeComputadora(codigo) == false) {
                if (Funciones.isValidCode(codigo)) {
                    comp.setCodigo(codigo.toUpperCase());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar espacios ni caracteres especiales.");
                    System.out.println("Ingrese el código nuevamente.");
                    Funciones.pause();
                }
            } else {
                Funciones.cls();
                System.out.println("¡ERROR! La computadora que quieres ingresar ya esta registrada.");
                System.out.println("Ingrese una computadora nueva.");
                Funciones.pause();
            }
        } while (true);

        //Campo - Marca
        do {
            Funciones.cls();
            System.out.println(titulo);
            System.out.print("          Ingrese la marca de la computadora: ");
            String marca = br.readLine();
            if (Funciones.isValidText(marca)) {
                comp.setMarca(marca.toUpperCase());
                break;
            } else {
                Funciones.cls();
                System.out.println("¡ERROR! No puede ingresar espacios ni caracteres especiales.");
                System.out.println("Ingrese el código nuevamente.");
                Funciones.pause();
            }
        } while (true);

        comp.setMantRealizado(false);

        Estado esta = new Estado();
        esta.setIdEstado(1);
        comp.setEstado(esta);

        comp.agregarHardware();
        comp.agregarSoftware();

        compC.crearComputadora(comp, lab.getIdLaboratorio());
        SoftwareControlador softC = new SoftwareControlador();
        HardwareControlador hardC = new HardwareControlador();
        
        softC.softwareComputadora(comp.getCodigo());
        hardC.hardwareComputadora(comp.getCodigo());
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimiento(lab);
        
        realizarMantenimiento(comp, mant);
        Funciones.pause();
    }

    public void verificarMantenimiento() {
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimientoEstudiante(getIdEstudiante());
        //Conexión
        ConexionBDD conexion = new ConexionBDD();
        Connection connection = (Connection) conexion.conectar();
        PreparedStatement ejecutar;
        ResultSet resultado;
        try {
            String consulta = "SELECT SUBSTRING_INDEX(det_mant_obj, '/-/', 1), "
                    + "SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 2), '/-/', -1) AS comp_codigo, "
                    + "SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 3), '/-/', -1) AS mant_tipo, "
                    + "SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 4), '/-/', -1) AS mant_obj, "
                    + "SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 5), '/-/', -1) AS id "
                    + "FROM detalle_mantenimiento "
                    + "WHERE SUBSTRING_INDEX(det_mant_obj, '/-/', 1) = '" + getCedula() + "' "
                    + "AND mant_id = " + mant.getIdMantenimiento() + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                Funciones.cls();
                while (resultado.next()) {
                    String codigo = resultado.getString("comp_codigo");
                    String tipo = resultado.getString("mant_tipo");
                    String descripcion = resultado.getString("mant_obj");
                    System.out.println("Código computadora: " + codigo + "\n" 
                            + "    Tipo de mantenimiento: " + tipo + "\n" 
                            + "    Descripción: " + descripcion + "\n" );
                }
                Funciones.pause();
            } else {
                Funciones.cls();
                System.out.println("Aún no haz realizado ningún mantenimiento a alguna computadora.");
                Funciones.pause();
            }
            
            resultado.close();
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
    }

    public void finalizarMantenimiento() {
        Scanner s = new Scanner(System.in);
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimientoEstudiante(getIdEstudiante());
        
        //Conexión
        ConexionBDD conexion = new ConexionBDD();
        Connection connection = (Connection) conexion.conectar();
        PreparedStatement ejecutar;
        ResultSet resultado;
        
        String opc;
        do{
            System.out.print("¿Está seguro de finalizar el mantenimiento? [Y/n] ");
            opc = s.next();
            switch (opc) {
                case "Y" -> {
                    try {
                        EstudianteControlador estC = new EstudianteControlador();
                        
                        String consulta = "SELECT SUBSTRING_INDEX(det_mant_obj, '/-/', 1), comp_id "
                                + "FROM detalle_mantenimiento "
                                + "WHERE SUBSTRING_INDEX(det_mant_obj, '/-/', 1) = '" + getCedula() + "' "
                                + "AND mant_id = " + mant.getIdMantenimiento() + ";";
                        ejecutar = (PreparedStatement) connection.prepareCall(consulta);
                        resultado = ejecutar.executeQuery(consulta);

                        if(resultado.next()){
                            while (resultado.next()) {
                                ComputadoraControlador compC = new ComputadoraControlador();
                                int idComputadora = resultado.getInt("comp_id");
                                compC.editarComputadoraMantenimiento(idComputadora);
                            }
                            estC.actualizarEstudianteMantenimiento(getMatricula(), true);
                        } else {
                            Funciones.cls();
                            System.out.println("Aún no haz realizado ningún mantenimiento a alguna computadora.");
                            Funciones.pause();
                        }
                        resultado.close();
                    } catch (Exception e) {
                        System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                                + "PARA SOLUCIONAR SU PROBLEMA: " + e);
                    }
                }
                case "n" -> {
                    Funciones.cls();
                    System.out.println("Regresando...");
                    Funciones.pause();
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                    continue;
                }
            }
            break;
        }while(true);
        
        
    }
    
    public void editarDatosPersonales() throws IOException {

        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        EstudianteControlador estc = new EstudianteControlador();
        CarreraControlador carc = new CarreraControlador();

        Estudiante estTemp = new Estudiante();

        String titulo = """
                    --------------------------------------------------
                                    Editar información
                    --------------------------------------------------                
        """;
        String opc;

        estTemp.setCedula(getCedula());

        do {
            //Campo -Nombres
            do {
                Funciones.cls();
                System.out.println("Sus nombres son: " + getNombre());
                System.out.print("¿Desea cambiar sus nombres? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Ingrese sus nombres: ");
                            String nombres = s.next();
                            if (Funciones.isValidText(nombres) == false) {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                                        + "especiales o números, ingrese su nombres nuevamente.");
                                Funciones.pause();
                            } else {
                                estTemp.setNombre(nombres.toUpperCase());
                                break;
                            }
                        } while (true);
                        opc = "";
                        break;
                    }
                    case "n" -> {
                        estTemp.setNombre(getNombre());
                        break;
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Apellidos
            do {
                Funciones.cls();
                System.out.println("Sus apellidos son: " + getApellido());
                System.out.print("¿Desea cambiar sus apellidos? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Ingrese sus apellidos: ");
                            String apellidos = br.readLine();
                            if (Funciones.isValidText(apellidos) == false) {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                                        + "especiales o números, ingrese su apellidos nuevamente.");
                                Funciones.pause();
                            } else {
                                estTemp.setApellido(apellidos.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setApellido(getApellido());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Email
            do {
                Funciones.cls();
                System.out.println("Su email es: " + getCorreoElectronico());
                System.out.print("¿Desea cambiar su email? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Ingrese su email: ");
                            String email = br.readLine();
                            if (Funciones.isValidEmail(email) == false) {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! El correo eléctronico ingresado "
                                        + "es incorrecto, ingrese su correo nuevamente.");
                                Funciones.pause();
                            } else {
                                estTemp.setCorreoElectronico(email);
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setCorreoElectronico(
                                getCorreoElectronico()
                        );
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Dirección
            do {
                Funciones.cls();
                System.out.println("Su dirección es: " + getDireccion());
                System.out.print("¿Desea cambiar su dirección? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Ingrese su dirección: ");
                            String direccion = br.readLine();
                            if (Funciones.isValidAdress(direccion) == false) {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                                        + "especiales, ingrese su dirección nuevamente.");
                                Funciones.pause();
                            } else {
                                estTemp.setDireccion(direccion.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setDireccion(getDireccion());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Teléfono
            do {
                Funciones.cls();
                System.out.println("Su teléfono es: " + getTelefono());
                System.out.print("¿Desea cambiar su teléfono? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Ingrese su teléfono: ");
                            String telefono = br.readLine();
                            if (Funciones.isValidNumeric(telefono) == false) {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                                        + "especiales o letras, ingrese su teléfono nuevamente.");
                                Funciones.pause();
                            } else {
                                if (telefono.length() == 10) {
                                    estTemp.setTelefono(telefono);
                                    break;
                                } else {
                                    Funciones.cls();
                                    System.out.println("    ¡ERROR! El telefono ingresado es incorrecto. "
                                            + " Ingrese su teléfono nuevamente.");
                                    Funciones.pause();
                                }
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setTelefono(getTelefono());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Matrícula
            do {
                Funciones.cls();
                System.out.println("Su matrícula es: " + getMatricula());
                System.out.print("¿Desea cambiar su matrícula? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Ingrese su matrícula: ");
                            String matricula = br.readLine();
                            if (Funciones.isValidCode(matricula) == false) {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                                        + "especiales, ingrese su matrícula nuevamente.");
                                Funciones.pause();
                            } else {
                                if (estc.existeMatricula(matricula.toUpperCase())) {
                                    Funciones.cls();
                                    System.out.println("""
                                                           ¡ERROR! Ingrese su matrícula, no puede ingresar la matrícula
                                                                                de otro estudiante.
                                                       """);
                                    Funciones.pause();
                                } else {
                                    estTemp.setMatricula(matricula.toUpperCase());
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setMatricula(getMatricula());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Carrera
            do {
                Funciones.cls();
                System.out.println("Su carrera es: " + getCarrera().getNombre());
                System.out.print("¿Desea cambiar su carrera? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            ArrayList<Carrera> listadoCarreras = carc.listarCarreras();
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Seleccione su carrera: \n");
                            for (Carrera p : listadoCarreras) {
                                System.out.print("   "
                                        + p.getIdCarrera() + ". "
                                        + p.getNombre() + ".\n");
                            }
                            System.out.print("  Opcion: ");
                            String opcCar = s.next();

                            if (Funciones.isValidNumeric(opcCar)) {
                                int a = Integer.parseInt(opcCar);
                                if (a > listadoCarreras.size()) {
                                    Funciones.cls();
                                    System.out.println("    ¡ERROR! Ingrese una opción correcta.");
                                    Funciones.pause();
                                } else {
                                    for (int i = 1; i <= listadoCarreras.size(); i++) {
                                        String x = Integer.toString(i);
                                        if (x.equals(opcCar)) {
                                            estTemp.setCarrera(listadoCarreras.get(i - 1));
                                        }
                                    }
                                    break;
                                }
                            } else {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setCarrera(getCarrera());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Nivel Carrera
            do {
                Funciones.cls();
                System.out.println("Su nivel de carrera es: " + getNivelCarrera());
                System.out.print("¿Desea cambiar su nivel? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Ingrese su nivel de la carrera: ");
                            String nivel = s.next();
                            if (Funciones.isValidNumeric(nivel) == false) {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                                        + "especiales o letras, ingrese el nivel nuevamente.");
                                Funciones.pause();
                            } else {
                                int nivelCarrera = Integer.parseInt(nivel);
                                if (nivelCarrera > 5 || nivelCarrera < 1) {
                                    Funciones.cls();
                                    System.out.println("    ¡ERROR! Ingrese un nivel correcto.");
                                    Funciones.pause();
                                } else {
                                    estTemp.setNivelCarrera(nivelCarrera);
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setNivelCarrera(getNivelCarrera());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Clave
            do {
                Funciones.cls();
                System.out.print("¿Desea cambiar su contraseña? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.println("""
                                                           La contraseña debe ser de 5 a 24 caracteres, puede
                                                               contener caracteres especiales como: ._@#*$%+-
                                                      ¡En caso de olvidar su contraseña, contactese con el administrador!
                                               """);
                            System.out.print("  Ingrese su clave: ");
                            String clave = br.readLine();
                            if (Funciones.isValidPassword(clave)) {
                                if (clave.length() > 24 || clave.length() < 5) {
                                    Funciones.cls();

                                    System.out.println("""
                                                           ¡ERROR! La clave ingresada no cumple con los 
                                                            parametros dados. Ingrese su clave de nuevo.
                                                       """);
                                    Funciones.pause();
                                } else {
                                    estTemp.setClave(clave);
                                    break;
                                }
                            } else {
                                Funciones.cls();
                                System.out.println("""
                                                       ¡ERROR! La clave ingresada no cumple con los 
                                                        parametros dados. Ingrese su clave de nuevo.
                                                   """);
                                Funciones.pause();
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setClave(getClave());
                        break;
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            Funciones.cls();
            estc.actualizarEstudiante(estTemp, getMatricula());

            System.out.println(titulo);
            System.out.println("""
                                                      ¡DATOS CAMBIADOS CON ÉXITO! 
                                    Sus datos fueron cambiados con éxito, no comparta esta información
                                                               con nadie
                               """);
            Funciones.pause();
            break;
        } while (true);
    }

}
