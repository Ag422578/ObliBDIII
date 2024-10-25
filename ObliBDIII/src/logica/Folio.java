package logica;

import java.util.List;

import logica.excepciones.PersistenciaException;
import logica.valueObjects.*;
import persistencia.daos.DAORevisiones;

public class Folio {
	private String codigo, caratula;
	private int paginas;
	private DAORevisiones secuencia;

	public Folio(String codigo, String caratula, int paginas) throws PersistenciaException {
		this.codigo = codigo;
		this.caratula = caratula;
		this.paginas = paginas;
		this.secuencia = new DAORevisiones(codigo);
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

	public boolean tieneRevision(int numR) throws PersistenciaException {
		boolean existe = false;
		if (secuencia.largo() >= numR)
			existe = true;
		return existe;
	}

	public int cantidadRevisiones() throws PersistenciaException {
		int largo = 0;
		largo = secuencia.largo();
		return largo;
	}

	public void addRevision(Revision rev) throws PersistenciaException {
		secuencia.insBack(rev);
	}

	public Revision obtenerRevision(int numR) throws PersistenciaException {
		Revision rev = null;
		rev = secuencia.kesimo(numR);
		return rev;
	}

	public List<VORevision> listarRevisiones() throws PersistenciaException {
		List<VORevision> revisiones = null;
		revisiones = secuencia.listarRevisiones();
		return revisiones;
	}

	public void borrarRevisiones() throws PersistenciaException {
		secuencia.borrarRevisiones();
	}
}
