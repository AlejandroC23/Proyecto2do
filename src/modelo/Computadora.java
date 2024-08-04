/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Computadora {
    
    private int idComputadora;
    private String codigo;
    private String marca;
    private boolean mantRealizado;
    private Estado estado;
    private ArrayList<Software> software;
    private ArrayList<Hardware> hardware;

    public Computadora() {
    }

    public Computadora(int idComputadora, String codigo, String marca, boolean mantRealizado, Estado estado, ArrayList<Software> software, ArrayList<Hardware> hardware) {
        this.idComputadora = idComputadora;
        this.codigo = codigo;
        this.marca = marca;
        this.mantRealizado = mantRealizado;
        this.estado = estado;
        this.software = software;
        this.hardware = hardware;
    }

    public int getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(int idComputadora) {
        this.idComputadora = idComputadora;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isMantRealizado() {
        return mantRealizado;
    }

    public void setMantRealizado(boolean mantRealizado) {
        this.mantRealizado = mantRealizado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ArrayList<Software> getSoftware() {
        return software;
    }

    public void setSoftware(ArrayList<Software> software) {
        this.software = software;
    }

    public ArrayList<Hardware> getHardware() {
        return hardware;
    }

    public void setHardware(ArrayList<Hardware> hardware) {
        this.hardware = hardware;
    }
}

