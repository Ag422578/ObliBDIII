package grafica.controladores;

import java.rmi.RemoteException;
import javax.swing.table.DefaultTableModel;

import grafica.ventanas.VentanaFolioMasRevisado;
import logica.IFachada;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolioMaxRev;

public class ControladorFolioMasRevisado {
	private IFachada fac;
	private VentanaFolioMasRevisado VFMR;

	public ControladorFolioMasRevisado(VentanaFolioMasRevisado VFMR) {
		this.VFMR = VFMR;
	}

	public void FolioMasRevisado() {
		try {
			DefaultTableModel dm = (DefaultTableModel) VFMR.tableFolioMasRevisado.getModel();
			while (VFMR.tableFolioMasRevisado.getRowCount() != 0)
				dm.removeRow(VFMR.tableFolioMasRevisado.getRowCount() - 1);
			VOFolioMaxRev FolioMaxRev = fac.folioMasRevisado();
			dm.addRow(new Object[] { FolioMaxRev.getCodigo(), FolioMaxRev.getCaratula(), FolioMaxRev.getPaginas(),
					FolioMaxRev.getCantRevisiones() });
		} catch (RemoteException e) {
			VFMR.mostrarResultado("Error de conexion con el servidor");
		} catch (PersistenciaException e) {
			VFMR.mostrarResultado("Error de persistencia");
		}
	}
}
