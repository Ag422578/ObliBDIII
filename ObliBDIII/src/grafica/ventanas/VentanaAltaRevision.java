package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import grafica.controladores.ControladorAltaRevision;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;

public class VentanaAltaRevision {
	private JFrame frmAltaDeFolio;
	private JTextField txtCod;
	private JTextField txtDesc;
	private ControladorAltaRevision controlador;

	public VentanaAltaRevision() {
		initialize();
		controlador = new ControladorAltaRevision(this);
	}

	private void initialize() {
		frmAltaDeFolio = new JFrame();
		frmAltaDeFolio.getContentPane().setBackground(SystemColor.activeCaption);
		frmAltaDeFolio.setFont(new Font("Arial", Font.BOLD, 15));
		frmAltaDeFolio.setTitle("Alta de revisión");
		frmAltaDeFolio.setBackground(SystemColor.inactiveCaption);
		frmAltaDeFolio.setBounds(100, 100, 438, 244);
		frmAltaDeFolio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaDeFolio.getContentPane().setLayout(null);

		JLabel lblCod = new JLabel("Código");
		lblCod.setForeground(SystemColor.text);
		lblCod.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCod.setBounds(10, 46, 97, 32);
		frmAltaDeFolio.getContentPane().add(lblCod);

		JLabel lblCar = new JLabel("Descripción");
		lblCar.setForeground(SystemColor.text);
		lblCar.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCar.setBounds(10, 83, 138, 32);
		frmAltaDeFolio.getContentPane().add(lblCar);

		txtCod = new JTextField();
		txtCod.setBounds(127, 51, 196, 29);
		frmAltaDeFolio.getContentPane().add(txtCod);
		txtCod.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(127, 88, 196, 29);
		frmAltaDeFolio.getContentPane().add(txtDesc);

		JTextArea txtrIngreseLosDatos = new JTextArea();
		txtrIngreseLosDatos.setForeground(SystemColor.text);
		txtrIngreseLosDatos.setBackground(SystemColor.activeCaption);
		txtrIngreseLosDatos.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrIngreseLosDatos.setText("Ingrese los datos de la revisión");
		txtrIngreseLosDatos.setBounds(50, 10, 304, 26);
		frmAltaDeFolio.getContentPane().add(txtrIngreseLosDatos);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cod = txtCod.getText();
				String desc = txtDesc.getText();
				txtCod.setText("");
				txtDesc.setText("");
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(50, 146, 115, 37);
		frmAltaDeFolio.getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAltaDeFolio.dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(208, 146, 115, 37);
		frmAltaDeFolio.getContentPane().add(btnCancelar);
	}

	public void mostrarResultado(String resu) {
		JOptionPane.showMessageDialog(frmAltaDeFolio, resu);
	}
	
	public void setVisible(boolean state) {
		frmAltaDeFolio.setVisible(state);
	}
}