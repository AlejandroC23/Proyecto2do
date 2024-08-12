/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Funciones;
import controlador.CarreraControlador;
import controlador.ComputadoraControlador;
import controlador.EstudianteControlador;
import controlador.LaboratorioControlador;
import controlador.MantenimientoControlador;
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
import modelo.Computadora;
import modelo.Laboratorio;
import modelo.Mantenimiento;

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
        """.indent(10));
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
                        String opcYN;
                        if ("0".equals(est.getCedula())) {
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
                            
                            if ("Y".equals(opcYN)) {
                                break;
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
                case "3" -> {
                    Funciones.cls();
                    System.out.println("""
                                                    Si presenta errores con el sistema contactese
                                                            a los siguientes números:
                                       
                                                             0995196339 - 0995113268
                                       
                                                  En caso de no recibir respuesta puede contactarse
                                                          al siguiente correo electrónico:
                                                    
                                                     alejandro.cevallos919@ist17dejulio.edu.ec
                                       """);
                    Funciones.pause();
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
        } while (!"4".equals(opcEst));
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
            
            est.setFinalizoMantenimiento(false);

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
                    if (a > listadoCarreras.size()) {
                        Funciones.cls();
                        System.out.println("    ¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    } else {
                        for (int i = 1; i <= listadoCarreras.size(); i++) {
                            String x = Integer.toString(i);
                            if (x.equals(opcCar)) {
                                est.setCarrera(listadoCarreras.get(i - 1));
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
    public static void mostrarInfo(Estudiante est) {
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

    //Mantenimientos de computadoras o registro
    public static void mantComputadoras(Estudiante est) throws IOException {
        Scanner s = new Scanner(System.in);
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimientoEstudiante(est.getIdEstudiante());
        
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = mant.getLaboratorio();
        
        if (lab.getComputadoras().isEmpty()) {
            
            String opc;
            do {
                Funciones.cls();
                System.out.println("No existen computadoras registradas al laboratorio. Registre una computadora.");
                System.out.print("Ingrese 'r' para regresar o 'i' para ingresar una computadora: ");
                opc = s.next();
                switch (opc) {
                    case "r" -> {
                        break;
                    }
                    case "i" -> {
                        est.registrarComputadora(lab);
                        break;
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    }
                }
            } while (true);
        } else {
            Funciones.cls();
            System.out.println("""
                                ------------------------------------------------
                                                  Introducción                  
                                ------------------------------------------------
                                  Bienvenido/a al apartado de mantenimiento de
                                   computadoras, a continuación se presentará
                                        un listado de computadoras en el
                                                  laboratorio.
                               
                                 Para regresar al menú anterior puede ingresar
                                   la letra 'r'. Si desea hacer mantenimiento
                                    a un computador, ingrese 'b' y digite el
                                      código del computador que necesite.
                               
                                  Si el computador no aparece, digite 'i' para
                                  registrar la computadora y su mantenimiento.
                               
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
                    if (a <= finPag) {
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
                        System.out.print("     Ingrese 'r' para regresar, 'b' para buscar o 'i' para registar. \n");
                        System.out.print("     Ingrese el número de página u opción que desea: ");
                        opc = s.next();
                    } else {
                        Funciones.cls();
                        opc = pag;
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                } else if ("b".equals(opc)) {
                    String buscarComp;
                    opc = "1";
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
                                    if (comp.isMantRealizado() == false) {
                                        String opcYN;
                                        do {
                                            Funciones.cls();
                                            System.out.print(String.format("¿Quiere hacer mantenimiento a la computadora %s? [Y/n] ",
                                                    comp.getCodigo()));
                                            opcYN = s.next();
                                            switch (opcYN) {
                                                case "Y" -> {
                                                    est.realizarMantenimiento(comp, mant);
                                                    break;
                                                }
                                                case "n" -> {
                                                    Funciones.cls();
                                                    System.out.println("Regresando...");
                                                    Funciones.pause();
                                                    continue;
                                                }
                                                default -> {
                                                    Funciones.cls();
                                                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                                                    Funciones.pause();
                                                }
                                            }
                                        } while (!"Y".equals(opcYN) && !"n".equals(opcYN));
                                    } else {
                                        Funciones.cls();
                                        System.out.println("""
                                                           La computadora ingresada ya se ha realizado mantenimiento.
                                                           Ingrese el código otra computadora. 
                                                           """);
                                        Funciones.pause();
                                    }
                                } else {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! No se encontró la computadora, ingrese el código nuevamente.");
                                    Funciones.pause();
                                }
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese un código correcto.");
                                Funciones.pause();
                            }
                        } else if ("r".equals(buscarComp)) {
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                        } else {
                            Funciones.cls();
                            opc = pag;
                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                            Funciones.pause();
                        }
                    } while (!"r".equals(buscarComp));
                    
                } else if ("r".equals(opc)) {
                    Funciones.cls();
                    opc = pag;
                    System.out.println("Regresando...");
                    Funciones.pause();
                    break;
                } else if ("i".equals(opc)) {
                    est.registrarComputadora(lab);
                    opc = pag;
                } else {
                    Funciones.cls();
                    opc = pag;
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                    continue;
                }
                
            } while (!"r".equals(pag));
        }
    }

    //Imprimir computadoras
    public static void infoComputadoras(Estudiante est) {
        Scanner s = new Scanner(System.in);
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimientoEstudiante(est.getIdEstudiante());
        
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = mant.getLaboratorio();
        
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
                    if (a <= finPag) {
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
                        continue;
                    }
                } else if ("b".equals(opc)) {
                    String buscarComp;
                    opc = "1";
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
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            break;
                        }
                    } while (!"r".equals(buscarComp));
                    
                } else if ("r".equals(opc)) {
                    Funciones.cls();
                    opc = pag;
                    System.out.println("Regresando...");
                    Funciones.pause();
                    break;
                } else {
                    Funciones.cls();
                    opc = pag;
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                    continue;
                }
            } while (!"r".equals(pag));
        }
    }

    //Mostrar información de laboratorio
    public static void infoLaboratorio(Estudiante est) {
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimientoEstudiante(est.getIdEstudiante());
        
        Laboratorio lab = mant.getLaboratorio();
        
        Funciones.cls();
        System.out.println("""
                    --------------------------------------------------
                                  Información Laboratorio                  
                    --------------------------------------------------""".indent(12));
        System.out.println(lab.toString());
        System.out.println("            --------------------------------------------------");
        Funciones.pause();
    }

    //Menú de información de laboratorio
    public static void menuInfoLaboratorio(Estudiante est) throws IOException {
        //Variables
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
                            3. Regresar
                        -------------------------------------------
            """);
            System.out.print("    Opción: ");
            opcEst = s.next();
            
            switch (opcEst) {
                case "1" -> {
                    infoLaboratorio(est);
                }
                case "2" -> {
                    infoComputadoras(est);
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
                }
            }
        } while (!"3".equals(opcEst));
    }

    //Menú - Sin mantenimiento asignado
    public static int menu1(Estudiante estudiante) throws IOException {
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
                          ¡No estas asignado a un mantenimiento!       

                         Si ya estas asignado a un mantenimiento       
                              y sigue apareciendo este menú         
                              ¡CONTACTA A UN ADMINISTRADOR!           

                        1. Mostrar información personal
                        2. Editar datos personales            
                        3. Recargar menú                 
                        0. Cerrar sesión               
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
                case "0" -> {
                    Funciones.cls();
                    System.out.println(" Cerrando sesión...\n");
                    Funciones.pause();
                    estudiante.setCedula(null);
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

    //Menú - Con mantenimiento asignado
    public static int menu2(Estudiante estudiante) throws IOException {
        Scanner s = new Scanner(System.in);
        
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                      Menú Principal                  
                    --------------------------------------------------
                        1. Realizar mantenimiento a computadora
                        2. Ver laboratorio
                        3. Verificar mantenimiento
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
                    mantComputadoras(estudiante);
                }
                case "2" -> {
                    menuInfoLaboratorio(estudiante);
                }
                case "3" -> {
                    estudiante.verificarMantenimiento();
                }
                case "4" -> {
                    estudiante.finalizarMantenimiento();
                    return 0;
                }
                case "5" -> {
                    mostrarInfo(estudiante);
                }
                case "6" -> {
                    estudiante.editarDatosPersonales();
                }
                case "7" -> {
                    Funciones.cls();
                    System.out.println(" Recargando menú...\n");
                    Funciones.pause();
                    return 0;
                }
                case "0" -> {
                    Funciones.cls();
                    System.out.println(" Cerrando sesión...\n");
                    Funciones.pause();
                    estudiante.setCedula(null);
                    return 1;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                }
            }
        } while (true);
    }
    
    //Menú - Finalizó mantenimiento
    public static int menu3(Estudiante estudiante) throws IOException {
        Scanner s = new Scanner(System.in);
        
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                      Menú Principal                  
                    --------------------------------------------------
                        Usted ha acabado el mantenimiento, espere
                             las indicaciones de su docente.
                        1. Reanudar mantenimiento
                        2. Ver laboratorio
                        3. Verificar mantenimiento
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
                    EstudianteControlador estC = new EstudianteControlador();
                    estC.actualizarEstudianteMantenimiento(estudiante.getMatricula(), false);
                    return 0;
                }
                case "2" -> {
                    menuInfoLaboratorio(estudiante);
                }
                case "3" -> {
                    estudiante.verificarMantenimiento();
                }
                case "4" -> {
                    mostrarInfo(estudiante);
                }
                case "5" -> {
                    estudiante.editarDatosPersonales();
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
                    estudiante.setCedula(null);
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
                MantenimientoControlador mantC = new MantenimientoControlador();
                
                est = estC.buscarEstudiante(est.getCedula());
                est.setIdEstudiante(estC.buscarIdEstudiante(est.getMatricula()));
                
                Mantenimiento mant = mantC.buscarMantenimientoEstudiante(est.getIdEstudiante());
                
                if (mant.getIdMantenimiento() == 0) {
                    int flag = menu1(est);
                    if (flag == 1) {
                        main(args);
                        break;
                    } else if (flag == 0) {
                        continue;
                    }
                } else {
                    if(est.isFinalizoMantenimiento()){
                        int flag = menu3(est);
                        if (flag == 1) {
                            main(args);
                            break;
                        } else if (flag == 0) {
                            continue;
                        }
                    } else {
                        int flag = menu2(est);
                        if (flag == 1) {
                            main(args);
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
        }
    }
}
