package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

import grafica.ventanas.VentanaListadoFolios;
import logica.IFachada;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;

public class ControladorListadoFolios {
	private IFachada fac;
	private VentanaListadoFolios VLF;
	
	public ControladorListadoFolios (VentanaListadoFolios listFol) {
		this.VLF = listFol;
	}
	
	public void ListarFolios () {
		try {
			DefaultTableModel dm = (DefaultTableModel) VLF.tableListadoFolios.getModel();
			while(VLF.tableListadoFolios.getRowCount() != 0)
				dm.removeRow(VLF.tableListadoFolios.getRowCount()-1);
			List<VOFolio> Folios = fac.listarFolios();
			for(int i = 0; i<Folios.size(); i++) {
				dm.addRow(new Object[] {Folios.get(i).getCodigo(), Folios.get(i).getCaratula(), Folios.get(i).getPaginas()});
			}
		} catch (RemoteException e) {
			VLF.mostrarResultado("Error de conexion con el servidor");
		} catch (PersistenciaException e) {
			VLF.mostrarResultado("Error de persistencia");
		}
		
	}
	
	
	
}
