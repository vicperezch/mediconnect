package com.mediconnect.view;

import com.mediconnect.controller.UsuarioController;
import com.mediconnect.model.Medico;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * @author Diego Flores
 * @version 1.0
 * @creationDate 26 de octubre de 2023
 * @lastModified 26 de octubre de 2023
 * @description Clase encargada de manejar la vista de login
 */
public class LoginGUI {

    // Atributos de la clase
    private static JFrame myFrame;
    private JPanel pnlLogin;
    private JPanel pnlHeader;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton btnLogin;
    private JButton btnRegistrarse;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JButton btnRegresar;
    UsuarioController usuarioController = new UsuarioController();

    /**
     * @description Constructor de la clase que contiene todos los listener
     */
    public LoginGUI() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui se realizara la verificacion del login
            }
        });
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterGUI myRegister = new RegisterGUI();
                myRegister.setVisible();
                myFrame.dispose();
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.activate();
                myFrame.dispose();
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (usuarioController.correoExistente(textField1.getText())) {
                    if (usuarioController.login(textField1.getText(), passwordField1.getPassword())) {
                        if (usuarioController.usuarioEnLogin(textField1.getText()) instanceof Medico) {
                            MedicoGUI medicoGUI = new MedicoGUI();
                            medicoGUI.setVisible();
                            myFrame.dispose();

                        } else {
                            PacienteGUI pacienteGUI = new PacienteGUI();
                            pacienteGUI.setVisible();
                            myFrame.dispose();
                        }

                    } else {
                        JOptionPane.showMessageDialog(myFrame, "Correo o contraseña no coinciden", "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(myFrame, "El correo no se encuentra registrado", "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     * @description Metodo que se encargara de cargar la vista cuando sea llamada
     *              desde otra
     */
    public void setVisible() {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new LoginGUI().pnlLogin);
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
