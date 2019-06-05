package com.mission.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mission.app.controller.ArchivoController;
import com.mission.app.model.Archivo;

/**
 * Clase principal!
 *
 */
public class App 
{
	private final static Logger LOGGER = Logger.getLogger("com.mission.app.App");
	
    public static void main( String[] args )
    {
    	App app = new App();
    	app.executeApp();
    }
    
    public void executeApp () {
    	
    	LOGGER.log(Level.INFO, "Inicio del proceso ETL");
    	
    	ArchivoController archivoController = new ArchivoController();
    	Archivo archivo = new Archivo ();
    	
    	/**
    	 * 0. Leer las propiedades para ubicar el archivo de entrada
    	 */
    	archivo = archivoController.leerPropiedades();
    	
		LOGGER.log(Level.INFO, "Propiedades leidas.");
		
		/**
    	 * 1. Lee el archivo de entrada y construye las URLs
    	 */
    	archivo = archivoController.leerArchivoEntrada(archivo);
    	
    	LOGGER.log(Level.INFO, "Archivo nombres leido. Cantidad de Items [" + archivo.getItems().size() + "]");
    	
    	/**
    	 * 2. Por cada pagina de Wikipedia extraer y analizar 
    	 *     el titulo y ultima fecha de modificacion
    	 */
    	archivo = archivoController.obtenerTituloFechaWikipedia(archivo);
    	
    	LOGGER.log(Level.INFO, "Consulta a Wikipedia realizada.");
    	
    	/**
    	 * 4. Crear archivo CSV con los datos de Wikipedia
    	 */
    	archivoController.escribirItemsArchivoCSV(archivo);
    	
    	LOGGER.log(Level.INFO, "Creacion CSV realizado.");
    	
    	/**
    	 * 5. Pregunta: Cuántas páginas de Wikipedia fueron modificadas durante este año?
    	 */
    	int cantPaginasModificadas = archivoController.obtenerCantidadPaginasModificadasAnio(archivo);
    	LOGGER.log(Level.INFO, "Cantidad de paginas modificadas en 2019 ["+cantPaginasModificadas  + "]" );
		
    }
}
