package grafica.ventanas;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import grafica.controladores.ControladorListadoFolios;

public class VentanaListadoFolios {

	private JFrame frmListadoFolios;
	private ControladorListadoFolios controlador;
	public JTable tableListadoFolios;

	public VentanaListadoFolios() {
	  initialize();
	  controlador = new ControladorListadoFolios(this);
	 }

	private void initialize() {
		frmListadoFolios = new JFrame();
		frmListadoFolios.getContentPane().setBackground(SystemColor.activeCaption);
		frmListadoFolios.setBackground(SystemColor.inactiveCaption);
		frmListadoFolios.setTitle("Listado de folios");
		frmListadoFolios.setBounds(100, 100, 515, 397);
		frmListadoFolios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListadoFolios.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Listado de folios del estudio notarial");
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
		btnCancelar.setBounds(210, 315, 85, 32);
		frmListadoFolios.getContentPane().add(btnCancelar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.ListarFolios();
			}
		});
		btnBuscar.setBounds(210, 58, 85, 32);
		frmListadoFolios.getContentPane().add(btnBuscar);

		tableListadoFolios = new JTable();
		tableListadoFolios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo de folio", "Car\u00E1tula", "P\u00E1ginas"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableListadoFolios.getColumnModel().getColumn(0).setPreferredWidth(207);
		tableListadoFolios.getColumnModel().getColumn(1).setPreferredWidth(207);
		tableListadoFolios.getColumnModel().getColumn(2).setPreferredWidth(208);
		tableListadoFolios.setBounds(39, 128, 438, 176);
		frmListadoFolios.getContentPane().add(tableListadoFolios);

		JScrollPane scrollPaneFolios = new JScrollPane();
		scrollPaneFolios.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneFolios.setBounds(38, 101, 439, 27);
		scrollPaneFolios.setViewportView(tableListadoFolios.getTableHeader());
		frmListadoFolios.getContentPane().add(scrollPaneFolios);
	}

	public void setVisible(boolean visible) {
		frmListadoFolios.setVisible(visible);
	}

	public void mostrarResultado(String Resultado) {
		JOptionPane.showMessageDialog(frmListadoFolios, Resultado);
	}

}
