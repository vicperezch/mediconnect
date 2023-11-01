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
    private Paciente paciente;
    private String establecimiento;

    /**
     * Constructor de la clase encargado de incializar todos los atributos de una cita
     * @param fecha
     * @param paciente
     * @param establecimiento
     */
    public Cita(Date fecha, Paciente paciente, String establecimiento) {
        this.fecha = fecha;
        this.paciente = paciente;
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
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @param paciente paciente que esta agendado en la cita
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
