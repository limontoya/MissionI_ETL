/**
 * 
 */
package com.mission.app.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.mission.app.model.Archivo;
import com.mission.app.model.Item;

/**
 * @author limon
 * Realiza todos los servicios para el Archivo
 */
public class ArchivoService {

	/**
	 * Constructor
	 */
	public ArchivoService () {

	}

	/**
	 * Obtiene los datos de cada propiedad para leer el archivo de entrada
	 * @return objeto tipo Archivo
	 * @throws IOException
	 * @throws Exception
	 */
	public Archivo setPropiedadesArchivo () throws IOException, Exception {

		Archivo archivo = new Archivo();
		Properties properties = new Properties();		
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("application.properties");

		//Cargar los valores fijos del archivo de propiedades
		properties.load(inputStream);

		//Establecer las propiedades en el objeto de la clase Archivo
		archivo.setFileInput(properties.getProperty("archivo-lectura"));
		archivo.setFileOutput(properties.getProperty("archivo-escritura"));
		archivo.setHeaderCSV(properties.getProperty("encabezado-csv"));
		archivo.setUrlWikipedia(properties.getProperty("item-url"));

		//Obligo a cerrar el inputStream
		try {
			if(inputStream!=null) inputStream.close();
		} catch (Exception ignore) { }

		return archivo;
	}
	
	/**
	 * Lee el archivo de entrada y carga cada linea en un HashSet
	 * @param fileInput
	 * @param urlWiki
	 * @return
	 * @throws IOException
	 */
	public HashSet<Item> setNombresArchivo (String fileInput, String urlWiki) throws IOException {
		
		//apache.commons.io File Utils para obtener todos los simbolos
		LineIterator it = FileUtils.lineIterator(new File(fileInput), "UTF-8");
		
		//Manejar los items del archivo a leer
		HashSet<Item> listOfItems = new HashSet<Item>();
		int lineNumber = 0;
		
		//Saltar primer linea
		it.nextLine();
		
	    while (it.hasNext()) {
	        String line = it.nextLine();
	        
	        line.trim();
			line = line.replace("\n", "").replaceAll("\r", "");
	        
			Item item = new Item();
			
			//Asigno un id
			item.setId(lineNumber);
			//Obtengo el dato del archivo como Name
			item.setName(line);	
			//Construyo la URL con el Name y URL de .Properties
			item.setUrl(urlWiki+item.getName());
			
			listOfItems.add(item);					
			
			lineNumber++;
	    }
	    
	    System.out.println("Cantidad de items leidos del archivo Original>> "+lineNumber);
	    try { if(it!=null) it.close(); } catch (Exception ignore) {	}
		
		return listOfItems;
	}
	
}
