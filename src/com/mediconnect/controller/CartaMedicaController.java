package com.mediconnect.controller;

/**
    * @author Juan Solís
    * @version 1.0.1
    * @creationDate 24/10/2023
    * @modificationDate 02/10/2023
    * Esta clase se encarga de llevar el control de la clase modelo de CartaMedica
*/

import com.mediconnect.model.CartaMedica;
import com.mediconnect.model.Medico;
import com.mediconnect.db.CSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CartaMedicaController {
    private UsuarioController usuarioControlador = new UsuarioController();
    private CSV csv = new CSV();
    private CartaMedica cartaMedica;
    private ArrayList<CartaMedica> listaCartasMedicas = new ArrayList<CartaMedica>();

    /**
     * Constructor de la clase CartaMedicaController.
     *
     */
    public CartaMedicaController(){
        try {
            listaCartasMedicas = csv.leerCartaMedica();

        } catch (IOException e) {
            System.out.println("Error al leer carta médica");
            System.out.println(e);
        }
        this.cartaMedica = new CartaMedica(listaCartasMedicas.size()+5000, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    ArrayList<String> enfermedades = new ArrayList<String>();
    ArrayList<String> alergias = new ArrayList<String>();
    ArrayList<String> diagnosticos = new ArrayList<String>();

    /**
     * Método que agrega una nueva enfermedad a la carta médica
     * 
     * @param nombreEnfermedad El nombre de la nueva enfermedad que será agregada
    */
    public boolean agregarEnfermedad(String nombreEnfermedad){
        for (String enfermedad : enfermedades) {
            if (enfermedad.equals(nombreEnfermedad)) {
                return false;
            }
        }

        enfermedades.add(nombreEnfermedad);
        cartaMedica.setEnfermedades(enfermedades);
        System.out.println(enfermedades);
        return true;
    }

    /**
     * Método que agrega una nueva alergia a la carta médica
     * 
     * @param nombreAlergia El nombre de la nueva alergia que será agregada
    */
    public boolean agregarAlergia(String nombreAlergia){
        for (String alergia : alergias) {
            if (alergia.equals(nombreAlergia)) {
                return false;
            }
        }

        alergias.add(nombreAlergia);
        cartaMedica.setAlergias(alergias);
        System.out.println(alergias);
        return true;
    }

    /**
     * Método que agrega una nuevo diagnóstico a la carta médica
     * 
     * @param diagnostico El nuevo diagnóstico que será agregado
    */
    public boolean agregarExamen(String nombreDiagnostico){
        for (String diagnostico : diagnosticos) {
            if (diagnostico.equals(nombreDiagnostico)) {
                return false;
            }
        }

        diagnosticos.add(nombreDiagnostico);
        cartaMedica.setExamenes(diagnosticos);
        System.out.println(diagnosticos);
        return true;
    }

    /**
     * Método que registra la carta médica de un paciente en el programa
     * 
     * @param nombre El nombre del paciente
     * @param apellido El apellido del paciente
     * @param correo El correo del paciente
     * @param password La contraseña del paciente
     * @param rol El rol de usuario (Paciente)
    */
    public boolean guardarCartaMedica(String nombre, String apellido, String correo, String password, String rol){
        try {
            usuarioControlador.registrarUsuario(nombre, apellido, correo, password, rol, cartaMedica.getId());
            csv.guardarCartaMedica(cartaMedica);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

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
}