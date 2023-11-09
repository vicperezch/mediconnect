package com.mediconnect.view;

import com.mediconnect.controller.RecetaMedicaController;
import com.mediconnect.model.Medico;
import com.mediconnect.model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Diego Flores, Juan Solís
 * @version 1.0.1
 * @creationDate 02 de noviembre de 2023
 * @lastModified 02 de noviembre de 2023
 * @description Clase encargada de manejar la vista de las recetas de Paciente
 */

public class RecetasMedicasPaciente {
    private static JFrame myFrame;
    private JPanel pnlRecetasMedicasPaciente;
    private JPanel pnlHeader;
    private JLabel lblTitle;
    private JLabel lblSubtitle;
    private JPanel pnlBody;
    private JPanel pnlForm;
    private JTable tblRecetas;
    private JButton btnRegresar;

    private RecetaMedicaController recetaController = new RecetaMedicaController();

    /**
     * @description Constructor de la clase RecetasMedicasPaciente
     */
    public RecetasMedicasPaciente(Paciente usuario, Medico usuairoMedico) {
        String[] col = { "Medicamento", "Justificación", "Observaciones" };
        DefaultTableModel modeloTablaRecetas = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblRecetas.setModel(modeloTablaRecetas);

        ArrayList<ArrayList<String>> recetas = recetaController.obtenerRecetasPaciente(usuario.getId());
        for (ArrayList<String> recetaInfo : recetas) {
            modeloTablaRecetas.addRow(recetaInfo.toArray());
        }

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuairoMedico == null) {
                    PacienteGUI menuPaciente = new PacienteGUI(usuario);
                    menuPaciente.setVisible(usuario);
                    myFrame.dispose();
                } else {
                    VerPacientesGUI menuVerPacientes = new VerPacientesGUI(usuairoMedico);
                    menuVerPacientes.setVisible(usuairoMedico);
                    myFrame.dispose();
                }
            }
        });
    }

    /**
     * @description Método que se encarga de cargar la vista cuando esta es llamada
     */
    public void setVisible(Paciente user, Medico userMedico) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new RecetasMedicasPaciente(user, userMedico).pnlRecetasMedicasPaciente);
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
