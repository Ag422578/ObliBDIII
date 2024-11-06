package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

import grafica.ventanas.VentanaFolioMasRevisado;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolioMaxRev;

public class ControladorFolioMasRevisado {
	private IFachada fac;
	private VentanaFolioMasRevisado VFMR;

	public ControladorFolioMasRevisado(VentanaFolioMasRevisado VFMR) {
		this.VFMR = VFMR;

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
		} catch (LogicaException e) {
			VFMR.mostrarResultado("Error de lógica");
		}
	}
}
