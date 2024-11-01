package grafica.ventanas;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import grafica.controladores.ControladorBorradoFolio;

public class VentanaBorradoFolio {
	private JFrame frmBorradoDeFolio;
	private JTextField txtCod;
	private ControladorBorradoFolio controlador;

	public VentanaBorradoFolio() {
		initialize();
		controlador = new ControladorBorradoFolio(this);
	}

	private void initialize() {
		frmBorradoDeFolio = new JFrame();
		frmBorradoDeFolio.getContentPane().setBackground(SystemColor.activeCaption);
		frmBorradoDeFolio.setFont(new Font("Arial", Font.BOLD, 15));
		frmBorradoDeFolio.setTitle("Borrado de folio");
		frmBorradoDeFolio.setBackground(SystemColor.inactiveCaption);
		frmBorradoDeFolio.setBounds(100, 100, 438, 314);
		frmBorradoDeFolio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBorradoDeFolio.getContentPane().setLayout(null);

		JLabel lblCod = new JLabel("Código");
		lblCod.setForeground(SystemColor.text);
		lblCod.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCod.setBounds(47, 104, 97, 32);
		frmBorradoDeFolio.getContentPane().add(lblCod);

		txtCod = new JTextField();
		txtCod.setBounds(128, 105, 196, 29);
		frmBorradoDeFolio.getContentPane().add(txtCod);
		txtCod.setColumns(10);

		JTextArea txtrIngreseLosDatos = new JTextArea();
		txtrIngreseLosDatos.setEditable(false);
		txtrIngreseLosDatos.setForeground(SystemColor.text);
		txtrIngreseLosDatos.setBackground(SystemColor.activeCaption);
		txtrIngreseLosDatos.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrIngreseLosDatos.setText("Borrado de folio");
		txtrIngreseLosDatos.setBounds(138, 25, 152, 26);
		frmBorradoDeFolio.getContentPane().add(txtrIngreseLosDatos);

		JButton btnNewButton = new JButton("Borrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cod = txtCod.getText();
				controlador.BorradoFolio(cod);
				txtCod.setText("");
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(62, 193, 115, 37);
		frmBorradoDeFolio.getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBorradoDeFolio.dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(237, 193, 115, 37);
		frmBorradoDeFolio.getContentPane().add(btnCancelar);
	}

	public void mostrarResultado(String resu) {
		JOptionPane.showMessageDialog(frmBorradoDeFolio, resu);
	}

	public void setVisible(boolean state) {
		frmBorradoDeFolio.setVisible(state);
	}
}
