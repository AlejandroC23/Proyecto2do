/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Encargado;

/**
 *
 * @author Alejandro
 */
public class EncargadoControlador {
    //ATRIBUTOS
    //Métodos
    private Encargado encargado;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public void crearEncargado(Encargado enc){
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
            ejecutar.setString(1, enc.getNombre());
            ejecutar.setString(2, enc.getApellido());
            ejecutar.setString(3, enc.getClave());
            ejecutar.setString(4, enc.getTelefono());
            ejecutar.setString(5, enc.getCorreoElectronico());
            ejecutar.setString(6, enc.getDireccion());
            ejecutar.setString(7, enc.getCedula());
            
            int res = ejecutar.executeUpdate();
            
            if(res > 0){
                consulta = "UPDATE encargados SET "
                    + "enc_titulo = ?, "
                    + "enc_cargo = ? " 
                    + "WHERE usu_id = ?;";
            
                ejecutar =(PreparedStatement) connection.prepareCall(consulta);
                ejecutar.setString(1, enc.getTitulo());
                ejecutar.setString(2, enc.getCargo());
                ejecutar.setInt(3, enc.getIdUsuario());
                
                res = ejecutar.executeUpdate();
                
                if(res > 0){
                    ejecutar.close();
                }else{
                    ejecutar.close();
                }
            }else{
                ejecutar.close();
            }
            
        } catch(Exception e){
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
    }
    
    public Encargado buscarEncargado(String cedula, String clave) {
        Encargado enc = new Encargado();
        try {
            String consulta = "SELECT usu_nombre, usu_apellido, usu_telefono, usu_correoElectronico, usu_direccion, usu_rol, usu_clave, enc_titulo, enc_cargo "
                    + "FROM encargados e, usuarios u "
                    + "WHERE e.usu_id = u.usu_id "
                    + "AND usu_cedula = '" + cedula + "' "
                    + "AND usu_clave = '" + clave + "' "
                    + "AND usu_rol = 2;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if (resultado.next()) {
                enc.setNombre(resultado.getString("usu_nombre"));
                enc.setApellido(resultado.getString("usu_apellido"));
                enc.setCorreoElectronico(resultado.getString("usu_correoElectronico"));
                enc.setDireccion(resultado.getString("usu_direccion"));
                enc.setTelefono(resultado.getString("usu_telefono"));
                enc.setCedula(cedula);
                enc.setClave(clave);
                enc.setRol(resultado.getInt("usu_rol"));
                enc.setTitulo(resultado.getString("enc_titulo"));
                enc.setCargo(resultado.getString("enc_cargo"));
                ejecutar.close();
                return enc;
            } else {
                enc.setCedula("0");
            }
            ejecutar.close();
            return enc;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return enc;
    }
    
    public Encargado buscarEncargado(String cedula) {
        Encargado enc = new Encargado();
        try {
            String consulta = "SELECT usu_nombre, usu_apellido, usu_telefono, usu_correoElectronico, usu_direccion, usu_rol, usu_clave, enc_titulo, enc_cargo "
                    + "FROM encargados e, usuarios u "
                    + "WHERE e.usu_id = u.usu_id "
                    + "AND usu_cedula = '" + cedula + "' "
                    + "AND usu_rol = 2;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if (resultado.next()) {
                enc.setNombre(resultado.getString("usu_nombre"));
                enc.setApellido(resultado.getString("usu_apellido"));
                enc.setCorreoElectronico(resultado.getString("usu_correoElectronico"));
                enc.setDireccion(resultado.getString("usu_direccion"));
                enc.setTelefono(resultado.getString("usu_telefono"));
                enc.setCedula(cedula);
                enc.setClave(resultado.getString("usu_clave"));
                enc.setRol(resultado.getInt("usu_rol"));
                enc.setTitulo(resultado.getString("enc_titulo"));
                enc.setCargo(resultado.getString("enc_cargo"));
                ejecutar.close();
                return enc;
            } else {
                enc.setCedula("0");
            }
            ejecutar.close();
            return enc;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return enc;
    }
    
    public void actualizarEncargado(Encargado enc, String titulo){
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
            ejecutar.setString(1, enc.getNombre());
            ejecutar.setString(2, enc.getApellido());
            ejecutar.setString(3, enc.getClave());
            ejecutar.setString(4, enc.getTelefono());
            ejecutar.setString(5, enc.getCorreoElectronico());
            ejecutar.setString(6, enc.getDireccion());
            ejecutar.setString(7, enc.getCedula());
            
            //Ejecutar la consulta
            int res = ejecutar.executeUpdate();
            
            if(res > 0){
                consulta = "UPDATE encargados SET "
                    + "enc_titulo = ? ,"
                    + "enc_cargo = ? "
                    + "WHERE enc_titulo = ? ;";
            
                ejecutar =(PreparedStatement) connection.prepareCall(consulta);
                ejecutar.setString(1, enc.getTitulo());
                ejecutar.setString(2, enc.getCargo());
                ejecutar.setString(3, titulo);

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
