package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import grafica.ventanas.VentanaAltaRevision;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;

public class ControladorAltaRevision {
	private IFachada fac;
	private VentanaAltaRevision VAR;

	public ControladorAltaRevision(VentanaAltaRevision VAR) {
		this.VAR = VAR;
		
	
					////////RMI /////////
	 try 
		{
			 	
			//obtengo ip y puerto de un archivo de configuracion
			Properties p = new Properties();
			String nomArch = "config/config.properties";
			p.load (new FileInputStream (nomArch));
			String ip = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + ip + ":" + puerto + "/Fachada";
			
			//accedo remotamente a la fachada publicada en el servidor
			fac = (IFachada) Naming.lookup(ruta);}
	 		catch (MalformedURLException e){e.printStackTrace();}
		 	catch (RemoteException e){e.printStackTrace();}
		 	catch (NotBoundException e) // si la ruta esta bien formada pero el servidor esta bajo
		 		{e.printStackTrace();}
		 	catch (FileNotFoundException e) // si no encuentra el archivo de configuracion
		 		{e.printStackTrace();}
		 	catch (IOException e) // si ocurre cualquier otro error de E/S
		 		{e.printStackTrace();} 	
		 
		 
}


	public void AltaRevision(String cod, String desc) {
		if (cod == null || cod.equals(""))
			VAR.mostrarResultado("El código está vacío");
		else if (desc == null || desc.equals(""))
			VAR.mostrarResultado("La descripción está vacía");
		else {
			try {
				fac.agregarRevision(cod, desc);
				VAR.mostrarResultado("La revisión fue añadida correctamente");
			} catch (RemoteException e) {
				VAR.mostrarResultado("Error de conexión con el servidor");
			} catch (PersistenciaException e) {
				VAR.mostrarResultado(e.getMensaje());
			} catch (LogicaException e) {
				VAR.mostrarResultado(e.getMensaje());
			}
		}
	}
}
