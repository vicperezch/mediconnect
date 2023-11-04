package com.mediconnect.view;

import com.mediconnect.model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nils Muralles, Juan Solís
 * @version 1.1.0
 * @creationDate 28 de octubre de 2023
 * @lastModified 02 de noviembre de 2023
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
    private JButton btnRegresar;

    /**
     * @description Constructor de la clase Paciente
     */
    public PacienteGUI(Paciente usuario) {
        btnCartaMedica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartaMedicaPacienteGUI myCartaMedica = new CartaMedicaPacienteGUI(usuario);
                myCartaMedica.setVisible(usuario);
                myFrame.dispose();
            }
        });
        btnProximasCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CitasPacienteGUI myCitas = new CitasPacienteGUI(usuario);
                myCitas.setVisible(usuario);
                myFrame.dispose();
            }
        });
        btnMedicinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecetasMedicasPaciente myRecetas = new RecetasMedicasPaciente(usuario);
                myRecetas.setVisible(usuario);
                myFrame.dispose();
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI myLogin = new LoginGUI();
                myLogin.setVisible();
                myFrame.dispose();
            }
        });
    }

    /**
     * @description Método que se encarga de cargar la vista cuando esta es llamada
     */
    public void setVisible(Paciente user) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new PacienteGUI(user).pnlPaciente);
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
