package persistencia;

import logica.IFabricaAbstracta;
import logica.excepciones.PersistenciaException;
import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

public class FabricaArchivo implements IFabricaAbstracta {
	
	public IDAORevisiones crearIDAORevisiones (String codFolio)  throws PersistenciaException{
		
	}
	
	public IDAOFolios crearIDAOFolios () throws PersistenciaException{
		
	}
}
