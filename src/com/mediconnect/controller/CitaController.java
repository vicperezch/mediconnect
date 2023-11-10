package com.mediconnect.controller;

import com.mediconnect.db.CSV;
import com.mediconnect.model.Cita;
import com.mediconnect.model.Paciente;
import com.mediconnect.model.Usuario;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Victor Pérez
 * @version 1.0.3
 * @creationDate 02/11/2023
 * @modificationDate 09/11/2023
 * @description Clase encargada de gestionar una instancia de Cita
 */
public class CitaController {
    private CSV csv;

    /**
     * Constructor de clase
     */
    public CitaController() {
        this.csv = new CSV();
    }

    /**
     * Método que obtiene todos los paciente registrados en el programa
     * 
     * @return Un ArrayList con los pacientes
     */
    public ArrayList<String> obtenerPacientes() {
        ArrayList<Usuario> usuarios;
        ArrayList<String> pacientes = new ArrayList<>();

        try {
            usuarios = csv.leerUsuarios();

            for (Usuario usuario: usuarios) {
                if (usuario instanceof Paciente) {
                    pacientes.add(usuario.toString());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer usuarios");
        }

        return pacientes;
    }


    /**
     * Método que agenda una nueva cita
     * 
     * @param medico El médico que agenda la cita
     * @param paciente El paciente con quién se agendó la cita
     * @param establecimiento El lugar en donde se llevará a cabo la cita
     * @param fecha La fecha de la cita
     * @param hora La hora de la cita
     * @return Boolean que confirma si se agendó correctamente la cita
     */
    public boolean agregarCita(Usuario medico, String paciente, String establecimiento, String fecha, String hora) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String nombrePaciente = paciente.split(" ")[0];
        String nombreApellido = paciente.split(" ")[1];
        int id_paciente = 0;

        try {
            Date fechaCita = df.parse(fecha + " " + hora);
            ArrayList<Usuario> usuarios = csv.leerUsuarios();

            for (Usuario usuario: usuarios) {
                if (usuario.getNombre().equals(nombrePaciente) && usuario.getApellido().equals(nombreApellido)) {
                    id_paciente = usuario.getId();
                }
            }

            csv.guardarCita(new Cita(fechaCita, id_paciente, medico.getId(), establecimiento));

            return true;

        } catch (ParseException | IOException e) {
            return false;
        }
    }


    /**
     * Método que obtiene todas las citas que un médico ha agendado
     * 
     * @param idMedico El id del médico que está solicitando sus citas
     * @return El ArrayList con las citas que tendrá el médico
     */
    public ArrayList<String> obtenerCitas(int idMedico) {
        ArrayList<Cita> citas;
        ArrayList<String> infoCitas = new ArrayList<>();

        try {
            citas = csv.leerCitas();

            for (Cita cita: citas) {
                if (cita.getIdMedico() == idMedico) {
                    infoCitas.add(cita.toString());
                }
            }

        } catch (IOException | ParseException e) {
            System.out.println("Error al leer las citas");

        }

        return infoCitas;
    }

    /**
     * Método que obtiene las citas que tendrá un paciente con los médico
     * 
     * @param idPaciente El id del áciente que está solicitando sus citas
     * @return El ArrayList con las citas que tendrá el paciente
     * 
     */
    public ArrayList<String> obtenerCitasPaciente(int idPaciente) {
        ArrayList<Cita> citas;
        ArrayList<String> infoCitas = new ArrayList<>();

        try {
            citas = csv.leerCitas();

            for (Cita cita: citas) {
                if (cita.getIdPaciente() == idPaciente) {
                    infoCitas.add(cita.toString());
                }
            }

        } catch (IOException | ParseException e) {
            System.out.println("Error al leer las citas");

        }

        return infoCitas;
    }


    /**
     * Método que obtiene la última cita que ha sido agendada en el programa
     * 
     * @param idMedico El id del médico que agendo la última cita
     * @return La última cita asociada al médico o null si no hay citas disponibles
     */
    public String obtenerUltimaCita(int idMedico) {
        ArrayList<String> citas = obtenerCitas(idMedico);

        return citas.get(citas.size() - 1);
    }
}
