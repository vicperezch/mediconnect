package com.mediconnect.view;

import com.mediconnect.controller.CitaController;
import com.mediconnect.controller.UsuarioController;
import com.mediconnect.db.CSV;
import com.mediconnect.model.Usuario;
import com.mediconnect.model.Medico;
import com.mediconnect.model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VerPacientesGUI {
    private static JFrame myFrame;
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
    CitaController citaController = new CitaController();
    UsuarioController usuarioController = new UsuarioController();

    public VerPacientesGUI(Usuario usuarioMedico) {

        cmbPaciente
                .setModel(new DefaultComboBoxModel<String>(citaController.obtenerPacientes().toArray(new String[0])));

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicoGUI myMedic = new MedicoGUI(usuarioMedico);
                myMedic.setVisible(usuarioMedico);
                myFrame.dispose();
            }
        });
        btnCartaMedica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioSeleccionado = Objects.requireNonNull(cmbPaciente.getSelectedItem()).toString();
                String[] datosUsuario = usuarioSeleccionado.split(" ");
                Paciente pacienteSeleccionado = (Paciente) usuarioController.usuarioPorNombre(datosUsuario[0],
                        datosUsuario[1]);
                CartaMedicaPacienteGUI cartaMedica = new CartaMedicaPacienteGUI(pacienteSeleccionado,
                        (Medico) usuarioMedico);
                cartaMedica.setVisible(pacienteSeleccionado, (Medico) usuarioMedico);
                myFrame.dispose();
            }
        });
        btnProximasCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioSeleccionado = Objects.requireNonNull(cmbPaciente.getSelectedItem()).toString();
                String[] datosUsuario = usuarioSeleccionado.split(" ");
                Paciente pacienteSeleccionado = (Paciente) usuarioController.usuarioPorNombre(datosUsuario[0],
                        datosUsuario[1]);
                CitasPacienteGUI citasPaciente = new CitasPacienteGUI(pacienteSeleccionado,
                        (Medico) usuarioMedico);
                citasPaciente.setVisible(pacienteSeleccionado, (Medico) usuarioMedico);
                myFrame.dispose();
            }
        });
        btnMedicinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioSeleccionado = Objects.requireNonNull(cmbPaciente.getSelectedItem()).toString();
                String[] datosUsuario = usuarioSeleccionado.split(" ");
                Paciente pacienteSeleccionado = (Paciente) usuarioController.usuarioPorNombre(datosUsuario[0],
                        datosUsuario[1]);
                RecetasMedicasPaciente recetaPaciente = new RecetasMedicasPaciente(pacienteSeleccionado,
                        (Medico) usuarioMedico);
                recetaPaciente.setVisible(pacienteSeleccionado, (Medico) usuarioMedico);
                myFrame.dispose();
            }
        });
    }

    public void setVisible(Usuario user) {
        myFrame = new JFrame("MediConnect");
        myFrame.setContentPane(new VerPacientesGUI(user).pnlMisPacientes);
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
