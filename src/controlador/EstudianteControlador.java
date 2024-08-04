/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Estudiante;
import modelo.Carrera;

/**
 *
 * @author Alejandro
 */
public class EstudianteControlador {

    //ATRIBUTOS
    //Métodos
    private Estudiante estudiante;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public void crearEstudiante(Estudiante est){
        try {
            String consulta = "INSERT INTO estudiantes(est_matricula, "
                    + "est_finalizoMantenimiento, "
                    + "car_id, "
                    + "est_nivelCarrera, "
                    + "usu_id) " +
            "VALUES ('" + est.getMatricula() + "', " 
                    + est.getFinalizoMantenimiento() + ", " 
                    + est.getCarrera().getIdCarrera() + ", " 
                    + est.getNivelCarrera() + ", " 
                    + est.getIdPersona() + ");";
            
            ejecutar =(PreparedStatement) connection.prepareCall(consulta);
            
            int res = ejecutar.executeUpdate();
            
            if(res > 0){
                ejecutar.close();
            }else{
                ejecutar.close();
            }
            
        } catch(Exception e){
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
    }
    
    public Estudiante buscarEstudiante(String cedula, String clave) {
        Estudiante est = new Estudiante();
        CarreraControlador carc = new CarreraControlador();
        try {
            String consulta = "SELECT usu_nombre, usu_apellido, usu_telefono, usu_correoElectronico, usu_direccion, usu_rol, usu_clave, est_matricula, est_finalizoMantenimiento, est_nivelCarrera "
                    + "FROM estudiantes e, usuarios u "
                    + "WHERE e.usu_id = u.usu_id "
                    + "AND usu_cedula = '" + cedula + "' "
                    + "AND usu_rol = 1;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if (resultado.next()) {
                est.setNombre(resultado.getString("usu_nombre"));
                est.setApellido(resultado.getString("usu_apellido"));
                est.setCorreoElectronico(resultado.getString("usu_correoElectronico"));
                est.setDireccion(resultado.getString("usu_direccion"));
                est.setTelefono(resultado.getString("usu_telefono"));
                est.setCedula(cedula);
                est.setClave(clave);
                est.setRol(resultado.getInt("usu_rol"));
                est.setMatricula(resultado.getString("est_matricula"));
                est.setFinalizoMantenimiento(resultado.getInt("est_finalizoMantenimiento"));
                est.setNivelCarrera(resultado.getInt("est_nivelCarrera"));
                est.setCarrera(carc.buscarCarrera(est.getMatricula()));
                ejecutar.close();
                return est;
            } else {
                est.setCedula("0");
            }
            ejecutar.close();
            return est;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return est;
    }
    
    public Estudiante buscarEstudiante(String cedula) {
        Estudiante est = new Estudiante();
        CarreraControlador carc = new CarreraControlador();
        try {
            String consulta = "SELECT usu_nombre, usu_apellido, usu_telefono, usu_correoElectronico, usu_direccion, usu_rol, usu_clave, est_matricula, est_finalizoMantenimiento, est_nivelCarrera "
                    + "FROM estudiantes e, usuarios u "
                    + "WHERE e.usu_id = u.usu_id "
                    + "AND usu_cedula = '" + cedula + "' "
                    + "AND usu_rol = 1;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if (resultado.next()) {
                est.setNombre(resultado.getString("usu_nombre"));
                est.setApellido(resultado.getString("usu_apellido"));
                est.setCorreoElectronico(resultado.getString("usu_correoElectronico"));
                est.setDireccion(resultado.getString("usu_direccion"));
                est.setTelefono(resultado.getString("usu_telefono"));
                est.setCedula(cedula);
                est.setClave(resultado.getString("usu_clave"));
                est.setRol(resultado.getInt("usu_rol"));
                est.setMatricula(resultado.getString("est_matricula"));
                est.setFinalizoMantenimiento(resultado.getInt("est_finalizoMantenimiento"));
                est.setNivelCarrera(resultado.getInt("est_nivelCarrera"));
                est.setCarrera(carc.buscarCarrera(est.getMatricula()));
                ejecutar.close();
                return est;
            } else {
                est.setCedula("0");
            }
            ejecutar.close();
            return est;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return est;
    }
    
    public boolean existeMatricula(String matricula){
        try {
            String consulta = "SELECT * FROM estudiantes "
                    + "WHERE est_matricula = '" + matricula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                ejecutar.close();
                return true;
            }else{
                ejecutar.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return false;
    }
    
    public void actualizarEstudiante(Estudiante est, String matricula){
        try {
            String consulta = "UPDATE usuarios SET "
                    + "usu_nombre = ? ,"
                    + "usu_apellido = ? ,"
                    + "usu_clave = ? ,"
                    + "usu_telefono = ? ,"
                    + "usu_correoElectronico = ? ,"
                    + "usu_direccion = ? "
                    + "WHERE usu_cedula = ? ;";
            
            ejecutar =(PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setString(1, est.getNombre());
            ejecutar.setString(2, est.getApellido());
            ejecutar.setString(3, est.getClave());
            ejecutar.setString(4, est.getTelefono());
            ejecutar.setString(5, est.getCorreoElectronico());
            ejecutar.setString(6, est.getDireccion());
            ejecutar.setString(7, est.getApellido());
            
            //Ejecutar la consulta
            int res = ejecutar.executeUpdate();
            
            if(res > 0){
                consulta = "UPDATE estudiantes SET "
                    + "est_matricula = ? ,"
                    + "car_id = ? ,"
                    + "est_nivelCarrera = ? "
                    + "WHERE est_matricula = ? ;";
            
                ejecutar =(PreparedStatement) connection.prepareCall(consulta);
                ejecutar.setString(1, est.getMatricula());
                ejecutar.setInt(2, est.getCarrera().getIdCarrera());
                ejecutar.setInt(3, est.getNivelCarrera());
                ejecutar.setString(4, matricula);

                //Ejecutar la consulta
                res = ejecutar.executeUpdate();
                
                if(res > 0){
                    ejecutar.close();
                }else{
                    ejecutar.close();
                }
            }else{
                ejecutar.close();
            }
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
    }
}
