package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import logica.excepciones.PersistenciaException;

public class ConexionArchivo implements IConexion {

	public ConexionArchivo() {
	}

	public void Escribir(String nomArch, Object obj) throws PersistenciaException {
		try {
			FileOutputStream f = new FileOutputStream(nomArch);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(obj);
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error al respaldar");
		}

	}
	
	public Object Listar(String nomArch) throws PersistenciaException {
		try {
			FileInputStream f = new FileInputStream(nomArch);
			ObjectInputStream o = new ObjectInputStream(f);
			Object Datos = (Object) o.readObject();
			o.close();
			f.close();
			return Datos;
		} catch (IOException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error al recuperar");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenciaException("No se encuentra la clase");
		}
	}
}
