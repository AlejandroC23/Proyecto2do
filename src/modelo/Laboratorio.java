/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ComputadoraControlador;
import controlador.ConexionBDD;
import controlador.EncargadoControlador;
import controlador.Funciones;
import controlador.LaboratorioControlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class Laboratorio {

    private int idLaboratorio;
    private String nombre;
    private int edificio;
    private String ciudad;
    private ArrayList<Computadora> computadoras;
    private Encargado encargado;

    public Laboratorio() {
    }

    public Laboratorio(int idLaboratorio, String nombre, int edificio, String ciudad, ArrayList<Computadora> computadoras, Encargado encargado) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.edificio = edificio;
        this.ciudad = ciudad;
        this.computadoras = computadoras;
        this.encargado = encargado;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdificio() {
        return edificio;
    }

    public void setEdificio(int edificio) {
        this.edificio = edificio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Computadora> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(ArrayList<Computadora> computadoras) {
        this.computadoras = computadoras;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }

    @Override
    public String toString() {
        return "                Nombre: " + getNombre() + "\n"
                + "                Edificio: " + getEdificio() + "\n"
                + "                Ciudad: " + getCiudad() + "\n"
                + "                Cantidad de computadoras: " + getComputadoras().size() + "\n"
                + "                Encargado: " + getEncargado().getNombre()
                + " " + getEncargado().getApellido();
    }

    public void buscarInformeMantenimiento() {
        Scanner s = new Scanner(System.in);
        do {
            Funciones.cls();
            System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD): ");
            String fechaIni = s.next();
            if (Funciones.isValidDate(fechaIni)) {
                Funciones.cls();
                System.out.println("Ingrese la fecha de fin (YYYY-MM-DD): ");
                String fechaFin = s.next();
                if (Funciones.isValidDate(fechaFin)) {
                    ConexionBDD conexion = new ConexionBDD();
                    Connection connection = (Connection) conexion.conectar();
                    PreparedStatement ejecutar;
                    ResultSet resultado;

                    ArrayList<String> detalleInforme = new ArrayList<>();

                    try {
                        String consulta = "SELECT m.mant_id, mant_fechaini, mant_fechafin, "
                                + "SUM(IF(SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 3), '/-/', -1) = 'MANTENIMIENTO PREVENTIVO', 1, 0)) AS mant_prev, "
                                + "SUM(IF(SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 3), '/-/', -1) = 'MANTENIMIENTO CORRECTIVO', 1, 0)) AS mant_corr "
                                + "FROM detalle_mantenimiento dm, mantenimientos m "
                                + "WHERE dm.mant_id = m.mant_id "
                                + "AND mant_fechafin IS NOT NULL "
                                + "AND (mant_fechaini = '" + fechaIni + "' "
                                + "OR mant_fechaini > '" + fechaIni + "') "
                                + "AND (mant_fechafin = '" + fechaFin + "' "
                                + "OR mant_fechafin < '" + fechaFin + "') "
                                + "GROUP BY m.mant_id;";

                        ejecutar = (PreparedStatement) connection.prepareCall(consulta);

                        resultado = ejecutar.executeQuery(consulta);

                        while (resultado.next()) {
                            String texto;
                            String fechaInicio = resultado.getString("mant_fechaini");
                            String fechaFinal = resultado.getString("mant_fechafin");
                            int cPrev = resultado.getInt("mant_prev");
                            int cCorr = resultado.getInt("mant_corr");
                            texto = fechaInicio + "/" + fechaFinal + "/" + cPrev + "/" + cCorr;
                            detalleInforme.add(texto);
                        }

                    } catch (Exception e) {
                        System.out.println("ERROR: " + e);
                    }

                    if (detalleInforme.isEmpty()) {
                        Funciones.cls();
                        System.out.println("Aún no existen mantenimientos registrados al laboratorio. Intentelo de nuevo más tarde.");
                        Funciones.pause();
                    } else {
                        Funciones.cls();
                        System.out.println("""
                                            ------------------------------------------------
                                                              Introducción                  
                                            ------------------------------------------------
                                                 Bienvenido/a al apartado de informe de 
                                                laboratorio a continuación se presentarán 
                                                los diferentes mantenimientos realizados 
                                                            al laboratorio.

                                             Para regresar al menú anterior puede ingresar
                                                             la letra 'r'.

                                                         Gracias por leer :)
                                            ------------------------------------------------
                                           """);
                        Funciones.pause();
                        String opc = "1";
                        String pag = "1";

                        int finPag;

                        if (detalleInforme.size() < 25) {
                            finPag = 1;
                        } else {
                            finPag = (int) Math.ceil(detalleInforme.size() / 25);
                        }

                        do {
                            if (Funciones.isValidNumeric(opc)) {
                                int a = Integer.parseInt(opc);
                                if (a <= finPag) {
                                    pag = opc;
                                    int x = a * 25;
                                    System.out.println(String.format("""
                                                              --------------------------------------------------------------------
                                                                Informe Laboratorio  - %s
                                                              --------------------------------------------------------------------
                                                        """, getNombre()));
                                    if (a == finPag) {
                                        for (int i = (x - 25); i < detalleInforme.size(); i++) {
                                            String[] texto = detalleInforme.get(i).split("/");
                                            System.out.println("Fecha: " + texto[0] + " - " + texto[1]
                                                    + " / Mant. Preventivos: " + texto[2]
                                                    + " / Mant. Correctivos: " + texto[3]);
                                        }
                                    } else {
                                        for (int i = (x - 25); i < x; i++) {
                                            System.out.println(detalleInforme.get(i));
                                        }
                                    }
                                    System.out.println("      --------------------------------------------------------------------");
                                    System.out.println("                                                        Página. " + a);
                                    System.out.print("     Ingrese 'r' para regresar. \n");
                                    System.out.print("     Ingrese el número de página u opción que desea: ");
                                    opc = s.next();
                                } else {
                                    Funciones.cls();
                                    opc = pag;
                                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                                    Funciones.pause();
                                    continue;
                                }
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
                            }
                        } while (!"r".equals(pag));
                        if (pag.equals("r")) {
                            break;
                        }
                    }
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una fecha correcta.");
                    Funciones.pause();
                }
            } else {
                Funciones.cls();
                System.out.println("¡ERROR! Ingrese una fecha correcta.");
                Funciones.pause();
            }
        } while (true);
    }

    public void imprimirInformeComputadoras() {
        Scanner s = new Scanner(System.in);
        if (getComputadoras().isEmpty()) {
            Funciones.cls();
            System.out.println("Aún no se realizan mantenimientos a computadoras del laboratorio. Intentelo de nuevo más tarde.");
            Funciones.pause();
        } else {
            Funciones.cls();
            System.out.println("""
                                ------------------------------------------------
                                                  Introducción                  
                                ------------------------------------------------
                                    Bienvenido/a al apartado de informes de
                                   computadoras, a continuación se presentará
                                        un listado de computadoras en el
                                                  laboratorio.
                               
                                 Para regresar al menú anterior puede ingresar
                                   la letra 'r'. Si desea hacer mantenimiento
                                    a un computador, ingrese 'b' y digite el
                                      código del computador que necesite.
                               
                                             Gracias por leer :)
                                ------------------------------------------------
                    """);
            Funciones.pause();
            String opc = "1";
            String pag = "1";

            int finPag;

            if (getComputadoras().size() < 25) {
                finPag = 1;
            } else {
                finPag = (int) Math.ceil(getComputadoras().size() / 25);
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
                                            """, getNombre()));
                        if (a == finPag) {
                            for (int i = (x - 25); i < getComputadoras().size(); i++) {
                                System.out.println(getComputadoras().get(i).infoBasica());
                            }
                        } else {
                            for (int i = (x - 25); i < x; i++) {
                                System.out.println(getComputadoras().get(i).infoBasica());
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
                                                getIdLaboratorio(),
                                                buscarComp);
                                if (comp.getIdComputadora() != 0) {
                                    String opcYN;
                                    do {
                                        Funciones.cls();
                                        System.out.print(String.format("¿Quiere el informe de la computadora %s? [Y/n] ",
                                                comp.getCodigo()));
                                        opcYN = s.next();
                                        switch (opcYN) {
                                            case "Y" -> {
                                                ConexionBDD conexion = new ConexionBDD();
                                                Connection connection = (Connection) conexion.conectar();
                                                PreparedStatement ejecutar;
                                                ResultSet resultado;

                                                try {
                                                    String consulta = "SELECT c.comp_id, m.mant_id, mant_fechaini, mant_fechafin,"
                                                            + "SUM(IF(SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 3), '/-/', -1) = 'MANTENIMIENTO PREVENTIVO', 1, 0)) AS mant_prev, "
                                                            + "SUM(IF(SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 3), '/-/', -1) = 'MANTENIMIENTO CORRECTIVO', 1, 0)) AS mant_corr "
                                                            + "FROM detalle_mantenimiento dm, computadoras c, mantenimientos m "
                                                            + "WHERE dm.comp_id = c.comp_id "
                                                            + "AND dm.mant_id = m.mant_id "
                                                            + "AND mant_fechafin IS NOT NULL"
                                                            + "AND comp_codigo = '" + comp.getCodigo() + "' "
                                                            + "GROUP BY m.mant_id;";

                                                    ejecutar = (PreparedStatement) connection.prepareCall(consulta);

                                                    resultado = ejecutar.executeQuery(consulta);
                                                    Funciones.cls();

                                                    int x = 0;
                                                    System.out.println("""
                                                                                    --------------------------------------------------------------------
                                                                                                             Informe Computadora
                                                                                    --------------------------------------------------------------------
                                                                       """);
                                                    while (resultado.next()) {
                                                        x++;
                                                        String fechaIni = resultado.getString("mant_fechaini");
                                                        String fechaFin = resultado.getString("mant_fechafin");
                                                        int cPrev = resultado.getInt("mant_prev");
                                                        int cCorr = resultado.getInt("mant_corr");
                                                        System.out.println(x + ". " + fechaIni + " - " + fechaFin + " / "
                                                                + "Mant. Preventivos: " + cPrev + " / "
                                                                + "Mant. Correctivos: " + cCorr + " / ");

                                                    }
                                                    Funciones.pause();
                                                    resultado.close();
                                                } catch (Exception e) {
                                                    System.out.println("ERROR: " + e);
                                                }
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

    public void imprimirInforme() {

        Scanner s = new Scanner(System.in);

        ConexionBDD conexion = new ConexionBDD();
        Connection connection = (Connection) conexion.conectar();
        PreparedStatement ejecutar;
        ResultSet resultado;

        ArrayList<String> detalleInforme = new ArrayList<>();

        try {
            String consulta = "SELECT m.mant_id, mant_fechaini, mant_fechafin, "
                    + "SUM(IF(SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 3), '/-/', -1) = 'MANTENIMIENTO PREVENTIVO', 1, 0)) AS mant_prev, "
                    + "SUM(IF(SUBSTRING_INDEX(SUBSTRING_INDEX(det_mant_obj, '/-/', 3), '/-/', -1) = 'MANTENIMIENTO CORRECTIVO', 1, 0)) AS mant_corr "
                    + "FROM detalle_mantenimiento dm, mantenimientos m "
                    + "WHERE dm.mant_id = m.mant_id "
                    + "AND mant_fechafin IS NOT NULL "
                    + "GROUP BY m.mant_id;";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);

            resultado = ejecutar.executeQuery(consulta);

            while (resultado.next()) {
                String texto;
                String fechaIni = resultado.getString("mant_fechaini");
                String fechaFin = resultado.getString("mant_fechafin");
                int cPrev = resultado.getInt("mant_prev");
                int cCorr = resultado.getInt("mant_corr");
                texto = fechaIni + "/" + fechaFin + "/" + cPrev + "/" + cCorr;
                detalleInforme.add(texto);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

        if (detalleInforme.isEmpty()) {
            Funciones.cls();
            System.out.println("Aún no existen mantenimientos registrados al laboratorio. Intentelo de nuevo más tarde.");
            Funciones.pause();
        } else {
            Funciones.cls();
            System.out.println("""
                                ------------------------------------------------
                                                  Introducción                  
                                ------------------------------------------------
                                     Bienvenido/a al apartado de informe de 
                                    laboratorio a continuación se presentarán 
                                    los diferentes mantenimientos realizados 
                                                al laboratorio.
                               
                                 Para regresar al menú anterior puede ingresar
                                                 la letra 'r'.
                               
                                             Gracias por leer :)
                                ------------------------------------------------
                               """);
            Funciones.pause();
            String opc = "1";
            String pag = "1";

            int finPag;

            if (detalleInforme.size() < 25) {
                finPag = 1;
            } else {
                finPag = (int) Math.ceil(detalleInforme.size() / 25);
            }

            do {
                if (Funciones.isValidNumeric(opc)) {
                    int a = Integer.parseInt(opc);
                    if (a <= finPag) {
                        pag = opc;
                        int x = a * 25;
                        System.out.println(String.format("""
                                                  --------------------------------------------------------------------
                                                    Informe Laboratorio  - %s
                                                  --------------------------------------------------------------------
                                            """, getNombre()));
                        if (a == finPag) {
                            for (int i = (x - 25); i < detalleInforme.size(); i++) {
                                String[] texto = detalleInforme.get(i).split("/");
                                System.out.println("Fecha: " + texto[0] + " - " + texto[1]
                                        + " / Mant. Preventivos: " + texto[2]
                                        + " / Mant. Correctivos: " + texto[3]);
                            }
                        } else {
                            for (int i = (x - 25); i < x; i++) {
                                System.out.println(detalleInforme.get(i));
                            }
                        }
                        System.out.println("      --------------------------------------------------------------------");
                        System.out.println("                                                        Página. " + a);
                        System.out.print("     Ingrese 'r' para regresar. \n");
                        System.out.print("     Ingrese el número de página u opción que desea: ");
                        opc = s.next();
                    } else {
                        Funciones.cls();
                        opc = pag;
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
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

    public void editarDatos() throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        Usuario usu = new Usuario();
        LaboratorioControlador labC = new LaboratorioControlador();

        Laboratorio labTemp = new Laboratorio();

        String titulo = """
                    --------------------------------------------------
                                    Editar información
                    --------------------------------------------------                
        """;
        String opc;

        labTemp.setIdLaboratorio(getIdLaboratorio());

        do {
            //Campo - Nombres
            do {
                Funciones.cls();
                System.out.println("El nombre del laboratorio es: " + getNombre());
                System.out.print("¿Desea cambiar el nombre? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
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
                                labTemp.setNombre(nombres.toUpperCase());
                                break;
                            }
                        } while (true);
                        opc = "";
                        break;
                    }
                    case "n" -> {
                        labTemp.setNombre(getNombre());
                        break;
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Edificio
            do {
                Funciones.cls();
                System.out.println("El nro. de edificio es: " + getEdificio());
                System.out.print("¿Desea cambiar el edificio? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
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
                                labTemp.setEdificio(edi);
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        labTemp.setEdificio(getEdificio());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Ciudad
            do {
                Funciones.cls();
                System.out.println("La ciudad es: " + getCiudad());
                System.out.print("¿Desea cambiar la ciudad? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
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
                                    labTemp.setCiudad("IBARRA");
                                    break;
                                }
                                case "2" -> {
                                    labTemp.setCiudad("URCUQUI");
                                    break;
                                }
                                default -> {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                                    Funciones.pause();
                                }
                            }
                        } while (!opcCiu.equals("1") && !opcCiu.equals("2"));
                    }
                    case "n" -> {
                        labTemp.setCiudad(getCiudad());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Encargado
            do {
                Funciones.cls();
                System.out.println("El encargado es: " + getEncargado().getNombre() + " " + getEncargado().getApellido());
                System.out.print("¿Desea cambiar el encargado? [Y/n] ");
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
                                        labTemp.setEncargado(enc);
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
                        labTemp.setEncargado(getEncargado());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));
            
            labC.editarLaboratorio(labTemp);
            System.out.println(titulo);
            System.out.println("""
                                                      ¡DATOS CAMBIADOS CON ÉXITO! 
                                         Los datos del laboratorio fueron cambiados con éxito.
                               """);
            Funciones.pause();
            break;
        } while (true);
    }
}
