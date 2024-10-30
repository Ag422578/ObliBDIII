package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.VentanaAltaRevision;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;

public class ControladorAltaRevision {
	private IFachada fac;
	private VentanaAltaRevision VAR;

	public ControladorAltaRevision(VentanaAltaRevision VAR) {
		this.VAR = VAR;
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
				e.printStackTrace();
			} catch (PersistenciaException e) {
				VAR.mostrarResultado("Error de persistencia con el servidor");
			} catch (LogicaException e) {
				VAR.mostrarResultado("Error al intentar añadir la revisión");
			}
		}
	}
}
