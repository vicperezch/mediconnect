package com.mediconnect.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

import com.mediconnect.db.CSV;
import com.mediconnect.model.Paciente;
import com.mediconnect.model.Receta;
import com.mediconnect.model.Usuario;

/**
 * @author Nils Muralles
 * @version 1.0.1
 * @creationDate 02/11/2023
 * @modificationDate 02/11/2023
 * @description Clase encargada de gestionar una instancia de RecetaMedica
 */
public class RecetaMedicaController {
    private CSV csv;

    /**
     * Constructor de CitaMedicaController
     */
    public RecetaMedicaController() {
        this.csv = new CSV();
    }

    /**
     * Obtiene a los pacientes del CSV
     * 
     * @return
     */
    public ArrayList<String> obtenerPacientes() {
        ArrayList<Usuario> usuarios;
        ArrayList<String> pacientes = new ArrayList<>();

        try {
            usuarios = csv.leerUsuarios();

            for (Usuario usuario : usuarios) {
                if (usuario instanceof Paciente) {
                    pacientes.add(usuario.toString());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer usuarios");
        }

        return pacientes;
    }

    public boolean agregarReceta(Usuario medico, String paciente, String medicamentos, String justificacion,
            String observaciones) {
        String nombrePaciente = paciente.split(" ")[0];
        String nombreApellido = paciente.split(" ")[1];
        int id_paciente = 0;
        ArrayList<Receta> recetas = new ArrayList<Receta>();

        try {

            ArrayList<Usuario> usuarios = csv.leerUsuarios();

            for (Usuario usuario : usuarios) {
                if (usuario.getNombre().equals(nombrePaciente) && usuario.getApellido().equals(nombreApellido)) {
                    id_paciente = usuario.getId();
                }
            }

            Receta nuevaReceta = new Receta(new Random().nextInt(9000) + 1000, new Date(), medico.getId(), id_paciente,
                    medicamentos, justificacion, observaciones);

            recetas.add(nuevaReceta);

            csv.guardarRecetas(recetas);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public Receta obtenerUltimaReceta() {
        ArrayList<Receta> recetas;
        try {
            recetas = csv.leerRecetas();

        } catch (IOException | ParseException e) {
            return null;
        }

        return recetas.get(recetas.size() - 1);
    }

    public ArrayList<String> obtenerRecetas() {
        ArrayList<Receta> recetas;
        ArrayList<String> infoRecetas = new ArrayList<String>();

        try {
            recetas = csv.leerRecetas();

            for (Receta receta : recetas) {
                String nombrePaciente = null;
                int idPaciente = receta.getIdPaciente();

                for (Usuario usuario : csv.leerUsuarios()) {
                    if (usuario instanceof Paciente && usuario.getId() == idPaciente) {
                        nombrePaciente = usuario.getNombre() + " " + usuario.getApellido();
                    }
                }

                infoRecetas.add(receta.toString(nombrePaciente));
            }

        } catch (IOException | ParseException e) {
            System.out.println("Error al leer las citas");

        }

        return infoRecetas;
    }

    /*
     * 
     */
    public ArrayList<ArrayList<String>> obtenerRecetasPaciente(int idPaciente) {
        ArrayList<Receta> recetas;
        ArrayList<ArrayList<String>> infoRecetas = new ArrayList<>();

        try {
            recetas = csv.leerRecetas();

            for (Receta receta : recetas) {
                if (receta.getIdPaciente() == idPaciente) {

                    ArrayList<String> recetaInfo = new ArrayList<>();
                    recetaInfo.add(receta.getMedicamentos());
                    recetaInfo.add(receta.getJustificacionReceta());
                    recetaInfo.add(receta.getObservaciones());

                    infoRecetas.add(recetaInfo);
                }
            }

        } catch (IOException | ParseException e) {
            System.out.println("Error al leer las recetas");
        }

        return infoRecetas;
    }
}
