package persistencia.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import logica.Folio;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import persistencia.Conexion;
import persistencia.IConexion;
import persistencia.consultas.Consultas;

public class DAOFoliosArchivo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<String, Folio> Folios;
	

	public DAOFoliosArchivo() {
		Folios = new TreeMap<String, Folio>();
	}

	public boolean member(int c) {
		return (Folios.containsKey(c));
	}

	public Folio find(String c) {
		return (Folios.get(c));
	}

	public void insert(Folio f, String c) {
		Folios.put(c, f);
	}

	public boolean esVacio() {
		return (Folios.isEmpty());
	}

	public void delete(String cod) throws PersistenciaException {
		Folios.remove(cod);
	}
	
	public List<VOFolio> listarFolios(IConexion con) throws PersistenciaException {
		;
		VOAsig[] Asigs = new VOAsig[DiccioCarrera.largo()];
		if (DiccioCarrera.estaVacio()) {
			monitor.terminoLectura();
			throw new CarreraVaciaException("La carrera no tiene asignaturas");
		} else
			Asigs = DiccioCarrera.listarAsignaturas();
		monitor.terminoLectura();
		return Asigs;
	}
	
	public VOFolioMaxRev folioMasRevisado(IConexion con) throws PersistenciaException {
		Consultas cons = new Consultas();
		VOFolioMaxRev fol = null;
		String query = cons.FolioMasRevisado();
		try {
			Connection connection = ((Conexion) con).getConnection();
			Statement pstmt = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next()) {
				fol = new VOFolioMaxRev(rs.getString("codigo"), rs.getString("caratula"), rs.getInt("paginas"),
						rs.getInt("cantidad"));
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
		return fol;
	}
}
