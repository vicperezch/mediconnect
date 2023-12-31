package com.mediconnect.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import com.mediconnect.controller.RecetaMedicaController;
import com.mediconnect.controller.UsuarioController;
import com.mediconnect.model.Paciente;
import com.mediconnect.model.Usuario;

/**
 * @author Diego Flores, Nils Muralles
 * @version 1.1.0
 * @creationDate 02 de noviembre de 2023
 * @lastModified 11 de noviembre de 2023
 * @description Clase encargada de manejar la vista de receta médica para los médicos
 */

public class RecetaMedica {
    private static JFrame myFrame;
    private JPanel pnlRecetaMedica;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JComboBox cmbPaciente;
    private JTextField txtMedicamentos;
    private JButton btnMedicamentos;
    private JTextField txtJustificacion;
    private JTextField txtObservaciones;
    private JLabel lblAgregarRecetas;
    private JTable tblRecetas;
    private JButton btnRegresar;
    RecetaMedicaController recetaMedicaController = new RecetaMedicaController();

    /**
     * @description Constructor de la clase RecetaMedica
     * @param usuarioMedico El usuario médico que actualmente está con sesión iniciada
     */
    public RecetaMedica(Usuario usuarioMedico) {
        String[] col = { "Paciente", "Fecha", "Receta" };
        DefaultTableModel modeloTabla = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        cmbPaciente.setModel(new DefaultComboBoxModel<String>(recetaMedicaController.obtenerPacientes().toArray(new String[0])));
        tblRecetas.setModel(modeloTabla);

        ArrayList<String> recetas = recetaMedicaController.obtenerRecetas();
        for (String receta : recetas) {
            modeloTabla.addRow(receta.split("-"));
        }

        btnMedicamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object selectedPaciente = cmbPaciente.getSelectedItem();
                    if (selectedPaciente != null) {
                        String paciente = selectedPaciente.toString();
                        String medicamentos = txtMedicamentos.getText();
                        String justificacion = txtJustificacion.getText();
                        String observaciones = txtObservaciones.getText();

                        if (paciente.isEmpty() || medicamentos.isEmpty() || justificacion.isEmpty() || observaciones.isEmpty()){
                            JOptionPane.showMessageDialog(myFrame, "Para agregar la receta asegurate de llenar todos los campos solicitados", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            boolean recetaExitosa = recetaMedicaController.agregarReceta(usuarioMedico, paciente, medicamentos,
                                    justificacion, observaciones);

                            if (recetaExitosa) {
                                JOptionPane.showMessageDialog(myFrame, "La receta fue agregada correctamente", "Éxito",
                                        JOptionPane.INFORMATION_MESSAGE);
                                txtMedicamentos.setText("");
                                txtJustificacion.setText("");
                                txtObservaciones.setText("");

                                modeloTabla.addRow(recetaMedicaController.obtenerUltimaReceta()
                                        .toString(cmbPaciente.getSelectedItem().toString()).split("-"));

                            } else {
                                JOptionPane.showMessageDialog(myFrame, "Ocurrió un error al agregar la receta", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "Aún no hay pacientes disponibles para seleccionar", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(myFrame, "Para poder agregar una receta, primero asegurate de que existan pacientes registrados", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicoGUI myMedic = new MedicoGUI(usuarioMedico);
                myMedic.setVisible(usuarioMedico);
                myFrame.dispose();
            }
        });
    }

    /**
     * @description Metodo que se encargara de cargar la vista cuando sea llamada
     *              desde otra
     * @param user El usuario médico que actualmente está con sesión iniciada
     */
    public void setVisible(Usuario user) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new RecetaMedica(user).pnlRecetaMedica);
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
