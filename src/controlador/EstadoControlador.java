/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Estado;

/**
 *
 * @author Alejandro
 */
public class EstadoControlador {
    //ATRIBUTOS
    //Métodos

    private Estado estado;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public Estado buscarEstado(int idEstado) {
        Estado esta = new Estado();
        try {
            String consulta = "SELECT * "
                    + "FROM estados "
                    + "WHERE esta_id = " + idEstado + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            if (resultado.next()) {
                esta.setIdEstado(idEstado);
                esta.setDescripcion(resultado.getString("esta_obj"));
                ejecutar.close();
                return esta;
            }
            ejecutar.close();
            return esta;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return esta;
    }
    
    public ArrayList<Estado> listarEstados() {
        ArrayList<Estado> listadoEstados = new ArrayList<>();
        try {
            String consulta = "SELECT * "
                    + "FROM estados;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while (resultado.next()) {
                Estado esta = new Estado();
                esta.setIdEstado(resultado.getInt("esta_id"));
                esta.setDescripcion(resultado.getString("esta_obj"));
                listadoEstados.add(esta);
            }
            ejecutar.close();
            return listadoEstados;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoEstados;
    }
}
