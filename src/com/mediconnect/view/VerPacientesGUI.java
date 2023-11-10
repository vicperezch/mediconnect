package com.mediconnect.view;

import com.mediconnect.controller.CitaController;
import com.mediconnect.controller.UsuarioController;
import com.mediconnect.db.CSV;
import com.mediconnect.model.Usuario;
import com.mediconnect.model.Medico;
import com.mediconnect.model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author Diego Flores, Nils Muralles
 * @version 1.1.0
 * @creationDate 10 de noviembre de 2023
 * @lastModified 11 de noviembre de 2023
 * @description Clase encargada de manejar la vista ver pacientes para el médico
 */
public class VerPacientesGUI {
    private static JFrame myFrame;
    private JPanel pnlMisPacientes;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JPanel pnlInfo;
    private JPanel pnlBotones;
    private JPanel pnlMedicamentos;
    private JLabel lblVerPacientes;
    private JComboBox cmbPaciente;
    private JTable tblVerPacientes;
    private JButton btnRegresar;
    private JLabel lblCartaMedica;
    private JButton btnCartaMedica;
    private JButton btnProximasCitas;
    private JButton btnMedicinas;
    CitaController citaController = new CitaController();
    UsuarioController usuarioController = new UsuarioController();

    /**
     * @description Constructor de la clase CartaMedicaPaciente
     * @param usuarioMedico El usuario médico que actualmente está con sesión iniciada
     */
    public VerPacientesGUI(Usuario usuarioMedico) {

        cmbPaciente
                .setModel(new DefaultComboBoxModel<String>(citaController.obtenerPacientes().toArray(new String[0])));

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicoGUI myMedic = new MedicoGUI(usuarioMedico);
                myMedic.setVisible(usuarioMedico);
                myFrame.dispose();
            }
        });
        btnCartaMedica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object selectedPaciente = cmbPaciente.getSelectedItem();
                    if (selectedPaciente != null) {
                        String usuarioSeleccionado = selectedPaciente.toString();
                        String[] datosUsuario = usuarioSeleccionado.split(" ");
                        Paciente pacienteSeleccionado = (Paciente) usuarioController.usuarioPorNombre(datosUsuario[0],
                                datosUsuario[1]);
                        CartaMedicaPacienteGUI cartaMedica = new CartaMedicaPacienteGUI(pacienteSeleccionado,
                                (Medico) usuarioMedico);
                        cartaMedica.setVisible(pacienteSeleccionado, (Medico) usuarioMedico);
                        myFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "No hay pacientes disponibles para seleccionar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(myFrame, "Debe asegurarse de que ya existan pacientes registrados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnProximasCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object selectedPaciente = cmbPaciente.getSelectedItem();
                    if (selectedPaciente != null) {
                        String usuarioSeleccionado = selectedPaciente.toString();
                        String[] datosUsuario = usuarioSeleccionado.split(" ");
                        Paciente pacienteSeleccionado = (Paciente) usuarioController.usuarioPorNombre(datosUsuario[0],
                                datosUsuario[1]);
                        CitasPacienteGUI citasPaciente = new CitasPacienteGUI(pacienteSeleccionado,
                                (Medico) usuarioMedico);
                        citasPaciente.setVisible(pacienteSeleccionado, (Medico) usuarioMedico);
                        myFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "No hay pacientes disponibles para seleccionar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(myFrame, "Debe asegurarse de que ya existan pacientes registrados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnMedicinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object selectedPaciente = cmbPaciente.getSelectedItem();
                    if (selectedPaciente != null) {
                        String usuarioSeleccionado = selectedPaciente.toString();
                        String[] datosUsuario = usuarioSeleccionado.split(" ");
                        Paciente pacienteSeleccionado = (Paciente) usuarioController.usuarioPorNombre(datosUsuario[0],
                                datosUsuario[1]);
                        RecetasMedicasPaciente recetaPaciente = new RecetasMedicasPaciente(pacienteSeleccionado,
                                (Medico) usuarioMedico);
                        recetaPaciente.setVisible(pacienteSeleccionado, (Medico) usuarioMedico);
                        myFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "No hay pacientes disponibles para seleccionar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(myFrame, "Debe asegurarse de que ya existan pacientes registrados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * @description Método que se encarga de cargar la vista cuando esta es llamada
     * @param user El usuario médico que actualmente está con sesión iniciada
     */
    public void setVisible(Usuario user) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new VerPacientesGUI(user).pnlMisPacientes);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        Dimension tamanioPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension tamanioVentana = myFrame.getSize();
        if (tamanioVentana.height > tamanioPantalla.height)
            tamanioVentana.height = tamanioPantalla.height;
        if (tamanioVentana.width > tamanioPantalla.width)
            tamanioVentana.width = tamanioPantalla.width;
        myFrame.setLocation((tamanioPantalla.width - tamanioVentana.width) / 2,
                (tamanioPantalla.height - tamanioVentana.height) / 2);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }
}
