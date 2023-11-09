package com.mediconnect.view;

import com.mediconnect.controller.CartaMedicaController;
import com.mediconnect.model.Medico;
import com.mediconnect.model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Diego Flores, Juan Solís
 * @version 1.1.0
 * @creationDate 02 de noviembre de 2023
 * @lastModified 02 de noviembre de 2023
 * @description Clase encargada de manejar la vista de la carta médica de
 *              Paciente
 */

public class CartaMedicaPacienteGUI {
    private static JFrame myFrame;
    private JPanel pnlCartaMedicaPaciente;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JLabel lblSubTitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JButton btnRegresar;
    private JPanel pnlEnfermedades;
    private JPanel pnlAlergias;
    private JLabel pnlExamenes;
    private JTable tblEnfermedades;
    private JTable tblAlergias;
    private JTable tblExamenes;

    private CartaMedicaController cartaMedicaController = new CartaMedicaController();

    /**
     * @description Constructor de la clase CartaMedicaPacienteGUI
     */
    public CartaMedicaPacienteGUI(Paciente usuario, Medico usuarioMedico) {
        String[] col = { "Enfermedades" };
        String[] col2 = { "Alergias" };
        String[] col3 = { "Exámenes" };

        DefaultTableModel modeloTablaEnfermedades = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel modeloTablaAlergias = new DefaultTableModel(col2, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel modeloTablaExamenes = new DefaultTableModel(col3, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblEnfermedades.setModel(modeloTablaEnfermedades);
        tblAlergias.setModel(modeloTablaAlergias);
        tblExamenes.setModel(modeloTablaExamenes);

        ArrayList<String> enfermedades = cartaMedicaController.obtenerEnfermedades(usuario.getIdCartaMedica());
        for (String enfermedad : enfermedades) {
            modeloTablaEnfermedades.addRow(enfermedad.split("-"));
        }

        ArrayList<String> alergias = cartaMedicaController.obtenerAlergias(usuario.getIdCartaMedica());
        for (String alergia : alergias) {
            modeloTablaAlergias.addRow(alergia.split("-"));
        }

        ArrayList<String> examenes = cartaMedicaController.obtenerExamenes(usuario.getIdCartaMedica());
        for (String examen : examenes) {
            modeloTablaExamenes.addRow(examen.split("-"));
        }

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuarioMedico == null) {
                    PacienteGUI menuPaciente = new PacienteGUI(usuario);
                    menuPaciente.setVisible(usuario);
                    myFrame.dispose();
                } else {
                    VerPacientesGUI menuVerPacientes = new VerPacientesGUI(usuarioMedico);
                    menuVerPacientes.setVisible(usuarioMedico);
                    myFrame.dispose();
                }
            }
        });
    }

    /**
     * @description Método que se encarga de cargar la vista cuando esta es llamada
     */
    public void setVisible(Paciente user, Medico userMedico) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new CartaMedicaPacienteGUI(user, userMedico).pnlCartaMedicaPaciente);
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