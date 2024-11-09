package persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import logica.excepciones.PersistenciaException;

public class EscrituraArchivo {
	private FileOutputStream f;
	private ObjectOutputStream o;

	public EscrituraArchivo(String nomArch) throws PersistenciaException {
		try {
			f = new FileOutputStream(nomArch);
			o = new ObjectOutputStream(f);
		} catch (IOException e) {
			throw new PersistenciaException("Error de persistencia");
		}
	}

	public FileOutputStream getOutputFile() {
		return this.f;
	}

	public ObjectOutputStream getOutputObject() {
		return this.o;
	}
	
	
	public void finalizarEscritura() throws PersistenciaException {
		try {
			f.close();
			o.close();
		} catch (IOException e) {
			throw new PersistenciaException("Error de persistencia");
		}
	}
}
