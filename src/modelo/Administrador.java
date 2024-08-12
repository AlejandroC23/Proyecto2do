/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.AdministradorControlador;
import controlador.EncargadoControlador;
import controlador.Funciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class Administrador extends Usuario {

    public Administrador() {
    }
    
    @Override
    public String toString() {
        return "                Cedula: " + getCedula() + "\n"
                + "                Nombres: " + getNombre() + "\n"
                + "                Apellidos: " + getApellido() + "\n"
                + "                Teléfono: " + getTelefono() + "\n"
                + "                Email: " + getCorreoElectronico() + "\n"
                + "                Dirección: " + getDireccion() + "\n";
    }
    
    public void editarDatosPersonales() throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        AdministradorControlador adminC = new AdministradorControlador();

        Administrador adminTemp = new Administrador();

        String titulo = """
                    --------------------------------------------------
                                    Editar información
                    --------------------------------------------------                
        """;
        String opc;

        adminTemp.setCedula(getCedula());

        do {
            //Campo - Nombres
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
                                adminTemp.setNombre(nombres.toUpperCase());
                                break;
                            }
                        } while (true);
                        opc = "";
                        break;
                    }
                    case "n" -> {
                        adminTemp.setNombre(getNombre());
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
                                adminTemp.setApellido(apellidos.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        adminTemp.setApellido(getApellido());
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
                                adminTemp.setCorreoElectronico(email);
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        adminTemp.setCorreoElectronico(getCorreoElectronico()
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
                                adminTemp.setDireccion(direccion.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        adminTemp.setDireccion(getDireccion());
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
                                    adminTemp.setTelefono(telefono);
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
                        adminTemp.setTelefono(getTelefono());
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
                                                     ¡En caso de olvidar su contraseña, contactese con otro administrador!
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
                                    adminTemp.setClave(clave);
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
                        adminTemp.setClave(getClave());
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
            adminC.editarEncargado(adminTemp);

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
