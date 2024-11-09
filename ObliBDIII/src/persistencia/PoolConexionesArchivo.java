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

	public PoolConexionesArchivo() {
		escribiendo = false;
		cantLectores = 0;
	}

	public synchronized IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		IConexion con = new ConexionArchivo();
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
		return con;
	}

	public synchronized void liberarConexion(IConexion con, boolean modifica) throws PersistenciaException {
		if (modifica) {
			escribiendo = false;
			notify();
		} else {
			cantLectores--;
			if (cantLectores == 0)
				notify();
		}
	}

}
