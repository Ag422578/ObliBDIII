package grafica.ventanas;

import javax.swing.JFrame;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import grafica.controladores.ControladorAltaRevision;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAltaRevision {
	private JFrame frmAltaDeRevision;
	private JTextField txtNum;
	private JTextField txtDesc;
	private ControladorAltaRevision controlador;

	public VentanaAltaRevision() {
		initialize();
		controlador = new ControladorAltaRevision(this);
	}

	private void initialize() {
		frmAltaDeRevision = new JFrame();
		frmAltaDeRevision.getContentPane().setBackground(SystemColor.activeCaption);
		frmAltaDeRevision.setFont(new Font("Arial", Font.BOLD, 15));
		frmAltaDeRevision.setTitle("Alta de revisión");
		frmAltaDeRevision.setBackground(SystemColor.inactiveCaption);
		frmAltaDeRevision.setBounds(100, 100, 438, 244);
		frmAltaDeRevision.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAltaDeRevision.getContentPane().setLayout(null);

		JLabel lblNum = new JLabel("Cod.Folio");
		lblNum.setForeground(SystemColor.text);
		lblNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNum.setBounds(10, 46, 97, 32);
		frmAltaDeRevision.getContentPane().add(lblNum);

		JLabel lblCar = new JLabel("Descripción");
		lblCar.setForeground(SystemColor.text);
		lblCar.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCar.setBounds(10, 83, 138, 32);
		frmAltaDeRevision.getContentPane().add(lblCar);

		txtNum = new JTextField();
		txtNum.setBounds(127, 51, 196, 29);
		frmAltaDeRevision.getContentPane().add(txtNum);
		txtNum.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(127, 88, 196, 29);
		frmAltaDeRevision.getContentPane().add(txtDesc);

		JTextArea txtrIngreseLosDatos = new JTextArea();
		txtrIngreseLosDatos.setEditable(false);
		txtrIngreseLosDatos.setForeground(SystemColor.text);
		txtrIngreseLosDatos.setBackground(SystemColor.activeCaption);
		txtrIngreseLosDatos.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrIngreseLosDatos.setText("Ingrese los datos de la revisión");
		txtrIngreseLosDatos.setBounds(50, 10, 304, 26);
		frmAltaDeRevision.getContentPane().add(txtrIngreseLosDatos);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cod = txtNum.getText();
				String desc = txtDesc.getText();
				controlador.AltaRevision(cod, desc);
				txtNum.setText("");
				txtDesc.setText("");
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(50, 146, 115, 37);
		frmAltaDeRevision.getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAltaDeRevision.dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(208, 146, 115, 37);
		frmAltaDeRevision.getContentPane().add(btnCancelar);
	}

	public void mostrarResultado(String resu) {
		JOptionPane.showMessageDialog(frmAltaDeRevision, resu);
	}
	
	public void setVisible(boolean state) {
		frmAltaDeRevision.setVisible(state);
	}
}