package com.mediconnect.model;

/**
    * @author Juan Solís
    * @version 1.0.0
    * @creationDate 15/10/2023
    * @modificationDate 15/10/2023
    * Esta clase modela a un Usuario para poder ser utilizado en el programa
*/

public class Usuario {
    protected String nombre;
    protected String apellido;
    protected String correo;
    protected String password;
    protected int id;
    protected String rol;

    /**
     * Este método agrega a un nuevo jugador portero al sistema
     * 
     * @param nombre El nombre del usuario
     * @param apellido El apellido del usuario
     * @param correo El correo del usuario
     * @param password La contraseña del usuario
     * @param id El id del usuario
     * @param rol El rol del usuario (medico o paciente)
    */
    public Usuario(String nombre, String apellido, String correo, String password, int id, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.id = id;
        this.rol = rol;
    }

    /**
     * Obtiene el nombre del usuario
     * @return El nombre del usuario
    */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre del usuario
     * @param nombre El nuevo nombre del usuario
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario
     * @return El apellido del usuario
    */
    public String getApellido() {
        return apellido;
    }

    /**
     * Actualiza el apellido del usuario
     * @param apellido El nuevo apellido del usuario
    */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el correo del usuario
     * @return El correo del usuario
    */
    public String getCorreo() {
        return correo;
    }

    /**
     * Actualiza el correo del usuario
     * @param correo El nuevo nombre del usuario
    */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario
     * @return La contraseña del usuario
    */
    public String getPassword() {
        return password;
    }

    /**
     * Actualiza la contraseña del usuario
     * @param password La nueva contraseña del usuario
    */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el ID del usuario
     * @return El ID del usuario
    */
    public int getId() {
        return id;
    }

    /**
     * Actualiza el ID del usuario
     * @param id El nuevo ID del usuario
    */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el rol del usuario
     * @return El rol del usuario
    */
    public String getRol() {
        return rol;
    }

    /**
     * Actualiza el rol del usuario
     * @param rol El nuevo rol del usuario
    */
    public void setRol(String rol) {
        this.rol = rol;
    }


    public void validarPassword(){

    }


    public void validarCorreo(){

    }


    public void validarRol(){

    }


    public void nuevoUsuario(){

    }


    public void login(){

    }

    /**
     * Devuelve una cadena de texto que muestra a la instancia de la clase Usuario
     * @return Una cadena de texto que muestra la información del Usuario
    */
    public String toString() {
        String estado = "Nombre: " + this.nombre;
        return estado;
    }
}
