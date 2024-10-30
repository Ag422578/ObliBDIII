

package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/*import Ejercicio3.LogicaPersistencia.Excepciones.PersistenciaException;
import Ejercicio3.LogicaPersistencia.ValueObjects.*;*/

public class Fachada {
	/*
	AccesoBD ClaseAccesoBD = new AccesoBD();
	Connection con = ClaseAccesoBD.crearConexion();

	public void agregarFolio(VOFolio voF) {
		try {
			if (!ClaseAccesoBD.ExisteFolio(con, voF.getCodigo()))
				ClaseAccesoBD.AgregarFolio(null, voF);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void agregarRevision(String codF, String desc) {
		try {
			if (ClaseAccesoBD.ExisteFolio(con, codF)) {
				int numero = ClaseAccesoBD.calcularUltimaRevision(con) + 1;
				VORevision r = new VORevision(numero, codF, desc);
				ClaseAccesoBD.AgregarRevision(con, r);
			}
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void borrarFolioRevisiones(String codF) {
		try {
			if (ClaseAccesoBD.ExisteFolio(con, codF)) {
				ClaseAccesoBD.BorrarFolioRevisiones(con, codF);
				ClaseAccesoBD.BorrarFolio(con, codF);
			}
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String darDescripcion(String codF, int numR) {
		String descripcion = null;
		try {
			if (ClaseAccesoBD.ExisteFolio(con, codF)) {
				if (ClaseAccesoBD.ExisteRevision(con, codF, numR)) {
					descripcion = ClaseAccesoBD.DarDescripcion(con, codF, numR);
				}
			}
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descripcion;
	}
	
	public List<VOFolio> listarFolios() {
		List<VOFolio> Folios = null;
		try {
			Folios = ClaseAccesoBD.ListarFolios(con);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Folios;
	}
	*/

}
