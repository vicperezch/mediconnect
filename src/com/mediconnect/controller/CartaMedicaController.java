package com.mediconnect.controller;

/**
    * @author Juan Solís
    * @version 1.1.3
    * @creationDate 24/10/2023
    * @modificationDate 09/11/2023
    * Esta clase se encarga de llevar el control de la clase modelo de CartaMedica
*/

import com.mediconnect.model.CartaMedica;
import com.mediconnect.db.CSV;

import java.io.IOException;
import java.util.ArrayList;

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
     * @return Boolean que confirma si se agregó correctamente 
    */
    public boolean agregarEnfermedad(String nombreEnfermedad){
        for (String enfermedad : enfermedades) {
            if (enfermedad.equals(nombreEnfermedad)) {
                return false;
            }
        }

        enfermedades.add(nombreEnfermedad);
        cartaMedica.setEnfermedades(enfermedades);
        return true;
    }

    /**
     * Método que agrega una nueva alergia a la carta médica
     * 
     * @param nombreAlergia El nombre de la nueva alergia que será agregada
     * @return Boolean que confirma si se agregó correctamente 
    */
    public boolean agregarAlergia(String nombreAlergia){
        for (String alergia : alergias) {
            if (alergia.equals(nombreAlergia)) {
                return false;
            }
        }

        alergias.add(nombreAlergia);
        cartaMedica.setAlergias(alergias);
        return true;
    }

    /**
     * Método que agrega una nuevo diagnóstico a la carta médica
     * 
     * @param nombreDiagnostico El nuevo diagnóstico que será agregado
     * @return Boolean que confirma si se agregó correctamente 
    */
    public boolean agregarExamen(String nombreDiagnostico){
        for (String diagnostico : diagnosticos) {
            if (diagnostico.equals(nombreDiagnostico)) {
                return false;
            }
        }

        diagnosticos.add(nombreDiagnostico);
        cartaMedica.setExamenes(diagnosticos);
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
     * @return Boolean que confirma si se registró correctamente 
    */
    public boolean guardarCartaMedica(String nombre, String apellido, String correo, String password, String rol){
        try {
            if (cartaMedica.getAlergias().size() == 0 || cartaMedica.getEnfermedades().size() == 0 || cartaMedica.getExamenes().size() == 0){
                return false;
            }

            usuarioControlador.registrarUsuario(nombre, apellido, correo, password, rol, cartaMedica.getId());
            csv.guardarCartaMedica(cartaMedica);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    
    /**
     * Método que lista todas las enfermedades de un paciente específico
     * 
     * @param idCarta El id de la carta médica de donde se tomarán los datos
     * @return Un ArrayList de enfermedades
     */
    public ArrayList<String> obtenerEnfermedades(int idCarta) {
        ArrayList<CartaMedica> cartasMedicas;
        ArrayList<String> enfermedades = new ArrayList<>();

        try {
            cartasMedicas = csv.leerCartaMedica();

            for (CartaMedica carta: cartasMedicas) {
                if (carta.getId() == idCarta) {
                    enfermedades.addAll(carta.getEnfermedades());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer la carta médica");

        }

        return enfermedades;
    }

    /**
     * Método que lista todas las alergias de un paciente específico
     * 
     * @param idCarta El id de la carta médica de donde se tomarán los datos
     * @return Un ArrayList de alergias
     */
    public ArrayList<String> obtenerAlergias(int idCarta) {
        ArrayList<CartaMedica> cartasMedicas;
        ArrayList<String> alergias = new ArrayList<>();

        try {
            cartasMedicas = csv.leerCartaMedica();

            for (CartaMedica carta: cartasMedicas) {
                if (carta.getId() == idCarta) {
                    alergias.addAll(carta.getAlergias());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer la carta médica");

        }

        return alergias;
    }

    /**
     * Método que lista todas los exámenes de diagnósticos de un paciente específico
     * 
     * @param idCarta El id de la carta médica de donde se tomarán los datos
     * @return Un ArrayList de exámenes
     */
    public ArrayList<String> obtenerExamenes(int idCarta) {
        ArrayList<CartaMedica> cartasMedicas;
        ArrayList<String> examenes = new ArrayList<>();

        try {
            cartasMedicas = csv.leerCartaMedica();

            for (CartaMedica carta: cartasMedicas) {
                if (carta.getId() == idCarta) {
                    examenes.addAll(carta.getExamenes());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer la carta médica");

        }

        return examenes;
    }
}