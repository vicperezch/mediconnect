package com.mediconnect.controller;

/**
    * @author Juan Solís
    * @version 1.0.1
    * @creationDate 01/11/2023
    * @modificationDate 07/11/2023
    * Esta clase se encarga de llevar el control de la clase modelo de Usuario
*/

import java.io.IOException;
import java.util.ArrayList;
import com.mediconnect.db.CSV;
import com.mediconnect.model.Medico;
import com.mediconnect.model.Paciente;
import com.mediconnect.model.Usuario;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioController {
    private CSV csv = new CSV();
    private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

    /**
     * Método que verifica que el correo ingresado por el usuario sea válido
     * 
     * @param correo El nuevo correo ingresado por el usuario
     *               y
     * @return Si el correo es válido o no lo es
     */
    public boolean formatoCorreoCorrecto(String correo) {
        Pattern pattern = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que verifica que el correo ingresado por el usuario aún no exista
     * 
     * @param correo El nuevo correo ingresado por el usuario
     * 
     * @return Si el correo ya existe o aún no
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
     * 
     * @return La contraseña codificada
     */
    public String codificaPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    /**
     * Método que decodifica la contraseña ingresada por el usuario
     * 
     * @param password La contraseña ingresada por el usuario
     * 
     * @return la contraseña decodificada
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
     * 
     * @return Si el registro del usuario se llevó a cabo correctamente
     */
    public boolean registrarUsuario(String nombre, String apellido, String correo, String password, String rol,
            int idCartaMedica) {

        if (formatoCorreoCorrecto(correo)) {
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
        } else {
            return false;
        }
    }

    /**
     * Método que ingresa al programa a un usuario ya creado
     * 
     * @param correo   Correo del usuario
     * @param password Contraseña del usuario
     * 
     * @return Si el inició de sesión se llevó a cabo correctamente
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

    /**
     * Método que se encarga de obtener al usuario que acaba de iniciar sesión para
     * mostrarle opciones especificas
     * 
     * @param correo El correo del usuario
     * 
     * @return El usuario que inició sesión
     */
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

    public Usuario usuarioPorNombre(String nombre, String apellido) {
        Usuario usuario = null;

        try {
            listaUsuarios = csv.leerUsuarios();
        } catch (Exception e) {
            System.out.println("Error al leer los datos");
        }

        for (Usuario usuarioActual : listaUsuarios) {
            if (usuarioActual.getNombre().equals(nombre) && usuarioActual.getApellido().equals(apellido)) {
                usuario = usuarioActual;
                return usuario;
            }
        }

        return usuario;
    }
}
