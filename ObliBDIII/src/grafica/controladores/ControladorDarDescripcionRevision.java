package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.VentanaDarDescripcionRevision;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;

public class ControladorDarDescripcionRevision {
	private IFachada fac; 
	private VentanaDarDescripcionRevision VDDR;
	
	public ControladorDarDescripcionRevision (VentanaDarDescripcionRevision VDDR) {
		this.VDDR = VDDR;
	}
	
	public void DarDescripcion (String cod, String num) {
		if(String.valueOf(num)==null || String.valueOf(num).equals(""))
			VDDR.mostrarResultado("El campo número esta vacio");
		else 
			if(cod==null || cod.equals(""))
				VDDR.mostrarResultado("El campo código esta vacio");
			else {
				try {
					int numR = Integer.parseInt(num);
					String desc = fac.darDescripcion(cod, numR);
					VDDR.mostrarResultado("La descripción es: " + desc);
				} catch (RemoteException e) {
					VDDR.mostrarResultado("Error de conexion con el servidor");
				} catch (PersistenciaException e) {
					VDDR.mostrarResultado("Error de persistencia");
				} catch (LogicaException e) {
					VDDR.mostrarResultado("Error de lógica");
				}
			}
	}
}
