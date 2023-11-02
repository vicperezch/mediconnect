package com.mediconnect.controller;

/**
    * @author Juan Solís
    * @version 1.0.0
    * @creationDate 24/10/2023
    * @modificationDate 24/10/2023
    * Esta clase se encarga de llevar el control de la clase modelo de CartaMedica
*/

import com.mediconnect.model.CartaMedica;
import com.mediconnect.model.Medico;

import java.util.ArrayList;
import java.util.Date;

public class CartaMedicaController {
    private CartaMedica cartaMedica;
    
    /**
     * Constructor de la clase CartaMedicaController.
     *
     * @param cartaMedica La instancia de CartaMedica que se va a gestionar con este controlador.
     */
    public CartaMedicaController(CartaMedica cartaMedica) {
        this.cartaMedica = cartaMedica;
    }

    /**
     * Método que agrega una nueva enfermedad a la carta médica
     * 
     * @param nombreEnfermedad El nombre de la nueva enfermedad que será agregada
    */
    public void agregarEnfermedad(String nombreEnfermedad){
        ArrayList<String> enfermedades = cartaMedica.getEnfermedades();
        boolean enfermedadEncontrada = false;

        for (String enfermedad : enfermedades) {
            if (enfermedad.equals(nombreEnfermedad)) {
                enfermedadEncontrada = true;
                break;
            }
        }

        if (!enfermedadEncontrada) {
            enfermedades.add(nombreEnfermedad);
            cartaMedica.setEnfermedades(enfermedades);
        } else {
            // Mensaje: La enfermedad ingresada ya está registrada para el paciente actual
        }
    }

    /**
     * Método que agrega una nueva alergia a la carta médica
     * 
     * @param nombreAlergia El nombre de la nueva alergia que será agregada
    */
    public void agregarElergia(String nombreAlergia){
        ArrayList<String> alergias = cartaMedica.getEnfermedades();
        boolean alergiaEncontrada = false;

        for (String alergia : alergias) {
            if (alergia.equals(nombreAlergia)) {
                alergiaEncontrada = true;
                break;
            }
        }

        if (!alergiaEncontrada) {
            alergias.add(nombreAlergia);
            cartaMedica.setAlergias(alergias);
        } else {
            // Mensaje: La alergia ingresada ya está registrada para el paciente actual
        }
    }

    /**
     * Método que agrega una nuevo diagnóstico a la carta médica
     * 
     * @param diagnostico El nuevo objeto Diagnostico que será agregado
    */
    public void agregarExamen(String diagnostico){
        ArrayList<String> diagnosticos = cartaMedica.getExamenes();
        diagnosticos.add(diagnostico);
        cartaMedica.setExamenes(diagnosticos);
    }

    /**
     * Método que agrega un nuevo tratamiento a la carta médica
     * 
     * @param receta El nuevo objeto Receta que será agregado
    */
    // public void agregarTratamiento(Receta receta) {
    //     ArrayList<Receta> tratamientos = cartaMedica.getTratamientos();
    //     tratamientos.add(receta);
    //     cartaMedica.setTratamientos(tratamientos);
    // }

    /**
     * Método que edita a una enfermeda de la carta médica
     * 
     * @param nombreEnfermedad El nombre actual de la enfermedad a editar
     * @param nuevaEnfermedad El nuevo nombre de la enfermedad a editar
    */
    public void editarEnfermedad(String nombreEnfermedad, String nuevaEnfermedad) {
        ArrayList<String> enfermedades = cartaMedica.getEnfermedades();
        boolean encontrada = false;

        for (String enfermedad : enfermedades) {
            if (enfermedad.equals(nombreEnfermedad)) {
                encontrada = true;
                enfermedades.set(enfermedades.indexOf(enfermedad), nuevaEnfermedad);
                break;
            }
        }

        if (!encontrada) {
            // Mensaje: No se encontró la enfermedad a editar
        }
        cartaMedica.setEnfermedades(enfermedades);
    }

    /**
     * Método que edita a una alergia de la carta médica
     * 
     * @param nombreAlergia El nombre actual de la alergia a editar
     * @param nuevaAlergia El nuevo nombre de la alergia a editar
    */
    public void editarAlergia(String nombreAlergia, String nuevaAlergia){
        ArrayList<String> alergias = cartaMedica.getAlergias();
        boolean encontrada = false;

        for (String alergia : alergias) {
            if (alergia.equals(nombreAlergia)) {
                encontrada = true;
                alergias.set(alergias.indexOf(alergia), nuevaAlergia);
                break;
            }
        }

        if (!encontrada) {
            // Mensaje: No se encontró la alergia a editar
        }
        cartaMedica.setAlergias(alergias);
    }

    /**
     * Método que edita a un exámen de la carta médica
     * 
     * @param fecha La fecha en la cuál se redactó el diagnóstico
     * @param medico El médico que redactó el diagnóstico
     * @param nuevoDiagnostico El nuevo objeto Diagnostico
    */
    public void editarExamen(Date fecha, Medico medico, String nuevoDiagnostico){
        
    }

    /**
     * Método que edita a un tratamiento de la carta médica
     * 
     * @param numeroReceta El número de la receta a editar
     * @param nuevaReceta El nuevo objeto Receta
    */
    // public void editarTratamiento(int numeroReceta, Receta nuevaReceta){
    //     ArrayList<Receta> recetas = cartaMedica.getTratamientos();
    //     boolean encontrada = false;

    //     for (Receta receta : recetas) {
    //         if (receta.getNumeroReceta() == numeroReceta) {
    //             int indice = recetas.indexOf(receta);
    //             recetas.set(indice, nuevaReceta);
    //             encontrada = true;
    //             break;
    //         }
    //     }

    //     if (encontrada) {
    //         cartaMedica.setTratamientos(recetas);
    //     } else {
    //         // Mensaje: No se encontró el tratamiento a editar
    //     }
    // }

    /**
     * Método que elimina una enfermedad de la carta médica
     * 
     * @param nombreEnfermedad El nombre de la enfermedad que será eliminada
    */
    public void eliminarEnfermedad(String nombreEnfermedad) {
        ArrayList<String> enfermedades = cartaMedica.getEnfermedades();
        boolean encontrada = false;

        for (String enfermedad : new ArrayList<>(enfermedades)) {
            if (enfermedad.equals(nombreEnfermedad)) {
                encontrada = true;
                enfermedades.remove(enfermedad);
            }
        }

        if (!encontrada) {
            // Mensaje: No se encontró la enfermedad para ser eliminada
        }
        cartaMedica.setEnfermedades(enfermedades);
    }

    /**
     * Método que elimina una alergia de la carta médica
     * 
     * @param nombreAlergia El nombre de la alergia que será eliminada
    */
    public void eliminarAlergia(String nombreAlergia){
        ArrayList<String> alergias = cartaMedica.getAlergias();
        boolean encontrada = false;

        for (String alergia : new ArrayList<>(alergias)) {
            if (alergia.equals(nombreAlergia)) {
                encontrada = true;
                alergias.remove(alergia);
            }
        }

        if (!encontrada) {
            // Mensaje: No se encontró la alergia para ser eliminada
        }
        cartaMedica.setAlergias(alergias);
    }

    /**
     * Método que elimina un exámen de la carta médica
     * 
     * @param diagnosticoEliminar El objeto Diagnostico que será eliminado
    */
    public void eliminarExamen(String diagnosticoEliminar){
        ArrayList<String> diagnosticos = cartaMedica.getExamenes();
        boolean encontrado = false;

        for (String diagnostico : new ArrayList<>(diagnosticos)) {
            if (diagnostico.equals(diagnosticoEliminar)) {
                diagnosticos.remove(diagnostico);
                encontrado = true;
            }
        }

        if (encontrado) {
            cartaMedica.setExamenes(diagnosticos);
        } else {
            // Mensaje: No se encontró el exámen a eliminar
        }
    }

    /**
     * Método que elimina un tratamiento de la carta médica
     * 
     * @param numeroReceta El número de la receta que será eliminada
    */
    // public void eliminarTratamiento(int numeroReceta){
    //     ArrayList<Receta> recetas = cartaMedica.getTratamientos();
    //     boolean encontrada = false;

    //     for (Receta receta : new ArrayList<>(recetas)) {
    //         if (receta.getNumeroReceta() == numeroReceta) {
    //             recetas.remove(receta);
    //             encontrada = true;
    //         }
    //     }

    //     if (encontrada) {
    //         cartaMedica.setTratamientos(recetas);
    //     } else {
    //         // Mensaje: No se encontró el tratamiento a eliminar
    //     }
    // }
}