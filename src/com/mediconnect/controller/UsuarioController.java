package com.mediconnect.controller;

/**
    * @author Juan Solís
    * @version 1.0.1
    * @creationDate 01/11/2023
    * @modificationDate 02/11/2023
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
    public boolean correoExistente(String correo) {
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
     * Método que decodifica la contraseña ingresada por el usuario
     * 
     * @param password La contraseña ingresada por el usuario
     */
    public String decodificarPassword(String password) {
        byte[] decodedPassword = Base64.getDecoder().decode(password);
        return new String(decodedPassword);
    }

    /**
     * Método registra un nuevo usuario en el programa
     * 
     * @param nombre   El nombre del usuario
     * @param apellido El apellido del usuario
     * @param correo   El correo del usuario
     * @param password La contraseña del usuario
     * @param rol      El rol del usuario (Medico/Paciente)
     */
    public boolean registrarUsuario(String nombre, String apellido, String correo, String password, String rol, int idCartaMedica) {
        if (!correoExistente(correo)) {
            password = codificaPassword(password);

            Usuario nuevoUsuario;
            int id = listaUsuarios.size() + 1;

            if ("Medico".equals(rol)) {
                nuevoUsuario = new Medico(nombre, apellido, correo, password, id, rol);
            } else {
                nuevoUsuario = new Paciente(nombre, apellido, correo, password, id, rol, idCartaMedica);
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

    /**
     * Método que ingresa a un usuario ya creado
     * 
     * @param correo   Correo del usuario
     * @param password Contraseña del usuario
     */
    public boolean login(String correo, char[] password) {

        try {
            listaUsuarios = csv.leerUsuarios();

        } catch (Exception e) {
            System.out.println("Error al leer los datos");
        }

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(correo)
                    && decodificarPassword(usuario.getPassword()).equals(new String(password))) {
                return true;
            }
        }
        return false;
    }

    public Usuario usuarioEnLogin(String correo) {
        Usuario usuarioEnLogin = null;

        try {
            listaUsuarios = csv.leerUsuarios();

        } catch (Exception e) {
            System.out.println("Error al leer los datos");
        }

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(correo)) {
                usuarioEnLogin = usuario;
                return usuario;
            }
        }

        return usuarioEnLogin;
    }
}
