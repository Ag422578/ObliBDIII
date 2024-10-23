package logica.valueObjects;

public class VORevision {
	private int numero;
	private String descripcion;
	private String codFolio;
	
	public VORevision(int numero, String descripcion, String codFolio) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
		this.codFolio = codFolio;
	}
	public int getNumero() {
		return numero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getCodFolio() {
		return codFolio;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setCodFolio(String codFolio) {
		this.codFolio = codFolio;
	}
	
	
	
}
