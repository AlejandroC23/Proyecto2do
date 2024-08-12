/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import modelo.DetalleMantenimiento;
import modelo.Mantenimiento;

/**
 *
 * @author Alejandro
 */
public class DetalleMantenimientoControlador {
    //ATRIBUTOS
    //Métodos
    private DetalleMantenimiento detalleMantenimiento;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearDetalleMantenimiento(DetalleMantenimiento detMant, int idMantenimiento) {
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "INSERT INTO detalle_mantenimiento(det_mant_obj, "
                    + "mant_id, "
                    + "comp_id) "
                    + "VALUES ('" + detMant.getDescripcion() + "', "
                    + idMantenimiento + ", "
                    + detMant.getComputadora().getIdComputadora() + ");";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);

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
}
