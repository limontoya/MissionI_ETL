/**
 * 
 */
package com.mission.app.service;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mission.app.model.Archivo;
import com.mission.app.model.Item;
import com.mission.app.util.ArchivoUtil;

/**
 * @author limon
 * Realiza todos los servicios para el Archivo
 */
public class ArchivoService {

	private final ArchivoUtil archivoUtil;
	
	/**
	 * Constructor
	 */
	public ArchivoService () {
		archivoUtil = new ArchivoUtil();
	}

	/**
	 * Obtiene los datos de cada propiedad para leer el archivo de entrada
	 * @return objeto tipo Archivo
	 * @throws IOException
	 * @throws Exception
	 */
	public Archivo setPropiedadesArchivo () throws IOException, Exception {

		Archivo archivo = new Archivo();
		Properties properties = archivoUtil.utilLoadPropertiesFile();

		//Establecer las propiedades en el objeto de la clase Archivo
		archivo.setFileInput(properties.getProperty("archivo-lectura"));
		archivo.setFileOutput(properties.getProperty("archivo-escritura"));
		archivo.setHeaderCSV(properties.getProperty("encabezado-csv"));
		archivo.setUrlWikipedia(properties.getProperty("item-url"));
		archivo.setUrlUserAgent(properties.getProperty("user-agent"));
		archivo.setUrlWikiTitle(properties.getProperty("wiki-titulo"));
		archivo.setUrlWikiDate(properties.getProperty("wiki-fecha"));
		archivo.setUrlWikipediaRedirect(properties.getProperty("item-url-redirect"));

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
	    
	    try { if(it!=null) it.close(); } catch (Exception ignore) {	}
		
		return listOfItems;
	}
	
	/**
	 * Obtiene el titulo y la ultima fecha de modificacion de cada nombre consultado en Wikipedia
	 * @param archivo Contiene las propiedades para conectar con Wikipedia y la Lista de items con los nombres dados
	 * @return Set de Items con el titulo de Wikipedia y la ultima fecha de modificacion de la pagina
	 * @throws Exception
	 */
	public HashSet<Item> getTituloFechaWikipedia (Archivo archivo) throws Exception {
		
		String title = ""; 
		String lastModDate = "";
        String date = "";
        
		//Recorrer el listado de items
		Item item;
		HashSet<Item> items = archivo.getItems();
		Iterator<Item> iter = items.iterator();

		Document doc = null;
		
		while( iter.hasNext() ){

			item = (Item) iter.next(); //Cast del objeto a Item

			
			try {
				//String itemURL, String userAgent, String itemURLRedirect
				doc = getDocumentJsoupConnect(
						item.getUrl(), archivo.getUrlUserAgent(), archivo.getUrlWikipediaRedirect(), item.getName());
								
			} 
			catch (HttpStatusException hse) {
				//System.out.println("El id ["+cant+"] item ["+item.getName()+"], no encontro la ruta. "+hse.getLocalizedMessage());
			}
			catch (MalformedURLException mue) {
				//System.out.println("El id ["+cant+"] item ["+item.getName()+"], tiene URL mal formada. "+mue.getLocalizedMessage());
			}
			catch (ConnectException conex) {
				//System.out.println("Conexion controlada, items leidos>> "+cant); conex.getMessage();
			}
			catch (Exception e) {
				//System.out.println("algo paso aqui>>> "+e.getMessage());
				e.printStackTrace();
				throw new Exception(e.getCause());
			}
			
			try {
				//HTML form id que contiene el Titulo
		    	title = doc.getElementById(archivo.getUrlWikiTitle()).text();
		    	//HTML form id que contiene la Fecha de ultima modificacion
		    	lastModDate = doc.getElementById(archivo.getUrlWikiDate()).text();
		    	
		    	if(lastModDate!=null) { 
		    		date = archivoUtil.utilSplitString(lastModDate, "on|at|,"); }
	    	
			} catch (NullPointerException npe) {
				date = "";
			}
			
			//Establecer para cada Item el valor de Titulo y Fecha de ultima modificacion
	    	item.setTitle(title);
	    	item.setLastModification(date);

	    	//System.out.println(item.getId()+" ->> "+item.getName()+" -- "+item.getTitle());
	    	//cant++;
		
		}
		return items;
	}
	
	/**
	 * Conectar con la URL y obtener el documento para encontrar el titulo y la fecha de modificacion
	 * @param url URL construida con la linea del archivo de entrada
	 * @param userAgent Conectar a la URL con los navegadores disponibles
	 * @param itemURLRedirect URL que redirecciona la busqueda de la pagina
	 * @return objeto Documento
	 * @throws Exception
	 */
	public Document getDocumentJsoupConnect (String itemURL, String userAgent, String itemURLRedirect, String itemName) throws Exception  {
		
		Document document = null;	
		String itemNewURL = "";
		
		try {
			//Conectar con Wikipedia
			document = Jsoup.connect(itemURL).userAgent(userAgent)
						.followRedirects(true).timeout(3000*10000).get();
			
		} catch (NullPointerException npe) {
			itemNewURL = itemURLRedirect + archivoUtil.encodeStringUTF8(itemName);			
			document = getDocumentJsoupConnect(itemNewURL, userAgent, itemURLRedirect, itemName);
		}
		
		return document;
	}
	
}
