/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Carrera;
import modelo.Estudiante;

/**
 *
 * @author Alejandro
 */
public class CarreraControlador {
    //ATRIBUTOS
    //Métodos
    private Carrera carrera;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public Carrera buscarCarrera(String matricula) {
        Carrera car = new Carrera();
        try {
            String consulta = "SELECT car_obj "
                    + "FROM estudiantes e, carreras c "
                    + "WHERE e.car_id = c.car_id "
                    + "AND est_matricula = '" + matricula + "'";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            if (resultado.next()) {
                car.setNombre(resultado.getString("car_obj"));
                ejecutar.close();
                return car;
            }
            ejecutar.close();
            return car;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return car;
    }
    
    public ArrayList<Carrera> listarCarreras(){
        ArrayList<Carrera> listadoCarreras = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM carreras;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while(resultado.next()){
                Carrera c = new Carrera();
                c.setIdCarrera(resultado.getInt("car_id"));
                c.setNombre(resultado.getString("car_obj"));
                listadoCarreras.add(c);
            }
            ejecutar.close();
            return listadoCarreras;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoCarreras;
    }
}
