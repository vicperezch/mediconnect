package com.mediconnect.view;

import com.mediconnect.controller.CitaController;
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
 * @description Clase encargada de manejar la vista de las citas de Paciente
 */

public class CitasPacienteGUI {
    private static JFrame myFrame;
    private JPanel pnlCitasPaciente;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JPanel pnlBody;
    private JButton btnRegresar;
    private JTable tblCitas;
    private JPanel pnlForm;

    private CitaController citaController = new CitaController();

    /**
     * @description Constructor de la clase CitasPacienteGUI
     */
    public CitasPacienteGUI(Paciente usuario) {
        String[] col = {"Fecha", "Establecimiento"};
        DefaultTableModel modeloTablaCitas = new DefaultTableModel(col, 0);
        tblCitas.setModel(modeloTablaCitas);

        ArrayList<String> citas = citaController.obtenerCitasPaciente(usuario.getId());
        for (String cita: citas) {
            modeloTablaCitas.addRow(cita.split("-"));
        }

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacienteGUI menuPaciente = new PacienteGUI(usuario);
                menuPaciente.setVisible(usuario);
                myFrame.dispose();
            }
        });
    }

    /**
     * @description Método que se encarga de cargar la vista cuando esta es llamada
     */
    public void setVisible(Paciente user) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new CitasPacienteGUI(user).pnlCitasPaciente);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        Dimension tamanioPantalla= Toolkit.getDefaultToolkit().getScreenSize();
        Dimension tamanioVentana = myFrame.getSize();
        if (tamanioVentana.height > tamanioPantalla.height)
            tamanioVentana.height = tamanioPantalla.height;
        if (tamanioVentana.width > tamanioPantalla.width)
            tamanioVentana.width = tamanioPantalla.width;
        myFrame.setLocation((tamanioPantalla.width - tamanioVentana.width) / 2, (tamanioPantalla.height - tamanioVentana.height) / 2);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }
}
