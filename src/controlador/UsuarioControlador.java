/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;

/**
 *
 * @author Alejandro
 */
public class UsuarioControlador {
     //ATRIBUTOS
    //Métodos
    private Usuario persona;
    //Conexión
    ConexionBDD conexion=new ConexionBDD();
    Connection connection=(Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public void crearUsuario(Usuario u){
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "INSERT INTO usuarios(usu_nombre, "
                    + "usu_apellido, "
                    + "usu_cedula, "
                    + "usu_clave, "
                    + "usu_telefono, "
                    + "usu_correoElectronico, "
                    + "usu_direccion, "
                    + "usu_rol) " +
            "VALUES ('" + u.getNombre() + "', '" 
                    + u.getApellido() + "', '" 
                    + u.getCedula() + "', '" 
                    + u.getClave() + "', '" 
                    + u.getTelefono() + "', '" 
                    + u.getCorreoElectronico() + "', '" 
                    + u.getDireccion() + "', '" 
                    + u.getRol() + "');";
            
            ejecutar =(PreparedStatement) connection.prepareCall(consulta);
            
            //Ejecutar la consulta
            int res = ejecutar.executeUpdate();
            
            if(res > 0){
                ejecutar.close();
            }else{
                ejecutar.close();
            }
            
        } catch(Exception e){
            //Captura el error en memoria y continúa la ejecución
            //ERROR - DEBUG
            System.out.println("ERROR: " + e);
        }
    }
    
    public int buscarIdPersona(String cedula){
        try {
            String consulta = "SELECT usu_id FROM usuarios "
                    + "WHERE usu_cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                int idPersona = resultado.getInt("usu_id");
                return idPersona;
            }else{
                System.out.println("¡ERROR! Ingrese una cédula válida");
            }
            ejecutar.close();
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return 0;
    }
    
    public boolean existeCuenta(String cedula){
        try {
            String consulta = "SELECT * FROM usuarios "
                    + "WHERE usu_cedula = '" + cedula + "';";
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
}
