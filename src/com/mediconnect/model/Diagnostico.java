package com.mediconnect.model;

import java.util.Date;

/**
 * @author Victor Pérez
 * @version 1.0.0
 * @creationDate 15/10/2023
 * @modificationDate 15/10/2023
 * Esta clase modela a un diagnóstico realizado por un médico
 */
public class Diagnostico {
    private String resultados;
    private Medico medico;
    private Date fecha;

    /**
     * Constructor de clase que crea una instancia de Diagnostico
     *
     * @param resultados Resultados y descripción del diagnóstico
     * @param medico Médico que realizó el diagnóstico
     * @param fecha Fecha en que se realizó el diagnóstico
     */
    public Diagnostico(String resultados, Medico medico, Date fecha) {
        this.resultados = resultados;
        this.medico = medico;
        this.fecha = fecha;
    }

    /**
     * Obtiene los resultados del diagnóstico
     * @return Los resultados como una String
     */
    public String getResultados() {
        return resultados;
    }

    /**
     * Actualiza los resultados del diagnóstico
     * @param resultados Los nuevos resultados
     */
    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    /**
     * Obtiene el médico que realizó el diagnóstico
     * @return El médico como una instancia de Medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Actualiza el médico que realizó el diagnóstico
     * @param medico Nuevo médico
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Obtiene la fecha en la que se realizó
     * @return La fecha como una Date
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Actualiza la fecha de emisión
     * @param fecha La nueva fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Permite mostrar la información del diagnóstico
     * @return Una cadena de texto que contiene el estado del diagnóstico
     */
    public String toString() {
        String estado = "Resultados: " + this.resultados;
        return estado;
    }
}
