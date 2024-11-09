package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import grafica.ventanas.VentanaDarDescripcionRevision;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;

public class ControladorDarDescripcionRevision {
	private IFachada fac;
	private VentanaDarDescripcionRevision VDDR;

	public ControladorDarDescripcionRevision(VentanaDarDescripcionRevision VDDR) {
		this.VDDR = VDDR;

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

	public void DarDescripcion(String cod, String num) {
		if (String.valueOf(num) == null || String.valueOf(num).equals(""))
			VDDR.mostrarResultado("El campo número esta vacio");
		else if (cod == null || cod.equals(""))
			VDDR.mostrarResultado("El campo código esta vacio");
		else {
			try {
				int numR = Integer.parseInt(num);
				if (numR <= 0) {
					VDDR.mostrarResultado("El número ingresado no es válido");
				} else {
					String desc = fac.darDescripcion(cod, numR - 1);
					VDDR.mostrarResultado("La descripción es: " + desc);
				}
			} catch (RemoteException e) {
				VDDR.mostrarResultado("Error de conexion con el servidor");
			} catch (PersistenciaException e) {
				VDDR.mostrarResultado(e.getMensaje());
			} catch (LogicaException e) {
				VDDR.mostrarResultado(e.getMensaje());
			} catch (NumberFormatException e) {
				VDDR.mostrarResultado("Los caracteres ingresados no son válidos");
			}

		}
	}
}
