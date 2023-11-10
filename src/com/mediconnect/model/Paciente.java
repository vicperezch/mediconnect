package com.mediconnect.model;

/**
 * @author Nils Muralles
 * @version 2.1.2
 * @creationDate 24/10/2023
 * @modificationDate 09/11/2023
 * Esta clase modela a un Paciente, que hereda de la clase Usuario, para poder ser utilizado en el programa
 */

public class Paciente extends Usuario {
    private int idCartaMedica;

    /**
     * Método que agrega un nuevo Paciente al sistema
     * 
     * @param nombre Nombre del paciente
     * @param apellido Apellido del paciente
     * @param correo Correo del paciente
     * @param password Contraseña del paciente
     * @param id Id del paciente
     * @param rol Rol del paciente
     * @param idCartaMedica ID de la carta médica del paciente
     */
    public Paciente(String nombre, String apellido, String correo, String password, int id, String rol, int idCartaMedica) {
        super(nombre, apellido, correo, password, id, rol);
        this.idCartaMedica = idCartaMedica;
    }

    /**
     * Obtiene el identificador de la carta médica
     *
     * @return El identificador de la carta médica
     */
    public int getIdCartaMedica() {
        return this.idCartaMedica;
    }

    /**
     * Permite mostrar la información del paciente
     * 
     * @return Una cadena de texto que contiene el estado del paciente
     */
    public String toString() {
        return this.nombre + " " + this.apellido;
    }

}
