/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.TipoSoftware;

/**
 *
 * @author Alejandro
 */
public class TipoSoftwareControlador {
    //ATRIBUTOS
    //Métodos

    private TipoSoftware tipoSoftware;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public TipoSoftware buscarTipoSoftware(int idTipoSoftware) {
        TipoSoftware tipoSoftware = new TipoSoftware();
        try {
            String consulta = "SELECT * "
                    + "FROM tipo_software "
                    + "WHERE tip_soft_id = " + idTipoSoftware + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            if (resultado.next()) {
                tipoSoftware.setIdTipoSoftware(idTipoSoftware);
                tipoSoftware.setDescripcion(resultado.getString("tip_soft_obj"));
                ejecutar.close();
                return tipoSoftware;
            }
            ejecutar.close();
            return tipoSoftware;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return tipoSoftware;
    }
    
    public ArrayList<TipoSoftware> listarTipoSoftware() {
        ArrayList<TipoSoftware> listadoTS = new ArrayList<>();
        try {
            String consulta = "SELECT * "
                    + "FROM tipo_software;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while (resultado.next()) {
                TipoSoftware ts = new TipoSoftware();
                ts.setIdTipoSoftware(resultado.getInt("tip_soft_id"));
                ts.setDescripcion(resultado.getString("tip_soft_obj"));
                listadoTS.add(ts);
            }
            ejecutar.close();
            return listadoTS;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoTS;
    }
}
