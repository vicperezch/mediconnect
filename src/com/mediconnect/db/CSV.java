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
 * @version 2.0.0
 * @creationDate 31/10/2023
 * @modificationDate 01/11/2023
 * @description Clase encargada de la persistencia de datos a través de archivos csv
 */
public class CSV {
    private File archivoUsuarios;
    private File archivoCitas;
    private File archivoCartasMedicas;
    private File archivoRecetas;
    private DateFormat df;


    /**
     * @description Constructor encargado de crear la instancia del archivo que almacenará la información
     */
    public CSV() {
        this.archivoUsuarios = new File("usuarios.csv");
        this.archivoCitas = new File("citas.csv");
        this.archivoCartasMedicas = new File("cartasMedicas.csv");
        this.archivoRecetas = new File("recetas.csv");
        this.df = new SimpleDateFormat("dd/mm/yyyy");
    }


    /**
     * @description Almacena los usuarios contenidos en un ArrayList en un archivo CSV
     * @param listaUsuarios ArrayList de usuarios a almacenar
     */
    public void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoUsuarios, true));

        if (!archivoUsuarios.exists()) {
            // Escribe el encabezado
            escritor.write("id,rol,correo,nombre,apellido,password,id_carta");
            escritor.newLine();
        }

        // Recorre el array de usuarios
        for (Usuario usuario: listaUsuarios) {

            // Escribe de acuerdo al rol de cada uno
            if (usuario instanceof Medico) {
                escritor.write(usuario.getId() + "," + usuario.getRol() + "," + usuario.getCorreo() + "," +
                        usuario.getNombre() + "," + usuario.getApellido() + "," + usuario.getPassword() + ",N/A");
                escritor.newLine();

            } else if (usuario instanceof Paciente) {
                escritor.write(usuario.getId() + "," + usuario.getRol() + "," + usuario.getCorreo() + "," +
                        usuario.getNombre() + "," + usuario.getApellido() + "," + usuario.getPassword() + "," +
                        ((Paciente) usuario).getIdCartaMedica());
                escritor.newLine();
            }
        }

        escritor.close();
    }


    /**
     * @Description Guarda la información de las citas en un CSV
     * @param citas ArrayList de las citas a guardar
     */
    public void guardarCitas(ArrayList<Cita> citas) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCitas, true));

        // Escribe el encabezado
        if (!archivoCitas.exists()) {
            escritor.write("id_medico,id_paciente,fecha,establecimiento");
            escritor.newLine();
        }

        // Escribe cada cita
        for (Cita cita: citas) {
            escritor.write(cita.getIdMedico() + "," + cita.getIdPaciente() + "," + df.format(cita.getFecha()) +
                    "," + cita.getEstablecimiento());
            escritor.newLine();
        }

        escritor.close();
    }


    /**
     * @description Almacena una carta médica en un ArrayList
     * @param carta La carta médica a guardar
     */
    public void guardarCartaMedica(CartaMedica carta) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCartasMedicas, true));

        // Escribe el encabezado
        if (!archivoCartasMedicas.exists()) {
            escritor.write("id,enfermedades,alergias,examenes");
            escritor.newLine();
        }

        // Escribe la carta
        escritor.write(carta.getId() + "," + String.join("-", carta.getEnfermedades()) + "," +
                String.join("-", carta.getAlergias()) + "," + String.join("-", carta.getExamenes()));

        escritor.close();
    }


    /**
     * @description Almacena cada receta contenida en un ArrayList en un archivo CSV
     * @param recetas ArrayList de recetas a guardar
     */
    public void guardarRecetas(ArrayList<Receta> recetas) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRecetas, true));

        // Escribre el encabezado
        if (!archivoRecetas.exists()) {
            escritor.write("id_medico,id_paciente,numero_receta,fecha,medicamentos,justificacion,observaciones");
            escritor.newLine();
        }

        // Escribe cada receta
        for (Receta receta: recetas) {
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
        BufferedReader lector = new BufferedReader(new FileReader(archivoUsuarios));
        String linea = lector.readLine();

        // Lee cada línea
        while (linea != null) {
            String[] datos = linea.split(",");

            // Lee de acuerdo al rol de cada uno
            if (datos[1].equals("Médico")) {
                Medico medico = new Medico(datos[3], datos[4], datos[2], datos[5], Integer.parseInt(datos[0]), datos[1]);

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
        BufferedReader lector = new BufferedReader(new FileReader(archivoCitas));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            citas.add(new Cita(df.parse(datos[2]), Integer.parseInt(datos[1]), Integer.parseInt(datos[0]), datos[3]));

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
        BufferedReader lector = new BufferedReader(new FileReader(archivoCartasMedicas));
        ArrayList<CartaMedica> cartas = new ArrayList<>();
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            CartaMedica carta = new CartaMedica(Integer.parseInt(datos[0]), new ArrayList<>(Arrays.asList(datos[1].split("-"))),
                    new ArrayList<>(Arrays.asList(datos[2].split("-"))), new ArrayList<>(Arrays.asList(datos[3].split("-"))));

            cartas.add(carta);

            linea = lector.readLine();
        }

        lector.close();
        return cartas;
    }


    /**
     * @description Lee las recetas contenidas en un CSV y las convierte en un ArrayList
     * @return ArrayList con las recetas
     */
    public ArrayList<Receta> leerRecetas() throws IOException, ParseException {
        ArrayList<Receta> recetas = new ArrayList<>();
        BufferedReader lector = new BufferedReader(new FileReader(archivoRecetas));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            Receta receta = new Receta(Integer.parseInt(datos[2]), df.parse(datos[3]), Integer.parseInt(datos[0]),
                    Integer.parseInt(datos[1]), new ArrayList<>(Arrays.asList(datos[4].split("-"))), datos[5], datos[6]);

            recetas.add(receta);
            linea = lector.readLine();
        }

        lector.close();
        return recetas;
    }
}
