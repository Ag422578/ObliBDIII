package grafica.ventanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import grafica.controladores.ControladorListadoRevisiones;
import java.awt.Color;
import javax.swing.JTextField;

public class VentanaListadoRevisiones {
	private JFrame frmListadoFolios;
	private ControladorListadoRevisiones controlador;
	public JTable tableListadoRevisiones;
	private JTextField txtCod;

	public VentanaListadoRevisiones() {
		initialize();
		controlador = new ControladorListadoRevisiones(this);
	}

	private void initialize() {
		frmListadoFolios = new JFrame();
		frmListadoFolios.getContentPane().setBackground(SystemColor.activeCaption);
		frmListadoFolios.setBackground(SystemColor.inactiveCaption);
		frmListadoFolios.setTitle("Listado de folios");
		frmListadoFolios.setBounds(100, 100, 515, 421);
		frmListadoFolios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListadoFolios.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Listado de revisiones de un folio");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(0, 23, 499, 24);
		frmListadoFolios.getContentPane().add(lblNewLabel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmListadoFolios.dispose();
			}
		});
		btnCancelar.setBounds(326, 339, 85, 32);
		frmListadoFolios.getContentPane().add(btnCancelar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cod = txtCod.getText();
				controlador.ListarRevisiones(cod);
			}
		});
		btnBuscar.setBounds(100, 339, 85, 32);
		frmListadoFolios.getContentPane().add(btnBuscar);

		tableListadoRevisiones = new JTable();
		tableListadoRevisiones.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "N\u00FAmero de revision", "Descripcion" }) {
					Class[] columnTypes = new Class[] { String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		tableListadoRevisiones.getColumnModel().getColumn(0).setPreferredWidth(286);
		tableListadoRevisiones.getColumnModel().getColumn(1).setPreferredWidth(334);
		tableListadoRevisiones.setBounds(38, 145, 438, 176);
		frmListadoFolios.getContentPane().add(tableListadoRevisiones);

		JScrollPane scrollPaneFolios = new JScrollPane();
		scrollPaneFolios.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneFolios.setBounds(37, 118, 439, 27);
		scrollPaneFolios.setViewportView(tableListadoRevisiones.getTableHeader());
		frmListadoFolios.getContentPane().add(scrollPaneFolios);

		JLabel lblCod = new JLabel("Código");
		lblCod.setForeground(Color.WHITE);
		lblCod.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCod.setBounds(100, 61, 97, 32);
		frmListadoFolios.getContentPane().add(lblCod);

		txtCod = new JTextField();
		txtCod.setColumns(10);
		txtCod.setBounds(171, 63, 196, 29);
		frmListadoFolios.getContentPane().add(txtCod);
	}

	public void setVisible(boolean visible) {
		frmListadoFolios.setVisible(visible);
	}

	public void mostrarResultado(String Resultado) {
		JOptionPane.showMessageDialog(frmListadoFolios, Resultado);
	}
}
