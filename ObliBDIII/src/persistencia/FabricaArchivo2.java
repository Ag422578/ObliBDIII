package persistencia;

import java.io.IOException;

import logica.IFabricaAbstracta;
import logica.excepciones.PersistenciaException;
import persistencia.daos.DAOFoliosArchivo;
import persistencia.daos.DAOFoliosArchivo2;
import persistencia.daos.DAORevisionesArchivo;
import persistencia.daos.DAORevisionesArchivo2;
import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

public class FabricaArchivo2 implements IFabricaAbstracta {

	public IDAORevisiones crearIDAORevisiones(String codFolio) throws PersistenciaException {
		IDAORevisiones dao = new DAORevisionesArchivo2(codFolio);
		return dao;
	}

	public IDAOFolios crearIDAOFolios(IFabricaAbstracta fab) throws PersistenciaException {

			return new DAOFoliosArchivo2();

	}
}
