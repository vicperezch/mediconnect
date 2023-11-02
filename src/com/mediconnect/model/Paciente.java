package com.mediconnect.model;

/**
 * @author Nils Muralles
 * @version 2.0.0
 * @creationDate 24/10/2023
 * @modificationDate 01/11/2023
 *                   Esta clase modela a un Paciente, que hereda de la clase
 *                   Usuario, para poder ser utilizado en el programa
 */

public class Paciente extends Usuario {
    private int idCartaMedica;


    /**
     * Método que agrega un nuevo Paciente al sistema
     * 
     * @param nombre      Nombre del paciente
     * @param apellido    Apellido del paciente
     * @param correo      Correo del paciente
     * @param password    Contraseña del paciente
     * @param id          Id del paciente
     * @param rol         Rol del paciente
     * @param idCartaMedica ID de la carta médica del paciente
     */
    public Paciente(String nombre, String apellido, String correo, String password, int id, String rol, int idCartaMedica) {
        super(nombre, apellido, correo, password, id, rol);
        this.idCartaMedica = idCartaMedica;
    }

    public int getIdCartaMedica() {return this.idCartaMedica;}

    public void validarPasword() {

    }

    public void validarCorreo() {

    }

    public void validarRol() {

    }

}
