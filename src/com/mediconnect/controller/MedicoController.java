package com.mediconnect.controller;

import com.mediconnect.model.Medico;
import com.mediconnect.model.Paciente;
import java.util.ArrayList;

/**
 * @author Victor Pérez
 * @version 1.0.0
 * @creationDate 27/10/2023
 * @modificationDate 27/10/2023
 * @description Contiene las funcionalidades de un usuario de tipo Medico
 */
public class MedicoController {
    private Medico medico;

    /**
     * @description Constructor de clase
     * @param medico La instancia de Medico que manejará este controlador
     */
    public MedicoController(Medico medico) {
        this.medico = medico;
    }

    /**
     * @description Permite mostrar la lista de pacientes que el médico tiene a cargo
     * @return String con los pacientes
     */
    public String verPacientes() {
        ArrayList<Paciente> pacientes = medico.getPacientes();
        String listaPacientes = "";

        // Evalúa
        if (!pacientes.isEmpty()) {
            for (Paciente paciente : pacientes) {
                listaPacientes += paciente.toString() + "\n";
            }

            return listaPacientes;

        } else {
            return "El médico no tiene ningún paciente asignado";
        }
    }


    public void verCitas() {

    }


    public void nuevoDiagnostico() {

    }


    public void nuevaCita() {

    }

    public void nuevaReceta() {

    }
}