
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class main {

	public static void main(String[] args) {
		 try {
	           Properties prop = new Properties();
	           String nomArch = "config/conexion.properties";
	           prop.load(new FileInputStream(nomArch));

	           String driver = prop.getProperty("driver");
	           String url = prop.getProperty("url");
	           String user = prop.getProperty("user");
	           String passw = prop.getProperty("passw");

	           Class.forName(driver);
	           Connection con = DriverManager.getConnection(url , user, passw);

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


