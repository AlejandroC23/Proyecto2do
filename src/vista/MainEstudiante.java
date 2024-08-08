/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Funciones;
import controlador.CarreraControlador;
import controlador.EstudianteControlador;
import controlador.UsuarioControlador;
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Usuario;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import modelo.Carrera;

/**
 *
 * @author Alejandro
 */
public class MainEstudiante {

    //Impresión de menú de inicio de sesión y registro
    public static void menuLogeoEstudiante() {
        System.out.println("""
        -------------------------------------------
                       Logeo Estudiante            
        -------------------------------------------
            1. Iniciar Sesión            
            2. Registrarse
            3. Contactarse con un administrador
            4. Regresar
        -------------------------------------------
        """.indent(30));
        System.out.print("  Opcion: ");
    }

    //Menú de inicio de sesión y registro
    public static Estudiante logeoEstudiante() throws IOException {
        //Variables
        Scanner s = new Scanner(System.in);
        Estudiante est = new Estudiante();
        String opcEst;
        do {
            //Menú Logeo Estudiante
            Funciones.cls();
            menuLogeoEstudiante();
            opcEst = s.next();

            switch (opcEst) {
                //Inicio de sesión
                case "1" -> {
                    do {
                        Funciones.cls();
                        est = inicioSesion();
                        if ("0".equals(est.getCedula())) {
                            Funciones.cls();
                            System.out.println("Usuario o contraseña incorrecto...\n");
                            System.out.print("¿Desea regresar al menú anterior? [Y/n] ");
                            String opcYN = s.next();
                            if ("Y".equals(opcYN)) {
                                break;
                            } else if ("n".equals(opcYN)) {
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        } else {
                            return est;
                        }
                    } while (true);
                    break;
                }
                //Registrarse
                case "2" -> {
                    Funciones.cls();
                    registro();
                    break;
                }
                //Regresar
                case "3" -> {
                    continue;
                }
                case "4" -> {
                    System.out.println("Regresando...");
                    return null;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                }
            }
        } while (!"3".equals(opcEst));
        return est;
    }

    //Inicio de sesión de usuario
    public static Estudiante inicioSesion() throws IOException {
        EstudianteControlador estC = new EstudianteControlador();
        Estudiante est = new Estudiante();

        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);

        Scanner s = new Scanner(System.in);
        System.out.println("""
                    --------------------------------------
                                Iniciar Sesión            
                    --------------------------------------
        """);
        System.out.print("     Usuario: ");
        String usuario = br.readLine();
        System.out.print("     Clave: ");
        String clave = br.readLine();

        if (Funciones.isValidNumeric(usuario) && Funciones.isValidPassword(clave)) {
            est = estC.buscarEstudiante(usuario, clave);
        } else {
            est.setCedula("0");
        }

        return est;
    }

    //Registro de usuario
    public static void registro() throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        EstudianteControlador estc = new EstudianteControlador();
        UsuarioControlador usuc = new UsuarioControlador();
        CarreraControlador carc = new CarreraControlador();

        Usuario usu = new Usuario();
        Estudiante est = new Estudiante();
        String titulo = """
                    --------------------------------------------------
                                      Registro Cuenta
                    --------------------------------------------------                
        """;

        do {
            //Campo - Cédula
            do {
                Funciones.cls();
                System.out.print(titulo);
                System.out.println("                    ¡Recuerde que este será tu usuario!");
                System.out.print("  Ingrese su cédula: ");
                String cedula = s.next();
                if (usu.validarCedula(cedula)) {
                    if (usuc.existeCuenta(cedula)) {
                        Funciones.cls();
                        System.out.println("""
                                            ¡ERROR! Ya existe una cuenta registrada en el sistema con esta cédula
                                            En caso de haber olvidado la clave. Contactese con el administrador.
                                           """);
                        Funciones.pause();
                        main(null);
                        break;
                    } else {
                        usu.setCedula(cedula);
                        break;
                    }
                } else {
                    Funciones.cls();
                    System.out.println("""
                                                ¡ERROR! Ingrese una cédula correcta.
                                           """);
                    Funciones.pause();
                }
            } while (true);

            //Campo - Nombre
            do {
                Funciones.cls();
                System.out.print(titulo);
                System.out.print("  Ingrese sus nombres: ");
                String nombres = br.readLine();
                if (Funciones.isValidText(nombres) == false) {
                    Funciones.cls();
                    System.out.println("    ¡ERROR! No puede ingresar caracteres "
                            + "especiales o números, ingrese su nombres nuevamente.");
                    Funciones.pause();
                } else {
                    usu.setNombre(nombres.toUpperCase());
                    break;
                }
            } while (true);

            //Campo - Apellidos
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
                    usu.setApellido(apellidos.toUpperCase());
                    break;
                }
            } while (true);

            //Campo - Email
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
                    Funciones.cls();
                    usu.setCorreoElectronico(email);
                    break;
                    
                }
            } while (true);

            //Campo - Dirección
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
                    usu.setDireccion(direccion.toUpperCase());
                    break;
                }
            } while (true);

            //Campo - Teléfono
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
                        usu.setTelefono(telefono);
                        break;
                    } else {
                        Funciones.cls();
                        System.out.println("    ¡ERROR! El telefono ingresado es incorrecto. "
                                + " Ingrese su teléfono nuevamente.");
                        Funciones.pause();
                    }
                }
            } while (true);

            //Campo - Matrícula
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
                        est.setMatricula(matricula.toUpperCase());
                        break;
                    }
                }
            } while (true);

            est.setFinalizoMantenimiento(0);

            //Campo - Carrera
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
                    System.out.println(a);
                    Funciones.pause();
                    if (a > listadoCarreras.size()) {
                        Funciones.cls();
                        System.out.println("    ¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    } else {
                        for (int i = 1; i <= listadoCarreras.size(); i++) {
                            String x = Integer.toString(i);
                            Funciones.pause();
                            if (x.equals(opcCar)) {
                                est.setCarrera(listadoCarreras.get(i));
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

            //Campo - Nivel Carrera
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
                        Funciones.cls();
                        est.setNivelCarrera(nivelCarrera);
                        break;
                    }
                }
            } while (true);

            //Campo - Clave
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
                        usu.setClave(clave);
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

            usu.setRol(1);

            usuc.crearUsuario(usu);

            Funciones.cls();
            int idUsuario = usuc.buscarIdUsuario(usu.getCedula());
            est.setIdUsuario(idUsuario);

            estc.crearEstudiante(est);

            System.out.println(titulo);
            System.out.println("""
                                                     ¡CUENTA REGISTRADA CON EXITO! 
                                    Para poder ingresar al sistema, incie sesión con su nueva cuenta
                               """);
            Funciones.pause();
            break;
        } while (true);
    }

    //Mostrar información de cuenta
    public static void mostrarInfo(Estudiante est){
        Funciones.cls();
        System.out.println("""
                    --------------------------------------------------
                                   Información Personal                  
                    --------------------------------------------------
        """);
        System.out.println(est.toString());
        System.out.println("            --------------------------------------------------");
        Funciones.pause();
    }
    
    //Menú - Sin laboratorio asignado
    public static int subMenu1(Estudiante estudiante) throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                      Menú Principal                  
                    --------------------------------------------------
                           ¡No estas asignado a un laboratorio!       

                          Si ya estas asignado a un laboratorio       
                              y sigue apareciendo este menú         
                              ¡CONTACTA A UN ADMINISTRADOR!           

                        1. Mostrar información personal
                        2. Editar datos personales            
                        3. Recargar menú                 
                        4. Cerrar sesión               
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            String opc = s.next();
            switch (opc) {
                case "1" -> {
                    mostrarInfo(estudiante);
                }
                case "2" -> {
                    estudiante.editarDatosPersonales();
                }
                case "3" -> {
                    Funciones.cls();
                    System.out.println(" Recargando menú...\n");
                    Funciones.pause();
                    return 0;
                }
                case "4" -> {
                    Funciones.cls();
                    System.out.println(" Cerrando sesión...\n");
                    Funciones.pause();
                    return 1;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                    break;
                }
            }
        } while (true);
    }

    public static void main(String[] args) throws IOException {
        Estudiante est = logeoEstudiante();
        if (est != null) {
            do {
                EstudianteControlador estC = new EstudianteControlador();
                Estudiante estTemp = est;
                est = estC.buscarEstudiante(est.getCedula());
                if (est.getFinalizoMantenimiento() == 0) {
                    int flag = subMenu1(estTemp);
                    if (flag == 1) {
                        main(args);
                        break;
                    }
                }
            } while (true);
        }
    }
}
