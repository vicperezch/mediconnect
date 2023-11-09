package com.mediconnect.view;

import com.mediconnect.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

/**
 * @author Victor Pérez
 * @version 1.0.0
 * @creationDate 27/10/2023
 * @lastModified 28/10/2023
 * @description Clase encargada de manejar la vista de un médico
 */
public class MedicoGUI {
    private static JFrame myFrame;
    private JPanel pnlMedico;
    private JPanel pnlHeader;
    private JPanel pnlBody;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JPanel pnlOptions;
    private JLabel lblCitas;
    private JLabel lblRecetas;
    private JButton btnPacientes;
    private JLabel lblPacientes;
    private JPanel pnlSubheader;
    private JButton btnCitas;
    private JButton btnRecetas;
    private JButton btnRegresar;

    /**
     * @description Constructor de la clase que contiene todos los listener
     */
    public MedicoGUI(Usuario usuario) {
        btnPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerPacientesGUI verPacientesGUI = new VerPacientesGUI(usuario);
                verPacientesGUI.setVisible(usuario);
                myFrame.dispose();
            }
        });

        btnCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CitasMedicoGUI citasMedicoGUI = new CitasMedicoGUI(usuario);
                citasMedicoGUI.setVisible(usuario);
                myFrame.dispose();
            }
        });

        btnRecetas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecetaMedica recetaMedicaGUI = new RecetaMedica(usuario);
                recetaMedicaGUI.setVisible(usuario);
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
     * @description Metodo que se encargara de cargar la vista cuando sea llamada
     *              desde otra
     */
    public void setVisible(Usuario user) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new MedicoGUI(user).pnlMedico);
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
