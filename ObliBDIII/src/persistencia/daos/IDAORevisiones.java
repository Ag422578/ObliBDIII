package persistencia.daos;
import java.rmi.Remote;
import java.util.List;

import logica.Revision;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VORevision;
import persistencia.IConexion;

public interface IDAORevisiones extends Remote {
	
	public void insBack(Revision rev, IConexion con) throws PersistenciaException;

	public int largo(IConexion con) throws PersistenciaException;

	public Revision kesimo(int numero, IConexion con) throws PersistenciaException;
	
	public List<VORevision> listarRevisiones(IConexion con) throws PersistenciaException;

	public void borrarRevisiones(IConexion con) throws PersistenciaException;
}
