package com.mediconnect.model;

/**
    * @author Juan Solís
    * @version 1.0.1
    * @creationDate 20/10/2023
    * @modificationDate 02/11/2023
    * Esta clase modela a una Carta Medica para poder ser utilizado en el programa
*/


import java.util.ArrayList;

public class CartaMedica {
    private int id;
    private ArrayList<String> enfermedades;
    private ArrayList<String> alergias;
    private ArrayList<String> examenes;
    
    /**
     * Este método agrega a un nuevo jugador portero al sistema
     * 
     * @param enfermedades Las enfermedadaes del usuario
     * @param alergias Las alergias del usuario
     * @param examenes Los examenes del usuario
    */
    public CartaMedica(int id, ArrayList<String> enfermedades, ArrayList<String> alergias, ArrayList<String> examenes) {
        this.id = id;
        this.enfermedades = enfermedades;
        this.alergias = alergias;
        this.examenes = examenes;
    }

    /**
     * Actualiza el id de la carta
     * @param id Nuevo ID
     */
    public void setId(int id) {this.id = id;}

    /**
     * Obtiene el id de la carta
     * @return int con el id
     */
    public int getId() {return this.id;}

    /**
     * Obtiene las enfermedades del usuario
     * @return Las enfermedades del usuario
    */
    public ArrayList<String> getEnfermedades() {
        return enfermedades;
    }

    /**
     * Actualiza las enfermedades del usuario
     * @param enfermedades las enfermedades del usuario
    */
    public void setEnfermedades(ArrayList<String> enfermedades) {
        this.enfermedades = enfermedades;
    }

    /**
     * Obtiene las alergias del usuario
     * @return Las alergias del usuario
    */
    public ArrayList<String> getAlergias() {
        return alergias;
    }

    /**
     * Actualiza las alergias del usuario
     * @param alergias las alergias del usuario
    */
    public void setAlergias(ArrayList<String> alergias) {
        this.alergias = alergias;
    }

    /**
     * Obtiene los examenes que le han realizado al usuario
     * @return Los examenes que le han realizado al usuario
    */
    public ArrayList<String> getExamenes() {
        return examenes;
    }

    /**
     * Actualiza los examenes que le han realizado al usuario
     * @param examenes los examenes que le han realizado al usuario
    */
    public void setExamenes(ArrayList<String> examenes) {
        this.examenes = examenes;
    }

    /**
     * Devuelve una cadena de texto que muestra a la instancia de la clase CartaMedica
     * @return Una cadena de texto que muestra la información de la CartaMedica
    */
    public String toString() {
        String estado = "Enfermedades: " + this.enfermedades;
        return estado;
    }
}