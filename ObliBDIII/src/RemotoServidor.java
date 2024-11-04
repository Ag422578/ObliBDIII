import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;
import java.io.FileInputStream;

import logica.Fachada;

public class RemotoServidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
		//instancio la clase que implementa la interfaz remota
		Fachada fachada = new Fachada();
		
//		// ingreso algunos servicios para prueba
//		VOServicio servicioVO = new VOServicio("a", 10, (float)10.9, true, false);
//		capaLogica.altaServicio(servicioVO);
//
//		servicioVO = new VOServicio("c", 10, (float)10.9, true, false);
//		capaLogica.altaServicio(servicioVO);
//			
//		servicioVO = new VOServicio("b", 10, (float)10.9, true, false);
//		capaLogica.altaServicio(servicioVO);
		
		//pongo a correr
		
		LocateRegistry.createRegistry(1099);
		
		//obtengo ip y puerto de un archivo de configuracion
		Properties p = new Properties();
		String nomArch = "config/config.properties";
		p.load (new FileInputStream (nomArch));
		String ip = p.getProperty("ipServidor");
		String puerto = p.getProperty("puertoServidor");
		String ruta = "//" + ip + ":" + puerto + "/Fachada";
		
		//Publico el objeto remoto
		Naming.rebind(ruta, fachada);
		System.out.println("+++++++++++++++++++++++++++++++SERVIDOR PUBLICADO CORRECTAMENTE+++++++++++++++++++++++++++++++\n\n");
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}

}


