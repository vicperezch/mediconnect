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
    }

    public void validarPassword() {

    }

    public void validarCorreo() {

    }

    public void validarRol() {

    }
}
