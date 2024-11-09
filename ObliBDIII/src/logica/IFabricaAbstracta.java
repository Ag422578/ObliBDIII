package logica;

import logica.excepciones.PersistenciaException;
import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

public interface IFabricaAbstracta {
	public IDAORevisiones crearIDAORevisiones (String codFolio)  throws PersistenciaException;
	public IDAOFolios crearIDAOFolios () throws PersistenciaException;
}
