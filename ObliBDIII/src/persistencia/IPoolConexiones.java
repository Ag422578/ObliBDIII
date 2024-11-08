package persistencia;

import logica.excepciones.PersistenciaException;

public abstract interface IPoolConexiones {
	public IConexion obtenerConexion (boolean  modifica) throws PersistenciaException;
	public void liberarConexion (IConexion con, boolean ok) throws PersistenciaException;
}
