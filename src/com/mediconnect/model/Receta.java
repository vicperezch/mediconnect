package com.mediconnect.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * @author Victor Pérez
 * @version 1.0.1
 * @creationDate 15/10/2023
 * @modificationDate 31/10/2023
 *                   Esta clase modela una receta médica para los pacientes
 */
public class Receta {
    private int numeroReceta;
    private Date fechaEmision;
    private int idMedico;
    private int idPaciente;
    private String medicamentos;
    private String justificacionReceta;
    private String observaciones;

    /**
     * Este método crea una nueva instancia de Receta
     *
     * @param numeroReceta        El número de la receta
     * @param fechaEmision        Fecha en la que se emitió la receta
     * @param idMedico            EL id del médico que realizó la receta
     * @param idPaciente          El id del paciente que recibirá la receta
     * @param medicamentos        Array con los medicamentos que contiene la receta
     * @param justificacionReceta La explicación del médico
     * @param observaciones       Observaciones que realiza el médico
     */
    public Receta(int numeroReceta, Date fechaEmision, int idMedico, int idPaciente, String medicamentos,
            String justificacionReceta, String observaciones) {
        this.numeroReceta = numeroReceta;
        this.fechaEmision = fechaEmision;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.medicamentos = medicamentos;
        this.justificacionReceta = justificacionReceta;
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el número de la receta
     * 
     * @return Número en forma de entero
     */
    public int getNumeroReceta() {
        return numeroReceta;
    }

    /**
     * Actualiza el número de la receta
     * 
     * @param numeroReceta Nuevo número
     */
    public void setNumeroReceta(int numeroReceta) {
        this.numeroReceta = numeroReceta;
    }

    /**
     * Obtiene la fecha de emisión
     * 
     * @return Fecha de emisión como instancia de Date
     */
    public Date getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Actualiza la fecha de emisión de la receta
     * 
     * @param fechaEmision Nueva fecha de emisión
     */
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Obtiene el médico que realizó la receta
     * 
     * @return El médico que realizó la receta
     */
    public int getIdMedico() {
        return idMedico;
    }

    /**
     * Actualiza el médico que realizó la receta
     * 
     * @param medico Nuevo médico
     */
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * Obtiene el paciente al que se le dará la receta
     * 
     * @return El paciente al que se le dará la receta
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * Actualiza el paciente que recibirá la receta
     * 
     * @param paciente Nuevo paciente
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * Obtiene el medicamento que contiene la receta
     * 
     * @return Medicamento de la receta
     */
    public String getMedicamentos() {
        return medicamentos;
    }

    /**
     * Actualiza el medicamento
     * 
     * @param medicamentos Nuevo medicamento
     */
    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    /**
     * Obtiene la justificación de la receta
     * 
     * @return Justificación como una String
     */
    public String getJustificacionReceta() {
        return justificacionReceta;
    }

    /**
     * Actualiza la justificación de la receta
     * 
     * @param justificacionReceta Nueva justificación
     */
    public void setJustificacionReceta(String justificacionReceta) {
        this.justificacionReceta = justificacionReceta;
    }

    /**
     * Obtiene las observaciones realizadas
     * 
     * @return Las observaciones como una String
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Actualiza las observaciones del médico
     * 
     * @param observaciones Nuevas observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Permite mostrar la información de la receta
     * 
     * @return Una cadena de texto que contiene el estado de la receta
     */
    public String toString(String nombrePaciente) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return nombrePaciente + "-" + df.format(fechaEmision) + "-" + medicamentos + " "
                + justificacionReceta + " "
                + observaciones;
    }
}
