
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import logica.Folio;
import logica.valueObjects.VOFolio;
import persistencia.daos.DAOFoliosArchivo;
import persistencia.daos.DAOFoliosArchivo2;


public class main {

	public static void main(String[] args) {
		 try {
			 
			 
			 DAOFoliosArchivo2 d = new DAOFoliosArchivo2();
			 
			 Folio f = new Folio("212-99", "algo", 8);
			 
			 d.insert(f, null);
			 d.insert(new Folio("212-992", "algo", 8), null);

			 
			 System.out.print(d.member("212-99--", null));
			 System.out.print(d.member("212-99", null));
			
			 System.out.print(d.find("212-99",null).getCaratula());
			 //d.delete("212-99", null);
			 System.out.print(d.member("212-99", null));
			 
			 for (VOFolio vo: d.listarFolios(null)) {
				 System.out.println(vo.getCodigo() + " " + vo.getCaratula() );
			 }

			 
			 
			 /*
	           Properties prop = new Properties();
	           String nomArch = "config/conexion.properties";
	           prop.load(new FileInputStream(nomArch));

	           String driver = prop.getProperty("driver");
	           String url = prop.getProperty("url");
	           String user = prop.getProperty("user");
	           String passw = prop.getProperty("passw");

	           Class.forName(driver);
	           Connection con = DriverManager.getConnection(url , user, passw);
*/
	           /*String crearTablaFolios = "CREATE TABLE Estudio.Folios (codigo VARCHAR(60) not null primary key, caratula VARCHAR(60) not null, paginas INT not null);";

	           String crearTablaRevisiones = "CREATE TABLE Estudio.Revisiones (numero INT not null, codFolio VARCHAR(60) not null, descripcion VARCHAR(60) not null, primary key (numero, codFolio), foreign key (codFolio) references Estudio.Folios (codigo));";
	           

	           String insertQuery = "INSERT INTO Estudio.Folios VALUES " +
	        		   "('FGH-0015', 'La comuna contra la señora que tiene 38 gatos', 5), " +
	        		   "('BBD-1278', 'Adolescentes descontrolados hasta las 5 AM', 2), " +
	        		   "('JJ-202', 'Vecinos reclaman por heces de perro en el hall', 9), " +
	        		   "('CEFJ-63', 'Vecinas rivales se arrojan macetas con frecuencia', 463);";

	           Statement stm1 = con.createStatement();
	           stm1.executeUpdate(crearTablaFolios);

	           Statement stm2 = con.createStatement();
	           stm2.executeUpdate(crearTablaRevisiones);

	           Statement stm4 = con.createStatement();
	           stm4.executeUpdate(insertQuery);
	           
	           stm1.close();
	           stm2.close();
	           stm4.close();*/
	           
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }

	  /* private static void CrearBaseDeDatos(Properties prop) throws ClassNotFoundException, SQLException {
	       String driver = prop.getProperty("driver");
	       String url = prop.getProperty("url");
	       String user = prop.getProperty("user");
	       String passw = prop.getProperty("passw");

	       Class.forName(driver);
	       Connection con = DriverManager.getConnection(url, user, passw);

	       String crearBaseQry = "CREATE DATABASE Estudio";

	       Statement stm = con.createStatement();
	       stm.executeUpdate(crearBaseQry);

	       con.close();
	   }*/	
	
		



		 
}


