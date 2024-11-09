package persistencia;

import logica.IFabricaAbstracta;
import logica.excepciones.PersistenciaException;
import persistencia.daos.DAOFolios;
import persistencia.daos.DAORevisiones;
import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

public class FabricaMYSQL implements IFabricaAbstracta {
	
	public IDAORevisiones crearIDAORevisiones (String codFolio) throws PersistenciaException {
		IDAORevisiones dao = new DAORevisiones(codFolio);
		return dao;
	}
	
	public IDAOFolios crearIDAOFolios (IFabricaAbstracta fab) throws PersistenciaException{
		IDAOFolios dao = new DAOFolios(fab);
		return dao;
	}
}
