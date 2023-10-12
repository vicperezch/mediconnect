package com.mediconnect.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
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

    public MainGUI(){
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui debe ir la referencia al JFrame de la vista de Login
                JOptionPane.showMessageDialog(null, "Aun no puedes logearte", "AVISO", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui debe ir la referencia al JFrame de la vista de registrarse
                JOptionPane.showMessageDialog(null, "Aun no puedes registrarte", "AVISO", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        activate();
    }

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



}
