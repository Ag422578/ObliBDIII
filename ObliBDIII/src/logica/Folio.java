package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import logica.excepciones.PersistenciaException;
import logica.valueObjects.*;
import persistencia.IConexion;
import persistencia.daos.DAORevisiones;
import persistencia.daos.IDAORevisiones;
import logica.Fachada;

public class Folio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo, caratula;
	private int paginas;
	private IDAORevisiones secuencia;

	public Folio(String codigo, String caratula, int paginas) throws PersistenciaException {
		try {
			this.codigo = codigo;
			this.caratula = caratula;
			this.paginas = paginas;
			Properties prop = new Properties();
			String nomArch = "config/fabrica.properties";
			prop.load(new FileInputStream(nomArch));
			String nomFab = prop.getProperty("nombreFabrica");
			IFabricaAbstracta fabrica = (IFabricaAbstracta) Class.forName(nomFab).getDeclaredConstructor().newInstance();
			secuencia = fabrica.crearIDAORevisiones(codigo);
		} catch (FileNotFoundException e) {
			throw new PersistenciaException("Error de persistencia");
		} catch (IOException e) {
			throw new PersistenciaException("Error de persistencia");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public String getCaratula() {
		return caratula;
	}

	public int getPaginas() {
		return paginas;
	}

	public boolean tieneRevision(int numR, IConexion con) throws PersistenciaException {
		boolean existe = false;
		if (secuencia.largo(con) >= numR)
			existe = true;
		return existe;
	}

	public int cantidadRevisiones(IConexion con) throws PersistenciaException {
		int largo = 0;
		largo = secuencia.largo(con);
		return largo;
	}

	public void addRevision(Revision rev, IConexion con) throws PersistenciaException {
		secuencia.insBack(rev, con);
	}

	public Revision obtenerRevision(int numR, IConexion con) throws PersistenciaException {
		Revision rev = null;
		rev = secuencia.kesimo(numR, con);
		return rev;
	}

	public List<VORevision> listarRevisiones(IConexion con) throws PersistenciaException {
		List<VORevision> revisiones = null;
		revisiones = secuencia.listarRevisiones(con);
		return revisiones;
	}

	public void borrarRevisiones(IConexion con) throws PersistenciaException {
		secuencia.borrarRevisiones(con);
	}
}
