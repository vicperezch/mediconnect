package com.mediconnect.model;

import java.util.Date;

/**
@author Diego Flores
@version 1.1.0
@creationDate 19/10/2023
@lastModified 31/10/2023
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
     * @param paciente
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
    public int idPaciente() {
        return idPaciente;
    }

    /**
     * @param paciente paciente que esta agendado en la cita
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return médico que esta agendado en la cita
     */
    public int idMedico() {
        return idMedico;
    }

    /**
     * @param medico médico que esta agendado en la cita
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
}
