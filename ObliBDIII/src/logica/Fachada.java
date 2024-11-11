
package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Properties;

import logica.excepciones.LogicaException;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import logica.valueObjects.VORevision;
import persistencia.IConexion;
import persistencia.IPoolConexiones;
import persistencia.PoolConexiones;
import persistencia.PoolConexionesArchivo;
import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

/*import Ejercicio3.LogicaPersistencia.Excepciones.PersistenciaException;
import Ejercicio3.LogicaPersistencia.ValueObjects.*;*/

public class Fachada extends UnicastRemoteObject implements IFachada, Serializable {

	private IDAOFolios Folios;
	private static final long serialVersionUID = 1L;

	private IPoolConexiones pool;
	private IFabricaAbstracta fabrica;

	public Fachada() throws PersistenciaException, InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
		
		Properties prop = new Properties();
		
		String nomArch = "config/fabrica.properties";
		prop.load(new FileInputStream(nomArch));
		String nomFab = prop.getProperty("nombreFabrica");
		fabrica = (IFabricaAbstracta) Class.forName(nomFab).getDeclaredConstructor().newInstance();
		Folios = fabrica.crearIDAOFolios(fabrica);
	
		nomArch = "config/pool.properties";
		prop.load(new FileInputStream(nomArch));
		String nomPool = prop.getProperty("nombrePool");
		pool = (IPoolConexiones) Class.forName(nomPool).getDeclaredConstructor().newInstance();

	}

	public void agregarFolio(VOFolio voF) throws PersistenciaException, RemoteException, LogicaException {
		IConexion con = null;
		boolean ok = false, modifica = true;
		try {
			con = pool.obtenerConexion(modifica);
			if (!Folios.member(voF.getCodigo(), con)) {
				Folio folio = new Folio(voF.getCodigo(), voF.getCaratula(), voF.getPaginas(), fabrica);
				Folios.insert(folio, con);
				ok = true;
			} else {
				if (con != null) {
					pool.liberarConexion(con, ok);
				}
				throw new LogicaException("El folio ya existe en el sistema");
			}
			if (con != null) {
				pool.liberarConexion(con, ok);
			}
		} catch (PersistenciaException ex) {
			if (con != null) {
				pool.liberarConexion(con, ok);
			}
			throw ex;
		}

	}

	public void agregarRevision(String codF, String desc)
			throws PersistenciaException, RemoteException, LogicaException {
		IConexion con = null;
		boolean ok = false, modifica = true;
		try {
			con = pool.obtenerConexion(modifica);
			if (!Folios.member(codF, con)) {
				if (con != null) {
					pool.liberarConexion(con, ok);
				}
				throw new LogicaException("El folio ingresado no existe");
			} else {
				Folio fol = Folios.find(codF, con);
				int numR = fol.cantidadRevisiones(con) + 1;
				Revision rev = new Revision(numR, desc);
				fol.addRevision(rev, con);
				ok = true;
			}
			if (con != null) {
				pool.liberarConexion(con, ok);
			}
		} catch (PersistenciaException ex) {
			if (con != null) {
				pool.liberarConexion(con, ok);
			}
			throw ex;
		}

	}

	public void borrarFolioRevisiones(String codF) throws PersistenciaException, RemoteException, LogicaException {
		IConexion con = null;
		boolean ok = false, modifica = true;
		try {
			con = pool.obtenerConexion(modifica);
			if (Folios.member(codF, con)) {
				Folio fol = Folios.find(codF, con);
				fol.borrarRevisiones(con);
				Folios.delete(codF, con);
				ok = true;
			} else {
				if (con != null) {
					pool.liberarConexion(con, ok);
				}
				throw new LogicaException("No existe el folio");
			}
			if (con != null) {
				pool.liberarConexion(con, ok);
			}
		} catch (PersistenciaException ex) {
			if (con != null)
				pool.liberarConexion(con, ok);
			throw ex;
		}

	}

	public String darDescripcion(String codF, int numR) throws PersistenciaException, RemoteException, LogicaException {
		IConexion con = null;
		boolean ok = false, modifica = true;
		String descripcion = null;
		try {
			con = pool.obtenerConexion(modifica);
			if (Folios.member(codF, con)) {
				Folio fol = Folios.find(codF, con);
				if (fol.tieneRevision(numR, con)) {
					Revision rev = fol.obtenerRevision(numR, con);
					descripcion = rev.getDescripcion();
					ok = true;
				} else {
					if (con != null) {
						pool.liberarConexion(con, ok);
					}
					throw new LogicaException("No existe la revision");
				}
			} else {
				if (con != null) {
					pool.liberarConexion(con, ok);
				}
				throw new LogicaException("No existe el folio");
			}
			if (con != null) {
				pool.liberarConexion(con, ok);
			}
		} catch (PersistenciaException ex) {
			if (con != null)
				pool.liberarConexion(con, ok);
			throw ex;
		}
		return descripcion;
	}

	public List<VOFolio> listarFolios() throws PersistenciaException, RemoteException {
		IConexion con = null;
		boolean ok = false, modifica = true;
		List<VOFolio> ListadoFolios = null;
		try {
			con = pool.obtenerConexion(modifica);
			ListadoFolios = Folios.listarFolios(con);
			ok = true;
			if (con != null) {
				pool.liberarConexion(con, ok);
			}
		} catch (PersistenciaException ex) {
			if (con != null)
				pool.liberarConexion(con, ok);
			throw ex;
		}
		return ListadoFolios;
	}

	public List<VORevision> listarRevisiones(String codF)
			throws PersistenciaException, RemoteException, LogicaException {
		List<VORevision> ListadoRevisiones = null;
		IConexion con = null;
		boolean ok = false, modifica = true;
		try {
			con = pool.obtenerConexion(modifica);
			if (Folios.member(codF, con)) {
				Folio fol = Folios.find(codF, con);
				ListadoRevisiones = fol.listarRevisiones(con);
				ok = true;
			} else {
				if (con != null) {
					pool.liberarConexion(con, ok);
				}
				throw new LogicaException("No existe el folio");
			}
			if (con != null) {
				pool.liberarConexion(con, ok);
			}
		} catch (PersistenciaException ex) {
			if (con != null)
				pool.liberarConexion(con, ok);
			throw ex;
		}
		return ListadoRevisiones;
	}

	public VOFolioMaxRev folioMasRevisado() throws PersistenciaException, RemoteException, LogicaException {
		IConexion con = null;
		boolean ok = false, modifica = true;
		VOFolioMaxRev maxFolio = null;
		try {
			con = pool.obtenerConexion(modifica);
			if (Folios.esVacio(con)) {
				if (con != null)
					pool.liberarConexion(con, ok);
				throw new LogicaException("No existe ningún folio");
			} else {
				maxFolio = Folios.folioMasRevisado(con);
				if (maxFolio.getCantRevisiones() == 0) {
					pool.liberarConexion(con, ok);
					throw new LogicaException("No existe ningun folio con revisiones");
				}
				ok = true;
			}
			if (con != null)
				pool.liberarConexion(con, ok);
		} catch (PersistenciaException ex) {
			if (con != null)
				pool.liberarConexion(con, ok);
			throw ex;
		}
		return maxFolio;
	}

}
