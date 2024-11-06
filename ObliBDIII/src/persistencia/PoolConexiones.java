package persistencia;

import java.sql.SQLException;

public class PoolConexiones {
	private String driver, url, user, password;
	private int nivelTransaccionalidad;
	private Conexion[] conexiones;
	private int tamanio, creadas, tope;
	
	public PoolConexiones() {
		
	}
	
	public IConexion obtenerConexion(boolean  modifica) throws Exception {
		while(creadas == tamanio) {
			wait();
		}
		creadas++;
		tope++;
		IConexion con = conexiones[creadas];
		modifica = true;
		return con;
	}
	
	public void liberarConexion(IConexion con, boolean  ok) throws Exception {
		if(ok) {
			((Conexion) con).getConnection().commit();
		}
		else {
			((Conexion) con).getConnection().rollback();
		}
		tope--;
		creadas--;
		conexiones[creadas] = (Conexion) con;
		notify();
	}
}
