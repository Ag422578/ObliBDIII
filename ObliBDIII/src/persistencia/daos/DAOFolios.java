package persistencia.daos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import logica.Folio;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.*;
import persistencia.consultas.Consultas;

public class DAOFolios {
	private String driver;
	private String url;
	private String user;
	private String passw;

	public DAOFolios() throws PersistenciaException {
		Properties prop = new Properties();
		String nomArch = "config/conexion.properties";
		try {
			prop.load(new FileInputStream(nomArch));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			passw = prop.getProperty("passw");
		} catch (FileNotFoundException e) {
			throw new PersistenciaException("Error al acceder al archivo de configuración");
		
		} catch (IOException e) {
			throw new PersistenciaException("Error de lectura de archivo");
		}
	}

	public boolean member(String cod) throws PersistenciaException {
		Consultas cons = new Consultas();
		boolean existe = false;
		String query = cons.ExisteFolio();
		
		try {
			Connection con = DriverManager.getConnection(url, user, passw);
			PreparedStatement pstmt = con.prepareStatement(query);
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

	public void insert(Folio fol) throws PersistenciaException {
		try {
			Connection con = DriverManager.getConnection(url, user, passw);
			Consultas cons = new Consultas();
			String query = cons.AgregarFolio();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, fol.getCodigo());
			pstmt.setString(2, fol.getCaratula());
			pstmt.setInt(3, fol.getPaginas());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public Folio find(String cod) throws PersistenciaException {
		Consultas cons = new Consultas();
		Folio fol = null;
		String query = cons.ExisteFolio();
		try {
			Connection con = DriverManager.getConnection(url, user, passw);
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, cod);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				fol = new Folio(rs.getString("codigo"), rs.getString("caratula"), rs.getInt("paginas"));
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
		return fol;
	}

	public void delete(String cod) throws PersistenciaException {
		try {
			Connection con = DriverManager.getConnection(url, user, passw);
			Consultas cons = new Consultas();
			String query = cons.BorrarFolio();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, cod);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public List<VOFolio> listarFolios() throws PersistenciaException {
		try {
			Connection con = DriverManager.getConnection(url, user, passw);
			Consultas cons = new Consultas();
			String query = cons.ListarFolios();
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			LinkedList<VOFolio> ListaFolios = new LinkedList<>();
			while (rs.next()) {
				VOFolio r = new VOFolio(rs.getString("codigo"), rs.getString("caratula"), rs.getInt("pagina"));
				ListaFolios.add(r);
			}
			rs.close();
			return ListaFolios;
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public boolean esVacio() throws PersistenciaException {
		try {
			boolean vacio = true;
			Connection con = DriverManager.getConnection(url, user, passw);
			Consultas cons = new Consultas();
			String query = cons.ListarFolios();
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next()) {
				vacio = false;
			}
			return vacio;
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public VOFolioMaxRev folioMasRevisado() throws PersistenciaException {
		Consultas cons = new Consultas();
		VOFolioMaxRev fol = null;
		String query = cons.FolioMasRevisado();
		try {
			Connection con = DriverManager.getConnection(url, user, passw);
			Statement pstmt = con.createStatement();
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next()) {
				fol = new VOFolioMaxRev(rs.getString("codigo"), rs.getString("caratula"), rs.getInt("pagina"),
						rs.getInt("cantidad"));
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
		return fol;
	}
}
