package com.mediconnect.model;

/**
    * @author Juan Solís
    * @version 1.0.0
    * @creationDate 20/10/2023
    * @modificationDate 20/10/2023
    * Esta clase modela a una Carta Medica para poder ser utilizado en el programa
*/


import java.util.ArrayList;

public class CartaMedica {
    private ArrayList<String> enfermedades = new ArrayList<>();
    private ArrayList<String> alergias = new ArrayList<>();
    private ArrayList<Diagnostico> examenes = new ArrayList<>();
    private ArrayList<Receta> tratamientos = new ArrayList<>();
    
    /**
     * Este método agrega a un nuevo jugador portero al sistema
     * 
     * @param enfermedades Las enfermedadaes del usuario
     * @param alergias Las alergias del usuario
     * @param examenes Los examenes del usuario
     * @param tratamientos Los tratamientos del usuario
    */
    public CartaMedica(ArrayList<String> enfermedades, ArrayList<String> alergias, ArrayList<Diagnostico> examenes,
            ArrayList<Receta> tratamientos) {
        this.enfermedades = enfermedades;
        this.alergias = alergias;
        this.examenes = examenes;
        this.tratamientos = tratamientos;
    }

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
    public ArrayList<Diagnostico> getExamenes() {
        return examenes;
    }

    /**
     * Actualiza los examenes que le han realizado al usuario
     * @param examenes los examenes que le han realizado al usuario
    */
    public void setExamenes(ArrayList<Diagnostico> examenes) {
        this.examenes = examenes;
    }

    /**
     * Obtiene los tratamientos (recetas) que ha recibido el usuario
     * @return Los tratamientos (recetas) que ha recidibo el usuario
    */
    public ArrayList<Receta> getTratamientos() {
        return tratamientos;
    }

    /**
     * Actualiza los tratamientos (recetas) que ha recibido el usuario
     * @param tratamientos los tratamientos (recetas) que ha recibido el usuario
    */
    public void setTratamientos(ArrayList<Receta> tratamientos) {
        this.tratamientos = tratamientos;
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