package logica;

import logica.excepciones.PersistenciaException;
import logica.valueObjects.*;
import persistencia.IConexion;
import persistencia.daos.IDAORevisiones;
import java.io.Serializable;
import java.util.List;

public class Folio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo, caratula;
	private int paginas;
	private IDAORevisiones secuencia;

	public Folio(String codigo, String caratula, int paginas, IFabricaAbstracta fabrica) throws PersistenciaException {
			this.codigo = codigo;
			this.caratula = caratula;
			this.paginas = paginas;
			secuencia = fabrica.crearIDAORevisiones(codigo);
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
