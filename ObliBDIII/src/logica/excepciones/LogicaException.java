package logica.excepciones;

public class LogicaException extends Exception {
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public LogicaException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
