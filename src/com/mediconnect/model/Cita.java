package com.mediconnect.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
@author Diego Flores, Victor Pérez
@version 2.0.0
@creationDate 19/10/2023
@lastModified 02/11/2023
@description Clase que se encargara de modelar las citas
*/
public class Cita {

    // Atributos de Cita
    private Date fecha;
    private int idPaciente;
    private int idMedico;
    private String establecimiento;

    /**
     * Constructor de la clase encargado de incializar todos los atributos de una cita
     * @param fecha
     * @param idPaciente
     * @param idMedico
     * @param establecimiento
     */
    public Cita(Date fecha, int idPaciente, int idMedico, String establecimiento) {
        this.fecha = fecha;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.establecimiento = establecimiento;
    }

    /**
     * @return fecha de la cita
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha fecha de la cita
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return paciente que esta agendado en la cita
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente ID del paciente que esta agendado en la cita
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return médico que esta agendado en la cita
     */
    public int getIdMedico() {
        return idMedico;
    }

    /**
     * @param idMedico ID del médico que esta agendado en la cita
     */
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * @return nombre del establecimiento donde es la cita
     */
    public String getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento establecimiento donde es la cita
     */
    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * Retorna la información de la cita
     * @return String con una representación de la cita
     */
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return df.format(this.fecha) + "-" + this.establecimiento;
    }
}
