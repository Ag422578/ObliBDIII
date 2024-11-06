package persistencia.consultas;

public class Consultas {

	public String ExisteFolio() {
		return "SELECT * FROM Folios WHERE codigo = ?";
	}
	
	public String cantidadRevisiones() {
		return "SELECT COUNT(*) AS cantidad FROM Revisiones WHERE codFolio = ?";
	}
	
	public String existeRevision() {
		return "SELECT numero from Revisiones WHERE codFolio = ? AND numero = ?";
	}
	
	public String listarRevision() {
		return "SELECT * from Revisiones WHERE codFolio = ? AND numero = ?";
	}
	
	public String UltimaRevision() {
		return "SELECT numero FROM Revisiones ORDER BY numero LIMIT 1";
	}
	
	public String AgregarFolio() {
		return "INSERT INTO Folios VALUES (?,?,?)";
	}

	public String AgregarRevision() {
		return "INSERT INTO Revisiones(numero, codFolio, descripcion)  VALUES (?,?,?)";
	}

	public String BorrarFolioRevisiones() {
		return "DELETE FROM Revisiones WHERE codFolio = ?";
	}

	public String BorrarFolio() {
		return "DELETE FROM Folios WHERE codigo = ?";
	}

	public String DarDescripcion() {
		return "SELECT Descripcion FROM Revisiones WHERE codFolio = ? AND numero = ?";
	}

	public String ListarFolios() {
		return "SELECT * FROM Folios ORDER BY codigo ASC";
	}

	public String ListarRevisiones() {
		return "SELECT * FROM Revisiones WHERE codFolio = ? ORDER BY numero ASC";
	}

	public String FolioMasRevisado() {
		return "SELECT *,(SELECT COUNT(*) FROM Revisiones WHERE codFolio = codigo) AS Cantidad from Folios a JOIN (SELECT codFolio from Revisiones GROUP BY codFolio ORDER BY COUNT(codFolio) DESC LIMIT 1) a2 on a.codigo = a2.codFolio";
	}
	
}
