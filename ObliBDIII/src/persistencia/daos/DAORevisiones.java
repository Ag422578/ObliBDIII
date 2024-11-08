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

import logica.Revision;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VORevision;
import persistencia.Conexion;
import persistencia.IConexion;
import persistencia.consultas.Consultas;

public class DAORevisiones {
	private String codigoFolio;

	public DAORevisiones(String codF) throws PersistenciaException {
		this.codigoFolio = codF;
	}

	public void insBack(Revision rev, IConexion con) throws PersistenciaException {
		try {
			Connection connection = ((Conexion) con).getConnection();
			Consultas cons = new Consultas();
			String query = cons.AgregarRevision();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, rev.getNumero());
			pstmt.setString(2, codigoFolio);
			pstmt.setString(3, rev.getDescripcion());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public int largo(IConexion con) throws PersistenciaException {
		Consultas cons = new Consultas();
		int num = 0;
		String query = cons.cantidadRevisiones();
		try {
			Connection connection = ((Conexion) con).getConnection();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, codigoFolio);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("cantidad");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public Revision kesimo(int numero, IConexion con) throws PersistenciaException {
		try {
			Connection connection = ((Conexion) con).getConnection();
			Consultas cons = new Consultas();
			String query = cons.listarRevision();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, codigoFolio);
			pstmt.setInt(2, numero);
			ResultSet rs = pstmt.executeQuery();
			String descripcion = "";
			if (rs.next()) {
				descripcion = rs.getString("descripcion");
			}
			Revision rev = new Revision(numero, descripcion);
			rs.close();
			pstmt.close();
			return rev;
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public List<VORevision> listarRevisiones(IConexion con) throws PersistenciaException {
		try {
			Connection connection = ((Conexion) con).getConnection();
			Consultas cons = new Consultas();
			String query = cons.ListarRevisiones();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, codigoFolio);
			ResultSet rs = pstmt.executeQuery();
			LinkedList<VORevision> ListaRevisiones = new LinkedList<>();
			while (rs.next()) {
				VORevision r = new VORevision(rs.getInt("numero"), rs.getString("descripcion"),
						rs.getString("codFolio"));
				ListaRevisiones.add(r);
			}
			rs.close();
			return ListaRevisiones;
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public void borrarRevisiones(IConexion con) throws PersistenciaException {
		try {
			Connection connection = ((Conexion) con).getConnection();
			Consultas cons = new Consultas();
			String query = cons.BorrarFolioRevisiones();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, codigoFolio);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}
}
