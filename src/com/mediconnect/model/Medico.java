package com.mediconnect.model;

import java.util.ArrayList;

/**
 * @author Nils Muralles
 * @version 1.0.0
 * @creationDate 19/10/2023
 * @modificationDate 19/10/2023
 *                   Esta clase modela a un Medico, que hereda de la clase
 *                   Usuario, para poder ser utilizado en el programa
 */
public class Medico extends Usuario {

    private ArrayList<Cita> citas = new ArrayList<>();
    private ArrayList<Paciente> pacientes = new ArrayList<>();

    /**
     * Método que agrega un nuevo Médico al sistema
     * 
     * @param nombre   El nombre del Médico
     * @param apellido El apellido del Médico
     * @param correo   El correo del Médico
     * @param password La contraseña del Médico
     * @param id       El id del Médico
     * @param rol      El rol del Médico
     */
    public Medico(String nombre, String apellido, String correo,
            String password, int id, String rol) {
        super(nombre, apellido, correo, password, id, rol);
        this.citas = new ArrayList<Cita>();
        this.pacientes = new ArrayList<Paciente>();
    }

    /**
     * Obtiene las citas del méidco
     * 
     * @return Citas del médico
     */
    public ArrayList<Cita> getCitas() {
        return citas;
    }

    /**
     * Actualiza las citas del médico
     * 
     * @param citas Citas del méidco
     */
    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }

    /**
     * Obtiene los pacientes del médico
     * 
     * @return Pacientes del médico
     */
    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * Actualiza los pacientes del médico
     * 
     * @param pacientes Pacientes del médico
     */
    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public void validarPassword() {

    }

    public void validarCorreo() {

    }

    public void validarRol() {

    }

    /**
     * Devuelve un String que representa al médico
     * 
     * @return String que representa al médico
     */
    @Override
    public String toString() {
        return "Medico [citas=" + citas + ", pacientes=" + pacientes + "]";
    }

}
