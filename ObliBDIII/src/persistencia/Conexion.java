package persistencia;

import java.sql.Connection;

public class Conexion implements IConexion {
	private Connection con;
	
	public Conexion(Connection connection) {
		this.con = connection;
	}
	
	public Connection getConnection() {
		return this.con;
	}
}
