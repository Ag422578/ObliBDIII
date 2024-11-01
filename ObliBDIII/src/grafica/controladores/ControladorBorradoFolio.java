package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.VentanaBorradoFolio;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;

public class ControladorBorradoFolio {
	private IFachada fac;
	private VentanaBorradoFolio VBF;

	public ControladorBorradoFolio(VentanaBorradoFolio VBF) {
		this.VBF = VBF;
	}

	public void BorradoFolio(String codF) {
		try {
			fac.borrarFolioRevisiones(codF);
			VBF.mostrarResultado("El folio fue borrado correctamente");
		} catch (RemoteException e) {
			VBF.mostrarResultado("Error de conexión con el servidor");
		} catch (PersistenciaException e) {
			VBF.mostrarResultado("Error de persistencia con el servidor");
		} catch (LogicaException e) {
			VBF.mostrarResultado("Error al intentar borrar el folio");
		}
	}
}
