package grafica.ventanas;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import grafica.controladores.ControladorDarDescripcionRevision;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaDarDescripcionRevision {

	private JFrame frame;
	private JTextField tfal;
	private JTextField tfced;
	private ControladorDarDescripcionRevision controlador;

	public VentanaDarDescripcionRevision() {
		initialize();
		controlador = new ControladorDarDescripcionRevision(this);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBackground(SystemColor.inactiveCaption);
		frame.setBounds(100, 100, 543, 348);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea txtrIngreseElAo = new JTextArea();
		txtrIngreseElAo.setEditable(false);
		txtrIngreseElAo.setForeground(SystemColor.text);
		txtrIngreseElAo.setText("Ingrese el código del folio y el número de revisión");
		txtrIngreseElAo.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrIngreseElAo.setBackground(SystemColor.activeCaption);
		txtrIngreseElAo.setBounds(55, 21, 438, 28);
		frame.getContentPane().add(txtrIngreseElAo);

		JLabel lblAoLectivo = new JLabel("Codigo del folio");
		lblAoLectivo.setForeground(SystemColor.text);
		lblAoLectivo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAoLectivo.setBounds(20, 74, 160, 32);
		frame.getContentPane().add(lblAoLectivo);

		JLabel lblCdula = new JLabel("Numero de revision");
		lblCdula.setForeground(SystemColor.text);
		lblCdula.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCdula.setBounds(20, 139, 180, 32);
		frame.getContentPane().add(lblCdula);

		tfal = new JTextField();
		tfal.setColumns(10);
		tfal.setBounds(204, 79, 196, 29);
		frame.getContentPane().add(tfal);

		tfced = new JTextField();
		tfced.setColumns(10);
		tfced.setBounds(204, 144, 196, 29);
		frame.getContentPane().add(tfced);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cod = tfal.getText();
				String num = tfced.getText();
				controlador.DarDescripcion(cod, num);
				tfal.setText("");
				tfced.setText("");
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(85, 207, 115, 37);
		frame.getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(271, 207, 115, 37);
		frame.getContentPane().add(btnCancelar);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	public void mostrarResultado(String resu) {
		JOptionPane.showMessageDialog(frame, resu);
	}

}
