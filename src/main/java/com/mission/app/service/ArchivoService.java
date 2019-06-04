/**
 * 
 */
package com.mission.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mission.app.model.Archivo;
import com.mission.app.model.Item;
import com.mission.app.util.ArchivoUtil;
import com.opencsv.CSVWriter;

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
	public List<Item> setNombresArchivo (String fileInput, String urlWiki) throws IOException {
		
		//apache.commons.io File Utils para obtener todos los simbolos
		LineIterator it = FileUtils.lineIterator(new File(fileInput), "UTF-8");
		
		//Manejar los items del archivo a leer
		List<Item> listOfItems = new ArrayList<Item>();
		Long lineNumber = 0L;
		
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
	public List<Item> getTituloFechaWikipedia (Archivo archivo) throws Exception {
		
		String title = ""; 
		String lastModDate = "";
        String date = "";
        
		//Recorrer el listado de items
		Item item;
		List<Item> items = new ArrayList<Item>();
		items = archivo.getItems();
		Iterator<Item> iter = items.iterator();

		Document doc = null;
		
		while( iter.hasNext() ){

			item = (Item) iter.next(); //Cast del objeto a Item
			
			try {
				//Conexion normal
				doc = getDocumentJsoupConnect(
						item.getUrl(), archivo.getUrlUserAgent());
			}
			catch (NullPointerException npe) {
				
				//Conexion no normal- cuando redirecciona una pagina
				doc = getDocumentJsoupConnectRedirect(
						archivo.getUrlUserAgent(), archivo.getUrlWikipediaRedirect(), item.getName());
				
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
		
		}
		return items;
	}
	
	/**
	 * Conectar con la URL y obtener el documento para encontrar el titulo y la fecha de modificacion
	 * @param itemURL URL construida con la linea del archivo de entrada
	 * @param userAgent Conectar a la URL con los navegadores disponibles
	 * @return objeto Documento
	 * @throws NullPointerException
	 * @throws IOException 
	 */
	public Document getDocumentJsoupConnect (String itemURL, String userAgent) throws NullPointerException, IOException  {
		
		Document document = null;
		
		//Conectar con Wikipedia
		document = Jsoup.connect(itemURL).userAgent(userAgent)
						.followRedirects(true).timeout(3000*10000).get();
				
		return document;
	}
	
	/**
	 * Conectar con la URL y obtener el documento para encontrar el titulo y la fecha de modificacion con Redireccion
	 * @param userAgent Conectar a la URL con los navegadores disponibles
	 * @param itemURLRedirect URL que redirecciona la busqueda de la pagina
	 * @param itemName linea del archivo de entrada
	 * @return objeto Documento
	 * @throws Exception
	 */
	public Document getDocumentJsoupConnectRedirect (String userAgent, String itemURLRedirect, String itemName) throws Exception  {
		
		Document document = null;	
		String itemNewURL = "";
		
		try {
			//Conectar con Wikipedia redireccionado 
			itemNewURL = itemURLRedirect + archivoUtil.encodeStringUTF8(itemName);			
			document = getDocumentJsoupConnect(itemNewURL, userAgent);
			
		} catch (NullPointerException npe) {
			npe.getStackTrace();
		}
		
		return document;
	}
	
	/**
	 * Utiliza CSVWriter para escribir la lista de items
	 * @param archivoSalida Nombre de archivo y directorio donde quedara el archivo CSV
	 * @param listOfItems Items del archivo de entrada
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public void setItemsArchivoCSV (String archivoSalida, List<Item> listOfItems) throws FileNotFoundException, IOException, Exception {

		String wrline = "";

		FileOutputStream fos = new FileOutputStream(archivoSalida);
		OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
		CSVWriter writer = new CSVWriter(osw);
		
		Item item;
		Iterator<Item> iter = listOfItems.iterator();
		
		while( iter.hasNext() ){

			item = (Item) iter.next(); //Cast del objeto a Item
		    
			wrline = item.getUrl()+";"+item.getName()+";"+item.getTitle()+";"+item.getLastModification()+"";
			//Crear record
			String [] record = wrline.split(";");

			//Escribir el record en la fila
			writer.writeNext(record);
		}

		try { writer.close(); } catch (Exception ext) { }

	}
	
}
