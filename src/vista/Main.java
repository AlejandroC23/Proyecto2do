/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Funciones;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class Main {
    public static void introduccion() throws IOException {
        Funciones.cls();
        System.out.println( """
        ------------------------------------------------------------------------
          Bienvenido/a al Sistema de Mantenimientos a Laboratorios del IST 17J  
        ------------------------------------------------------------------------
                       El siguiente sistema tiene como finalidad                
              registrar los mantenimientos realizados por los estudiantes       
                             en prácticas preprofesionales.                     

             Para registrarse o iniciar sesión se utilizará su cédula como      
                   usuario de su cuenta, recuerde bien su contraseña.           
                  En caso de olvidarla contactese con el administrador          
                                      del sistema.                              
        ------------------------------------------------------------------------
        """.indent(20));
        Funciones.pause();
    }
    
    public static void menuPrincipal() throws IOException{
        System.out.println("""
        ------------------------------------------
                    Pantalla Principal            
        ------------------------------------------
            1. Logeo Estudiante            
            2. Logeo Docente Encargado            
            3. Logeo Administrador
            4. Salir
        ------------------------------------------
        """.indent(30));
        System.out.print("Opcion: ");
    }
    
    public static void main(String[] args) throws IOException {
        //Introducción del sistema
        introduccion();
        //Variables
        String opcMain;
        Scanner s = new Scanner(System.in);
        //Menú principal
        do{
            Funciones.cls();
            menuPrincipal();
            opcMain = s.next();
            if(null != opcMain) 
            switch (opcMain) {
                //Opción 1 - Estudiante
                case "1" -> {
                    MainEstudiante.main(args);
                }
                //Opción 2 - Encargado
                case "2" -> {
                    MainEncargado.main(args);
                }
                case "3" -> {
                    MainAdministrador.main();
                }
                case "4" -> {
                    System.out.println("Saliendo del programa...");
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                }
            }
        }while(!"4".equals(opcMain));
    }
}
