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
import modelo.Encargado;
import modelo.Laboratorio;

/**
 *
 * @author Alejandro
 */
public class LaboratorioControlador {
    //ATRIBUTOS
    //Métodos

    private Laboratorio laboratorio;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public Laboratorio buscarLaboratorio(String cedula) {
        EncargadoControlador encC = new EncargadoControlador();
        Encargado encTemp = encC.buscarEncargado(cedula);
        Laboratorio lab = new Laboratorio();
        try {
          String consulta = "SELECT lab_nombre, lab_edificio, lab_ciudad "
                    + "FROM encargados e, laboratorios l, usuarios u "
                    + "WHERE e.enc_id = l.enc_id "
                    + "AND e.usu_id = u.usu_id "
                    + "AND usu_cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            if (resultado.next()) {
                lab.setNombre(resultado.getString("lab_nombre"));
                lab.setEdificio(resultado.getInt("lab_edificio"));
                lab.setCiudad(resultado.getString("lab_ciudad"));
                lab.setEncargado(encTemp);
                ejecutar.close();
                return lab;
            } else {
                encTemp.setCedula("1");
                lab.setEncargado(encTemp);
                ejecutar.close();
                return lab;
            }
        } catch (Exception e) {
            encTemp.setCedula("1");
            lab.setEncargado(encTemp);
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return lab;
    }
    
    public int buscarIdLaboratorio(String cedulaEncargado){
        try {
            String consulta = "SELECT lab_id "
                    + "FROM encargados e, laboratorios l, usuarios u "
                    + "WHERE e.enc_id = l.enc_id "
                    + "AND e.usu_id = u.usu_id "
                    + "AND usu_cedula = '" + cedulaEncargado + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                int idLaboratorio = resultado.getInt("lab_id");
                return idLaboratorio;
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
}
