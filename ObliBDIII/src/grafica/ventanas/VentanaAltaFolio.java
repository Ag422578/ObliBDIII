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
import grafica.controladores.ControladorAltaFolio;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;

public class VentanaAltaFolio {
	private JFrame frmAltaDeFolio;
	private JTextField txtCod;
	private JTextField txtCar;
	private JTextField txtPag;
	private ControladorAltaFolio controlador;

	public VentanaAltaFolio() {
		initialize();
		controlador = new ControladorAltaFolio(this);
	}

	private void initialize() {
		frmAltaDeFolio = new JFrame();
		frmAltaDeFolio.getContentPane().setBackground(SystemColor.activeCaption);
		frmAltaDeFolio.setFont(new Font("Arial", Font.BOLD, 15));
		frmAltaDeFolio.setTitle("Alta de folio");
		frmAltaDeFolio.setBackground(SystemColor.inactiveCaption);
		frmAltaDeFolio.setBounds(100, 100, 438, 314);
		frmAltaDeFolio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaDeFolio.getContentPane().setLayout(null);

		JLabel lblCod = new JLabel("Código");
		lblCod.setForeground(SystemColor.text);
		lblCod.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCod.setBounds(10, 46, 97, 32);
		frmAltaDeFolio.getContentPane().add(lblCod);

		JLabel lblCar = new JLabel("Carátula");
		lblCar.setForeground(SystemColor.text);
		lblCar.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCar.setBounds(10, 83, 97, 32);
		frmAltaDeFolio.getContentPane().add(lblCar);

		JLabel lblPag = new JLabel("Páginas");
		lblPag.setForeground(SystemColor.text);
		lblPag.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPag.setBounds(10, 122, 97, 32);
		frmAltaDeFolio.getContentPane().add(lblPag);

		txtCod = new JTextField();
		txtCod.setBounds(109, 51, 196, 29);
		frmAltaDeFolio.getContentPane().add(txtCod);
		txtCod.setColumns(10);

		txtCar = new JTextField();
		txtCar.setColumns(10);
		txtCar.setBounds(109, 88, 196, 29);
		frmAltaDeFolio.getContentPane().add(txtCar);

		txtPag = new JTextField();
		txtPag.setColumns(10);
		txtPag.setBounds(109, 127, 196, 29);
		frmAltaDeFolio.getContentPane().add(txtPag);

		JTextArea txtrIngreseLosDatos = new JTextArea();
		txtrIngreseLosDatos.setForeground(SystemColor.text);
		txtrIngreseLosDatos.setBackground(SystemColor.activeCaption);
		txtrIngreseLosDatos.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrIngreseLosDatos.setText("Ingrese los datos del folio");
		txtrIngreseLosDatos.setBounds(84, 10, 255, 26);
		frmAltaDeFolio.getContentPane().add(txtrIngreseLosDatos);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cod = txtCod.getText();
				String car = txtCar.getText();
				String pag = txtPag.getText();
				txtCod.setText("");
				txtCar.setText("");
				txtPag.setText("");
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(62, 193, 115, 37);
		frmAltaDeFolio.getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAltaDeFolio.dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(237, 193, 115, 37);
		frmAltaDeFolio.getContentPane().add(btnCancelar);
	}

	public void mostrarResultado(String resu) {
		JOptionPane.showMessageDialog(frmAltaDeFolio, resu);
	}
	
	public void setVisible(boolean state) {
		frmAltaDeFolio.setVisible(state);
	}
}