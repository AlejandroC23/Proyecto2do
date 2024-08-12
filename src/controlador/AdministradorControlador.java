/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Administrador;

/**
 *
 * @author Alejandro
 */
public class AdministradorControlador {
    public Administrador administrador;
    //Conexión
    ConexionBDD conexion=new ConexionBDD();
    Connection connection=(Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    public Administrador buscarAdmin(String cedula){
        Administrador admin = new Administrador();
        try {
            String consulta = "SELECT * FROM usuarios "
                    + "WHERE usu_cedula = '" + cedula + "' "
                    + "AND usu_rol = 3;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                admin.setIdUsuario(resultado.getInt("usu_id"));
                admin.setNombre(resultado.getString("usu_nombre"));
                admin.setApellido(resultado.getString("usu_apellido"));
                admin.setCedula(cedula);
                admin.setApellido(resultado.getString("usu_telefono"));
                admin.setCorreoElectronico(resultado.getString("usu_correoElectronico"));
                admin.setApellido(resultado.getString("usu_direccion"));
                admin.setRol(resultado.getInt("usu_rol"));
                ejecutar.close();
                return admin;
            }else{
                admin.setCedula("0");
            }
            ejecutar.close();
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return admin;
    }
    
    public Administrador buscarAdmin(String cedula, String clave){
        Administrador admin = new Administrador();
        try {
            String consulta = "SELECT * FROM usuarios "
                    + "WHERE usu_cedula = '" + cedula + "' "
                    + "AND usu_clave = '" + clave + "' "
                    + "AND usu_rol = 3;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                admin.setIdUsuario(resultado.getInt("usu_id"));
                admin.setNombre(resultado.getString("usu_nombre"));
                admin.setApellido(resultado.getString("usu_apellido"));
                admin.setCedula(cedula);
                admin.setTelefono(resultado.getString("usu_telefono"));
                admin.setCorreoElectronico(resultado.getString("usu_correoElectronico"));
                admin.setDireccion(resultado.getString("usu_direccion"));
                admin.setRol(resultado.getInt("usu_rol"));
                ejecutar.close();
                return admin;
            }else{
                admin.setCedula("0");
            }
            ejecutar.close();
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return admin;
    }
    
    public void editarEncargado(Administrador admin){
        try {
            String consulta = "UPDATE usuarios SET "
                    + "usu_nombre = ?, "
                    + "usu_apellido = ?, "
                    + "usu_clave = ?, "
                    + "usu_telefono = ?, "
                    + "usu_correoElectronico = ?, " 
                    + "usu_direccion = ? " 
                    + "WHERE usu_cedula = ?;";
            
            ejecutar =(PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setString(1, admin.getNombre());
            ejecutar.setString(2, admin.getApellido());
            ejecutar.setString(3, admin.getClave());
            ejecutar.setString(4, admin.getTelefono());
            ejecutar.setString(5, admin.getCorreoElectronico());
            ejecutar.setString(6, admin.getDireccion());
            ejecutar.setString(7, admin.getCedula());
            
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
    
    public void cambiarClave(String cedula, String clave){
        try {
            String consulta = "UPDATE usuarios SET "
                    + "usu_clave = ? "
                    + "WHERE usu_cedula = ?;";
            
            ejecutar =(PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setString(1, clave);
            ejecutar.setString(2, cedula);
            
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
    
    public void crearAdmin(String cedula, String clave){
        try {
            String consulta = "INSERT INTO usuarios(usu_nombre, "
                    + "usu_apellido, "
                    + "usu_cedula, "
                    + "usu_clave, "
                    + "usu_telefono, "
                    + "usu_correoElectronico, "
                    + "usu_direccion, "
                    + "usu_rol)"
                    + "VALUES ('.', "
                    + "'.', "
                    + "?, "
                    + "?, "
                    + "'0', "
                    + "'.', "
                    + "'.', "
                    + "3);";
            
            ejecutar =(PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setString(1, cedula);
            ejecutar.setString(2, clave);
            
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
}
