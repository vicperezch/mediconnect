package com.mediconnect.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Diego Flores
 * @version 2.0.0
 * @creationDate 13 de octubre de 2023
 * @lastModified 02 de noviembre de 2023
 * @description Clase encargada de manejar la vista principal
 */

public class MainGUI {

    //Atributos de la clase
    private static JFrame myFrame;
    private JLabel lblTitle;
    private JLabel lblBienvenida;
    private JLabel lblInstruccion;
    private JPanel pnlBody;
    private JPanel pnlHeader;
    private JPanel pnlMain;
    private JPanel pnlOpciones;
    private JButton btnLogin;
    private JButton btnRegistro;
    private JLabel lblLogo;

    /**
     * @description Constructor de la clase que contiene a los listener
     */
    public MainGUI(){
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI myLogin = new LoginGUI();
                myLogin.setVisible();
                myFrame.dispose();
            }
        });
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterGUI myRegister = new RegisterGUI();
                myRegister.setVisible();
                myFrame.dispose();
            }
        });
    }

    /**
     * @description Metodo main que se encarga de iniciar el programa
     * @param args
     */
    public static void main(String[] args) {
        activate();
    }

    /**
     * @description Metodo que se utiliza para llamar a la vista desde otra
     */
    public static void activate(){
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new MainGUI().pnlMain);
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

    /**
     * @description Metodo que se encargara de cargar la vista cuando sea llamada desde otra
     */
    public void setVisible() {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new MainGUI().pnlMain);
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
