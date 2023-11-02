package com.mediconnect.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartaMedicaGUI {
    private JPanel pnlCartaMedica;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JLabel lblUserIcon;
    private JButton btnTratamientos;
    private JTextField txtTratamientos;
    private JPanel pnlTratamientos;
    private JPanel pnlEnfermedades;
    private JButton btnEnfermedades;
    private JTextField txtEnfermedades;
    private JTextField txtAlergias;
    private JPanel pnlAlergias;
    private JLabel lblTratamientos;
    private JLabel lblEnfermedades;
    private JLabel lblAlergias;
    private JButton btnAlergias;
    private JLabel lblExamenes;
    private JPanel pnlExamenes;
    private JTextField txtExamenes;
    private JButton btnExamenes;
    private JLabel lblSubtitle;

    public CartaMedicaGUI() {
        btnEnfermedades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnAlergias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnExamenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnTratamientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
