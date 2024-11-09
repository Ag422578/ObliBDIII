package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

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

		try {
			// obtengo ip y puerto de un archivo de configuracion
			Properties p = new Properties();
			String nomArch = "config/config.properties";
			p.load(new FileInputStream(nomArch));
			String ip = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + ip + ":" + puerto + "/Fachada";

			// accedo remotamente a la fachada publicada en el servidor
			fac = (IFachada) Naming.lookup(ruta);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) // si la ruta esta bien formada pero el servidor esta bajo
		{
			e.printStackTrace();
		} catch (FileNotFoundException e) // si no encuentra el archivo de configuracion
		{
			e.printStackTrace();
		} catch (IOException e) // si ocurre cualquier otro error de E/S
		{
			e.printStackTrace();
		}
	}

	public void ListarRevisiones(String codF) {
		try {
			DefaultTableModel dm = (DefaultTableModel) VLR.tableListadoRevisiones.getModel();
			while (VLR.tableListadoRevisiones.getRowCount() != 0)
				dm.removeRow(VLR.tableListadoRevisiones.getRowCount() - 1);
			List<VORevision> Revisiones = fac.listarRevisiones(codF);
			if (Revisiones.isEmpty()) {
				VLR.mostrarResultado("No existen revisiones en el sistema para ese folio");
			} else {
				for (int i = 0; i < Revisiones.size(); i++) {
					dm.addRow(new Object[] { Revisiones.get(i).getNumero(), Revisiones.get(i).getDescripcion() });
				}
			}
		} catch (RemoteException e) {
			VLR.mostrarResultado("Error de conexion con el servidor");
		} catch (PersistenciaException e) {
			VLR.mostrarResultado(e.getMensaje());
		} catch (LogicaException e) {
			VLR.mostrarResultado(e.getMensaje());
		}

	}
}
