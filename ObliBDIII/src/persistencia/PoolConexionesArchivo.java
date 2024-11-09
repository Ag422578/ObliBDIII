package persistencia;

import java.io.Serializable;

import logica.excepciones.PersistenciaException;

public class PoolConexionesArchivo implements IPoolConexiones, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean escribiendo;
	private static int cantLectores;
	private ConexionArchivo arch;

	public PoolConexionesArchivo() {
		escribiendo = false;
		cantLectores = 0;
	}

	public synchronized IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		IConexion conexion = new ConexionArchivo();
		if (modifica) {
			while (!escribiendo) {
				if (cantLectores == 0) {
					escribiendo = true;
				} else
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		} else {
			while (escribiendo)
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			cantLectores++;
		}
		return conexion;
	}

	public synchronized void liberarConexion(IConexion con, boolean ok) throws PersistenciaException {
	}

	public synchronized void comienzoLectura() {
		while (escribiendo)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		cantLectores++;
	}

	public synchronized void terminoLectura() {
		cantLectores--;
		if (cantLectores == 0)
			notify();
	}

	public synchronized void comienzoEscritura() {
		while (!escribiendo) {
			if (cantLectores == 0) {
				escribiendo = true;
			} else
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public synchronized void terminoEscritura() {
		escribiendo = false;
		notify();
	}

}
