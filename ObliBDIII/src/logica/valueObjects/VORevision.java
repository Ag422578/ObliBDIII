package logica.valueObjects;

import java.io.Serializable;

public class VORevision implements Serializable {
	private int numero;
	private String descripcion;
	private String codFolio;
	private static final long serialVersionUID = 1L;
	
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
