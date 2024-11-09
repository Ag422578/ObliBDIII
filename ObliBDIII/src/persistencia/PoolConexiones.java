package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Connection;

import logica.excepciones.PersistenciaException;

public class PoolConexiones implements IPoolConexiones {
	private String driver, url, user, password;
	private int nivelTransaccionalidad;
	private Conexion[] conexiones;
	private int tamanio, creadas, tope;

	public PoolConexiones() throws PersistenciaException {
		Properties prop = new Properties();
		String nomArch = "config/conexion.properties";
		try {
			prop.load(new FileInputStream(nomArch));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("passw");
		} catch (FileNotFoundException e) {
			throw new PersistenciaException("Error al acceder al archivo de configuración");

		} catch (IOException e) {
			throw new PersistenciaException("Error de lectura de archivo");
		}
		nivelTransaccionalidad = Connection.TRANSACTION_SERIALIZABLE;
		creadas = 0;
		tope = 0;
		tamanio = 3; // leerlo del properties
		conexiones = new Conexion[tamanio];
	}

	public synchronized IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		IConexion con = null;
		try {
			while (con == null) {
				if (tope > 0) {
					con = conexiones[tope - 1];
					tope--;
				} else if (creadas < tamanio) {
					Connection connection = DriverManager.getConnection(url, user, password);
					con = new Conexion(connection);
					creadas++;
				} else {
					wait();
				}
			}
			((Conexion) con).getConnection().setAutoCommit(false);
			((Conexion) con).getConnection().setTransactionIsolation(nivelTransaccionalidad);
		} catch (SQLException ex) {
			throw new PersistenciaException("Error al obtener conexión");
		} catch (InterruptedException e) {
			throw new PersistenciaException("Error al obtener conexión");
		}
		return con;
	}

	public synchronized void liberarConexion(IConexion con, boolean ok) throws PersistenciaException {
		try {
			if (ok) {
				((Conexion) con).getConnection().commit();
			} else {
				((Conexion) con).getConnection().rollback();
			}
			conexiones[tope] = (Conexion) con;
			tope++;
			notify();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new PersistenciaException("Error al liberar conexión");
		}

	}
}
