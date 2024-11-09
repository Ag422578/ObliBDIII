package persistencia.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import logica.Folio;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import logica.valueObjects.VORevision;
import persistencia.IConexion;

public class DAOFoliosArchivo2 implements IDAOFolios {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public DAOFoliosArchivo2() {
		
	}

	public boolean member(String cod, IConexion con) {
		
	    File currentDir = new File(System.getProperty("user.dir"));
	    
	    File[] files = currentDir.listFiles();
	    
	    if (files != null) {
	        for (File file : files) {
	            if (file.isFile() && file.getName().contains("folio-"+cod+".txt")) {
	                return true;
	            }
	        }
	    }
	    
	    return false;
	}


	@Override
	public void insert(Folio fol, IConexion ICon) throws PersistenciaException {
		
		VOFolio vo = new VOFolio(fol.getCodigo(), fol.getCaratula(), fol.getPaginas());
	    
	    String fileName = "folio-" + vo.getCodigo() + ".txt";
	    
	  
	    try (FileOutputStream fileOut = new FileOutputStream(fileName);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
	        
	        out.writeObject(vo); 
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    	    	
	}

	@Override
	public Folio find(String cod, IConexion con) throws PersistenciaException {
	    File currentDir = new File(System.getProperty("user.dir"));
	    
	    File[] files = currentDir.listFiles();
    
	    if (files != null) {
	        for (File file : files) {
	            if (file.isFile() && file.getName().contains(cod)) {
	                try (FileInputStream fileIn = new FileInputStream(file);
	                     ObjectInputStream in = new ObjectInputStream(fileIn)) {
	                    
	                	VOFolio vo =  (VOFolio) in.readObject();
	    				return  new Folio(vo.getCodigo(), vo.getCaratula(), vo.getPaginas());
	                    
	                } catch (IOException | ClassNotFoundException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	    
	    return null; 
	}
	

	@Override
	public void delete(String cod, IConexion con) throws PersistenciaException {
		
		File file = new File(System.getProperty("user.dir") + "/folio-" + cod + ".txt");
	    
	    if (file.exists() && file.isFile()) {
	        file.delete();
	    } else {
	        System.out.println("Archivo no encontrado");
	    }
		
	}

	@Override
	public List<VOFolio> listarFolios(IConexion con) throws PersistenciaException {
		
		List<VOFolio> lista = new ArrayList<VOFolio>();
		File currentDir = new File(System.getProperty("user.dir"));
	    
	    File[] files = currentDir.listFiles();
    
	    if (files != null) {
	        for (File file : files) {
	            if (file.isFile() && file.getName().contains("folio")) {
	                try (FileInputStream fileIn = new FileInputStream(file);
	                     ObjectInputStream in = new ObjectInputStream(fileIn)) {
	                    
	                	VOFolio vo =  (VOFolio) in.readObject();
	                	lista.add(vo);
	                	
	                	
	                } catch (IOException | ClassNotFoundException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
		
		
		return lista;
	}

	@Override
	public boolean esVacio(IConexion con) throws PersistenciaException {
		// TODO Auto-generated method stub
		return listarFolios(null).size()==0;
	}

	@Override
	public VOFolioMaxRev folioMasRevisado(IConexion con) throws PersistenciaException {
		// TODO Auto-generated method stub
		//return listarFolios(null).;
		 List<VOFolio> folios = listarFolios(null);
		 
		 int maximo= -1;
		 VOFolio voFolioMax= null;
		 for (VOFolio f: folios) {
		
				File file = new File(System.getProperty("user.dir") + "/revisiones-" + f.getCodigo() + ".txt");

			 
			 try (FileInputStream fileIn = new FileInputStream(file);
                     ObjectInputStream in = new ObjectInputStream(fileIn)) {
                    
				 List<VORevision> revisiones =  (List<VORevision>) in.readObject();
    				
				 if (revisiones.size() > maximo) {
					 maximo = revisiones.size();
					 voFolioMax = f;
				 }
                    
                } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 			 
		 }
		 
		 return new VOFolioMaxRev(voFolioMax.getCodigo(), voFolioMax.getCaratula(), voFolioMax.getPaginas(), maximo);
		
	}

}
