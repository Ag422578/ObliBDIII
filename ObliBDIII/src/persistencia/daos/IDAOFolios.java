package persistencia.daos;

import java.util.List;

import logica.Folio;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import persistencia.IConexion;

public interface IDAOFolios {

	public boolean member(String cod, IConexion con) throws PersistenciaException;

	public void insert(Folio fol, IConexion ICon) throws PersistenciaException;

	public Folio find(String cod, IConexion con) throws PersistenciaException;

	public void delete(String cod, IConexion con) throws PersistenciaException;

	public List<VOFolio> listarFolios(IConexion con) throws PersistenciaException;

	public boolean esVacio(IConexion con) throws PersistenciaException;

	public VOFolioMaxRev folioMasRevisado(IConexion con) throws PersistenciaException;

}
