/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Hardware;

/**
 *
 * @author Alejandro
 */
public class HardwareControlador {
    //ATRIBUTOS
    //Métodos

    private Hardware hardware;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearHardware(Hardware hard) {

        try {
            String consulta = "INSERT INTO hardware(had_descripcion, "
                    + "had_nroSerie, "
                    + "tip_had_id, "
                    + "esta_id) "
                    + "VALUES ('" + hard.getDescripcion() + "', '"
                    + hard.getNroSerie() + "', "
                    + hard.getTipo().getIdTipoHardware() + ", "
                    + hard.getEstado().getIdEstado() + ");";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);

            int res = ejecutar.executeUpdate();

            if (res > 0) {
                ejecutar.close();
            } else {
                ejecutar.close();
            }

        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
    }
    
    public void hardwareComputadora(String codigo){
        
        try {
            String consulta = "SELECT had_id, SUBSTRING_INDEX(had_descripcion,'/-/',-1), comp_id "
                    + "FROM hardware, computadoras " 
                    + "WHERE SUBSTRING_INDEX(had_descripcion,'/-/',-1) = comp_codigo "
                    + "AND comp_codigo = '" + codigo + ";";
            
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while(resultado.next()){
                int idHardware = resultado.getInt("had_id");
                int idComputadora = resultado.getInt("comp_id");
                consulta = "INSERT INTO computadoras_hardware(had_id, "
                    + "comp_id) " 
                    + "VALUES (" + idHardware + ", "
                    + idComputadora + ");";
            }
            ejecutar.close();
        } catch(Exception e){
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
    }

    public int buscarIdHardware(String nroSerie) {
        int idHardware = 0;
        try {
            String consulta = "SELECT had_id "
                    + "FROM hardware "
                    + "WHERE had_nroSerie = '" + nroSerie + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            if (resultado.next()) {
                idHardware = resultado.getInt("had_id");
                ejecutar.close();
                return idHardware;
            } else {
                ejecutar.close();
                return idHardware;
            }
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return idHardware;
    }
    
    public void editarHardwareEstado(int idHardware, int idEstado) {
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "UPDATE hardware "
                    + "SET esta_id = ? "
                    + "WHERE had_id = ?;";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setInt(1, idEstado);
            ejecutar.setInt(2, idHardware);

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

    public ArrayList<Hardware> listarHardware(int idComputadora) {
        ArrayList<Hardware> listadoHardware = new ArrayList<>();
        TipoHardwareControlador tipoC = new TipoHardwareControlador();
        EstadoControlador estaC = new EstadoControlador();
        try {
            String consulta = "SELECT * "
                    + "FROM hardware h, computadoras_hardware ch "
                    + "WHERE h.had_id = ch.had_id "
                    + "AND comp_id = " + idComputadora + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            while (resultado.next()) {
                Hardware hard = new Hardware();
                int idEstado = resultado.getInt("esta_id");
                int idTipoHardware = resultado.getInt("tip_had_id");
                hard.setIdHarware(resultado.getInt("had_id"));
                hard.setNroSerie(resultado.getString("had_nroSerie"));
                hard.setDescripcion(resultado.getString("had_descripcion"));
                hard.setEstado(estaC.buscarEstado(idEstado));
                hard.setTipo(tipoC.buscarTipoHardware(idTipoHardware));
                listadoHardware.add(hard);
            }
            ejecutar.close();
            return listadoHardware;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoHardware;
    }
}
