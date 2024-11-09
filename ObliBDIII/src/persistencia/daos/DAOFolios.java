package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import logica.Folio;
import logica.IFabricaAbstracta;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.*;
import persistencia.Conexion;
import persistencia.IConexion;
import persistencia.consultas.Consultas;

public class DAOFolios implements IDAOFolios {
	private IFabricaAbstracta fabrica;
	public DAOFolios(IFabricaAbstracta fab) throws PersistenciaException {
		this.fabrica = fab;
	}

	public boolean member(String cod, IConexion con) throws PersistenciaException {
		Consultas cons = new Consultas();
		boolean existe = false;
		String query = cons.ExisteFolio();		
		try {
			Connection connection = ((Conexion) con).getConnection();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, cod);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error de acceso a datos.");
		}
		return existe;
	}

	public void insert(Folio fol, IConexion con) throws PersistenciaException {
		try {
			Connection connection = ((Conexion) con).getConnection();
			Consultas cons = new Consultas();
			String query = cons.AgregarFolio();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, fol.getCodigo());
			pstmt.setString(2, fol.getCaratula());
			pstmt.setInt(3, fol.getPaginas());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public Folio find(String cod, IConexion con) throws PersistenciaException {
		Consultas cons = new Consultas();
		Folio fol = null;
		String query = cons.ExisteFolio();
		try {
			Connection connection = ((Conexion) con).getConnection();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, cod);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				fol = new Folio(rs.getString("codigo"), rs.getString("caratula"), rs.getInt("paginas"), fabrica);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
		return fol;
	}

	public void delete(String cod, IConexion con) throws PersistenciaException {
		try {
			Connection connection = ((Conexion) con).getConnection();
			Consultas cons = new Consultas();
			String query = cons.BorrarFolio();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, cod);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public List<VOFolio> listarFolios(IConexion con) throws PersistenciaException {
		try {
			Connection connection = ((Conexion) con).getConnection();
			Consultas cons = new Consultas();
			String query = cons.ListarFolios();
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			LinkedList<VOFolio> ListaFolios = new LinkedList<>();
			while (rs.next()) {
				VOFolio r = new VOFolio(rs.getString("codigo"), rs.getString("caratula"), rs.getInt("paginas"));
				ListaFolios.add(r);
			}
			rs.close();
			return ListaFolios;
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public boolean esVacio(IConexion con) throws PersistenciaException {
		try {
			boolean vacio = true;
			Connection connection = ((Conexion) con).getConnection();
			Consultas cons = new Consultas();
			String query = cons.ListarFolios();
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				vacio = false;
			}
			return vacio;
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
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
