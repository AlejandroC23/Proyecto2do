/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.EstadoControlador;
import controlador.Funciones;
import controlador.TipoHardwareControlador;
import controlador.TipoSoftwareControlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class Computadora {

    private int idComputadora;
    private String codigo;
    private String marca;
    private boolean mantRealizado;
    private Estado estado;
    private ArrayList<Software> software;
    private ArrayList<Hardware> hardware;

    public Computadora() {
    }

    public Computadora(int idComputadora, String codigo, String marca, boolean mantRealizado, Estado estado, ArrayList<Software> software, ArrayList<Hardware> hardware) {
        this.idComputadora = idComputadora;
        this.codigo = codigo;
        this.marca = marca;
        this.mantRealizado = mantRealizado;
        this.estado = estado;
        this.software = software;
        this.hardware = hardware;
    }

    public int getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(int idComputadora) {
        this.idComputadora = idComputadora;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isMantRealizado() {
        return mantRealizado;
    }

    public void setMantRealizado(boolean mantRealizado) {
        this.mantRealizado = mantRealizado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ArrayList<Software> getSoftware() {
        return software;
    }

    public void setSoftware(ArrayList<Software> software) {
        this.software = software;
    }

    public ArrayList<Hardware> getHardware() {
        return hardware;
    }

    public void setHardware(ArrayList<Hardware> hardware) {
        this.hardware = hardware;
    }

    public String infoBasica() {
        String codigo = getCodigo();
        String marca = getMarca();
        String estado = getEstado().getDescripcion();

        if (codigo.length() < 18 && codigo.length() != 18) {
            if (codigo.length() % 2 == 0) {
                int x = ((18 - codigo.length()) / 2) - 1;
                codigo = " ".repeat(x / 2) + codigo + " ".repeat((x / 2) - 1) + "|";
            } else {
                int x = (Math.round(18 - codigo.length() / 2)) - 1;
                codigo = " ".repeat(x / 2) + codigo + " ".repeat((x / 2) - 1) + "|";
            }
        } else {
            codigo = codigo + "|";
        }
        if (marca.length() < 56 && codigo.length() != 56) {
            if (marca.length() % 2 == 0) {
                int x = ((56 - marca.length()) / 2);
                marca = " ".repeat(x / 2) + marca + " ".repeat((x / 2) - 1) + "|";
            } else {
                int x = (Math.round(56 - marca.length() / 2));
                marca = " ".repeat(x / 2) + marca + " ".repeat((x / 2) - 1) + "|";
            }
        } else {
            marca = marca + "|";
        }
        if (estado.length() < 18 && estado.length() != 18) {
            if (estado.length() % 2 == 0) {
                int x = ((18 - estado.length()) / 2) - 1;
                estado = " ".repeat(x / 2) + estado + " ".repeat((x / 2)) + "|";
            } else {
                int x = (Math.round(18 - estado.length() / 2)) - 1;
                estado = " ".repeat(x / 2) + estado + " ".repeat((x / 2)) + "|";
            }
        } else {
            estado = estado + "|";
        }
        return "     |" + codigo + marca + estado;
    }

    public void agregarSoftware() throws IOException {

        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        String titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                     Sistemas Operativos
                                        --------------------------------------------
                        """;

        TipoSoftwareControlador tsC = new TipoSoftwareControlador();
        ArrayList<TipoSoftware> listadoTS = tsC.listarTipoSoftware();

        EstadoControlador estaC = new EstadoControlador();
        ArrayList<Estado> listadoEstados = estaC.listarEstados();

        ArrayList<Software> softTemp = new ArrayList<>();

        int cso = 0;
        do {
            Software so = new Software();
            cso++;

            //Sistema Operativo - Descripcion
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               Tipo de Sistema Operativo:
                                                 1. Windows
                                                 2. Linux
                                                 3. MacOS
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        so.setDescripcion("WINDOWS/-/"+getCodigo());
                        break;
                    }
                    case "2" -> {
                        so.setDescripcion("LINUX/-/"+getCodigo());
                        break;
                    }
                    case "3" -> {
                        so.setDescripcion("MACOS/-/"+getCodigo());
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

            //Sistema Operativo - Nombre
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el nombre del sistema operativo: ");
                String nombre = br.readLine();
                if (Funciones.isValidAdress(nombre)) {
                    so.setNombre(nombre.toUpperCase());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el nombre nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Sistema Operativo - Version
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese la versión detallada del sistema operativo: ");
                String version = br.readLine();
                if (Funciones.isValidCode(version)) {
                    so.setVersion(version);
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese la versión nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Sistema Operativo - Estado
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               El estado del sistema operativo es:
                                                 1. Funcional
                                                 2. Necesita reparación
                                                 3. Dañado
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        so.setEstado(listadoEstados.get(0));
                        break;
                    }
                    case "2" -> {
                        so.setEstado(listadoEstados.get(1));
                        setEstado(listadoEstados.get(1));
                        break;
                    }
                    case "3" -> {
                        so.setEstado(listadoEstados.get(2));
                        setEstado(listadoEstados.get(3));
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

            so.setTipo(listadoTS.get(0));
            softTemp.add(so);
            String opcYN;
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("¿El computador tiene otro sistema operativo? [Y/n] ");
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

            if ("n".equals(opcYN)) {
                break;
            } else {
                if (cso == 5) {
                    Funciones.cls();
                    System.out.println("""
                                       No puede agregar más sistemas operativos. 
                                       Contactese con el docente si necesita agregar más sistemas operativos""");
                    Funciones.pause();
                    break;
                }
            }
        } while (true);

        titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                        Aplicaciones
                                        --------------------------------------------
                        """;

        do {
            Software app = new Software();

            //Aplicacion - Nombre
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el nombre de la aplicación: ");
                String nombre = br.readLine();
                if (Funciones.isValidText(nombre)) {
                    app.setNombre(nombre.toUpperCase());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el nombre nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Aplicacion - Descripcion
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               ¿Para qué sistema operativo es?
                                                 1. Windows
                                                 2. Linux
                                                 3. MacOS
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        app.setDescripcion("WINDOWS/-/"+getCodigo());
                        break;
                    }
                    case "2" -> {
                        app.setDescripcion("LINUX/-/"+getCodigo());
                        break;
                    }
                    case "3" -> {
                        app.setDescripcion("MACOS/-/"+getCodigo());
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

            //Aplicacion - Version
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese la versión detallada de la aplicación: ");
                String version = br.readLine();
                if (Funciones.isValidCode(version)) {
                    app.setVersion(version.toUpperCase());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese la versión nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Aplicacion - Estado
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               El estado de la aplicación es:
                                                 1. Funcional
                                                 2. Necesita reparación
                                                 3. Dañado
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        app.setEstado(listadoEstados.get(0));
                        break;
                    }
                    case "2" -> {
                        app.setEstado(listadoEstados.get(1));
                        break;
                    }
                    case "3" -> {
                        app.setEstado(listadoEstados.get(2));
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

            app.setTipo(listadoTS.get(0));
            softTemp.add(app);

            String opcYN;
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("¿Quiere añadir otra aplicación a la computadora? [Y/n] ");
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

            if ("n".equals(opcYN)) {
                break;
            }
        } while (true);

        setSoftware(softTemp);
    }

    public void agregarHardware() throws IOException {

        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        TipoHardwareControlador thC = new TipoHardwareControlador();
        ArrayList<TipoHardware> listadoTH = thC.listarTipoHardware();

        EstadoControlador estaC = new EstadoControlador();
        ArrayList<Estado> listadoEstados = estaC.listarEstados();

        ArrayList<Hardware> hardTemp = new ArrayList<>();

        int cram = 0;
        int cdisc = 0;

        String titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                    Hardware - Placa Madre
                                        --------------------------------------------
                        """;

        //Placa Madre
        do {
            Hardware pm = new Hardware();

            //Placa Madre - Descripción
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el modelo de la placa madre: ");
                String modelo = br.readLine();
                if (Funciones.isValidCode(modelo)) {
                    pm.setDescripcion(modelo.toUpperCase() + "/-/" +getCodigo());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el modelo nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Placa Madre - Nro. Serie
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el número de serie de la placa madre (si no tiene ingrese NA): ");
                String nroSerie = br.readLine();
                if (Funciones.isValidCode(nroSerie)) {
                    pm.setNroSerie(nroSerie.toUpperCase());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el nro. serie nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Placa Madre - Estado
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               El estado de la placa madre es:
                                                 1. Funcional
                                                 2. Necesita reparación
                                                 3. Dañado
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        pm.setEstado(listadoEstados.get(0));
                        setEstado(listadoEstados.get(0));
                        break;
                    }
                    case "2" -> {
                        pm.setEstado(listadoEstados.get(1));
                        setEstado(listadoEstados.get(3));
                        break;
                    }
                    case "3" -> {
                        pm.setEstado(listadoEstados.get(2));
                        setEstado(listadoEstados.get(3));
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

            pm.setTipo(listadoTH.get(5));
            hardTemp.add(pm);

            break;
        } while (true);

        titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                    Hardware - Procesador
                                        --------------------------------------------
                        """;

        //Procesador
        do {
            Hardware procesador = new Hardware();

            //Procesador - Descripcion
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               Tipo de Procesador:
                                                 1. AMD
                                                 2. Intel
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        String modelo;
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el modelo del procesador AMD: ");
                            modelo = s.next();
                            if (Funciones.isValidCode(modelo)) {
                                procesador.setDescripcion("AMD/-/" + modelo.toUpperCase() + "/-/" +getCodigo());
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el modelo nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        break;
                    }
                    case "2" -> {
                        String modelo;
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el modelo del procesador Intel: ");
                            modelo = s.next();
                            if (Funciones.isValidCode(modelo)) {
                                procesador.setDescripcion("INTEL/-/" + modelo.toUpperCase() + "/-/" +getCodigo());
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el modelo nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);
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

            //Procesador - Nro. Serie
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el número de serie del procesador (si no tiene ingrese NA): ");
                String nroSerie = br.readLine();
                if (Funciones.isValidCode(nroSerie)) {
                    procesador.setNroSerie(nroSerie.toUpperCase());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el nro. serie nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Procesador - Estado
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               El estado del procesador es:
                                                 1. Funcional
                                                 2. Necesita reparación
                                                 3. Dañado
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        procesador.setEstado(listadoEstados.get(0));
                        setEstado(listadoEstados.get(0));
                        break;
                    }
                    case "2" -> {
                        procesador.setEstado(listadoEstados.get(1));
                        setEstado(listadoEstados.get(3));
                        break;
                    }
                    case "3" -> {
                        procesador.setEstado(listadoEstados.get(2));
                        setEstado(listadoEstados.get(3));
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

            procesador.setTipo(listadoTH.get(2));
            hardTemp.add(procesador);

            break;
        } while (true);

        titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                       Hardware - RAM
                                        --------------------------------------------
                        """;

        //RAM
        do {
            Hardware ram = new Hardware();
            cram++;

            //RAM - Descripción
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el modelo de la RAM: ");
                String modelo = br.readLine();
                if (Funciones.isValidCode(modelo)) {
                    do {
                        Funciones.cls();
                        System.out.println(titulo);
                        System.out.print("Ingrese la capacidad de la RAM (GB): ");
                        String capacidad = br.readLine();
                        if (Funciones.isValidNumeric(capacidad)) {
                            do {
                                Funciones.cls();
                                System.out.println(titulo);
                                System.out.print("Ingrese la velocidad de la RAM (MHz): ");
                                String velocidad = br.readLine();
                                if (Funciones.isValidNumeric(velocidad)) {
                                    ram.setDescripcion(modelo.toUpperCase()
                                            + "/-/" + capacidad.toUpperCase()
                                            + "/-/" + velocidad.toUpperCase()
                                            + "/-/" +getCodigo());
                                    break;
                                } else {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                                    System.out.println("Ingrese el nombre nuevamente.");
                                    Funciones.pause();
                                }
                            } while (true);
                            break;
                        } else {
                            Funciones.cls();
                            System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                            System.out.println("Ingrese el nombre nuevamente.");
                            Funciones.pause();
                        }
                    } while (true);
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el nombre nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //RAM - Nro. Serie
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el número de serie de la RAM (si no tiene ingrese NA): ");
                String nroSerie = br.readLine();
                if (Funciones.isValidCode(nroSerie)) {
                    ram.setNroSerie(nroSerie.toUpperCase());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el nro. serie nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //RAM - Estado
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               El estado de la RAM es:
                                                 1. Funcional
                                                 2. Necesita reparación
                                                 3. Dañado
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        ram.setEstado(listadoEstados.get(0));
                        setEstado(listadoEstados.get(0));
                        break;
                    }
                    case "2" -> {
                        ram.setEstado(listadoEstados.get(1));
                        setEstado(listadoEstados.get(3));
                        break;
                    }
                    case "3" -> {
                        ram.setEstado(listadoEstados.get(2));
                        setEstado(listadoEstados.get(1));
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

            ram.setTipo(listadoTH.get(3));
            hardTemp.add(ram);

            String opcYN;
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("¿Quiere añadir otra ram a la computadora? [Y/n] ");
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

            if ("n".equals(opcYN)) {
                break;
            } else {
                if (cram == 4) {
                    Funciones.cls();
                    System.out.println("""
                                       No puede agregar más RAMs. 
                                       Contactese con el docente si necesita agregar otra RAM.""");
                    Funciones.pause();
                    break;
                }
            }
        } while (true);

        titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                    Hardware - Disco Duro
                                        --------------------------------------------
                        """;

        //Discos duros
        do {
            Hardware disco = new Hardware();
            cdisc++;

            //Disco Duro - Descripción
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el modelo del disco duro: ");
                String modelo = br.readLine();
                if (Funciones.isValidCode(modelo)) {
                    do {
                        Funciones.cls();
                        System.out.println(titulo);
                        System.out.print("Ingrese la capacidad del disco duro (GB): ");
                        String capacidad = br.readLine();
                        if (Funciones.isValidNumeric(capacidad)) {
                            do {
                                Funciones.cls();
                                System.out.println(titulo);
                                System.out.print("Ingrese la velocidad del disco duro (MB/s): ");
                                String velocidad = br.readLine();
                                if (Funciones.isValidNumeric(velocidad)) {
                                    disco.setDescripcion(modelo.toUpperCase()
                                            + "/-/" + capacidad.toUpperCase()
                                            + "/-/" + velocidad.toUpperCase()
                                            + "/-/" + getCodigo());
                                    break;
                                } else {
                                    Funciones.cls();
                                    System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                                    System.out.println("Ingrese la velocidad nuevamente.");
                                    Funciones.pause();
                                }
                            } while (true);
                            break;
                        } else {
                            Funciones.cls();
                            System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                            System.out.println("Ingrese la capacidad nuevamente.");
                            Funciones.pause();
                        }
                    } while (true);
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el modelo nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Disco Duro - Nro. Serie
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("Ingrese el número de serie del disco duro (si no tiene ingrese NA): ");
                String nroSerie = br.readLine();
                if (Funciones.isValidCode(nroSerie)) {
                    disco.setNroSerie(nroSerie.toUpperCase());
                    break;
                } else {
                    Funciones.cls();
                    System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                    System.out.println("Ingrese el nro. serie nuevamente.");
                    Funciones.pause();
                }
            } while (true);

            //Disco Duro - Estado
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.println("""
                                               El estado del disco duro es:
                                                 1. Funcional
                                                 2. Necesita reparación
                                                 3. Dañado
                                   """);
                System.out.print("           Opción: ");
                String opc = s.next();
                switch (opc) {
                    case "1" -> {
                        disco.setEstado(listadoEstados.get(0));
                        setEstado(listadoEstados.get(0));
                        break;
                    }
                    case "2" -> {
                        disco.setEstado(listadoEstados.get(1));
                        setEstado(listadoEstados.get(1));
                        break;
                    }
                    case "3" -> {
                        disco.setEstado(listadoEstados.get(2));
                        setEstado(listadoEstados.get(1));
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

            disco.setTipo(listadoTH.get(6));
            hardTemp.add(disco);

            String opcYN;
            do {
                Funciones.cls();
                System.out.println(titulo);
                System.out.print("¿Quiere añadir otro disco a la computadora? [Y/n] ");
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

            if ("n".equals(opcYN)) {
                break;
            } else {
                if (cdisc == 4) {
                    Funciones.cls();
                    System.out.println("""
                                       No puede agregar más discos. 
                                       Contactese con el docente si necesita agregar otro disco.""");
                    Funciones.pause();
                    break;
                }
            }
        } while (true);

        //Tarjeta de Video
        do {
            System.out.print("¿Quiere ingresar tarjeta de video? [Y/n] ");
            String opcYN = s.next();
            switch (opcYN) {
                case "Y" -> {
                    titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                 Hardware - Tarjeta de Video
                                        --------------------------------------------
                        """;

                    do {
                        Hardware tv = new Hardware();

                        //Tarjeta de Video - Descripción
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el modelo de la tarjeta de video: ");
                            String modelo = br.readLine();
                            if (Funciones.isValidCode(modelo)) {
                                do {
                                    Funciones.cls();
                                    System.out.println(titulo);
                                    System.out.print("Ingrese la velocidad de la tarjeta de video (GRAM): ");
                                    String velocidad = br.readLine();
                                    if (Funciones.isValidNumeric(velocidad)) {
                                        tv.setDescripcion(modelo.toUpperCase()
                                                + "/-/" + velocidad.toUpperCase()
                                                + "/-/" + getCodigo());
                                        break;
                                    } else {
                                        Funciones.cls();
                                        System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                                        System.out.println("Ingrese la velocidad nuevamente.");
                                        Funciones.pause();
                                    }
                                } while (true);
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el modelo nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Tarjeta de Video - Nro. Serie
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el número de serie de la tarjeta de video (si no tiene ingrese NA): ");
                            String nroSerie = br.readLine();
                            if (Funciones.isValidCode(nroSerie)) {
                                tv.setNroSerie(nroSerie.toUpperCase());
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el nro. serie nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Tarjeta de Video - Estado
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.println("""
                                                           El estado de la placa madre es:
                                                             1. Funcional
                                                             2. Necesita reparación
                                                             3. Dañado
                                               """);
                            System.out.print("           Opción: ");
                            String opc = s.next();
                            switch (opc) {
                                case "1" -> {
                                    tv.setEstado(listadoEstados.get(0));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "2" -> {
                                    tv.setEstado(listadoEstados.get(1));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "3" -> {
                                    tv.setEstado(listadoEstados.get(2));
                                    setEstado(listadoEstados.get(0));
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

                        tv.setTipo(listadoTH.get(4));
                        hardTemp.add(tv);

                        break;
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
            break;
        } while (true);

        //Mouse
        do {
            System.out.print("¿El computador tiene mouse? [Y/n] ");
            String opcYN = s.next();
            switch (opcYN) {
                case "Y" -> {
                    titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                      Hardware - Mouse
                                        --------------------------------------------
                        """;

                    do {
                        Hardware ms = new Hardware();

                        //Mouse - Descripción
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el código del mouse: ");
                            String codigo = br.readLine();
                            if (Funciones.isValidCode(codigo)) {
                                do {
                                    Funciones.cls();
                                    System.out.println(titulo);
                                    System.out.print("Ingrese el modelo del mouse: ");
                                    String modelo = br.readLine();
                                    if (Funciones.isValidCode(modelo)) {
                                        ms.setDescripcion("MOUSE/-/" + codigo.toUpperCase()
                                                + "/-/" + modelo.toUpperCase()
                                                + "/-/" + getCodigo());
                                        break;
                                    } else {
                                        Funciones.cls();
                                        System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                                        System.out.println("Ingrese el modelo nuevamente.");
                                        Funciones.pause();
                                    }
                                } while (true);
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el codigo nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Mouse - Nro. Serie
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el número de serie del mouse (si no tiene ingrese NA): ");
                            String nroSerie = br.readLine();
                            if (Funciones.isValidCode(nroSerie)) {
                                ms.setNroSerie(nroSerie.toUpperCase());
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el nro. serie nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Mouse - Estado
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.println("""
                                                           El estado del mouse es:
                                                             1. Funcional
                                                             2. Necesita reparación
                                                             3. Dañado
                                               """);
                            System.out.print("           Opción: ");
                            String opc = s.next();
                            switch (opc) {
                                case "1" -> {
                                    ms.setEstado(listadoEstados.get(0));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "2" -> {
                                    ms.setEstado(listadoEstados.get(1));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "3" -> {
                                    ms.setEstado(listadoEstados.get(2));
                                    setEstado(listadoEstados.get(0));
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

                        ms.setTipo(listadoTH.get(1));
                        hardTemp.add(ms);

                        break;
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
            break;
        } while (true);

        //Teclado
        do {
            System.out.print("¿El computador tiene teclado? [Y/n] ");
            String opcYN = s.next();
            switch (opcYN) {
                case "Y" -> {
                    titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                     Hardware - Teclado
                                        --------------------------------------------
                        """;

                    do {
                        Hardware ms = new Hardware();

                        //Teclado - Descripción
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el código del teclado: ");
                            String codigo = br.readLine();
                            if (Funciones.isValidCode(codigo)) {
                                do {
                                    Funciones.cls();
                                    System.out.println(titulo);
                                    System.out.print("Ingrese el modelo del teclado: ");
                                    String modelo = br.readLine();
                                    if (Funciones.isValidCode(modelo)) {
                                        ms.setDescripcion("TECLADO/-/" + codigo.toUpperCase()
                                                + "/-/" + modelo.toUpperCase()
                                                + "/-/" + getCodigo());
                                        break;
                                    } else {
                                        Funciones.cls();
                                        System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                                        System.out.println("Ingrese el modelo nuevamente.");
                                        Funciones.pause();
                                    }
                                } while (true);
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el codigo nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Teclado - Nro. Serie
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el número de serie del teclado (si no tiene ingrese NA): ");
                            String nroSerie = br.readLine();
                            if (Funciones.isValidCode(nroSerie)) {
                                ms.setNroSerie(nroSerie.toUpperCase());
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el nro. serie nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Teclado - Estado
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.println("""
                                                           El estado del teclado es:
                                                             1. Funcional
                                                             2. Necesita reparación
                                                             3. Dañado
                                               """);
                            System.out.print("           Opción: ");
                            String opc = s.next();
                            switch (opc) {
                                case "1" -> {
                                    ms.setEstado(listadoEstados.get(0));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "2" -> {
                                    ms.setEstado(listadoEstados.get(1));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "3" -> {
                                    ms.setEstado(listadoEstados.get(2));
                                    setEstado(listadoEstados.get(0));
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

                        ms.setTipo(listadoTH.get(1));
                        hardTemp.add(ms);

                        break;
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
            break;
        } while (true);

        //Monitor
        do {
            System.out.print("¿El computador tiene monitor? [Y/n] ");
            String opcYN = s.next();
            switch (opcYN) {
                case "Y" -> {
                    titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                     Hardware - Monitor
                                        --------------------------------------------
                        """;

                    do {
                        Hardware mon = new Hardware();

                        //Monitor - Descripción
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el código del monitor: ");
                            String codigo = br.readLine();
                            if (Funciones.isValidCode(codigo)) {
                                do {
                                    Funciones.cls();
                                    System.out.println(titulo);
                                    System.out.print("Ingrese el modelo del monitor: ");
                                    String modelo = br.readLine();
                                    if (Funciones.isValidCode(modelo)) {
                                        mon.setDescripcion("MONITOR/-/" + codigo.toUpperCase()
                                                + "/-/" + modelo.toUpperCase()
                                                + "/-/" + getCodigo());
                                        break;
                                    } else {
                                        Funciones.cls();
                                        System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                                        System.out.println("Ingrese el modelo nuevamente.");
                                        Funciones.pause();
                                    }
                                } while (true);
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el codigo nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Monitor - Nro. Serie
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el número de serie del monitor (si no tiene ingrese NA): ");
                            String nroSerie = br.readLine();
                            if (Funciones.isValidCode(nroSerie)) {
                                mon.setNroSerie(nroSerie.toUpperCase());
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el nro. serie nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Monitor - Estado
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.println("""
                                                           El estado del monitor es:
                                                             1. Funcional
                                                             2. Necesita reparación
                                                             3. Dañado
                                               """);
                            System.out.print("           Opción: ");
                            String opc = s.next();
                            switch (opc) {
                                case "1" -> {
                                    mon.setEstado(listadoEstados.get(0));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "2" -> {
                                    mon.setEstado(listadoEstados.get(1));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "3" -> {
                                    mon.setEstado(listadoEstados.get(2));
                                    setEstado(listadoEstados.get(0));
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

                        mon.setTipo(listadoTH.get(0));
                        hardTemp.add(mon);

                        break;
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
            break;
        } while (true);

        //Parlantes
        do {
            System.out.print("¿El computador tiene parlantes? [Y/n] ");
            String opcYN = s.next();
            switch (opcYN) {
                case "Y" -> {
                    titulo = """
                                        --------------------------------------------
                                                   Registro de computadora
                                                     Hardware - Parlantes
                                        --------------------------------------------
                        """;

                    do {
                        Hardware mon = new Hardware();

                        //Parlantes - Descripción
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el código de los parlantes: ");
                            String codigo = br.readLine();
                            if (Funciones.isValidCode(codigo)) {
                                do {
                                    Funciones.cls();
                                    System.out.println(titulo);
                                    System.out.print("Ingrese el modelo de los parlantes: ");
                                    String modelo = br.readLine();
                                    if (Funciones.isValidCode(modelo)) {
                                        mon.setDescripcion("PARLANTES/-/" + codigo.toUpperCase()
                                                + "/-/" + modelo.toUpperCase()
                                                + "/-/" + getCodigo());
                                        break;
                                    } else {
                                        Funciones.cls();
                                        System.out.println("¡ERROR! No puede ingresar caracteres especiales ni letras.");
                                        System.out.println("Ingrese el modelo nuevamente.");
                                        Funciones.pause();
                                    }
                                } while (true);
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el codigo nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Parlantes - Nro. Serie
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.print("Ingrese el número de serie de los parlantes (si no tiene ingrese NA): ");
                            String nroSerie = br.readLine();
                            if (Funciones.isValidCode(nroSerie)) {
                                mon.setNroSerie(nroSerie.toUpperCase());
                                break;
                            } else {
                                Funciones.cls();
                                System.out.println("¡ERROR! No puede ingresar caracteres especiales.");
                                System.out.println("Ingrese el nro. serie nuevamente.");
                                Funciones.pause();
                            }
                        } while (true);

                        //Parlantes - Estado
                        do {
                            Funciones.cls();
                            System.out.println(titulo);
                            System.out.println("""
                                                           El estado de los parlantes es:
                                                             1. Funcional
                                                             2. Necesita reparación
                                                             3. Dañado
                                               """);
                            System.out.print("           Opción: ");
                            String opc = s.next();
                            switch (opc) {
                                case "1" -> {
                                    mon.setEstado(listadoEstados.get(0));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "2" -> {
                                    mon.setEstado(listadoEstados.get(1));
                                    setEstado(listadoEstados.get(0));
                                    break;
                                }
                                case "3" -> {
                                    mon.setEstado(listadoEstados.get(2));
                                    setEstado(listadoEstados.get(0));
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

                        mon.setTipo(listadoTH.get(0));
                        hardTemp.add(mon);

                        break;
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
            break;
        } while (true);

        setHardware(hardTemp);
    }


    @Override
    public String toString() {
        String mantRealizado;
        if (isMantRealizado()) {
            mantRealizado = "Si";
        } else {
            mantRealizado = "No";
        }
        String info = "    Código: " + getCodigo() + "\n"
                + "    Marca: " + getMarca() + "\n"
                + "    Mantenimiento Realizado: " + mantRealizado + "\n"
                + "    Estado: " + getEstado().getDescripcion() + "\n"
                + "    Software: \n";
        for (Software p : getSoftware()) {
            info += "        " + p.getTipo().getDescripcion() + " - "
                    + p.getNombre() + " - "
                    + p.getVersion() + " - "
                    + p.getEstado().getDescripcion() + "\n";
        }
        info += "    Hardware: \n";
        for (Hardware p : getHardware()) {
            info += "        " + p.getTipo().getDescripcion() + " - "
                    + p.getNroSerie() + " - "
                    + p.getEstado().getDescripcion() + "\n";
        }
        return info;
    }
}
