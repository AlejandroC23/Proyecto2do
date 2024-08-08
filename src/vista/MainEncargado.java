/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Funciones;
import controlador.EncargadoControlador;
import controlador.LaboratorioControlador;
import controlador.MantenimientoControlador;
import controlador.UsuarioControlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import modelo.Encargado;
import modelo.Laboratorio;
import modelo.Mantenimiento;

/**
 *
 * @author Alejandro
 */
public class MainEncargado {

    //Impresión de menú de inicio de sesión y registro
    public static void menuLogeoEncargado() {
        System.out.println("""
        -------------------------------------------
                       Logeo Encargado            
        -------------------------------------------
            1. Iniciar Sesión
            2. Contactarse con un administrador
            3. Regresar
        -------------------------------------------
        """.indent(30));
        System.out.print("  Opcion: ");
    }

    //Menú de inicio de sesión y registro
    public static Encargado logeoEncargado() throws IOException {
        //Variables
        Scanner s = new Scanner(System.in);
        Encargado enc = new Encargado();
        String opcEst;
        do {
            //Menú Logeo Encargado
            Funciones.cls();
            menuLogeoEncargado();
            opcEst = s.next();

            switch (opcEst) {
                //Inicio de sesión
                case "1" -> {
                    do {
                        Funciones.cls();
                        enc = inicioSesion();
                        if ("0".equals(enc.getCedula())) {
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
                            if ("0".equals(enc.getTelefono())) {
                                enc = registro(enc.getCedula());
                                return enc;
                            }
                            return enc;
                        }
                    } while (true);
                    break;
                }
                //Contacto con administrador
                case "2" -> {
                    Funciones.cls();
                    Funciones.pause();
                    break;
                }
                //Regresar
                case "3" -> {
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
        return enc;
    }

    //Inicio de sesión de usuario
    public static Encargado inicioSesion() throws IOException {
        EncargadoControlador encC = new EncargadoControlador();
        Encargado enc = new Encargado();

        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);

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
            enc = encC.buscarEncargado(usuario, clave);
        } else {
            enc.setCedula("0");
        }

        return enc;
    }

    //Registro de usuario
    public static Encargado registro(String cedula) throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        EncargadoControlador encc = new EncargadoControlador();
        UsuarioControlador usuc = new UsuarioControlador();

        Encargado enc = new Encargado();
        String titulo = """
                    --------------------------------------------------
                                      Registro Datos
                    --------------------------------------------------                
        """;

        do {
            //Campo - Nombre
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
                    enc.setNombre(nombres.toUpperCase());
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
                    enc.setApellido(apellidos.toUpperCase());
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
                    enc.setCorreoElectronico(email);
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
                    enc.setDireccion(direccion.toUpperCase());
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
                        enc.setTelefono(telefono);
                        break;
                    } else {
                        Funciones.cls();
                        System.out.println("    ¡ERROR! El telefono ingresado es incorrecto. "
                                + " Ingrese su teléfono nuevamente.");
                        Funciones.pause();
                    }
                }
            } while (true);

            //Campo - Título
            do {
                Funciones.cls();
                System.out.print(titulo);
                System.out.print("  Ingrese su título: ");
                String titulos = br.readLine();
                if (Funciones.isValidText(titulos) == false) {
                    Funciones.cls();
                    System.out.println("    ¡ERROR! No puede ingresar caracteres "
                            + "especiales, ingrese su título nuevamente.");
                    Funciones.pause();
                } else {
                    enc.setTitulo(titulos.toUpperCase());
                    break;
                }
            } while (true);

            //Campo - Cargo
            do {
                Funciones.cls();
                System.out.print(titulo);
                System.out.print("  Ingrese su cargo en la institución: ");
                String cargo = br.readLine();
                if (Funciones.isValidText(cargo) == false) {
                    Funciones.cls();
                    System.out.println("    ¡ERROR! No puede ingresar caracteres "
                            + "especiales, ingrese su cargo nuevamente.");
                    Funciones.pause();
                } else {
                    enc.setCargo(cargo.toUpperCase());
                    break;
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
                        enc.setClave(clave);
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

            enc.setRol(2);
            enc.setCedula(cedula);

            Funciones.cls();
            int idUsuario = usuc.buscarIdUsuario(enc.getCedula());
            enc.setIdUsuario(idUsuario);

            encc.crearEncargado(enc);

            System.out.println(titulo);
            System.out.println("""
                                                     ¡CUENTA REGISTRADA CON EXITO! 
                                    Sus datos personales fueron creados con éxito, recuerde su contraseña.
                               """);
            Funciones.pause();
            return enc;
        } while (true);
    }
    
    //Mostrar información de cuenta
    public static void mostrarInfo(Encargado enc){
        Funciones.cls();
        System.out.println("""
                    --------------------------------------------------
                                   Información Personal                  
                    --------------------------------------------------
        """);
        System.out.println(enc.toString());
        System.out.println("            --------------------------------------------------");
        Funciones.pause();
    }

    //Menú - Sin laboratorio asignado
    public static int menu1(Encargado encargado) throws IOException {
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
                //Mostrar información encargado
                case "1" -> {
                    mostrarInfo(encargado);
                }
                //Editar información encargado
                case "2" -> {
                    encargado.editarDatosPersonales();
                }
                //Recargar menú y funciones
                case "3" -> {
                    Funciones.cls();
                    System.out.println(" Recargando menú...\n");
                    Funciones.pause();
                    return 0;
                }
                //Cerrar sesión de la cuenta
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
    
    //Menú - Laboratorio asignado
    public static int menu2(Encargado encargado) throws IOException {
        Scanner s = new Scanner(System.in);

        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                      Menú Principal                  
                    --------------------------------------------------      
                        1. Comenzar mantenimiento
                        2. Imprimir informes
                        3. Ver información computadoras
                        4. Mostrar información personal
                        5. Editar datos personales            
                        6. Recargar menú                 
                        0. Cerrar sesión               
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            String opc = s.next();
            switch (opc) {
                case "1" -> {
                    encargado.realizarMantenimiento();
                    return 0;
                }
                case "4" -> {
                    mostrarInfo(encargado);
                }
                case "5" -> {
                    encargado.editarDatosPersonales();
                }
                case "6" -> {
                    Funciones.cls();
                    System.out.println(" Recargando menú...\n");
                    Funciones.pause();
                    return 0;
                }
                case "0" -> {
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
    
    //Menú - Mantenimiento inicializado
    public static int menu3(Encargado encargado) throws IOException {
        Scanner s = new Scanner(System.in);
        
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = labC.buscarLaboratorio(encargado.getCedula());

        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                      Menú Principal                  
                    --------------------------------------------------      
                        1. Asignar estudiantes a mantenimiento
                        2. Ver estados de computadoras
                        3. Verificar información de mantenimiento
                        4. Finalizar mantenimiento
                        5. Mostrar información personal
                        6. Editar datos personales            
                        7. Recargar menú                 
                        0. Cerrar sesión               
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            String opc = s.next();
            switch (opc) {
                case "1" -> {
                }
                case "5" -> {
                    mostrarInfo(encargado);
                }
                case "6" -> {
                    encargado.editarDatosPersonales();
                }
                case "7" -> {
                    Funciones.cls();
                    System.out.println("Recargando menú...\n");
                    Funciones.pause();
                    return 0;
                }
                case "0" -> {
                    Funciones.cls();
                    System.out.println("Cerrando sesión...\n");
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
        Encargado enc = logeoEncargado();
        if (enc != null) {
            do {
                EncargadoControlador encC = new EncargadoControlador();
                LaboratorioControlador labC = new LaboratorioControlador();
                MantenimientoControlador mantC = new MantenimientoControlador();
                
                enc = encC.buscarEncargado(enc.getCedula());
                Laboratorio lab = labC.buscarLaboratorio(enc.getCedula());
                lab.setIdLaboratorio(labC.buscarIdLaboratorio(enc.getCedula()));
                Mantenimiento mant = mantC.buscarMantenimiento(lab);
                
                if (!enc.getCedula().equals(lab.getEncargado().getCedula())) {
                    int flag = menu1(enc);
                    if (flag == 1) {
                        main(args);
                        break;
                    }else if(flag == 0){
                        continue;
                    }
                    break;
                } else if (enc.getCedula().equals(lab.getEncargado().getCedula())) {
                    if (mant.getIdMantenimiento() != 0){
                        int flag = menu3(enc);
                        switch (flag) {
                            case 0 -> {
                                continue;
                            }
                            case 1 -> {
                                main(args);
                                break;
                            }
                            case 2 -> {
                                continue;
                            }
                            default -> {
                            }
                        }
                    } else {
                        int flag = menu2(enc);
                        switch (flag) {
                            case 0 -> {
                                continue;
                            }
                            case 1 -> {
                                main(args);
                                break;
                            }
                            case 2 -> {
                                continue;
                            }
                            default -> {
                            }
                        }
                    }
                    
                }
            } while (true);
        }
    }
}
