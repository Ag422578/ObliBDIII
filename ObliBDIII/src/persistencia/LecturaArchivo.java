package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import logica.Folio;
import logica.excepciones.PersistenciaException;

public class LecturaArchivo {
	private FileInputStream f;
	private ObjectInputStream o;

	public LecturaArchivo(String nomArch) throws PersistenciaException {
		try {
			f = new FileInputStream(nomArch);
			o = new ObjectInputStream(f);
		} catch (IOException e) {
			throw new PersistenciaException("Error de persistencia");
		}
	}

	public FileInputStream getInputFile() {
		return this.f;
	}

	public ObjectInputStream getInputObject() {
		return this.o;
	}

	public void finalizarLectura() throws PersistenciaException {
		try {
			f.close();
			o.close();
		} catch (IOException e) {
			throw new PersistenciaException("Error de persistencia");
		}
	}

	public Folio recuperarFolio(String Cod) throws PersistenciaException {
		try {
			Folio fol = (Folio) o.readObject();
			return fol;
		} catch (ClassNotFoundException e) {
			throw new PersistenciaException("Error de persistencia");
		} catch (IOException e) {
			throw new PersistenciaException("Error de persistencia");
		}
	}

}
