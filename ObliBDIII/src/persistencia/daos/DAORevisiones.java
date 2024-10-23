package persistencia.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import logica.Revision;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VORevision;
import persistencia.consultas.Consultas;

public class DAORevisiones {
	private String driver;
	private String url;
	private String user;
	private String passw;
	private String codigoFolio;

	public DAORevisiones(String codF) {
		this.codigoFolio = codF;
	}

	public void insBack(Revision rev) {
		try {
			Consultas cons = new Consultas();
			String query = cons.AgregarRevision();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rev.getNumero());
			pstmt.setString(2, rev.getDescripcion());
			pstmt.setString(3, codigoFolio);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}

	public int largo() {
		Consultas cons = new Consultas();
		int num = 0;
		String query = cons.ExisteFolio();
		try {
			Statement pstmt = con.createStatement();
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next()) {
				num = rs.getInt("numero");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public Revision kesimo(int numero) {
		
	}

	public List<VORevision> listarRevisiones() {
		try {
			Consultas cons = new Consultas();
			String query = cons.ListarRevisiones();
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
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

	public void borrarRevisiones() {
		try {
			Consultas cons = new Consultas();
			String query = cons.BorrarFolioRevisiones();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, codigoFolio);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error de acceso a datos.");
		}
	}
}
