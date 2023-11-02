package com.mediconnect.controller;

/**
    * @author Juan Solís
    * @version 1.0.0
    * @creationDate 1/11/2023
    * @modificationDate 1/11/2023
    * Esta clase se encarga de llevar el control de la clase modelo de Usuario
*/

import java.io.IOException;
import java.util.ArrayList;
import com.mediconnect.db.CSV;
import com.mediconnect.model.Medico;
import com.mediconnect.model.Paciente;
import com.mediconnect.model.Usuario;
import java.util.Base64;

public class UsuarioController {
    private CSV csv = new CSV();
    private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

    /**
     * Método que verifica que el correo ingresado por el usuario aún no exista
     * 
     * @param correo El nuevo correo ingresado por el usuario
    */
    private boolean correoExistente(String correo) {
        try {
            listaUsuarios = csv.leerUsuarios();

        } catch (IOException e) {
            System.out.println("Error al leer los datos de usuarios");
        }

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método codifica la contraseña ingresada por el usuario
     * 
     * @param password La contraseña ingresada por el usuario
    */
    public String codificaPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
    
    /**
     * Método registra un nuevo usuario en el programa
     * 
     * @param nombre El nombre del usuario
     * @param apellido El apellido del usuario
     * @param correo El correo del usuario
     * @param password La contraseña del usuario
     * @param rol El rol del usuario (Medico/Paciente)
    */
    public boolean registrarUsuario(String nombre, String apellido, String correo, String password, String rol){
        if (!correoExistente(correo)) {
            password = codificaPassword(password);

            Usuario nuevoUsuario;
            int id = listaUsuarios.size() + 1;

            if ("Medico".equals(rol)) {
                nuevoUsuario = new Medico(nombre, apellido, correo, password, id, rol);
            } else {
                nuevoUsuario = new Paciente(nombre, apellido, correo, password, id, rol, 1);
            }

            try {
                csv.guardarUsuarios(nuevoUsuario);
            } catch (IOException e) {
                System.out.println("Error al guardar los datos");
            }
            return true;
        } else {
            return false;
        }
        
    }
}
