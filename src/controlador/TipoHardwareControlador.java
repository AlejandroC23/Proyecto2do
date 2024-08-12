/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.TipoHardware;

/**
 *
 * @author Alejandro
 */
public class TipoHardwareControlador {
    //ATRIBUTOS
    //Métodos

    private TipoHardware tipoHardware;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public TipoHardware buscarTipoHardware(int idTipoHardware) {
        TipoHardware tipoHardware = new TipoHardware();
        try {
            String consulta = "SELECT * "
                    + "FROM tipo_hardware "
                    + "WHERE tip_had_id = " + idTipoHardware + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            if (resultado.next()) {
                tipoHardware.setIdTipoHardware(idTipoHardware);
                tipoHardware.setDescripcion(resultado.getString("tip_had_obj"));
                ejecutar.close();
                return tipoHardware;
            }
            ejecutar.close();
            return tipoHardware;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return tipoHardware;
    }
    
    public ArrayList<TipoHardware> listarTipoHardware() {
        ArrayList<TipoHardware> listadoTH = new ArrayList<>();
        try {
            String consulta = "SELECT * "
                    + "FROM tipo_hardware;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while (resultado.next()) {
                TipoHardware th = new TipoHardware();
                th.setIdTipoHardware(resultado.getInt("tip_had_id"));
                th.setDescripcion(resultado.getString("tip_had_obj"));
                listadoTH.add(th);
            }
            ejecutar.close();
            return listadoTH;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoTH;
    }
}
