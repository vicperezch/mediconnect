package com.mediconnect.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mediconnect.controller.CartaMedicaController;

/**
 * @author Diego Flores, Juan Solís
 * @version 1.5.1
 * @creationDate 02 de octubre de 2023
 * @lastModified 02 de noviembre de 2023
 * @description Clase encargada de manejar la vista para registrar la carta médica de un usuario paciente
 */

public class CartaMedicaGUI {
    private static JFrame myFrame;
    private JPanel pnlCartaMedica;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JLabel lblUserIcon;
    private JButton btnTratamientos;
    private JPanel pnlEnfermedades;
    private JButton btnEnfermedades;
    private JTextField txtEnfermedades;
    private JTextField txtAlergias;
    private JPanel pnlAlergias;
    private JLabel lblEnfermedades;
    private JLabel lblAlergias;
    private JButton btnAlergias;
    private JLabel lblExamenes;
    private JPanel pnlExamenes;
    private JTextField txtExamenes;
    private JButton btnExamenes;
    private JLabel lblSubtitle;
    private JButton btnFinalizar;

    CartaMedicaController cartaMedicaController = new CartaMedicaController();

    /**
     * @description Constructor de la clase que contiene a los listener
     *
     * @param nombre El nombre del paciente
     * @param apellido El apellido del paciente
     * @param correo El correo del paciente
     * @param password La contraseña del paciente
     * @param rol El rol de usuario (Paciente)
     */
    public CartaMedicaGUI(String nombre, String apellido, String correo, String password, String rol) {
        btnEnfermedades.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String enfermedad = txtEnfermedades.getText();

                boolean enfermedadExitosa = cartaMedicaController.agregarEnfermedad(enfermedad);

                if (enfermedadExitosa) {
                    JOptionPane.showMessageDialog(myFrame, "La enfermedad fue registrada correctamente", "Éxito",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(myFrame, "La enfermedad ya se encuentra registrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
                txtEnfermedades.setText("");
            }
        });
        btnAlergias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alergia = txtAlergias.getText();

                boolean alergiaExitosa = cartaMedicaController.agregarAlergia(alergia);

                if (alergiaExitosa) {
                    JOptionPane.showMessageDialog(myFrame, "La alergia fue registrada correctamente", "Éxito",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(myFrame, "La alergia ya se encuentra registrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
                txtAlergias.setText("");
            }
        });
        btnExamenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String examen = txtExamenes.getText();

                boolean examenExitoso = cartaMedicaController.agregarExamen(examen);

                if (examenExitoso) {
                    JOptionPane.showMessageDialog(myFrame, "El exámen fue registrado correctamente", "Éxito",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(myFrame, "La exámen ya se encuentra registrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
                txtExamenes.setText("");
            }
        });
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean cartaExitosa = cartaMedicaController.guardarCartaMedica(nombre, apellido, correo, password, rol);

                if (cartaExitosa) {
                    JOptionPane.showMessageDialog(myFrame, "El paciente fue registrado correctamente", "Éxito",JOptionPane.INFORMATION_MESSAGE);
                    LoginGUI myLogin = new LoginGUI();
                    myLogin.setVisible();
                    myFrame.dispose();

                } else {
                    JOptionPane.showMessageDialog(myFrame, "Ocurrió un error al registrar el paciente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * @description Metodo que se encargara de cargar la vista cuando sea llamada desde otra
     */
    public void setVisible(String nombre, String apellido, String correo, String password, String rol) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new CartaMedicaGUI(nombre, apellido, correo, password, rol).pnlCartaMedica);
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
