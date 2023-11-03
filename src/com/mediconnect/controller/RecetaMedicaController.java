package com.mediconnect.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

import com.mediconnect.db.CSV;
import com.mediconnect.model.Cita;
import com.mediconnect.model.Paciente;
import com.mediconnect.model.Receta;
import com.mediconnect.model.Usuario;
import com.mediconnect.view.RecetaMedica;

/**
 * @author Nils Muralles
 * @version 1.0.0
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

    public boolean agregarReceta(Usuario usuario, String paciente, String medicamentos, String justificacion,
            String observaciones) {
        ArrayList<Receta> recetas = new ArrayList<Receta>();
        ArrayList<String> listaMedicamentos = new ArrayList<String>();
        listaMedicamentos.add(medicamentos);

        Receta nuevaReceta = new Receta(new Random().nextInt(9000) + 1000, new Date(), usuario.getId(), 0,
                listaMedicamentos, justificacion, observaciones);

        recetas.add(nuevaReceta);

        try {
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
                infoRecetas.add(receta.toString());
            }

        } catch (IOException | ParseException e) {
            System.out.println("Error al leer las citas");

        }

        return infoRecetas;
    }

}
