package logica;

public class Revision {
	private int numero;
	private String descripcion;
	
	public Revision(int num, String desc) {
		this.numero = num;
		this.descripcion = desc;
	}

	public int getNumero() {
		return numero;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	
}
