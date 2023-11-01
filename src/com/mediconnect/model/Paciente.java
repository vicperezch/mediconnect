package com.mediconnect.model;

/**
 * @author Nils Muralles
 * @version 1.0.1
 * @creationDate 24/10/2023
 * @modificationDate 24/10/2023
 *                   Esta clase modela a un Paciente, que hereda de la clase
 *                   Usuario, para poder ser utilizado en el programa
 */

public class Paciente extends Usuario {
    /**
     * Método que agrega un nuevo Paciente al sistema
     * 
     * @param nombre      Nombre del paciente
     * @param apellido    Apellido del paciente
     * @param correo      Correo del paciente
     * @param password    Contraseña del paciente
     * @param id          Id del paciente
     * @param rol         Rol del paciente
     * @param cartaMedica Carta Médica del paciente
     */
    public Paciente(String nombre, String apellido, String correo, String password, int id, String rol) {
        super(nombre, apellido, correo, password, id, rol);
    }

    public void validarPasword() {

    }

    public void validarCorreo() {

    }

    public void validarRol() {

    }

}
