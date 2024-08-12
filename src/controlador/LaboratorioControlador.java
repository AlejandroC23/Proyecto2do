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

    public void crearLaboratorio(Laboratorio lab) {
        try {
            String consulta = "INSERT INTO laboratorios(lab_nombre, "
                    + "lab_edificio, "
                    + "lab_ciudad, "
                    + "enc_id) "
                    + "VALUES (?,?,?,?);";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setString(1, lab.getNombre());
            ejecutar.setInt(2, lab.getEdificio());
            ejecutar.setString(3, lab.getCiudad());
            ejecutar.setInt(4, lab.getEncargado().getIdEncargado());
            
            int res = ejecutar.executeUpdate();

            if (res > 0) {
                ejecutar.close();
            } else {
                ejecutar.close();
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    public Laboratorio buscarLaboratorio(String cedula) {
        ComputadoraControlador compC = new ComputadoraControlador();
        EncargadoControlador encC = new EncargadoControlador();
        Encargado encTemp = encC.buscarEncargado(cedula);
        Laboratorio lab = new Laboratorio();
        try {
          String consulta = "SELECT lab_id, lab_nombre, lab_edificio, lab_ciudad "
                    + "FROM encargados e, laboratorios l, usuarios u "
                    + "WHERE e.enc_id = l.enc_id "
                    + "AND e.usu_id = u.usu_id "
                    + "AND usu_cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            if (resultado.next()) {
                int idLaboratorio = resultado.getInt("lab_id");
                lab.setNombre(resultado.getString("lab_nombre"));
                lab.setEdificio(resultado.getInt("lab_edificio"));
                lab.setCiudad(resultado.getString("lab_ciudad"));
                lab.setComputadoras(compC.listarComputadoras(idLaboratorio));
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
    
    public Laboratorio buscarLaboratorio(int idLaboratorio) {
        ComputadoraControlador compC = new ComputadoraControlador();
        EncargadoControlador encC = new EncargadoControlador();
        Laboratorio lab = new Laboratorio();
        try {
          String consulta = "SELECT lab_id, lab_nombre, lab_edificio, lab_ciudad, e.enc_id "
                    + "FROM encargados e, laboratorios l "
                    + "WHERE e.enc_id = l.enc_id "
                    + "AND lab_id = " + idLaboratorio + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            if (resultado.next()) {
                lab.setIdLaboratorio(idLaboratorio);
                lab.setNombre(resultado.getString("lab_nombre"));
                lab.setEdificio(resultado.getInt("lab_edificio"));
                lab.setCiudad(resultado.getString("lab_ciudad"));
                int idEncargado = resultado.getInt("e.enc_id");
                lab.setComputadoras(compC.listarComputadoras(idLaboratorio));
                lab.setEncargado(encC.buscarEncargado(idEncargado));
                ejecutar.close();
                return lab;
            } else {
                ejecutar.close();
                return lab;
            }
        } catch (Exception e) {
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
    
    public ArrayList<Laboratorio> listarLaboratorios() {
        ComputadoraControlador compC = new ComputadoraControlador();
        EncargadoControlador encC = new EncargadoControlador();
        ArrayList<Laboratorio> listadoLaboratorios = new ArrayList<>();
        try {
          String consulta = "SELECT lab_id, lab_nombre, lab_edificio, lab_ciudad, e.enc_id "
                    + "FROM encargados e, laboratorios l "
                    + "WHERE e.enc_id = l.enc_id;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            while (resultado.next()) {
                Laboratorio lab = new Laboratorio();
                lab.setIdLaboratorio(resultado.getInt("lab_id"));
                lab.setNombre(resultado.getString("lab_nombre"));
                lab.setEdificio(resultado.getInt("lab_edificio"));
                lab.setCiudad(resultado.getString("lab_ciudad"));
                int idEncargado = resultado.getInt("e.enc_id");
                lab.setComputadoras(compC.listarComputadoras(lab.getIdLaboratorio()));
                lab.setEncargado(encC.buscarEncargado(idEncargado));
                listadoLaboratorios.add(lab);
            } 
            ejecutar.close();
            return listadoLaboratorios;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoLaboratorios;
    }
    
    public void editarLaboratorio(Laboratorio lab) {
        try {
            String consulta = "UPDATE laboratorios "
                    + "SET lab_nombre = ?, "
                    + "lab_edificio = ?, "
                    + "lab_ciudad = ?, "
                    + "enc_id = ? "
                    + "WHERE lab_id = ?;";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setString(1, lab.getNombre());
            ejecutar.setInt(2, lab.getEdificio());
            ejecutar.setString(3, lab.getCiudad());
            ejecutar.setInt(4, lab.getEncargado().getIdEncargado());
            ejecutar.setInt(5, lab.getIdLaboratorio());
            
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
