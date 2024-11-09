package persistencia;

import java.io.IOException;

import logica.IFabricaAbstracta;
import logica.excepciones.PersistenciaException;
import persistencia.daos.DAOFoliosArchivo;
import persistencia.daos.DAORevisionesArchivo;
import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

public class FabricaArchivo implements IFabricaAbstracta {

	public IDAORevisiones crearIDAORevisiones(String codFolio) throws PersistenciaException {
		IDAORevisiones dao = new DAORevisionesArchivo(codFolio);
		return dao;
	}

	public IDAOFolios crearIDAOFolios(IFabricaAbstracta fab) throws PersistenciaException {
		try {
			return new DAOFoliosArchivo();
		} catch (PersistenciaException e) {
			throw new PersistenciaException("Error de persistencia");
		} catch (IOException e) {
			throw new PersistenciaException("Error de persistencia");
		}
	}
}
