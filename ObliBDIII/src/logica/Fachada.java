

package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import persistencia.daos.DAOFolios;

import Ejercicio3.LogicaPersistencia.Excepciones.PersistenciaException;
import Ejercicio3.LogicaPersistencia.ValueObjects.*;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;

public class Fachada {
	
	// acondicioné y arranqué con los de folio para adelantar que era solo invocar a los metodos del dao
	// no creo que sean con catch, capaz throw, pero lo vemos mañana
	
	private DAOFolios daoFolios;

	public void agregarFolio(VOFolio voF) {
		 try 
		 {
			 if(!daoFolios.member(voF.getCodigo()))
			 {
				 Folio folio = new Folio(voF.getCodigo(), voF.getCaratula(), voF.getPaginas());
			 	 daoFolios.insert(folio);
			 }	
		 } catch(PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }

	}
	/*
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
	*/
	public void borrarFolioRevisiones(String codF) {
		try {
			if (daoFolios.member(codF)) {
				// ClaseAccesoBD.BorrarFolioRevisiones(con, codF);  Acá me genera dudas lo que dice de que accede interno
				daoFolios.delete(codF);
			}
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
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
	*/
	public List<VOFolio> listarFolios() {
		List<VOFolio> Folios = null;
		try {
			Folios = daoFolios.listarFolios();
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Folios;
	}
	/*
	public List<VORevision> listarRevisiones (String codF)
	{
	
	}
	*/
	public VOFolioMaxRev folioMasRevisado ()
	{
		VOFolioMaxRev voFolMaxRev = null;
		try {
			daoFolios.folioMasRevisado();
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voFolMaxRev;
	}

}
