package grafica.controladores;

import java.rmi.RemoteException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import grafica.ventanas.VentanaListadoRevisiones;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VORevision;

public class ControladorListadoRevisiones {
	private IFachada fac;
	private VentanaListadoRevisiones VLR;

	public ControladorListadoRevisiones(VentanaListadoRevisiones listRev) {
		this.VLR = listRev;
	}

	public void ListarRevisiones(String codF) {
		try {
			DefaultTableModel dm = (DefaultTableModel) VLR.tableListadoRevisiones.getModel();
			while (VLR.tableListadoRevisiones.getRowCount() != 0)
				dm.removeRow(VLR.tableListadoRevisiones.getRowCount() - 1);
			List<VORevision> Revisiones = fac.listarRevisiones(codF);
			for (int i = 0; i < Revisiones.size(); i++) {
				dm.addRow(new Object[] { Revisiones.get(i).getNumero(), Revisiones.get(i).getDescripcion() });
			}
		} catch (RemoteException e) {
			VLR.mostrarResultado("Error de conexion con el servidor");
		} catch (PersistenciaException e) {
			VLR.mostrarResultado("Error de persistencia");
		} catch (LogicaException e) {
			VLR.mostrarResultado("Error de lógica");
		}

	}
}
