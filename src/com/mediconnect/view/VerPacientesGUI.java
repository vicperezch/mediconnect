package com.mediconnect.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerPacientesGUI {
    private JPanel pnlMisPacientes;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JPanel pnlInfo;
    private JPanel pnlBotones;
    private JPanel pnlMedicamentos;
    private JLabel lblVerPacientes;
    private JComboBox cmbPaciente;
    private JTable tblVerPacientes;
    private JButton btnRegresar;
    private JLabel lblCartaMedica;
    private JButton btnCartaMedica;
    private JButton btnProximasCitas;
    private JButton btnMedicinas;

    public VerPacientesGUI() {
        String[] col = {"Nombre","Carta Médica", "Citas","Medicamentos"};
        DefaultTableModel modeloTabla = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblVerPacientes.setModel(modeloTabla);
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
