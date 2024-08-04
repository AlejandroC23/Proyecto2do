/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Alejandro
 */
public class Funciones {
    //Limpiar pantalla
    public static void cls2() {
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }
    
    public static void cls(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Comando para limpiar pantalla en Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para limpiar pantalla en Unix/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al intentar limpiar la pantalla: " + ex.getMessage());
        }
    }
    
    //Función para presionar la tecla Enter y continuar
    public static void pause2() {
        System.out.println("Presione Enter para continuar... ");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }
    
    public static void pause(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Comando para pausar en Windows
                new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
            } else {
                System.out.println("El comando 'pause' no es compatible con este sistema operativo.");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al intentar pausar la consola: " + ex.getMessage());
        }
    }
    
    public static boolean isValidNumeric(String texto) {
        String regex = "^[0-9]+$";
        return texto.matches(regex);
    }
    
    public static boolean isValidText(String texto) {
        String regex = "^[a-zA-Z]+( [a-zA-Z]+)*$";
        return texto.matches(regex);
    }
    
    public static boolean isValidPassword(String texto) {
        String regex = "^[a-zA-Z0-9._@#*$%+-]+$";
        return texto.matches(regex);
    }
    
    public static boolean isValidCode(String texto) {
        String regex = "^[a-zA-Z0-9_.-]+$";
        return texto.matches(regex);
    }
    
    public static boolean isValidAdress(String texto) {
        String regex = "^[a-zA-Z0-9]+( [a-zA-Z0-9-]+)*$";
        return Pattern.matches(regex, texto);
    }
    
    public static boolean isValidEmail(String texto) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, texto);
    }
    
    public static boolean isValidDate(String fecha) {
        String regex = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        return Pattern.matches(regex, fecha);
    }
}
