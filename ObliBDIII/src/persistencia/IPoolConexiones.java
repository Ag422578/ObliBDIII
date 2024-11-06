package persistencia;

public interface IPoolConexiones {
	public IConexion obtenerConexion (boolean  modifica) throws Exception;
	public void liberarConexion (IConexion con, boolean  ok) throws Exception;
}
