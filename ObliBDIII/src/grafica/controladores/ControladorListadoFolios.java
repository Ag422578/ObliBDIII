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

import grafica.ventanas.VentanaListadoFolios;
import logica.IFachada;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;

public class ControladorListadoFolios {
	private IFachada fac;
	private VentanaListadoFolios VLF;

	public ControladorListadoFolios(VentanaListadoFolios listFol) {
		this.VLF = listFol;
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

	public void ListarFolios() {
		try {
			DefaultTableModel dm = (DefaultTableModel) VLF.tableListadoFolios.getModel();
			while (VLF.tableListadoFolios.getRowCount() != 0)
				dm.removeRow(VLF.tableListadoFolios.getRowCount() - 1);
			List<VOFolio> Folios = fac.listarFolios();
			if (Folios.isEmpty()) {
				VLF.mostrarResultado("No existen folios en el sistema");
			} else {
				for (int i = 0; i < Folios.size(); i++) {
					dm.addRow(new Object[] { Folios.get(i).getCodigo(), Folios.get(i).getCaratula(),
							Folios.get(i).getPaginas() });
				}
			}
		} catch (RemoteException e) {
			VLF.mostrarResultado("Error de conexion con el servidor");
		} catch (PersistenciaException e) {
			e.printStackTrace();
			VLF.mostrarResultado("Error de persistencia");
		}

	}

}
