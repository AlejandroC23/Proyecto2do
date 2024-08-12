/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.EncargadoControlador;
import controlador.EstudianteControlador;
import controlador.Funciones;
import controlador.LaboratorioControlador;
import controlador.MantenimientoControlador;
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
public class Encargado extends Usuario {

    private int idEncargado;
    private String titulo;
    private String cargo;
    private int idUsuario;

    public Encargado() {
    }

    public Encargado(int idEncargado, String titulo, String cargo, int idUsuario, String nombre, String apellido, String correoElectronico, String direccion, String telefono, String cedula, String clave, int rol) {
        super(idUsuario, nombre, apellido, correoElectronico, direccion, telefono, cedula, clave, rol);
        this.idEncargado = idEncargado;
        this.titulo = titulo;
        this.cargo = cargo;
        this.idUsuario = idUsuario;
    }
    
    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "                Cedula: " + getCedula() + "\n"
                + "                Nombres: " + getNombre() + "\n"
                + "                Apellidos: " + getApellido() + "\n"
                + "                Teléfono: " + getTelefono() + "\n"
                + "                Email: " + getCorreoElectronico() + "\n"
                + "                Dirección: " + getDireccion() + "\n"
                + "                Título: " + getTitulo() + "\n"
                + "                Cargo: " + getCargo() + "\n";
    }

    public void realizarMantenimiento(){
        LaboratorioControlador labC = new LaboratorioControlador();
        MantenimientoControlador mantC = new MantenimientoControlador();
        
        int idLaboratorio = labC.buscarIdLaboratorio(getCedula());
        mantC.crearMantenimiento(idLaboratorio);
    }
    
    public boolean finalizarMantenimiento(){
        Scanner s = new Scanner(System.in);
        
        LaboratorioControlador labC = new LaboratorioControlador();
        Laboratorio lab = labC.buscarLaboratorio(getCedula());
        lab.setIdLaboratorio(labC.buscarIdLaboratorio(getCedula()));
        
        MantenimientoControlador mantC = new MantenimientoControlador();
        Mantenimiento mant = mantC.buscarMantenimiento(lab);
        
        boolean finalizaronMant = true;
        String opcYN;
        
        if(mant.getEstudiantes().isEmpty() == false){
            for(Estudiante p : mant.getEstudiantes()){
                if(p.isFinalizoMantenimiento() == false){
                    finalizaronMant = false;
                }
            }

            if(finalizaronMant){
                do{
                    Funciones.cls();
                    System.out.println("¿Desea finalizar el mantenimiento? [Y/n] ");
                    opcYN = s.next();
                    switch(opcYN){
                        case "Y" -> {
                            do{
                                Funciones.cls();
                                System.out.println("¿Está seguro? [Y/n] ");
                                opcYN = s.next();
                                switch(opcYN){
                                    case "Y" -> {
                                        EstudianteControlador estC = new EstudianteControlador();
                                        for(Estudiante p : mant.getEstudiantes()){
                                            estC.actualizarEstudianteMantenimiento(p.getMatricula(), false);
                                        }
                                        mantC.editarMantenimiento(mant.getIdMantenimiento());
                                        return finalizaronMant;
                                    }
                                    case "n" -> {
                                        Funciones.cls();
                                        System.out.println("Regresando...");
                                        Funciones.pause();
                                        return false;
                                    }
                                    default -> {
                                        Funciones.cls();
                                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                                        Funciones.pause();
                                    }
                                }
                            }while(!"Y".equals(opcYN) && !"n".equals(opcYN));
                        }
                        case "n" -> {
                            Funciones.cls();
                            System.out.println("Regresando...");
                            Funciones.pause();
                            return false;
                        }
                        default -> {
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                            Funciones.pause();
                        }
                    }
                }while(!"Y".equals(opcYN) && !"n".equals(opcYN));
            }else{
                Funciones.cls();
                System.out.println("""
                                   ¡No puede finalizar el mantenimiento al laboratorio! 
                                   Algunos estudiantes aún no han finalizado el mantenimiento, asegurese que todos
                                   los estudiantes hayan finalizado el mantenimiento.
                                   """);
                Funciones.pause();
                return finalizaronMant;
            }
        }else{
            Funciones.cls();
            System.out.println("¡ERROR! Aún no ha asignado estudiantes al mantemiento.");
            Funciones.pause();
        }
        return finalizaronMant;
    }
    
    public void editarDatosPersonales() throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        EncargadoControlador encc = new EncargadoControlador();

        Encargado encTemp = new Encargado();

        String titulo = """
                    --------------------------------------------------
                                    Editar información
                    --------------------------------------------------                
        """;
        String opc;

        encTemp.setCedula(getCedula());

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
                                encTemp.setNombre(nombres.toUpperCase());
                                break;
                            }
                        } while (true);
                        opc = "";
                        break;
                    }
                    case "n" -> {
                        encTemp.setNombre(getNombre());
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
                                encTemp.setApellido(apellidos.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        encTemp.setApellido(getApellido());
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
                                encTemp.setCorreoElectronico(email);
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        encTemp.setCorreoElectronico(getCorreoElectronico()
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
                                encTemp.setDireccion(direccion.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        encTemp.setDireccion(getDireccion());
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
                                    encTemp.setTelefono(telefono);
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
                        encTemp.setTelefono(getTelefono());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Título
            do {
                Funciones.cls();
                System.out.println("Su título es: " + getTitulo());
                System.out.print("¿Desea cambiar su título? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
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
                                encTemp.setTitulo(titulos.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        encTemp.setTitulo(getTitulo());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Cargo
            do {
                Funciones.cls();
                System.out.println("Su cargo es: " + getCargo());
                System.out.print("¿Desea cambiar su título? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
                        do {
                            Funciones.cls();
                            System.out.print(titulo);
                            System.out.print("  Ingrese su título: ");
                            String cargo = br.readLine();
                            if (Funciones.isValidText(cargo) == false) {
                                Funciones.cls();
                                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                                        + "especiales, ingrese su cargo nuevamente.");
                                Funciones.pause();
                            } else {
                                encTemp.setCargo(cargo.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        encTemp.setCargo(getCargo());
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
                                    encTemp.setClave(clave);
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
                        encTemp.setClave(getClave());
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
            encc.actualizarEncargado(encTemp, getTitulo());

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
