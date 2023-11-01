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
 * @version 1.0.0
 * @creationDate 31/10/2023
 * @modificationDate 31/10/2023
 * @description Clase encargada de la persistencia de datos a través de archivos csv
 */
public class CSV {
    private File archivoUsuarios;
    private File archivoCitas;
    private File archivoCartasMedicas;
    private File archivoDiagnosticos;
    private File archivoRecetas;
    private DateFormat df;


    /**
     * @description Constructor encargado de crear la instancia del archivo que almacenará la información
     */
    public CSV() {
        this.archivoUsuarios = new File("usuarios.csv");
        this.archivoCitas = new File("citas.csv");
        this.archivoCartasMedicas = new File("cartasMedicas.csv");
        this.archivoDiagnosticos = new File("diagnosticos.csv");
        this.archivoRecetas = new File("recetas.csv");
        this.df = new SimpleDateFormat("dd/mm/yyyy");
    }


    /**
     * @description Almacena los usuarios contenidos en un ArrayList en un archivo CSV
     * @param listaUsuarios ArrayList de usuarios a almacenar
     */
    public void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoUsuarios));

        // Escribe el encabezado
        escritor.write("id,rol,id_medico,correo,nombre,apellido,password");
        escritor.newLine();

        // Recorre el array de usuarios
        for (Usuario usuario: listaUsuarios) {

            // Escribe de acuerdo al rol de cada uno
            if (usuario instanceof Medico) {
                escritor.write(usuario.getId() + "," + usuario.getRol() + ",N/A," + usuario.getCorreo() + "," +
                        usuario.getNombre() + "," + usuario.getApellido() + "," + usuario.getPassword());
                escritor.newLine();

                // Guarda las citas del médico
                guardarCitas(usuario.getId(), ((Medico) usuario).getCitas());

                // Guarda los pacientes del médico
                ArrayList<Paciente> pacientes = ((Medico) usuario).getPacientes();

                for (Paciente paciente: pacientes) {
                    escritor.write(paciente.getId() + "," + paciente.getRol() + "," + usuario.getId() + "," +
                            paciente.getCorreo() + "," + paciente.getNombre() + "," + paciente.getApellido() + "," +
                            paciente.getPassword());
                    escritor.newLine();

                    // Guarda la carta médica del paciente
                    guardarCartaMedica(paciente.getId(), paciente.getCartaMedica());
                }
            }
        }

        escritor.close();
    }


    /**
     * @Description Guarda la información de las citas en un CSV
     * @param idMedico ID del médico al que le corresponde la cita
     * @param citas ArrayList de las citas a guardar
     */
    public void guardarCitas(int idMedico, ArrayList<Cita> citas) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCitas, true));

        // Escribe el encabezado
        if (!archivoCitas.exists()) {
            escritor.write("fecha,establecimiento,id_medico,id_paciente");
            escritor.newLine();
        }

        // Escribe cada cita
        for (Cita cita: citas) {
            escritor.write(df.format(cita.getFecha()) + "," + cita.getEstablecimiento() + "," +
                    idMedico + "," + cita.getPaciente().getId());
            escritor.newLine();
        }

        escritor.close();
    }


    /**
     * @description Almacena una carta médica en un ArrayList
     * @param idPaciente ID del paciente al que pertenece la carta médica
     * @param carta La carta médica a guardar
     */
    public void guardarCartaMedica(int idPaciente, CartaMedica carta) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoCartasMedicas, true));

        // Escribe el encabezado
        if (!archivoCartasMedicas.exists()) {
            escritor.write("id_paciente,enfermedades,alergias");
            escritor.newLine();
        }

        // Escribe la carta
        escritor.write(idPaciente + "," + String.join("-", carta.getEnfermedades()) + "," +
                String.join("-", carta.getAlergias()));

        escritor.close();

        // Escribe los diagnósticos y recetas de la carta
        ArrayList<Diagnostico> diagnosticos = carta.getExamenes();
        ArrayList<Receta> recetas = carta.getTratamientos();

        guardarDiagnosticos(idPaciente, diagnosticos);
        guardarRecetas(idPaciente, recetas);
    }


    /**
     * @description Almacena cada diagnóstico contenido en un ArrayList
     * @param idPaciente ID del paciente al que pertenece el diagnóstico
     * @param diagnosticos ArrayList de diagnósticos a guardar
     */
    public void guardarDiagnosticos(int idPaciente, ArrayList<Diagnostico> diagnosticos) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoDiagnosticos, true));

        // Escribe el encabezado
        if (!archivoDiagnosticos.exists()) {
            escritor.write("id_paciente,fecha,resultados");
            escritor.newLine();
        }

        // Escribe cada diagnóstico
        for (Diagnostico diagnostico: diagnosticos) {
            escritor.write(idPaciente + "," + df.format(diagnostico.getFecha()) + "," + diagnostico.getResultados());
            escritor.newLine();
        }

        escritor.close();
    }


    /**
     * @description Almacena cada receta contenida en un ArrayList en un archivo CSV
     * @param idPaciente ID del paciente al que pertenece la receta
     * @param recetas ArrayList de recetas a guardar
     */
    public void guardarRecetas(int idPaciente, ArrayList<Receta> recetas) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoRecetas, true));

        // Escribre el encabezado
        if (!archivoRecetas.exists()) {
            escritor.write("id_paciente,numero_receta,fecha,medicamentos,justificacion,observaciones");
            escritor.newLine();
        }

        // Escribe cada receta
        for (Receta receta: recetas) {
            escritor.write(idPaciente + "," + receta.getNumeroReceta() + "," + df.format(receta.getFechaEmision()) + "," +
                    String.join("-", receta.getMedicamentos()) + "," + receta.getJustificacionReceta() + "," +
                    receta.getObservaciones());
            escritor.newLine();
        }

        escritor.close();
    }


    /**
     * @description Lee los usuarios contenidos en un CSV
     * @return ArrayList con los usuarios
     */
    public ArrayList<Usuario> leerUsuarios() throws IOException, ParseException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        BufferedReader lector = new BufferedReader(new FileReader(archivoUsuarios));
        String linea = lector.readLine();

        // Lee cada línea
        while (linea != null) {
            String[] datos = linea.split(",");

            // Lee únicamente los médicos
            if (datos[1].equals("medico")) {
                Medico medico = new Medico(datos[4], datos[5], datos[3], datos[6], Integer.parseInt(datos[0]), datos[1]);
                ArrayList<Paciente> pacientes = leerPacientes(Integer.parseInt(datos[0]), medico);
                ArrayList<Cita> citas = new ArrayList<>();

                // Lee cada paciente del médico
                for (Paciente paciente: pacientes) {
                    citas.addAll(leerCitas(Integer.parseInt(datos[0]), paciente));
                    usuarios.add(paciente);
                }

                medico.setPacientes(pacientes);
                medico.setCitas(citas);

                // Añade al médico al ArrayList
                usuarios.add(medico);
            }

            linea = lector.readLine();
        }

        lector.close();

        return usuarios;
    }


    /**
     * @description lee las citas contenidas en un CSV
     * @param idMedico ID del médico a quien le pertenece la cita
     * @param paciente Paciente de la cita
     */
    public ArrayList<Cita> leerCitas(int idMedico, Paciente paciente) throws IOException, ParseException {
        ArrayList<Cita> citas = new ArrayList<>();
        BufferedReader lector = new BufferedReader(new FileReader(archivoCitas));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            if (Integer.parseInt(datos[2]) == idMedico) {
                citas.add(new Cita(df.parse(datos[0]), paciente, datos[1]));
            }

            linea = lector.readLine();
        }

        lector.close();
        return citas;
    }


    /**
     * @description Lee todos los pacientes que corresponden a un médico
     * @param idMedico ID que le pertenece al médico responsable
     * @return ArrayList con los pacientes
     */
    public ArrayList<Paciente> leerPacientes(int idMedico, Medico medico) throws IOException, ParseException {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        BufferedReader lector = new BufferedReader(new FileReader(archivoUsuarios));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            if (datos[1].equals("paciente") && Integer.parseInt(datos[2]) == idMedico) {
                CartaMedica cartaMedica = leerCartaMedica(Integer.parseInt(datos[0]), medico);

                pacientes.add(new Paciente(datos[4], datos[5], datos[3], datos[6], Integer.parseInt(datos[0]),
                        datos[1], cartaMedica));
            }

            linea = lector.readLine();
        }

        lector.close();
        return pacientes;
    }


    /**
     * @description Lee la información de una carta médica en el CSV
     * @param idPaciente ID del paciente al que le pertenece la carta
     * @param medico Médico responsable del paciente
     * @return CartaMedica del paciente
     */
    public CartaMedica leerCartaMedica(int idPaciente, Medico medico) throws IOException, ParseException {
        BufferedReader lector = new BufferedReader(new FileReader(archivoCartasMedicas));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            if (Integer.parseInt(datos[0]) == idPaciente) {
                ArrayList<Diagnostico> diagnosticos = leerDiagnosticos(idPaciente, medico);
                ArrayList<Receta> recetas = leerRecetas(idPaciente, medico);

                CartaMedica carta = new CartaMedica(new ArrayList<String>(Arrays.asList(datos[1].split("-"))),
                        new ArrayList<String>(Arrays.asList(datos[2].split("-"))), diagnosticos, recetas);

                return carta;
            }

            linea = lector.readLine();
        }

        lector.close();
        return  new CartaMedica(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<Diagnostico>(),
                new ArrayList<Receta>());
    }


    /**
     * @description Lee los diagnósticos contenidos en un CSV
     * @param idPaciente ID del paciente al que le pertenece el diagnóstico
     * @param medico Médico del paciente
     * @return ArrayList de diagnósticos del paciente
     */
    public ArrayList<Diagnostico> leerDiagnosticos(int idPaciente, Medico medico) throws IOException, ParseException {
        ArrayList<Diagnostico> diagnosticos = new ArrayList<>();
        BufferedReader lector = new BufferedReader(new FileReader(archivoDiagnosticos));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            // Revisa que el id coincide con el que se está buscando
            if (Integer.parseInt(datos[0]) == idPaciente) {
                diagnosticos.add(new Diagnostico(datos[2], medico, df.parse(datos[1])));
            }

            linea = lector.readLine();
        }

        lector.close();
        return diagnosticos;
    }


    /**
     * @description Lee las recetas contenidas en un CSV y las convierte en un ArrayList
     * @param idPaciente ID del paciente de quien se buscan las recetas
     * @param medico Médico que prescribió la receta
     * @return ArrayList con las recetas
     */
    public ArrayList<Receta> leerRecetas(int idPaciente, Medico medico) throws IOException, ParseException {
        ArrayList<Receta> recetas = new ArrayList<>();
        BufferedReader lector = new BufferedReader(new FileReader(archivoRecetas));
        String linea = lector.readLine();

        while (linea != null) {
            String[] datos = linea.split(",");

            if (Integer.parseInt(datos[0]) == idPaciente) {
                recetas.add(new Receta(Integer.parseInt(datos[1]), df.parse(datos[2]), medico,
                        new ArrayList<String>(Arrays.asList(datos[3].split("-"))), datos[4], datos[5]));
            }

            linea = lector.readLine();
        }

        lector.close();
        return recetas;
    }
}
