package logica.valueObjects;

import java.io.Serializable;

public class VOFolio implements Serializable{
	private String codigo;
	private String caratula;
	private int paginas;
	private static final long serialVersionUID = 1L;
	
	public VOFolio(String codigo, String caratula, int paginas) {
		super();
		this.codigo = codigo;
		this.caratula = caratula;
		this.paginas = paginas;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public String getCaratula() {
		return caratula;
	}
	public int getPaginas() {
		return paginas;
	}
	
	
}
