package grafica.controladores;

import java.rmi.RemoteException;

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
				VAF.mostrarResultado("El alumno fue inscripto correctamente");
			} catch (RemoteException e) {
				VAF.mostrarResultado("Error de conexión con el servidor");
				e.printStackTrace();
			} catch (PersistenciaException e) {
				VAF.mostrarResultado("Error de persistencia con el servidor");
			} catch (LogicaException e) {
				VAF.mostrarResultado("Error al intentar añadir el folio");
			}
		}
	}
}
