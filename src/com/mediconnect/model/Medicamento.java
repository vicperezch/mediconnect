package com.mediconnect.model;

/*
@author Nils Muralles
@version 1.0
@creationDate 14 de octubre de 2023
@lastModified 14 de octubre de 2023
@description Clase que modela un medicamento
*/

public class Medicamento {

    // Atributos de Medicamento
    private String nombre;
    private String dosis;
    private String formaAdministracion;
    private String frecuencia;

    // Constructor de Medicamento
    public Medicamento(String nombre, String dosis, String formaAdministracion, String frecuencia) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.formaAdministracion = formaAdministracion;
        this.frecuencia = frecuencia;
    }

    /**
     * @return Nombre del medicamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Nombre del medicamento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Dosis del medicamento
     */
    public String getDosis() {
        return dosis;
    }

    /**
     * @param dosis Dosis del medicamento
     */
    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    /**
     * @return Forma de administración del medicamento
     */
    public String getFormaAdministracion() {
        return formaAdministracion;
    }

    /**
     * @param formaAdministracion Forma de administración del medicamento
     */
    public void setFormaAdministracion(String formaAdministracion) {
        this.formaAdministracion = formaAdministracion;
    }

    /**
     * @return Frecuencia con la que se debe tomar el medicamento
     */
    public String getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia Frecuencia con la que se debe tomar el medicamento
     */
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * @return Representación de medicamento como String
     */
    @Override
    public String toString() {
        return "Medicamento [nombre=" + nombre + ", dosis=" + dosis + ", formaAdministracion=" + formaAdministracion
                + ", frecuencia=" + frecuencia + "]";
    }

}
