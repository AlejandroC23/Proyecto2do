/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.CarreraControlador;
import controlador.EstudianteControlador;
import controlador.Funciones;
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
public class Estudiante extends Usuario {
    
    private int idEstudiante;
    private String matricula;
    private int finalizoMantenimiento;
    private Carrera carrera;
    private int nivelCarrera;
    private int idUsuario;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String matricula, int finalizoMantenimiento, Carrera carrera, int nivelCarrera, int idUsuario, String nombre, String apellido, String correoElectronico, String direccion, String telefono, String cedula, String clave, int rol) {
        super(idUsuario, nombre, apellido, correoElectronico, direccion, telefono, cedula, clave, rol);
        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.finalizoMantenimiento = finalizoMantenimiento;
        this.carrera = carrera;
        this.nivelCarrera = nivelCarrera;
        this.idUsuario = idUsuario;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getFinalizoMantenimiento() {
        return finalizoMantenimiento;
    }

    public void setFinalizoMantenimiento(int finalizoMantenimiento) {
        this.finalizoMantenimiento = finalizoMantenimiento;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public int getNivelCarrera() {
        return nivelCarrera;
    }

    public void setNivelCarrera(int nivelCarrera) {
        this.nivelCarrera = nivelCarrera;
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
    public String toString(){
        return "                Cedula: " + getCedula() + "\n"
                + "                Nombres: " + getNombre() + "\n"
                + "                Apellidos: " + getApellido() + "\n"
                + "                Teléfono: " + getTelefono() + "\n"
                + "                Email: " + getCorreoElectronico() + "\n"
                + "                Dirección: " + getDireccion() + "\n"
                + "                Matrícula: " + getMatricula() + "\n"
                + "                Carrera: " + getCarrera().getNombre() + "\n" 
                + "                Nivel de Carrera: " + getNivelCarrera() + "\n";
    }
    
    public void realizarMantenimiento(){
        
    }
    
    public Computadora buscarComputadora(){
        Computadora n = new Computadora();
        return n;
    }
    
    public void registrarComputadora(){
        
    }
    
    public void registrarMantenimiento(){
        
    }
    
    public void editarMantenimiento(){
        
    }
    
    public void editarDatosPersonales() throws IOException {
        
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);

        EstudianteControlador estc = new EstudianteControlador();
        CarreraControlador carc = new CarreraControlador();

        Estudiante estTemp = new Estudiante();

        String titulo = """
                    --------------------------------------------------
                                    Editar información
                    --------------------------------------------------                
        """;
        String opc;

        estTemp.setCedula(getCedula());

        do {
            //Campo -Nombres
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
                                estTemp.setNombre(nombres.toUpperCase());
                                break;
                            }
                        } while (true);
                        opc = "";
                        break;
                    }
                    case "n" -> {
                        estTemp.setNombre(getNombre());
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
                                estTemp.setApellido(apellidos.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setApellido(getApellido());
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
                                estTemp.setCorreoElectronico(email);
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setCorreoElectronico(
                                getCorreoElectronico()
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
                                estTemp.setDireccion(direccion.toUpperCase());
                                break;
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setDireccion(getDireccion());
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
                                    estTemp.setTelefono(telefono);
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
                        estTemp.setTelefono(getTelefono());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Matrícula
            do {
                Funciones.cls();
                System.out.println("Su matrícula es: " + getMatricula());
                System.out.print("¿Desea cambiar su matrícula? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
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
                                    estTemp.setMatricula(matricula.toUpperCase());
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setMatricula(getMatricula());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Carrera
            do {
                Funciones.cls();
                System.out.println("Su carrera es: " + getCarrera().getNombre());
                System.out.print("¿Desea cambiar su carrera? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
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
                                            estTemp.setCarrera(listadoCarreras.get(i));
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
                        break;
                    }
                    case "n" -> {
                        estTemp.setCarrera(getCarrera());
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                        continue;
                    }
                }
            } while ("n".equals(opc) && "Y".equals(opc));

            //Campo - Nivel Carrera
            do {
                Funciones.cls();
                System.out.println("Su nivel de carrera es: " + getNivelCarrera());
                System.out.print("¿Desea cambiar su nivel? [Y/n] ");
                opc = s.next();
                switch (opc) {
                    case "Y" -> {
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
                                    estTemp.setNivelCarrera(nivelCarrera);
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    }
                    case "n" -> {
                        estTemp.setNivelCarrera(getNivelCarrera());
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
                                    estTemp.setClave(clave);
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
                        estTemp.setClave(getClave());
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
            estc.actualizarEstudiante(estTemp, getMatricula());

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
    
    public void verEstadosComputadoras(){
        
    }
    
    public void finalizarMantenimiento(){
        
    }
}
