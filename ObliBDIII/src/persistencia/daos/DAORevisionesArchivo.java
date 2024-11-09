package persistencia.daos;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import logica.Revision;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VORevision;
import persistencia.EscrituraArchivo;
import persistencia.IConexion;
import persistencia.LecturaArchivo;

public class DAORevisionesArchivo implements IDAORevisiones, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Revision> Revisiones;
	private String codigoFolio;
	LecturaArchivo l;
	EscrituraArchivo e;

	public DAORevisionesArchivo(String codF) throws PersistenciaException {
		try {
			this.codigoFolio = codF;
			Revisiones = new ArrayList<Revision>();
			l = new LecturaArchivo("revisiones-" + codigoFolio + ".txt");
			Revisiones = (List<Revision>) l.getInputObject().readObject();
			l.finalizarLectura();
		} catch (ClassNotFoundException e) {
			throw new PersistenciaException("Error de persistencia");
		} catch (IOException e) {
			throw new PersistenciaException("Error de persistencia");
		}
	}

	public void insBack(Revision rev, IConexion con) throws PersistenciaException {
		try {
			System.out.println("HOLA");
			Revisiones.add(rev);
			e = new EscrituraArchivo("revisiones-" + codigoFolio + ".txt");
			e.getOutputObject().writeObject(Revisiones);
			e.finalizarEscritura();
		} catch (IOException e) {
			throw new PersistenciaException("Error de Persistencia");
		} catch (PersistenciaException e2) {
			throw e2;
		}

	}

	public int largo(IConexion con) throws PersistenciaException {
		return Revisiones.size();
	}

	public Revision kesimo(int numero, IConexion con) throws PersistenciaException {
		return Revisiones.get(numero);
	}

	public List<VORevision> listarRevisiones(IConexion con) throws PersistenciaException {
		List<VORevision> VORevisiones = new ArrayList<VORevision>();
		for (int i = 0; i < Revisiones.size(); i++) {
			Revision rev = Revisiones.get(i);
			VORevision VOrev = new VORevision(rev.getNumero(), rev.getDescripcion(), codigoFolio);
			VORevisiones.add(VOrev);
		}
		return VORevisiones;
	}

	public void borrarRevisiones(IConexion con) throws PersistenciaException {
		try {
			for (int i = 0; i < Revisiones.size(); i++) {
				Revisiones.remove(i);
			}
			e = new EscrituraArchivo("revisiones-" + codigoFolio + ".txt");
			e.getOutputObject().writeObject(Revisiones);
		} catch (PersistenciaException e) {
			throw e;
		} catch (IOException e1) {
			throw new PersistenciaException("Error de persistencia");
		}

	}
}