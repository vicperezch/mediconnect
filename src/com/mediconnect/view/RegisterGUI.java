package com.mediconnect.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mediconnect.controller.UsuarioController;

/**
 * @author Juan Solís
 * @version 1.5
 * @creationDate 26 de octubre de 2023
 * @lastModified 1 de noviembre de 2023
 * @description Clase encargada de manejar la vista de registro de los usuarios
 */

public class RegisterGUI {
    // Atributos de la clase
    private static JFrame myFrame;
    private JPanel pnlRegister;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton btnCancelar;
    private JButton btnRegistrarse;
    private JRadioButton medicoRadioButton;
    private JRadioButton pacienteRadioButton;
    private JTextField apellidoField;
    private JTextField nombreField;
    UsuarioController usuarioController = new UsuarioController();

    /**
     * @description Constructor de la clase que contiene a los listener
     */
    public RegisterGUI(){

        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(medicoRadioButton);
        medicoRadioButton.setSelected(true);
        radioButtons.add(pacienteRadioButton);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI myMain = new MainGUI();
                myMain.setVisible();
                myFrame.dispose();
            }
        });

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI myLogin = new LoginGUI();
                myLogin.setVisible();
                myFrame.dispose();
            }
        });

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String correo = emailField.getText();
                String password = new String(passwordField.getPassword());
                String rol = medicoRadioButton.isSelected()?"Medico" : "Paciente";

                if(nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(myFrame, "Debe ingresar todos los datos solicitados", "Advertencia", JOptionPane.WARNING_MESSAGE);

                } else {
                    boolean registroExitoso = usuarioController.registrarUsuario(nombre, apellido, correo, password, rol);

                    if (registroExitoso) {
                        JOptionPane.showMessageDialog(myFrame, "El usuario fue registrado exitosamente", "Éxito",JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(myFrame, "El correo ya se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    /**
     * @description Metodo que se encargara de cargar la vista cuando sea llamada desde otra
     */
    public void setVisible() {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new RegisterGUI().pnlRegister);
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
