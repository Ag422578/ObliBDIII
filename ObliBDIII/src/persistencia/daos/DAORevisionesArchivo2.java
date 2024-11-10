package persistencia.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import logica.Folio;
import logica.Revision;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VORevision;
import persistencia.EscrituraArchivo;
import persistencia.IConexion;
import persistencia.LecturaArchivo;

public class DAORevisionesArchivo2 implements IDAORevisiones, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoFolio;

	public DAORevisionesArchivo2(String codF) throws PersistenciaException {
		this.codigoFolio = codF;
	/*	try {
			LecturaArchivo l = new LecturaArchivo("revisiones-" + codigoFolio + ".txt");
			Revisiones = (ArrayList<Revision>) l.getInputObject().readObject();
			l.finalizarLectura();
		} catch (NullPointerException e) {
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		*/
	}

	public void insBack(Revision rev, IConexion con) throws PersistenciaException {
	
			 
			
			  File currentDir = new File(System.getProperty("user.dir")+"/archivos");

	    

			     try (FileInputStream fileIn = new FileInputStream(currentDir+"/revisiones-" + codigoFolio + ".txt");
			                     ObjectInputStream in = new ObjectInputStream(fileIn)) {
			                    
					 ArrayList<Revision> revisiones =  (ArrayList<Revision>) in.readObject();
					 
					 revisiones.add(rev);
					 
					    try (FileOutputStream fileOut = new FileOutputStream(currentDir+"/revisiones-" + codigoFolio + ".txt");
						         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
						        
						        out.writeObject(revisiones); 
						        
						    } catch (IOException e) {
						        e.printStackTrace();
						    }
					 
					 
			                    
			                } catch (IOException | ClassNotFoundException e) {
			                    e.printStackTrace();
			                }

		  

	}

	public int largo(IConexion con) throws PersistenciaException {
		
		  File currentDir = new File(System.getProperty("user.dir")+"/archivos");

		  try (FileInputStream fileIn = new FileInputStream(currentDir+"/revisiones-" + codigoFolio + ".txt");
                  ObjectInputStream in = new ObjectInputStream(fileIn)) {
                 
		 ArrayList<Revision> revisiones =  (ArrayList<Revision>) in.readObject();
	
		return  revisiones.size();
				
             } catch (IOException | ClassNotFoundException e) {
                 e.printStackTrace();
             }
		  
		  return -1;


	}

	public Revision kesimo(int numero, IConexion con) throws PersistenciaException {
		  File currentDir = new File(System.getProperty("user.dir")+"/archivos");

		  try (FileInputStream fileIn = new FileInputStream(currentDir+"/revisiones-" + codigoFolio + ".txt");
              ObjectInputStream in = new ObjectInputStream(fileIn)) {
             
		 ArrayList<Revision> revisiones =  (ArrayList<Revision>) in.readObject();

		 return revisiones.get(numero);
		 
         } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
         }
	
		  return null;
	}

	public List<VORevision> listarRevisiones(IConexion con) throws PersistenciaException {
		  File currentDir = new File(System.getProperty("user.dir")+"/archivos");

		  try (FileInputStream fileIn = new FileInputStream(currentDir+"/revisiones-" + codigoFolio + ".txt");
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
               
		 ArrayList<Revision> revisiones =  (ArrayList<Revision>) in.readObject();
	
		 List<VORevision> vorevisiones = new ArrayList<VORevision>();
		 
		 for (Revision r :  revisiones) {
			 
			 vorevisiones.add(new VORevision(r.getNumero(),  r.getDescripcion(),codigoFolio));
			 
			 System.out.println(r.getDescripcion()+"---");
		 }
		 		 
		return  vorevisiones;
		
           } catch (IOException | ClassNotFoundException e) {
               e.printStackTrace();
           }
		  
		  return new ArrayList<VORevision>();
	}

	public void borrarRevisiones(IConexion con) throws PersistenciaException {
		  File currentDir = new File(System.getProperty("user.dir")+"/archivos");
		  
		  

			File file = new File(System.getProperty("user.dir") +"/archivos"+"/revisiones-" + codigoFolio + ".txt");
		    
		    if (file.exists() && file.isFile()) {
		        file.delete();
		    } else {
		        System.out.println("Archivo no encontrado");
		    }
	  
		
	}
}