package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

import logica.excepciones.PersistenciaException;

public class PoolConexiones {
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
		nivelTransaccionalidad = 0;
		creadas = 0;
		tope = 0;
		tamanio = 3;
		conexiones = new Conexion[tamanio];
	}

	public IConexion obtenerConexion(boolean modifica) throws Exception {
		IConexion con = null;
		while (tope == 0 && creadas == tamanio)
			wait();
		if (tope != 0) {
			con = (IConexion) conexiones[tope];
			tope--;
			if (modifica)
				nivelTransaccionalidad = 1;
		} else if (creadas != tamanio) {
			con = (IConexion) DriverManager.getConnection(url, user, password);
			creadas++;
			if (modifica)
				nivelTransaccionalidad = 1;
		}
		return con;
	}

	public void liberarConexion(IConexion con, boolean ok) throws Exception {
		if (nivelTransaccionalidad != 0) {
			if (ok) {
				((Conexion) con).getConnection().commit();
			} else {
				((Conexion) con).getConnection().rollback();
			}
		}
		conexiones[tope] = (Conexion) con;
		tope++;
		notify();
	}
}
