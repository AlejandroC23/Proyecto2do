/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ComputadoraControlador;
import controlador.ConexionBDD;
import controlador.EncargadoControlador;
import controlador.AdministradorControlador;
import controlador.EstudianteControlador;
import controlador.Funciones;
import controlador.LaboratorioControlador;
import controlador.MantenimientoControlador;
import controlador.UsuarioControlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Administrador;
import modelo.Computadora;
import modelo.Encargado;
import modelo.Estudiante;
import modelo.Laboratorio;
import modelo.Mantenimiento;
import modelo.Usuario;
import static vista.MainEncargado.main;
import static vista.MainEncargado.menu1;
import static vista.MainEncargado.menu2;
import static vista.MainEncargado.menu3;
import static vista.MainEstudiante.main;
import static vista.MainEstudiante.menu1;
import static vista.MainEstudiante.menu2;
import static vista.MainEstudiante.menu3;

/**
 *
 * @author User
 */
public class MainAdministrador {
    //Impresión de menú de inicio de sesión y registro
    public static void menuLogeoAdministrador() {
        System.out.println("""
        -------------------------------------------
                    Logeo Administrador            
        -------------------------------------------
            1. Iniciar Sesión
            2. Regresar
        -------------------------------------------
        """.indent(10));
        System.out.print("  Opcion: ");
    }

    //Menú de inicio de sesión y registro
    public static Administrador logeoAdministrador() throws IOException {
        //Variables
        Scanner s = new Scanner(System.in);
        Administrador admin = new  Administrador();
        String opcAdmin;
        do {
            //Menú Logeo Encargado
            Funciones.cls();
            menuLogeoAdministrador();
            opcAdmin = s.next();

            switch (opcAdmin) {
                //Inicio de sesión
                case "1" -> {
                    do {
                        Funciones.cls();
                        admin = inicioSesion();
                        String opcYN;
                        if ("0".equals(admin.getCedula())) {
                            do {
                                Funciones.cls();
                                System.out.println("Usuario o contraseña incorrecto...\n");
                                System.out.print("¿Desea regresar al menú anterior? [Y/n] ");
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
                                break;
                            } while (true);
                            
                            if("Y".equals(opcYN)){
                                break;
                            }
                            
                        } else {
                            if ("0".equals(admin.getTelefono())) {
                                admin = registro(admin.getCedula());
                                return admin;
                            }
                            return admin;
                        }
                    } while (true);
                }
                //Regresar
                case "2" -> {
                    Funciones.pause();
                    System.out.println("Regresando...");
                    Funciones.cls();
                    admin.setCedula(null);
                    return admin;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                }
            }
        } while (!"2".equals(admin));
        return admin;
    }

    //Inicio de sesión de usuario
    public static Administrador inicioSesion() throws IOException {
        AdministradorControlador adminC = new AdministradorControlador();
        Administrador admin = new  Administrador();

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
            admin = adminC.buscarAdmin(usuario, clave);
        } else {
            admin.setCedula("0");
        }

        return admin;
    }

    //Registro de usuario
    public static Administrador registro(String cedula) throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        AdministradorControlador adminC = new AdministradorControlador();
        Administrador admin = new  Administrador();

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
                    admin.setNombre(nombres.toUpperCase());
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
                    admin.setApellido(apellidos.toUpperCase());
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
                    admin.setCorreoElectronico(email);
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
                    admin.setDireccion(direccion.toUpperCase());
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
                        admin.setTelefono(telefono);
                        break;
                    } else {
                        Funciones.cls();
                        System.out.println("    ¡ERROR! El telefono ingresado es incorrecto. "
                                + " Ingrese su teléfono nuevamente.");
                        Funciones.pause();
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
                        admin.setClave(clave);
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

            admin.setRol(3);
            admin.setCedula(cedula);

            Funciones.cls();
            
            adminC.editarEncargado(admin);

            System.out.println(titulo);
            System.out.println("""
                                                     ¡CUENTA REGISTRADA CON EXITO! 
                                    Sus datos personales fueron creados con éxito, recuerde su contraseña.
                               """);
            Funciones.pause();
            return admin;
        } while (true);
    }

    //Mostrar información de cuenta
    public static void infoCuentaAdmin(Administrador admin) {
        Funciones.cls();
        System.out.println("""
                    --------------------------------------------------
                                   Información Personal                  
                    --------------------------------------------------""".indent(12));
        System.out.println(admin.toString());
        System.out.println("            --------------------------------------------------");
        Funciones.pause();
    }

    //Imprimir computadoras
    public static void infoComputadoras(Laboratorio lab) {
        Scanner s = new Scanner(System.in);

        if (lab.getComputadoras().isEmpty()) {
            Funciones.cls();
            System.out.println("No existen computadoras registradas al laboratorio. Intentelo de nuevo más tarde.");
            Funciones.pause();
        } else {
            Funciones.cls();
            System.out.println("""
                                ------------------------------------------------
                                                  Introducción                  
                                ------------------------------------------------
                                   Bienvenido/a al apartado de información de
                                   computadoras, a continuación se presentará
                                      un listado de las computadoras en el
                                                  laboratorio.
                               
                                 Para regresar al menú anterior puede ingresar
                                 la letra 'r'. Si desea conocer más información
                                   de un computador, ingrese 'b' y digite el
                                      código del computador que necesite.
                                             Gracias por leer :)
                                ------------------------------------------------
                               """);
            Funciones.pause();
            String opc = "1";
            String pag = "1";

            int finPag;

            if (lab.getComputadoras().size() < 25) {
                finPag = 1;
            } else {
                finPag = (int) Math.ceil(lab.getComputadoras().size() / 25);
            }

            do {
                if (Funciones.isValidNumeric(opc)) {
                    int a = Integer.parseInt(opc);
                    if (a <= finPag){
                        pag = opc;
                        int x = a * 25;
                        System.out.println(String.format("""
                                                  --------------------------------------------------------------------
                                                    Información de Computadoras  - %s
                                                  --------------------------------------------------------------------
                                                 |      Codigo      |             Marca            |      Estado      |
                                            """, lab.getNombre()));
                        if (a == finPag) {
                            for (int i = (x - 25); i < lab.getComputadoras().size(); i++) {
                                System.out.println(lab.getComputadoras().get(i).infoBasica());
                            }
                        } else {
                            for (int i = (x - 25); i < x; i++) {
                                System.out.println(lab.getComputadoras().get(i).infoBasica());
                            }
                        }
                        System.out.println("      --------------------------------------------------------------------");
                        System.out.println("                                                        Página. " + a);
                        System.out.print("     Ingrese 'r' para regresar o 'b' para buscar. \n");
                        System.out.print("     Ingrese el número de página u opción que desea: ");
                        opc = s.next();
                    } else {
                        Funciones.cls();
                        opc = pag;
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    }
                } else if ("b".equals(opc)) {
                    String buscarComp;
                    do {
                        Funciones.cls();
                        System.out.print("Ingrese r para regresar a visualizar las computadoras.\n");
                        System.out.print("Ingrese el código de computadora a buscar: ");
                        buscarComp = s.next();
                        if (!"r".equals(buscarComp)) {
                            if (Funciones.isValidCode(buscarComp)) {
                                ComputadoraControlador compC = new ComputadoraControlador();
                                Computadora comp = compC.
                                        buscarComputadoraLaboratorio(
                                                lab.getIdLaboratorio(),
                                                buscarComp);
                                if (comp.getIdComputadora() != 0) {
                                    Funciones.cls();
                                    System.out.println(comp.toString());
                                    Funciones.pause();
                                } else {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! No se encontró la computadora, ingrese el código nuevamente.");
                                    Funciones.pause();
                                }
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        } else {
                            break;
                        }
                    } while (!"r".equals(buscarComp));

                } else if ("r".equals(opc)) {
                    Funciones.cls();
                    System.out.println("Regresando...");
                    Funciones.pause();
                    break;
                } else {
                    Funciones.cls();
                    opc = pag;
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                }
            } while (!"r".equals(pag));
        }
    }
    
    //Mostrar información de laboratorio
    public static void infoLaboratorio(Laboratorio lab) {
        
        Funciones.cls();
        System.out.println("""
                    --------------------------------------------------
                                  Información Laboratorio                  
                    --------------------------------------------------""".indent(12));
        System.out.println(lab.toString());
        System.out.println("            --------------------------------------------------");
        Funciones.pause();
    }
    
    //Crear laboratorio Administrador
    public static void crearLaboratorio() throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        Usuario usu = new Usuario();
        LaboratorioControlador labC = new LaboratorioControlador();

        Laboratorio lab = new Laboratorio();

        String titulo = """
                    --------------------------------------------------
                                  Registrar Laboratorio
                    --------------------------------------------------                
        """;
        String opc;

        do {
            //Campo - Nombres
            do {
                Funciones.cls();
                System.out.print(titulo);
                System.out.print("  Ingrese el nombre del laboratorio: ");
                String nombres = s.next();
                if (Funciones.isValidText(nombres) == false) {
                    Funciones.cls();
                    System.out.println("    ¡ERROR! No puede ingresar caracteres "
                            + "especiales o números, ingrese el nombre nuevamente.");
                    Funciones.pause();
                } else {
                    lab.setNombre(nombres.toUpperCase());
                    break;
                }
            } while (true);

            //Campo - Edificio
            do {
                Funciones.cls();
                System.out.print(titulo);
                System.out.print("  Ingrese el nro. de edificio: ");
                String edificio = br.readLine();
                if (Funciones.isValidNumeric(edificio) == false) {
                    Funciones.cls();
                    System.out.println("    ¡ERROR! No puede ingresar caracteres "
                            + "especiales o números, ingrese el edicio nuevamente.");
                    Funciones.pause();
                } else {
                    int edi = Integer.parseInt(edificio);
                    lab.setEdificio(edi);
                    break;
                }
            } while (true);

            //Campo - Ciudad
            String opcCiu;
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                     Elija la ciudad:
                                      1. Ibarra
                                      2. Urcuqui
                                   """);
                System.out.print("  Opción: ");
                opcCiu = s.next();
                switch (opcCiu) {
                    case "1" -> {
                        lab.setCiudad("IBARRA");
                        break;
                    }
                    case "2" -> {
                        lab.setCiudad("URCUQUI");
                        break;
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    }
                }
            } while (!opcCiu.equals("1") && !opcCiu.equals("2"));

            //Campo - Encargado
            do {
                Funciones.cls();
                System.out.print("¿Desea agregar el encargado? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        String cedula;
                        do {
                            Funciones.cls();
                            System.out.print("  Ingrese la cédula del encargado: ");
                            cedula = s.next();
                            if (Funciones.isValidNumeric(cedula)) {
                                if (usu.validarCedula(cedula)) {
                                    EncargadoControlador encC = new EncargadoControlador();
                                    Encargado enc = new Encargado();
                                    enc.setIdEncargado(encC.buscarIdEncargado(cedula));
                                    if (enc.getIdEncargado() == 0) {
                                        Funciones.cls();
                                        System.out.println("¡ERROR! Ingrese otro encargado disponible.");
                                        Funciones.pause();
                                    } else {
                                        lab.setEncargado(enc);
                                        break;
                                    }
                                } else {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                    Funciones.pause();
                                }
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                Funciones.pause();
                            }
                        } while (true);
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
            } while ("n".equals(opc) && "Y".equals(opc));
            
            labC.crearLaboratorio(lab);
            System.out.println(titulo);
            System.out.println("""
                                                      ¡DATOS CREADOS CON ÉXITO! 
                                         Los datos del laboratorio fueron cambiados con éxito.
                               """);
            Funciones.pause();
            break;
        } while (true);
    }
    
    //Menú Cuentas Administrador
    public static void menuCuentasAdmin() throws IOException {
        Scanner s = new Scanner(System.in);
        
        UsuarioControlador usuC = new UsuarioControlador();
        Usuario usu = new Usuario();
        String opc;
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                   Cuentas Administrador                  
                    --------------------------------------------------    
                        1. Cambiar contraseña
                        2. Crear cuenta
                        3. Regresar         
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            opc = s.next();
            switch (opc) {
                //Cambiar contraseña
                case "1" -> {
                    String cedula;
                    do{
                        Funciones.cls();
                        System.out.print("Ingresa la cédula para cambio de contraseña (ingrese 'r' para regresar): ");
                        cedula = s.next();
                        if(Funciones.isValidNumeric(cedula)){
                            if(usu.validarCedula(cedula)){
                                String opcYN;
                                do{
                                    Funciones.cls();
                                    System.out.print("¿Esta seguro cambiar la contraseña? [Y/n] ");
                                    opcYN = s.next();
                                    switch (opcYN){
                                        case "Y" -> {
                                            AdministradorControlador adminC = new AdministradorControlador();
                                            String clave = cedula + ".@";
                                            System.out.println(String.format("""
                                                                      Se cambiará la contraseña del usuario %s
                                                                            a la contraseña %s
                                                               """,cedula, clave));
                                            adminC.cambiarClave(cedula, clave);
                                            break;
                                        }
                                        case "n" -> {
                                            Funciones.cls();
                                            System.out.println("Regresando...");
                                            Funciones.pause();
                                            break;
                                        }
                                        default -> {
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                        }
                                    }
                                }while(!opcYN.equals("Y") && !opcYN.equals("n"));
                                break;
                            }else{
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                Funciones.pause();
                            }
                        }else if ("r".equals(cedula)){
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            break;
                        }else{
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una cédula correcta.");
                            Funciones.pause();
                        }
                    }while(true);
                }
                //Crear cuenta
                case "2" -> {
                    String cedula;
                    do{
                        Funciones.cls();
                        System.out.print("Ingresa la cédula para crear cuenta (ingrese 'r' para regresar): ");
                        cedula = s.next();
                        if(Funciones.isValidNumeric(cedula)){
                            if(usu.validarCedula(cedula)){
                                if(usuC.existeCuenta(cedula)){
                                    String opcYN;
                                    AdministradorControlador adminC = new AdministradorControlador();
                                    String clave = cedula + ".@";
                                    System.out.println(String.format("""
                                                              Se creará la contraseña del usuario %s
                                                                    con la contraseña %s
                                                       """,cedula, clave));
                                    adminC.crearAdmin(cedula, clave);
                                    break;
                                } else {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! Ya hay una cuenta registrada, ingerse otra cédula.");
                                    Funciones.pause();
                                }
                            }else{
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                Funciones.pause();
                            }
                        }else if ("r".equals(cedula)){
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            break;
                        }else{
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una cédula correcta.");
                            Funciones.pause();
                        }
                    }while(true);
                } 
                //Regresar
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
                    break;
                }
            }
        } while (!"3".equals(opc));
    }
    
    //Menú Cuentas Encargado
    public static void menuCuentasEnc() throws IOException {
        Scanner s = new Scanner(System.in);
        
        UsuarioControlador usuC = new UsuarioControlador();
        Usuario usu = new Usuario();
        String opc;
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                     Cuentas Encargado                  
                    --------------------------------------------------    
                        1. Cambiar contraseña
                        2. Crear cuenta
                        3. Regresar         
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            opc = s.next();
            switch (opc) {
                //Cambiar contraseña
                case "1" -> {
                    String cedula;
                    do{
                        Funciones.cls();
                        System.out.print("Ingresa la cédula para cambio de contraseña (ingrese 'r' para regresar): ");
                        cedula = s.next();
                        if(Funciones.isValidNumeric(cedula)){
                            if(usu.validarCedula(cedula)){
                                String opcYN;
                                do{
                                    Funciones.cls();
                                    System.out.print("¿Esta seguro cambiar la contraseña? [Y/n] ");
                                    opcYN = s.next();
                                    switch (opcYN){
                                        case "Y" -> {
                                            EncargadoControlador encC = new EncargadoControlador();
                                            String clave = cedula + ".@";
                                            System.out.println(String.format("""
                                                                      Se cambiará la contraseña del usuario %s
                                                                            a la contraseña %s
                                                               """,cedula, clave));
                                            encC.cambiarClave(cedula, clave);
                                            break;
                                        }
                                        case "n" -> {
                                            Funciones.cls();
                                            System.out.println("Regresando...");
                                            Funciones.pause();
                                            break;
                                        }
                                        default -> {
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                        }
                                    }
                                }while(!opcYN.equals("Y") && !opcYN.equals("n"));
                                break;
                            }else{
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                Funciones.pause();
                            }
                        }else if ("r".equals(cedula)){
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            break;
                        }else{
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una cédula correcta.");
                            Funciones.pause();
                        }
                    }while(true);
                }
                //Crear cuenta
                case "2" -> {
                    String cedula;
                    do{
                        Funciones.cls();
                        System.out.print("Ingresa la cédula para crear cuenta (ingrese 'r' para regresar): ");
                        cedula = s.next();
                        if(Funciones.isValidNumeric(cedula)){
                            if(usu.validarCedula(cedula)){
                                if(usuC.existeCuenta(cedula)){
                                    EncargadoControlador encC = new EncargadoControlador();
                                    String clave = cedula + ".@";
                                    System.out.println(String.format("""
                                                              Se creará la contraseña del usuario %s
                                                                    con la contraseña %s
                                                       """,cedula, clave));
                                    encC.crearEnc(cedula, clave);
                                    break;
                                } else {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! Ya hay una cuenta registrada, ingerse otra cédula.");
                                    Funciones.pause();
                                }
                            }else{
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                Funciones.pause();
                            }
                        }else if ("r".equals(cedula)){
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            break;
                        }else{
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una cédula correcta.");
                            Funciones.pause();
                        }
                    }while(true);
                } 
                //Regresar
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
                    break;
                }
            }
        } while (!"3".equals(opc));
    }
    
    //Menú Cuentas Estudiante
    public static void menuCuentasEst() throws IOException {
        Scanner s = new Scanner(System.in);
        
        UsuarioControlador usuC = new UsuarioControlador();
        Usuario usu = new Usuario();
        String opc;
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                     Cuentas Encargado                  
                    --------------------------------------------------    
                        1. Cambiar contraseña
                        2. Regresar         
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            opc = s.next();
            switch (opc) {
                //Cambiar contraseña
                case "1" -> {
                    String cedula;
                    do{
                        Funciones.cls();
                        System.out.print("Ingresa la cédula para cambio de contraseña (ingrese 'r' para regresar): ");
                        cedula = s.next();
                        if(Funciones.isValidNumeric(cedula)){
                            if(usu.validarCedula(cedula)){
                                String opcYN;
                                do{
                                    Funciones.cls();
                                    System.out.print("¿Esta seguro cambiar la contraseña? [Y/n] ");
                                    opcYN = s.next();
                                    switch (opcYN){
                                        case "Y" -> {
                                            EstudianteControlador estC = new EstudianteControlador();
                                            String clave = cedula + ".@";
                                            System.out.println(String.format("""
                                                                      Se cambiará la contraseña del usuario %s
                                                                            a la contraseña %s
                                                               """,cedula, clave));
                                            estC.cambiarClave(cedula, clave);
                                            break;
                                        }
                                        case "n" -> {
                                            Funciones.cls();
                                            System.out.println("Regresando...");
                                            Funciones.pause();
                                            break;
                                        }
                                        default -> {
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                        }
                                    }
                                }while(!opcYN.equals("Y") && !opcYN.equals("n"));
                                break;
                            }else{
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                Funciones.pause();
                            }
                        }else if ("r".equals(cedula)){
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            break;
                        }else{
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una cédula correcta.");
                            Funciones.pause();
                        }
                    }while(true);
                }
                //Regresar
                case "2" -> {
                    Funciones.cls();
                    System.out.println("Regresando...");
                    Funciones.pause();
                    break;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                    break;
                }
            }
        } while (!"3".equals(opc));
    }
    
    //Menú de información de laboratorio
    public static void menuInfoLaboratorio(Laboratorio lab) throws IOException {
        
        Scanner s = new Scanner(System.in);
        String opcEst;
        do {
            Funciones.cls();
            System.out.println("""
                        -------------------------------------------
                                        Laboratorio            
                        -------------------------------------------
                            1. Ver información
                            2. Ver computadoras
                            3. Editar Laboratorio
                            4. Regresar
                        -------------------------------------------
            """);
            System.out.print("    Opción: ");
            opcEst = s.next();

            switch (opcEst) {
                case "1" -> {
                    infoLaboratorio(lab);
                }
                case "2" -> {
                    infoComputadoras(lab);
                }
                case "3" -> {
                    lab.editarDatos();
                }
                case "4" -> {
                    Funciones.cls();
                    System.out.println("Regresando...");
                    Funciones.pause();
                    break;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                }
            }
        } while (!"4".equals(opcEst));
    }
    
    //Menú Laboratorio Administrador
    public static void menuLaboratorio(Administrador admin) throws IOException {
        Scanner s = new Scanner(System.in);
        Laboratorio lab = new Laboratorio();
        String opc;
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                      Menú Laboratorio                  
                    --------------------------------------------------    
                        1. Crear Laboratorio
                        2. Ver Laboratorio
                        3. Regresar         
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            opc = s.next();
            switch (opc) {
                //Crear laboratorio
                case "1" -> {
                    crearLaboratorio();
                }
                //Ver laboratorio
                case "2" -> {
                    LaboratorioControlador labC = new LaboratorioControlador();
                    ArrayList<Laboratorio> listadoLaboratorios = labC.listarLaboratorios();
                    String opcLab;
                    do{
                        System.out.println("""
                                                    --------------------------------------------------
                                                                       Laboratorios                 
                                                    --------------------------------------------------
                                            """);
                        for(Laboratorio p : listadoLaboratorios){
                            System.out.println("        " + p.getIdLaboratorio() + ". " 
                                    + p.getNombre() + " - " 
                                    + p.getCiudad());
                        }
                        opcLab = s.next();
                        if(Funciones.isValidNumeric(opcLab)){
                            int x = Integer.parseInt(opcLab);
                            if(x > listadoLaboratorios.size()){
                                for (int i = 1; i <= listadoLaboratorios.size(); i++) {
                                    if (x == i) {
                                        lab = listadoLaboratorios.get(i-1);
                                        break;
                                    }
                                }
                                menuInfoLaboratorio(lab);
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        } else {
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                            Funciones.pause();
                        }
                    } while (true);
                    
                }
                //Regresar
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
                    break;
                }
            }
        } while (!"3".equals(opc));
    }
    
    //Menú Cuentas Administrador
    public static void menuCuentas() throws IOException {
        Scanner s = new Scanner(System.in);
        Laboratorio lab = new Laboratorio();
        String opc;
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                        Menú Cuentas                  
                    --------------------------------------------------    
                        1. Cuentas Administrador
                        2. Cuentas Encargado
                        3. Cuentas Estudiante
                        4. Regresar         
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            opc = s.next();
            switch (opc) {
                //Cuentas Administrador
                case "1" -> {
                    menuCuentasAdmin();
                }
                //Cuentas Encargado
                case "2" -> {
                    menuCuentasEnc();
                }
                //Cuentas Estudiante
                case "3" -> {
                    menuCuentasEst();
                }
                //Regresar
                case "4" -> {
                    Funciones.cls();
                    System.out.println("Regresando...");
                    Funciones.pause();
                    break;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                    break;
                }
            }
        } while (!"4".equals(opc));
    }
    
    //Menú Cuentas Administrador
    public static void menuFuncionalidades(Administrador admin) throws IOException {
        Scanner s = new Scanner(System.in);
        Usuario usu = new Usuario();
        String opc;
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                   Menú Funcionalidades                  
                    --------------------------------------------------    
                        1. Iniciar cuenta Encargado
                        2. Iniciar cuenta Estudiante
                        3. Editar datos personales
                        4. Mostrar información
                        5. Regresar         
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            opc = s.next();
            switch (opc) {
                //Iniciar Cuentas Encargado
                case "1" -> {
                    String cedula;
                    do{
                        Funciones.cls();
                        System.out.print("Ingresa la cédula para ingresar al menú encargado (ingrese 'r' para regresar): ");
                        cedula = s.next();
                        if(Funciones.isValidNumeric(cedula)){
                            if(usu.validarCedula(cedula)){
                                String opcYN;
                                do{
                                    Funciones.cls();
                                    System.out.print("¿Esta seguro de entrar en la cuenta? [Y/n] ");
                                    opcYN = s.next();
                                    switch (opcYN){
                                        case "Y" -> {
                                            EncargadoControlador encC = new EncargadoControlador();
                                            Encargado enc = encC.buscarEncargado(cedula);
                                            if("0".equals(enc.getCedula())){
                                                do {
                                                    
                                                    LaboratorioControlador labC = new LaboratorioControlador();
                                                    MantenimientoControlador mantC = new MantenimientoControlador();


                                                    Laboratorio lab = labC.buscarLaboratorio(enc.getCedula());
                                                    lab.setIdLaboratorio(labC.buscarIdLaboratorio(enc.getCedula()));

                                                    Mantenimiento mant = new Mantenimiento();
                                                    mant.setIdMantenimiento(mantC.buscarIdMantenimiento(lab.getIdLaboratorio()));

                                                    if (enc.getCedula().equals(lab.getEncargado().getCedula()) == false) {
                                                        int flag = menu1(enc);
                                                        if (flag == 1) {
                                                            break;
                                                        } else if (flag == 0) {
                                                            continue;
                                                        }
                                                        break;
                                                    } else if (enc.getCedula().equals(lab.getEncargado().getCedula())) {
                                                        if (mant.getIdMantenimiento() != 0) {
                                                            int flag = menu3(enc);
                                                            switch (flag) {
                                                                case 0 -> {
                                                                    continue;
                                                                }
                                                                case 1 -> {
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

                                                    if (enc.getCedula() == null) {
                                                        break;
                                                    }
                                                } while (true);
                                            }else{
                                                Funciones.cls();
                                                System.out.println("¡ERROR! Ingrese una cédula de estudiante correcta.");
                                                Funciones.pause();
                                            }
                                        }
                                        case "n" -> {
                                            Funciones.cls();
                                            System.out.println("Regresando...");
                                            Funciones.pause();
                                            break;
                                        }
                                        default -> {
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                        }
                                    }
                                }while(!opcYN.equals("Y") && !opcYN.equals("n"));
                                break;
                            }else{
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                Funciones.pause();
                            }
                        }else if ("r".equals(cedula)){
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            break;
                        }else{
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una cédula correcta.");
                            Funciones.pause();
                        }
                    }while(true);
                }
                //Inciar Cuentas Estudiante
                case "2" -> {
                    String cedula;
                    do{
                        Funciones.cls();
                        System.out.print("Ingresa la cédula para ingresar al menú encargado (ingrese 'r' para regresar): ");
                        cedula = s.next();
                        if(Funciones.isValidNumeric(cedula)){
                            if(usu.validarCedula(cedula)){
                                String opcYN;
                                do{
                                    Funciones.cls();
                                    System.out.print("¿Esta seguro de entrar en la cuenta? [Y/n] ");
                                    opcYN = s.next();
                                    switch (opcYN){
                                        case "Y" -> {
                                            EstudianteControlador estC = new EstudianteControlador();
                                            Estudiante est = estC.buscarEstudiante(cedula);
                                            if("0".equals(est.getCedula())){
                                                do {
                                                    MantenimientoControlador mantC = new MantenimientoControlador();

                                                    est.setIdEstudiante(estC.buscarIdEstudiante(est.getMatricula()));

                                                    Mantenimiento mant = mantC.buscarMantenimientoEstudiante(est.getIdEstudiante());

                                                    if (mant.getIdMantenimiento() == 0) {
                                                        int flag = menu1(est);
                                                        if (flag == 1) {
                                                            break;
                                                        } else if (flag == 0) {
                                                            continue;
                                                        }
                                                    } else {
                                                        if(est.isFinalizoMantenimiento()){
                                                            int flag = menu3(est);
                                                            if (flag == 1) {
                                                                break;
                                                            } else if (flag == 0) {
                                                                continue;
                                                            }
                                                        } else {
                                                            int flag = menu2(est);
                                                            if (flag == 1) {
                                                                break;
                                                            } else if (flag == 0) {
                                                                continue;
                                                            }
                                                        }
                                                    }

                                                    if (est.getCedula() == null) {
                                                        break;
                                                    }
                                                } while (true);
                                            }else{
                                                Funciones.cls();
                                                System.out.println("¡ERROR! Ingrese una cédula de estudiante correcta.");
                                                Funciones.pause();
                                            }
                                        }
                                        case "n" -> {
                                            Funciones.cls();
                                            System.out.println("Regresando...");
                                            Funciones.pause();
                                            break;
                                        }
                                        default -> {
                                            Funciones.cls();
                                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                                            Funciones.pause();
                                        }
                                    }
                                }while(!opcYN.equals("Y") && !opcYN.equals("n"));
                                break;
                            }else{
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una cédula correcta.");
                                Funciones.pause();
                            }
                        }else if ("r".equals(cedula)){
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            break;
                        }else{
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una cédula correcta.");
                            Funciones.pause();
                        }
                    }while(true);
                }
                //Editar Información Personal
                case "3" -> {
                    admin.editarDatosPersonales();
                }
                //Mostrar Información
                case "4" -> {
                    infoCuentaAdmin(admin);
                }
                //Regresar
                case "5" -> {
                    Funciones.cls();
                    System.out.println("Regresando...");
                    Funciones.pause();
                    break;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                    break;
                }
            }
        } while (!"5".equals(opc));
    }
    
    //Menú Principal Administrador
    public static int menu(Administrador admin) throws IOException {
        Scanner s = new Scanner(System.in);

        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                      Menú Principal                  
                    --------------------------------------------------    
                        1. Menú Laboratorio
                        2. Menú Cuentas
                        3. Menú Funcionalidades
                        4. Recargar menú                 
                        0. Cerrar sesión               
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            String opc = s.next();
            switch (opc) {
                //Menú Laboratorio
                case "1" -> {
                    //infoCuentaAdmin(admin);
                }
                //Menú Cuentas
                case "2" -> {
                    menuCuentas();
                }
                //Menú Funcionalidades
                case "3" -> {
                    menuFuncionalidades(admin);
                }
                //Recargar menú y funciones
                case "4" -> {
                    Funciones.cls();
                    System.out.println(" Recargando menú...\n");
                    Funciones.pause();
                    return 0;
                }
                //Cerrar sesión de la cuenta
                case "0" -> {
                    Funciones.cls();
                    System.out.println(" Cerrando sesión...\n");
                    Funciones.pause();
                    admin.setCedula(null);
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
        Scanner s = new Scanner(System.in);
        String claveAdmin;
        Funciones.cls();
        System.out.println("""
                                Bienvenido/a ingrese la clave de administrador
                                que se le proporcionó: """);
        claveAdmin = s.next();
        if(claveAdmin.equals("ADMIN.@-655*")){
            Administrador admin = logeoAdministrador();
            AdministradorControlador adminC = new AdministradorControlador();
            if (admin.getCedula() != null) {
                do {
                    admin = adminC.buscarAdmin(admin.getCedula());

                    int flag = menu(admin);
                    if (flag == 1) {
                        main(args);
                        break;
                    }
                }while(true);
            }
        }else{
            Funciones.cls();
            System.out.println("¡ERROR! La clave ingresada es erronéa. Vuelvelo a intentar después :)");
            Funciones.pause();
            main(args);
        }
    }
}
