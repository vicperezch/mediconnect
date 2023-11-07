package com.mediconnect.db;

import com.mediconnect.model.Medico;
import com.mediconnect.model.Paciente;
import com.mediconnect.model.Usuario;
import com.mediconnect.model.Cita;
import com.mediconnect.model.CartaMedica;
import com.mediconnect.model.Receta;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Victor Pérez
 * @version 2.1.0
 * @creationDate 31/10/2023
 * @modificationDate 02/11/2023
 * @description Clase encargada de la persistencia de datos a través de archivos
 *              csv
 */
public class CSV {
    private File archivoUsuarios;
    private File archivoCitas;
    private File archivoCartasMedicas;
    private File archivoRecetas;
    private DateFormat df;
    private String ruta;

    /**
     * @description Constructor encargado de crear la instancia del archivo que
     *              almacenará la información
     */
    public CSV() {
        this.ruta = new File("").getAbsolutePath();
        this.archivoUsuarios = new File(ruta + "/src/com/mediconnect/db/usuarios.csv");
        this.archivoCitas = new File(ruta + "/src/com/mediconnect/db/citas.csv");
        this.archivoCartasMedicas = new File(ruta + "/src/com/mediconnect/db/cartasMedicas.csv");
        this.archivoRecetas = new File(ruta + "/src/com/mediconnect/db/recetas.csv");
        this.df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }

    /**
     * @description Almacena los usuarios contenidos en un ArrayList en un archivo
     *              CSV
     * @param nuevoUsuario EL nuevo objeto Usuario a guardar
     */
    public void guardarUsuarios(Usuario nuevoUsuario) throws IOException {
        crearArchivo("id,rol,correo,nombre,apellido,password,id_carta", archivoUsuarios);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoUsuarios, true));

        // Escribe de acuerdo al rol de cada uno
        if (nuevoUsuario instanceof Medico) {
            escritor.write(nuevoUsuario.getId() + "," + nuevoUsuario.getRol() + "," + nuevoUsuario.getCorreo() + "," +
                    nuevoUsuario.getNombre() + "," + nuevoUsuario.getApellido() + "," + nuevoUsuario.getPassword()
                    + ",N/A");
            escritor.newLine();

        } else {
            escritor.write(nuevoUsuario.getId() + "," + nuevoUsuario.getRol() + "," + nuevoUsuario.getCorreo() + "," +
                    nuevoUsuario.getNombre() + "," + nuevoUsuario.getApellido() + "," + nuevoUsuario.getPassword() + ","
                    +
                    ((Paciente) nuevoUsuario).getIdCartaMedica());
            escritor.newLine();
        }

        escritor.close();
    }

    /**
     * @Description Guarda la información de las citas en un CSV
     * @param cita La cita a guardar
     */
    public void guardarCita(Cita cita) throws IOException {
        crearArchivo("id_medico,id_paciente,fecha,establecimiento", archivoCitas);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCitas, true));

        // Escribe la cita
        escritor.write(cita.getIdMedico() + "," + cita.getIdPaciente() + "," + df.format(cita.getFecha()) +
                "," + cita.getEstablecimiento());

        escritor.newLine();
        escritor.close();
    }

    /**
     * @description Almacena una carta médica en un ArrayList
     * @param carta La carta médica a guardar
     */
    public void guardarCartaMedica(CartaMedica carta) throws IOException {
        crearArchivo("id,enfermedades,alergias,examenes", archivoCartasMedicas);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCartasMedicas, true));

        // Escribe la carta
        escritor.write(carta.getId() + "," + String.join("-", carta.getEnfermedades()) + "," +
                String.join("-", carta.getAlergias()) + "," + String.join("-", carta.getExamenes()));
        escritor.newLine();

        escritor.close();
    }

    /**
     * @description Almacena cada receta contenida en un ArrayList en un archivo CSV
     * @param recetas ArrayList de recetas a guardar
     */
    public void guardarRecetas(ArrayList<Receta> recetas) throws IOException {
        crearArchivo("id_medico,id_paciente,numero_receta,fecha,medicamentos,justificacion,observaciones",
                archivoRecetas);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRecetas, true));

        // Escribe cada receta
        for (Receta receta : recetas) {
            escritor.write(receta.getIdMedico() + "," + receta.getIdPaciente() + "," + receta.getNumeroReceta()
                    + "," + df.format(receta.getFechaEmision()) + "," + String.join("-", receta.getMedicamentos())
                    + "," + receta.getJustificacionReceta() + "," + receta.getObservaciones());
            escritor.newLine();
        }

        escritor.close();
    }

    /**
     * @description Lee los usuarios contenidos en un CSV
     * @return ArrayList con los usuarios
     */
    public ArrayList<Usuario> leerUsuarios() throws IOException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        crearArchivo("id,rol,correo,nombre,apellido,password,id_carta", archivoUsuarios);

        BufferedReader lector = new BufferedReader(new FileReader(archivoUsuarios));
        String linea = lector.readLine();

        // Lee cada línea
        while (linea != null) {
            String[] datos = linea.split(",");

            // Lee de acuerdo al rol de cada uno
            if (datos[1].equals("Medico")) {
                Medico medico = new Medico(datos[3], datos[4], datos[2], datos[5], Integer.parseInt(datos[0]),
                        datos[1]);

                // Añade al médico al ArrayList
                usuarios.add(medico);

            } else if (datos[1].equals("Paciente")) {
                Paciente paciente = new Paciente(datos[3], datos[4], datos[2], datos[5], Integer.parseInt(datos[0]),
                        datos[1], Integer.parseInt(datos[6]));

                // Añade el paciente al ArrayList
                usuarios.add(paciente);
            }

            linea = lector.readLine();
        }

        lector.close();
        return usuarios;
    }

    /**
     * @description lee las citas contenidas en un CSV
     * @return ArrayList con todas las citas
     */
    public ArrayList<Cita> leerCitas() throws IOException, ParseException {
        ArrayList<Cita> citas = new ArrayList<>();
        crearArchivo("id_medico,id_paciente,fecha,establecimiento", archivoCitas);

        BufferedReader lector = new BufferedReader(new FileReader(archivoCitas));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            if (!datos[0].equals("id_medico")) {
                citas.add(
                        new Cita(df.parse(datos[2]), Integer.parseInt(datos[1]), Integer.parseInt(datos[0]), datos[3]));
            }

            linea = lector.readLine();
        }

        lector.close();
        return citas;
    }

    /**
     * @description Lee la información de una carta médica en el CSV
     * @return CartaMedica del paciente
     */
    public ArrayList<CartaMedica> leerCartaMedica() throws IOException {
        ArrayList<CartaMedica> cartas = new ArrayList<>();
        crearArchivo("id,enfermedades,alergias,examenes", archivoCartasMedicas);

        BufferedReader lector = new BufferedReader(new FileReader(archivoCartasMedicas));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            if (!datos[0].equals("id")) {
                CartaMedica carta = new CartaMedica(Integer.parseInt(datos[0]),
                        new ArrayList<>(Arrays.asList(datos[1].split("-"))),
                        new ArrayList<>(Arrays.asList(datos[2].split("-"))),
                        new ArrayList<>(Arrays.asList(datos[3].split("-"))));

                cartas.add(carta);
            }

            linea = lector.readLine();
        }

        lector.close();
        return cartas;
    }

    /**
     * @description Lee las recetas contenidas en un CSV y las convierte en un
     *              ArrayList
     * @return ArrayList con las recetas
     */
    public ArrayList<Receta> leerRecetas() throws IOException, ParseException {
        ArrayList<Receta> recetas = new ArrayList<>();
        crearArchivo("id_medico,id_paciente,numero_receta,fecha,medicamentos,justificacion,observaciones",
                archivoRecetas);

        BufferedReader lector = new BufferedReader(new FileReader(archivoRecetas));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            if (!datos[0].equals("id_medico")) {
                Receta receta = new Receta(Integer.parseInt(datos[2]), df.parse(datos[3]), Integer.parseInt(datos[0]),
                        Integer.parseInt(datos[1]), datos[4], datos[5], datos[6]);

                recetas.add(receta);
            }

            linea = lector.readLine();
        }

        lector.close();
        return recetas;
    }

    /**
     *
     */
    public void crearArchivo(String encabezado, File archivo) throws IOException {
        if (!archivo.exists()) {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));

            escritor.write(encabezado);
            escritor.newLine();
            escritor.close();
        }
    }
}
