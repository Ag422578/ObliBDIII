package grafica.ventanas;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import grafica.controladores.ControladorFolioMasRevisado;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaFolioMasRevisado {

	private JFrame frame;
	private ControladorFolioMasRevisado controlador;
	public JTable tableFolioMasRevisado;

	public VentanaFolioMasRevisado() {
		initialize();
		controlador = new ControladorFolioMasRevisado(this);
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
		txtrIngreseElAo.setText("Folio mas revisado");
		txtrIngreseElAo.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrIngreseElAo.setBackground(SystemColor.activeCaption);
		txtrIngreseElAo.setBounds(178, 39, 170, 28);
		frame.getContentPane().add(txtrIngreseElAo);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.FolioMasRevisado();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(85, 241, 115, 37);
		frame.getContentPane().add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCancelar.setBounds(358, 241, 115, 37);
		frame.getContentPane().add(btnCancelar);

		JScrollPane scrollPaneFolios = new JScrollPane();
		scrollPaneFolios.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneFolios.setBounds(39, 124, 466, 61);
		frame.getContentPane().add(scrollPaneFolios);

		tableFolioMasRevisado = new JTable();
		scrollPaneFolios.setViewportView(tableFolioMasRevisado);
		tableFolioMasRevisado.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Car\u00E1tula", "P\u00E1ginas", "Cantidad de revisiones" }));
		tableFolioMasRevisado.getColumnModel().getColumn(0).setPreferredWidth(85);
		tableFolioMasRevisado.getColumnModel().getColumn(1).setPreferredWidth(149);
		tableFolioMasRevisado.getColumnModel().getColumn(2).setPreferredWidth(153);
		tableFolioMasRevisado.getColumnModel().getColumn(3).setPreferredWidth(236);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	public void mostrarResultado(String resu) {
		JOptionPane.showMessageDialog(frame, resu);
	}
}
