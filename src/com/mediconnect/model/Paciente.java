package com.mediconnect.model;

/**
 * @author Nils Muralles
 * @version 1.0.0
 * @creationDate 24/10/2023
 * @modificationDate 24/10/2023
 *                   Esta clase modela a un Paciente, que hereda de la clase
 *                   Usuario, para poder ser utilizado en el programa
 */

public class Paciente extends Usuario {

    private CartaMedica cartaMedica;

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
    public Paciente(String nombre, String apellido, String correo, String password, int id, String rol,
            CartaMedica cartaMedica) {
        super(nombre, apellido, correo, password, id, rol);
        this.cartaMedica = cartaMedica;
    }

    /**
     * Obtiene la carta médica del paciente
     * 
     * @return Carta médica del paciente
     */
    public CartaMedica getCartaMedica() {
        return cartaMedica;
    }

    /**
     * Actualiza la carta médica del paciente
     * 
     * @param cartaMedica Carta médica del paciente
     */
    public void setCartaMedica(CartaMedica cartaMedica) {
        this.cartaMedica = cartaMedica;
    }

    public void validarPasword() {

    }

    public void validarCorreo() {

    }

    public void validarRol() {

    }

    /**
     * Devuelve un String que representa al paciente
     * 
     * @return String que representa al paciente
     */
    @Override
    public String toString() {
        return "Paciente [cartaMedica=" + cartaMedica + "]";
    }

}
