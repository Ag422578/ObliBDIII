package grafica.controladores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.io.FileInputStream;

import grafica.ventanas.VentanaAltaFolio;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;


public class ControladorAltaFolio {
	private IFachada fac;
	private VentanaAltaFolio VAF;

	public ControladorAltaFolio(VentanaAltaFolio VAF) {
		this.VAF = VAF;
		
			
		//////// RMI /////////
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

		
	
	

	public void AltaFolio(String cod, String car, String pag) {
		if (String.valueOf(pag) == null || String.valueOf(pag).equals(""))
			VAF.mostrarResultado("El campo pagina esta vacìo");
		else if (cod == null || cod.equals(""))
			VAF.mostrarResultado("El código está vacío");
		else if (car == null || car.equals(""))
			VAF.mostrarResultado("La carátula está vacía");
		else {
			int pagInt = Integer.parseInt(pag);
			VOFolio VOF = new VOFolio(cod, car, pagInt);
			try {
				fac.agregarFolio(VOF);
				VAF.mostrarResultado("El folio fue añadido correctamente");
			} catch (RemoteException e) {
				VAF.mostrarResultado("Error de conexión con el servidor");
			} catch (PersistenciaException e) {
				VAF.mostrarResultado(e.getMensaje());
			} catch (LogicaException e) {
				VAF.mostrarResultado(e.getMensaje());
			} catch (Exception e) {
				VAF.mostrarResultado("Error al solicitar conexión");
			}
		}
	}
}
