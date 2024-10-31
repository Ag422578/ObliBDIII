package persistencia.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import logica.Revision;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VORevision;
import persistencia.consultas.Consultas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.Cleaner;


public class DAORevisiones {
	private String driver;
	private String url;
	private String user;
	private String passw;
	private String codigoFolio;
	
	
	
	Connection obtenerCon() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, passw);
	}
	
	void cerrar(Connection con) throws SQLException {
		con.close();
	}


	public DAORevisiones() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
		super();
			driver = "com.mysql.jdbc.Driver";
			Properties p = new Properties();
			String nomArch = "C:\\Desarrollo\\PrimerEjemploBD\\src\\config.properties";
			p.load(new FileInputStream(nomArch));
			url = p.getProperty("url2");
			user = p.getProperty("usuario");
			passw = p.getProperty("password");

	}

	public DAORevisiones(String codF) {
		this.codigoFolio = codF;
	}

	public void insBack(Revision rev) throws ClassNotFoundException, PersistenciaException {
		try {
			Consultas cons = new Consultas();
			String query = cons.AgregarRevision();
			Connection con = obtenerCon();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rev.getNumero());
			pstmt.setString(2, rev.getDescripcion());
			pstmt.setString(3, codigoFolio);
			pstmt.executeUpdate();
			pstmt.close();
			cerrar(con);
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public int largo() throws ClassNotFoundException, SQLException {
		Consultas cons = new Consultas();
		int num = 0;
		String query = cons.ExisteFolio();
		Connection con = null;
		try {
			con = obtenerCon();

			Statement pstmt = con.createStatement();


			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next()) {
				num = rs.getInt("numero");
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrar(con);
		return num;
	}

	public Revision kesimo(int numero) {
		
		return null;
	}

	public List<VORevision> listarRevisiones() throws ClassNotFoundException, PersistenciaException {
		try {
			Consultas cons = new Consultas();
			String query = cons.ListarRevisiones();
			Connection con = obtenerCon();

			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			LinkedList<VORevision> ListaRevisiones = new LinkedList<>();
			while (rs.next()) {
				VORevision r = new VORevision(rs.getInt("numero"), rs.getString("descripcion"),
						rs.getString("codFolio"));
				ListaRevisiones.add(r);
			}
			rs.close();
			cerrar(con);
			return ListaRevisiones;
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public void borrarRevisiones() throws ClassNotFoundException, PersistenciaException {
		try {
			Consultas cons = new Consultas();
			String query = cons.BorrarFolioRevisiones();
		
			Connection con = obtenerCon();

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, codigoFolio);
			pstmt.executeUpdate();
			pstmt.close();
			cerrar(con);
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}
}
