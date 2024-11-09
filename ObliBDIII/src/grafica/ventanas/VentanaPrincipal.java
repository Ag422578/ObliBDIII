package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class VentanaPrincipal {
	private JFrame frmMenuPrincipal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmMenuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		initialize();
	}

	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.getContentPane().setBackground(new Color(240, 255, 240));
		frmMenuPrincipal.getContentPane().setForeground(SystemColor.menu);
		frmMenuPrincipal.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIconTextGap(10);
		lblNewLabel.setIcon(new ImageIcon("C:\\Taller2-Nuevo\\config\\Logo.jpeg"));
		lblNewLabel.setBounds(0, 0, 484, 437);
		frmMenuPrincipal.getContentPane().add(lblNewLabel);
		frmMenuPrincipal.setBackground(SystemColor.inactiveCaption);
		frmMenuPrincipal.setFont(new Font("Arial", Font.BOLD, 15));
		frmMenuPrincipal.setTitle("Menu Principal");
		frmMenuPrincipal.setBounds(100, 100, 500, 500);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmMenuPrincipal.setJMenuBar(menuBar);

		JMenu mnFolio = new JMenu("Menu Folios");
		mnFolio.setFont(new Font("Arial", Font.PLAIN, 15));
		menuBar.add(mnFolio);

		// ALTA DE UN FOLIO
		JMenuItem mntmAltaFolio = new JMenuItem("Alta de un folio");
		mntmAltaFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAltaFolio Ventana = new VentanaAltaFolio();
				Ventana.setVisible(true);
			}
		});
		mntmAltaFolio.setFont(new Font("Arial", Font.PLAIN, 12));
		mnFolio.add(mntmAltaFolio);

		// BAJA DE UN FOLIO
		JMenuItem mntmBajaFolio = new JMenuItem("Baja de un folio");
		mntmBajaFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBorradoFolio Ventana = new VentanaBorradoFolio();
				Ventana.setVisible(true);
			}
		});
		mntmBajaFolio.setFont(new Font("Arial", Font.PLAIN, 12));
		mnFolio.add(mntmBajaFolio);

		// LISTADO DE FOLIOS
		JMenuItem mntmListFolio = new JMenuItem("Listado de folios");
		mntmListFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoFolios Ventana = new VentanaListadoFolios();
				Ventana.setVisible(true);
			}
		});
		mntmListFolio.setFont(new Font("Arial", Font.PLAIN, 12));
		mnFolio.add(mntmListFolio);

		// FOLIO CON MAS REVISIONES
		JMenuItem mntmMaxRevFolio = new JMenuItem("Folio con más revisiones");
		mntmMaxRevFolio.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmMaxRevFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaFolioMasRevisado Ventana = new VentanaFolioMasRevisado();
				Ventana.setVisible(true);
			}
		});
		mnFolio.add(mntmMaxRevFolio);

		// ---------------------------------------------------------------------------
		JMenu mnRevisiones = new JMenu("Menu Revisiones");
		mnRevisiones.setFont(new Font("Arial", Font.PLAIN, 15));
		menuBar.add(mnRevisiones);

		// ALTA DE REVISIONES
		JMenuItem mntmAltaRev = new JMenuItem("Alta de una revisión");
		mntmAltaRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAltaRevision Ventana = new VentanaAltaRevision();
				Ventana.setVisible(true);
			}
		});
		mntmAltaRev.setFont(new Font("Arial", Font.PLAIN, 12));
		mnRevisiones.add(mntmAltaRev);

		// DAR DESCRIPCION DE UNA REVISIÓN
		JMenuItem mntmDescRev = new JMenuItem("Obtener descripción de una revisión");
		mntmDescRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaDarDescripcionRevision Ventana = new VentanaDarDescripcionRevision();
				Ventana.setVisible(true);
			}
		});
		mntmDescRev.setFont(new Font("Arial", Font.PLAIN, 12));
		mnRevisiones.add(mntmDescRev);

		// LISTADO DE REVISIONES
		JMenuItem mntmListRev = new JMenuItem("Listado de revisiones");
		mntmListRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListadoRevisiones Ventana = new VentanaListadoRevisiones();
				Ventana.setVisible(true);
			}
		});
		mntmListRev.setFont(new Font("Arial", Font.PLAIN, 12));
		mnRevisiones.add(mntmListRev);

		JMenu mnRespaldos = new JMenu("Menú Respaldo");
		mnRespaldos.setFont(new Font("Arial", Font.PLAIN, 15));
		menuBar.add(mnRespaldos);

		JMenuItem mntmSave = new JMenuItem("Guardar datos");
		mntmSave.setFont(new Font("Arial", Font.PLAIN, 12));
		mnRespaldos.add(mntmSave);
	}

	public void setVisible(boolean visible) {
		frmMenuPrincipal.setVisible(visible);
	}
}
