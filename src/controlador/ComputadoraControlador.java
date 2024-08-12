/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Computadora;
import modelo.Hardware;
import modelo.Software;

/**
 *
 * @author Alejandro
 */
public class ComputadoraControlador {
    //ATRIBUTOS
    //Métodos

    private Computadora computadora;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearComputadora(Computadora comp, int idLaboratorio) {
        SoftwareControlador softC = new SoftwareControlador();
        HardwareControlador hardC = new HardwareControlador();
        try {
            String consulta = "INSERT INTO computadoras(comp_codigo, "
                    + "comp_marca, "
                    + "comp_mantRealizado, "
                    + "esta_id, "
                    + "lab_id) "
                    + "VALUES ('" + comp.getCodigo() + "', '"
                    + comp.getMarca() + "', "
                    + comp.isMantRealizado() + ", "
                    + comp.getEstado().getIdEstado() + ", "
                    + idLaboratorio + ");";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);

            int res = ejecutar.executeUpdate();

            if (res > 0) {
                int idComputadora = buscarIdComputadora(comp.getCodigo());
                for (Software p : comp.getSoftware()) {
                    softC.crearSoftware(p);
                }
                for (Hardware q : comp.getHardware()) {
                    hardC.crearHardware(q);
                }
                
                
            } else {
                ejecutar.close();
            }

        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
    }

    public Computadora buscarComputadoraLaboratorio(int idLaboratorio, String codigo) {
        Computadora c = new Computadora();
        SoftwareControlador softC = new SoftwareControlador();
        HardwareControlador hardC = new HardwareControlador();
        EstadoControlador estaC = new EstadoControlador();
        try {
            String consulta = "SELECT comp_id, comp_marca, comp_mantRealizado, esta_id "
                    + "FROM computadoras "
                    + "WHERE lab_id = " + idLaboratorio + " "
                    + "AND comp_codigo = '" + codigo + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            if (resultado.next()) {
                int idComputadora = resultado.getInt("comp_id");
                int idEstado = resultado.getInt("esta_id");
                c.setIdComputadora(idComputadora);
                c.setCodigo(codigo);
                c.setMarca(resultado.getString("comp_marca"));
                c.setMantRealizado(resultado.getBoolean("comp_mantRealizado"));
                c.setSoftware(softC.listarSoftware(idComputadora));
                c.setHardware(hardC.listarHardware(idComputadora));
                c.setEstado(estaC.buscarEstado(idEstado));
            } else {
                c.setIdComputadora(0);
            }
            ejecutar.close();
            return c;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return c;
    }

    public int buscarIdComputadora(String codigo) {
        int idComputadora = 0;
        try {
            String consulta = "SELECT comp_id "
                    + "FROM computadoras "
                    + "WHERE comp_codigo = '" + codigo + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            if (resultado.next()) {
                idComputadora = resultado.getInt("comp_id");
                ejecutar.close();
                return idComputadora;
            } else {
                ejecutar.close();
                return idComputadora;
            }
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return idComputadora;
    }

    public boolean existeComputadora(String codigo) {
        boolean x = true;
        try {
            String consulta = "SELECT comp_id "
                    + "FROM computadoras "
                    + "WHERE comp_codigo = '" + codigo + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            if (resultado.next()) {
                x = true;
                ejecutar.close();
                return x;
            } else {
                x = false;
                ejecutar.close();
                return x;
            }
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return x;
    }
    
    public void editarComputadoraEstado(int idComputadora, int idEstado) {
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "UPDATE computadoras "
                    + "SET esta_id = ? "
                    + "WHERE comp_id = ?;";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setInt(1, idEstado);
            ejecutar.setInt(2, idComputadora);

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
    
    public void editarComputadoraMantenimiento(int idComputadora) {
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "UPDATE computadoras "
                    + "SET comp_mantRealizado = true "
                    + "WHERE comp_id = ?;";

            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            ejecutar.setInt(1, idComputadora);

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

    public ArrayList<Computadora> listarComputadoras(int idLaboratorio) {
        ArrayList<Computadora> listadoComputadoras = new ArrayList<>();
        SoftwareControlador softC = new SoftwareControlador();
        HardwareControlador hardC = new HardwareControlador();
        EstadoControlador estaC = new EstadoControlador();
        try {
            String consulta = "SELECT comp_id, comp_codigo, comp_marca, comp_mantRealizado, esta_id "
                    + "FROM computadoras "
                    + "WHERE lab_id = " + idLaboratorio + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);

            while (resultado.next()) {
                Computadora c = new Computadora();
                int idComputadora = resultado.getInt("comp_id");
                int idEstado = resultado.getInt("esta_id");
                c.setCodigo(resultado.getString("comp_codigo"));
                c.setMarca(resultado.getString("comp_marca"));
                c.setMantRealizado(resultado.getBoolean("comp_mantRealizado"));
                c.setSoftware(softC.listarSoftware(idComputadora));
                c.setHardware(hardC.listarHardware(idComputadora));
                c.setEstado(estaC.buscarEstado(idEstado));
                listadoComputadoras.add(c);
            }
            ejecutar.close();
            return listadoComputadoras;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                    + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoComputadoras;
    }
}
