/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.Encargado;
import modelo.Laboratorio;
import modelo.Mantenimiento;

/**
 *
 * @author Alejandro
 */
public class MantenimientoControlador {
    //ATRIBUTOS
    //Métodos

    private Mantenimiento mantenimiento;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public void crearMantenimiento(int idLaboratorio){
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "INSERT INTO mantenimientos(mant_fechaini, "
                    + "lab_id) " 
                    + "VALUES (CURDATE(), " 
                    + idLaboratorio + ");";
            
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

    public Mantenimiento buscarMantenimiento(Laboratorio lab) {
        Mantenimiento man = new Mantenimiento();
        try {
          String consulta = "SELECT mant_id, mant_fechaini, mant_fechafin "
                    + "FROM mantenimientos m, laboratorios l "
                    + "WHERE m.lab_id = l.lab_id "
                    + "AND m.lab_id = " + lab.getIdLaboratorio() + " "
                    + "AND mant_fechafin IS NULL "
                    + "AND (mant_fechaini < CURDATE()"
                    + "OR mant_fechaini = CURDATE());";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            if (resultado.next()) {
                man.setIdMantenimiento(resultado.getInt("mant_id"));
                man.setFechaInicio(resultado.getString("mant_fechaini"));
                man.setFechaFin(resultado.getString("mant_fechafin"));
                man.setLaboratorio(lab);
                ejecutar.close();
                return man;
            } else {
                man.setIdMantenimiento(0);
                ejecutar.close();
                return man;
            }
        } catch (Exception e) {
            man.setIdMantenimiento(0);
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return man;
    }
}
