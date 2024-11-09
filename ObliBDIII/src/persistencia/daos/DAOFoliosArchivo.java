package persistencia.daos;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import logica.Folio;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import persistencia.EscrituraArchivo;
import persistencia.IConexion;
import persistencia.LecturaArchivo;

public class DAOFoliosArchivo implements IDAOFolios, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TreeMap<String, Folio> Folios;
	LecturaArchivo l;
	EscrituraArchivo e;

	public DAOFoliosArchivo() throws PersistenciaException, IOException {
		try {
			Folios = new TreeMap<String, Folio>();
			l = new LecturaArchivo("Folios.txt");
			Folios = (TreeMap<String, Folio>) l.getInputObject().readObject();
			l.finalizarLectura();
		} catch (ClassNotFoundException e) {

		} catch (IOException e) {

		} catch (PersistenciaException e) {

		}
	}

	public boolean member(String c, IConexion arch) throws PersistenciaException {
		boolean existe = Folios.containsKey(c);
		return existe;

	}

	public Folio find(String c, IConexion arch) {
		Folio fol = Folios.get(c);
		return fol;
	}

	public void insert(Folio f, IConexion arch) throws PersistenciaException {
		try {
			Folios.put(f.getCodigo(), f);
			e = new EscrituraArchivo("folio-" + f.getCodigo() + ".txt");
			e.getOutputObject().writeObject(f);
			e.finalizarEscritura();
			e = new EscrituraArchivo("Folios.txt");
			e.getOutputObject().writeObject(Folios);
			e.finalizarEscritura();
		} catch (PersistenciaException e) {
			throw e;
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new PersistenciaException("Error de persistencia");
		}

	}

	public boolean esVacio(IConexion arch) {
		return Folios.isEmpty();
	}

	public void delete(String cod, IConexion arch) throws PersistenciaException {
		try {
			Folios.get(cod).borrarRevisiones(arch);
			Folios.remove(cod);
			e = new EscrituraArchivo("Folios.txt");
			e.getOutputObject().writeObject(Folios);
			e.finalizarEscritura();
		} catch (IOException e1) {
			throw new PersistenciaException("Error de persistencia");
		}
	}

	public List<VOFolio> listarFolios(IConexion con) throws PersistenciaException {
		List<VOFolio> VOFolios = new ArrayList<VOFolio>();
		Iterator<Folio> Iter = Folios.values().iterator();
		while (Iter.hasNext()) {
			Folio fol = Iter.next();
			VOFolio VOFol = new VOFolio(fol.getCodigo(), fol.getCaratula(), fol.getPaginas());
			VOFolios.add(VOFol);
		}
		return VOFolios;
	}

	public VOFolioMaxRev folioMasRevisado(IConexion con) throws PersistenciaException {
		Iterator<Folio> Iter = Folios.values().iterator();
		Folio fol = Iter.next();
		Folio folmaxRev = fol;
		while (Iter.hasNext()) {
			fol = Iter.next();
			if (folmaxRev.cantidadRevisiones(con) < fol.cantidadRevisiones(con))
				folmaxRev = fol;
		}
		VOFolioMaxRev revi = new VOFolioMaxRev(folmaxRev.getCodigo(), folmaxRev.getCaratula(), folmaxRev.getPaginas(),
				folmaxRev.cantidadRevisiones(con));
		return revi;
	}
}
