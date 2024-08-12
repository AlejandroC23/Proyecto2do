/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ComputadoraControlador;
import controlador.ConexionBDD;
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
import java.sql.ResultSet;
import java.util.Scanner;
import modelo.Computadora;
import modelo.Encargado;
import modelo.Estudiante;
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
        """.indent(10));
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
                        String opcYN;
                        if ("0".equals(enc.getCedula())) {
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
                            if ("0".equals(enc.getTelefono())) {
                                enc = registro(enc.getCedula());
                                return enc;
                            }
                            return enc;
                        }
                    } while (true);
                }
                case "2" -> {
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
                //Regresar
                case "3" -> {
                    Funciones.pause();
                    System.out.println("Regresando...");
                    Funciones.cls();
                    enc.setCedula(null);
                    return enc;
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
    public static void infoCuentaEncargado(Encargado enc) {
        Funciones.cls();
        System.out.println("""
                    --------------------------------------------------
                                   Información Personal                  
                    --------------------------------------------------""".indent(12));
        System.out.println(enc.toString());
        System.out.println("            --------------------------------------------------");
        Funciones.pause();
    }

    //Imprimir computadoras
    public static void infoComputadoras(String cedula) {
        Scanner s = new Scanner(System.in);
        
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = labC.buscarLaboratorio(cedula);
        lab.setIdLaboratorio(labC.buscarIdLaboratorio(cedula));

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
    public static void infoLaboratorio(String cedula) {
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = labC.buscarLaboratorio(cedula);
        lab.setIdLaboratorio(labC.buscarIdLaboratorio(cedula));
        
        Funciones.cls();
        System.out.println("""
                    --------------------------------------------------
                                  Información Laboratorio                  
                    --------------------------------------------------""".indent(12));
        System.out.println(lab.toString());
        System.out.println("            --------------------------------------------------");
        Funciones.pause();
    }
    
    //Mostrar estudiantes asignados
    public static void estudiantesAsignados(String cedula) {
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = labC.buscarLaboratorio(cedula);
        lab.setIdLaboratorio(labC.buscarIdLaboratorio(cedula));
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimiento(lab);
        
        Funciones.cls();
        System.out.println("""
                    ------------------------------------------------------
                                    Estudiantes Asignados                  
                    ------------------------------------------------------""".indent(12));
        for (int i = 0; i < mant.getEstudiantes().size(); i++) {
            Estudiante est = mant.getEstudiantes().get(i);
            System.out.println("              " + (i+1) 
                    + ". " + est.getNombre() 
                    + " " + est.getApellido());
        }
        System.out.println("            ------------------------------------------------------");
        Funciones.pause();
    }
    
    //Mostrar Detalle Mantenimiento
    public static void estudiantesMantenimientos(String cedula) {
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = labC.buscarLaboratorio(cedula);
        lab.setIdLaboratorio(labC.buscarIdLaboratorio(cedula));
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimiento(lab);
        
        //Conexión
        ConexionBDD conexion = new ConexionBDD();
        Connection connection = (Connection) conexion.conectar();
        PreparedStatement ejecutar;
        ResultSet resultado;
        try {
            String consulta = "SELECT SUBSTRING_INDEX(det_mant_obj, '/-/', 1) AS est_cedula, usu_nombre, usu_apellido, "
                    + "SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 2), '/-/', -1) AS comp_codigo, "
                    + "SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 3), '/-/', -1) AS mant_tipo, "
                    + "SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 4), '/-/', -1) AS mant_obj "
                    + "FROM detalle_mantenimiento dm, usuarios u "
                    + "WHERE mant_id = " + mant.getIdMantenimiento() + " "
                    + "AND usu_cedula = SUBSTRING_INDEX(det_mant_obj, '/-/', 1);";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                Funciones.cls();
                int x = 0;
                System.out.println("""
                            ---------------------------------------------------------------
                                              Detalle del Mantenimiento                  
                            ---------------------------------------------------------------""".indent(15));
                while (resultado.next()) {
                    x++;
                    String cedulaEstudiante = resultado.getString("est_cedula");
                    String nombreCompleto = resultado.getString("usu_nombre") + " " 
                            + resultado.getString("usu_apellido");
                    String codigo = resultado.getString("comp_codigo");
                    String tipo = resultado.getString("mant_tipo");
                    String descripcion = resultado.getString("mant_obj");
                    System.out.println(x + ". " + cedulaEstudiante + " / " 
                            + nombreCompleto + " / "  
                            + "Computadora - " + codigo + " / "
                            + tipo + " / "  
                            + descripcion + "\n" );
                }
                System.out.println("            ---------------------------------------------------------------");
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
    
    //Menú de información de laboratorio
    public static void menuInfoLaboratorio(String cedula) throws IOException {
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
                    infoLaboratorio(cedula);
                }
                case "2" -> {
                    infoComputadoras(cedula);
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
    
    //Menú información de mantenimiento
    public static void menuMantenimiento(String cedula) throws IOException {
        //Variables
        Scanner s = new Scanner(System.in);
        String opcEst;
        do {
            Funciones.cls();
            System.out.println("""
                        --------------------------------------------
                                        Mantenimiento            
                        --------------------------------------------
                            1. Ver estudiantes asignados
                            2. Ver mantenimientos de estudiantes
                            3. Regresar
                        --------------------------------------------
            """);
            System.out.print("    Opción: ");
            opcEst = s.next();

            switch (opcEst) {
                case "1" -> {
                    estudiantesAsignados(cedula);
                }
                case "2" -> {
                    estudiantesMantenimientos(cedula);
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
    
    //Menú de informes
    public static void menuInformes(String cedula) throws IOException {
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = labC.buscarLaboratorio(cedula);
        lab.setIdLaboratorio(labC.buscarIdLaboratorio(cedula));
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimiento(lab);
        
        //Variables
        Scanner s = new Scanner(System.in);
        String opcEst;
        do {
            Funciones.cls();
            System.out.println("""
                        -------------------------------------------
                                         Informes            
                        -------------------------------------------
                            1. Imprimir informe
                            2. Buscar informe
                            3. Imprimir informe de computadora
                            4. Regresar
                        -------------------------------------------
            """);
            System.out.print("    Opción: ");
            opcEst = s.next();

            switch (opcEst) {
                case "1" -> {
                    lab.imprimirInforme();
                }
                case "2" -> {
                    infoComputadoras(cedula);
                }
                case "3" -> {
                    lab.imprimirInformeComputadoras();
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
                        0. Cerrar sesión               
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            String opc = s.next();
            switch (opc) {
                //Mostrar información encargado
                case "1" -> {
                    infoCuentaEncargado(encargado);
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
                case "0" -> {
                    Funciones.cls();
                    System.out.println(" Cerrando sesión...\n");
                    Funciones.pause();
                    encargado.setCedula(null);
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
                        3. Ver laboratorio
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
                case "2" -> {
                    menuInformes(encargado.getCedula());
                }
                case "3" -> {
                    menuInfoLaboratorio(encargado.getCedula());
                }
                case "4" -> {
                    infoCuentaEncargado(encargado);
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
                    encargado.setCedula(null);
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
        lab.setIdLaboratorio(labC.buscarIdLaboratorio(encargado.getCedula()));
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimiento(lab);
        
        do {
            Funciones.cls();
            System.out.println("""
                    --------------------------------------------------
                                      Menú Principal                  
                    --------------------------------------------------      
                        1. Asignar estudiantes a mantenimiento
                        2. Quitar estudiante de mantenimiento
                        3. Ver laboratorio
                        4. Información de mantenimiento
                        5. Finalizar mantenimiento
                        6. Mostrar información personal
                        7. Editar datos personales            
                        8. Recargar menú                 
                        0. Cerrar sesión               
                    --------------------------------------------------
            """);
            System.out.print("    Opción: ");
            String opc = s.next();
            switch (opc) {
                case "1" -> {
                    mant.asignarEstudiante();
                }
                case "2" -> {
                    mant.quitarEstudiante();
                }
                case "3" -> {
                    menuInfoLaboratorio(encargado.getCedula());
                }
                case "4" -> {
                    menuMantenimiento(encargado.getCedula());
                }
                case "5" -> {
                    boolean finalizar = encargado.finalizarMantenimiento();
                    if (finalizar) {
                        return 0;
                    }else{
                        continue;
                    }
                }
                case "6" -> {
                    infoCuentaEncargado(encargado);
                }
                case "7" -> {
                    encargado.editarDatosPersonales();
                }
                case "8" -> {
                    Funciones.cls();
                    System.out.println("Recargando menú...\n");
                    Funciones.pause();
                    return 0;
                }
                case "0" -> {
                    Funciones.cls();
                    System.out.println("Cerrando sesión...\n");
                    Funciones.pause();
                    encargado.setCedula(null);
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
        if (enc.getCedula() != null) {
            do {
                EncargadoControlador encC = new EncargadoControlador();
                LaboratorioControlador labC = new LaboratorioControlador();
                MantenimientoControlador mantC = new MantenimientoControlador();

                enc = encC.buscarEncargado(enc.getCedula());
                
                Laboratorio lab = labC.buscarLaboratorio(enc.getCedula());
                lab.setIdLaboratorio(labC.buscarIdLaboratorio(enc.getCedula()));
                
                Mantenimiento mant = new Mantenimiento();
                mant.setIdMantenimiento(mantC.buscarIdMantenimiento(lab.getIdLaboratorio()));

                if (enc.getCedula().equals(lab.getEncargado().getCedula()) == false) {
                    int flag = menu1(enc);
                    if (flag == 1) {
                        main(args);
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

                if (enc.getCedula() == null) {
                    break;
                }
            } while (true);
        }
    }
}
