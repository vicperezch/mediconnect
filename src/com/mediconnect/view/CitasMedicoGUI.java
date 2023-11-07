package com.mediconnect.view;

import com.mediconnect.controller.CitaController;
import com.mediconnect.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class CitasMedicoGUI {
    private static JFrame myFrame;
    private JPanel pnlCitasMedico;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JPanel pnlVerCitas;
    private JTable tblCitas;
    private JLabel lblTituloTabla;
    private JLabel lblAgregaCitas;
    private JPanel pnlAgregarCitas;
    private JTextField txtEstablecimiento;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JButton btnRegresar;
    private JComboBox cmbPaciente;
    private JButton btnAgregarCita;
    private CitaController citaController = new CitaController();

    public CitasMedicoGUI(Usuario usuario) {
        String[] col = {"Fecha", "Establecimiento"};
        DefaultTableModel modeloTabla = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        cmbPaciente.setModel(new DefaultComboBoxModel<String>(citaController.obtenerPacientes().toArray(new String[0])));
        tblCitas.setModel(modeloTabla);

        ArrayList<String> citas = citaController.obtenerCitas(usuario.getId());
        for (String cita: citas) {
            modeloTabla.addRow(cita.split("-"));
        }

        btnAgregarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String establecimiento = txtEstablecimiento.getText();
                String fecha = txtFecha.getText();
                String hora = txtHora.getText();
                String paciente = Objects.requireNonNull(cmbPaciente.getSelectedItem()).toString();

                boolean citaExitosa = citaController.agregarCita(usuario, paciente, establecimiento, fecha, hora);

                if (citaExitosa) {
                    JOptionPane.showMessageDialog(myFrame, "La cita fue agregada correctamente", "Éxito",JOptionPane.INFORMATION_MESSAGE);
                    txtEstablecimiento.setText("");
                    txtFecha.setText("");
                    txtHora.setText("");

                    modeloTabla.addRow(citaController.obtenerUltimaCita(usuario.getId()).split("-"));

                } else {
                    JOptionPane.showMessageDialog(myFrame, "Ocurrió un error al agregar la cita", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicoGUI medicoGUI = new MedicoGUI(usuario);
                medicoGUI.setVisible(usuario);
                myFrame.dispose();
            }
        });
    }

    /**
     * @description Metodo que se encargara de cargar la vista cuando sea llamada desde otra
     */
    public void setVisible(Usuario user) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new CitasMedicoGUI(user).pnlCitasMedico);
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
