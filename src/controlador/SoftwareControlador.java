/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Software;

/**
 *
 * @author Alejandro
 */
public class SoftwareControlador {
    //ATRIBUTOS
    //Métodos

    private Software software;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public void crearSoftware(Software soft){
        
        try {
            String consulta = "INSERT INTO software(soft_nombre, "
                    + "soft_descripcion, "
                    + "soft_version, "
                    + "esta_id, "
                    + "tip_soft_id) " +
            "VALUES ('" + soft.getNombre() + "', '" 
                    + soft.getDescripcion() + "', '" 
                    + soft.getVersion() + "', " 
                    + soft.getEstado().getIdEstado() + ", " 
                    + soft.getTipo().getIdTipoSoftware() + ");";
            
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
    
    public void softwareComputadora(String codigo){
        
        try {
            String consulta = "SELECT soft_id, SUBSTRING_INDEX(soft_descripcion,'/-/',-1), comp_id "
                    + "FROM software, computadoras " 
                    + "WHERE SUBSTRING_INDEX(soft_descripcion,'/-/',-1) = comp_codigo "
                    + "AND comp_codigo = '" + codigo + ";";
            
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while(resultado.next()){
                int idSoftware = resultado.getInt("soft_id");
                int idComputadora = resultado.getInt("comp_id");
                consulta = "INSERT INTO computadoras_software(soft_id, "
                    + "comp_id) " 
                    + "VALUES (" + idSoftware + ", "
                    + idComputadora + ");";
            }
            ejecutar.close();
        } catch(Exception e){
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
    }
    
    public int buscarIdSoftware(String version, String codigo){
        int idSoftware = 0;
        try {
            String consulta = "SELECT soft_id "
                    + "FROM software "
                    + "WHERE soft_version = '" + version +"';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                idSoftware = resultado.getInt("soft_id");
                ejecutar.close();
                return idSoftware;
            } else {
                ejecutar.close();
                return idSoftware;
            }
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return idSoftware;
    }
    
    public void editarSoftwareEstado(int idSoftware, int idEstado) {
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "UPDATE software "
                    + "SET esta_id = ? "
                    + "WHERE soft_id = ?;";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setInt(1, idEstado);
            ejecutar.setInt(2, idSoftware);

            //Ejecutar la consulta
            int res = ejecutar.executeUpdate();

            if (res > 0) {
                ejecutar.close();
            } else {
                ejecutar.close();
            }

        } catch (Exception e) {
            //Captura el error en memoria y continúa la ejecución
            //ERROR - DEBUG
            System.out.println("ERROR: " + e);
        }
    }
    
    public ArrayList<Software> listarSoftware(int idComputadora){
        ArrayList<Software> listadoSoftware = new ArrayList<>();
        TipoSoftwareControlador tipoC = new TipoSoftwareControlador();
        EstadoControlador estaC = new EstadoControlador();
        try {
            String consulta = "SELECT * "
                    + "FROM software s, computadoras_software cs "
                    + "WHERE s.soft_id = cs.soft_id "
                    + "AND comp_id = " + idComputadora +";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while(resultado.next()){
                Software soft = new Software();
                int idEstado = resultado.getInt("esta_id");
                int idTipoSoftware = resultado.getInt("tip_soft_id");
                soft.setIdSoftware(resultado.getInt("soft_id"));
                soft.setNombre(resultado.getString("soft_nombre"));
                soft.setDescripcion(resultado.getString("soft_descripcion"));
                soft.setVersion(resultado.getString("soft_version"));
                soft.setEstado(estaC.buscarEstado(idEstado));
                soft.setTipo(tipoC.buscarTipoSoftware(idTipoSoftware));
                listadoSoftware.add(soft);
            }
            ejecutar.close();
            return listadoSoftware;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoSoftware;
    }
}
