
package logica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import logica.valueObjects.VORevision;
import persistencia.daos.DAOFolios;

/*import Ejercicio3.LogicaPersistencia.Excepciones.PersistenciaException;
import Ejercicio3.LogicaPersistencia.ValueObjects.*;*/

public class Fachada extends UnicastRemoteObject implements IFachada {
	
	private DAOFolios Folios;
	
	
	private static final long serialVersionUID = 1L;
	
	public Fachada() throws RemoteException, PersistenciaException {
		super();
		// TODO Auto-generated constructor stub
		Folios= new DAOFolios();
	}
	

	public void agregarFolio(VOFolio voF) throws PersistenciaException, RemoteException, LogicaException {
		
		System.out.print(voF.getCodigo());
		if (!Folios.member(voF.getCodigo())) {
			Folio folio = new Folio(voF.getCodigo(), voF.getCaratula(), voF.getPaginas());
			Folios.insert(folio);
		} else
			throw new LogicaException("Error");

	}

	public void agregarRevision(String codF, String desc)
			throws PersistenciaException, RemoteException, LogicaException {
		if (!Folios.member(codF)) {
			throw new LogicaException("Error");
		} else {
			Folio fol = Folios.find(codF);
			int numR = fol.cantidadRevisiones() + 1;
			Revision rev = new Revision(numR, desc);
			fol.addRevision(rev);
		}

	}

	public void borrarFolioRevisiones(String codF) throws PersistenciaException, RemoteException, LogicaException {
		if (Folios.member(codF)) {
			Folio fol = Folios.find(codF);
			fol.borrarRevisiones();
			Folios.delete(codF);
		} else
			throw new LogicaException("No existe el folio");
	}

	public String darDescripcion(String codF, int numR) throws PersistenciaException, RemoteException, LogicaException {
		String descripcion = null;
		if (Folios.member(codF)) {
			Folio fol = Folios.find(codF);
			if (fol.tieneRevision(numR)) {
				Revision rev = fol.obtenerRevision(numR);
				descripcion = rev.getDescripcion();
			} else {
				throw new LogicaException("No existe la revision");
			}
		} else
			throw new LogicaException("No existe el folio");
		return descripcion;
	}

	public List<VOFolio> listarFolios() throws PersistenciaException, RemoteException {
		List<VOFolio> ListadoFolios = null;
		ListadoFolios = Folios.listarFolios();
		return ListadoFolios;
	}

	public List<VORevision> listarRevisiones(String codF) throws PersistenciaException, RemoteException, LogicaException {
		List<VORevision> ListadoRevisiones = null;
		if (Folios.member(codF)) {
			Folio fol = Folios.find(codF);
			ListadoRevisiones = fol.listarRevisiones();
		} else
			throw new LogicaException("No existe el folio");
		return ListadoRevisiones;
	}

	public VOFolioMaxRev folioMasRevisado() throws PersistenciaException, RemoteException {
		VOFolioMaxRev maxFolio = null;
		maxFolio = Folios.folioMasRevisado();
		return maxFolio;
	}

}
