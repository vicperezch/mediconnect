package com.mediconnect.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nils Muralles
 * @version 1.0
 * @creationDate 28 de octubre de 2023
 * @lastModified 28 de octubre de 2023
 * @description Clase encargada de manejar la vista de Paciente
 */
public class PacienteGUI {
    private static JFrame myFrame;
    private JPanel pnlPaciente;
    private JPanel pnlHeader;
    private JLabel lblTittle;
    private JPanel pnlSubheader;
    private JLabel lblSubtittle;
    private JPanel pnlBody;
    private JPanel pnlOptions;
    private JButton btnCartaMedica;
    private JButton btnProximasCitas;
    private JButton btnMedicinas;
    private JLabel lblCartaMedica;
    private JLabel lblProximasCitas;
    private JLabel lblMedicinas;

    /**
     * @description Constructor de la clase Paciente
     */
    public PacienteGUI() {
        btnCartaMedica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnProximasCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnMedicinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * @description Método que se encarga de cargar la vista cuando esta es llamada
     */
    public void setVisible() {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new PacienteGUI().pnlPaciente);
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
