package com.mediconnect.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Victor Pérez
 * @version 1.0.0
 * @creationDate 15/10/2023
 * @modificationDate 15/10/2023
 * Esta clase modela una receta médica para los pacientes
 */
public class Receta {
    private int numeroReceta;
    private Date fechaEmision;
    private Medico medico;
    private ArrayList<Medicamento> medicametos;
    private String justificacionReceta;
    private String observaciones;

    /**
     * Este método crea una nueva instancia de Receta
     *
     * @param numeroReceta El número de la receta
     * @param fechaEmision Fecha en la que se emitió la receta
     * @param medico Médico que realizó la receta
     * @param medicametos Array con los medicamentos que contiene la receta
     * @param justificacionReceta La explicación del médico
     * @param observaciones Observaciones que realiza el médico
     */
    public Receta(int numeroReceta, Date fechaEmision, Medico medico, ArrayList<Medicamento> medicametos, String justificacionReceta, String observaciones) {
        this.numeroReceta = numeroReceta;
        this.fechaEmision = fechaEmision;
        this.medico = medico;
        this.medicametos = medicametos;
        this.justificacionReceta = justificacionReceta;
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el número de la receta
     * @return Número en forma de entero
     */
    public int getNumeroReceta() {
        return numeroReceta;
    }

    /**
     * Actualiza el número de la receta
     * @param numeroReceta Nuevo número
     */
    public void setNumeroReceta(int numeroReceta) {
        this.numeroReceta = numeroReceta;
    }

    /**
     * Obtiene la fecha de emisión
     * @return Fecha de emisión como instancia de Date
     */
    public Date getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Actualiza la fecha de emisión de la receta
     * @param fechaEmision Nueva fecha de emisión
     */
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Obtiene el médico que realizó la receta
     * @return El médico como instancia de la clase Medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Actualiza el médico que realizó la receta
     * @param medico Nuevo médico
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Obtiene la lista de medicamentos que contiene la receta
     * @return Lista de medicamentos como un ArrayList
     */
    public ArrayList<Medicamento> getMedicametos() {
        return medicametos;
    }

    /**
     * Actualiza la lista de medicamentos
     * @param medicametos Nueva lista de medicamentos
     */
    public void setMedicametos(ArrayList<Medicamento> medicametos) {
        this.medicametos = medicametos;
    }

    /**
     * Obtiene la justificación de la receta
     * @return Justificación como una String
     */
    public String getJustificacionReceta() {
        return justificacionReceta;
    }

    /**
     * Actualiza la justificación de la receta
     * @param justificacionReceta Nueva justificación
     */
    public void setJustificacionReceta(String justificacionReceta) {
        this.justificacionReceta = justificacionReceta;
    }

    /**
     * Obtiene las observaciones realizadas
     * @return Las observaciones como una String
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Actualiza las observaciones del médico
     * @param observaciones Nuevas observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Permite mostrar la información de la receta
     * @return Una cadena de texto que contiene el estado de la receta
     */
    public String toString() {
        String estado = "Número: " + this.numeroReceta;
        return estado;
    }
}
