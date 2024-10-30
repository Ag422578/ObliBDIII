package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import logica.valueObjects.VORevision;

public interface IFachada extends Remote {
	
	public void agregarFolio(VOFolio voF) throws PersistenciaException, RemoteException, LogicaException;

	public void agregarRevision(String codF, String desc) throws PersistenciaException, RemoteException, LogicaException;

	public void borrarFolioRevisiones(String codF) throws PersistenciaException, RemoteException, LogicaException;

	public String darDescripcion(String codF, int numR) throws PersistenciaException, RemoteException, LogicaException;

	public List<VOFolio> listarFolios() throws PersistenciaException, RemoteException;
	
	public List<VORevision> listarRevisiones(String codF) throws PersistenciaException, RemoteException, LogicaException;
	
	public VOFolioMaxRev folioMasRevisado() throws PersistenciaException, RemoteException;
}
