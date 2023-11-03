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
 * @version 1.0.0
 * @creationDate 02/11/2023
 * @modificationDate 02/11/2023
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
     *
     * @return
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
     *
     */
    public boolean agregarCita(Usuario medico, String paciente, String establecimiento, String fecha, String hora) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String nombrePaciente = paciente.split(" ")[0];
        String nombreApellido = paciente.split(" ")[1];
        int id_paciente = 0;
        System.out.println(fecha);
        System.out.println(hora);

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
     *
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
     *
     */
    public String obtenerUltimaCita(int idMedico) {
        ArrayList<String> citas = obtenerCitas(idMedico);

        return citas.get(citas.size() - 1);
    }
}
