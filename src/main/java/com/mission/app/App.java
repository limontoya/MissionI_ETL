package com.mission.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mission.app.controller.ArchivoController;
import com.mission.app.model.Archivo;

/**
 * Hello world!
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
    	
		LOGGER.log(Level.INFO, "Propiedades leidas correctamente.");
		
		/**
    	 * 1. Lee el archivo de entrada y construye las URLs
    	 */
    	archivo = archivoController.leerArchivoEntrada(archivo);
    	
    	LOGGER.log(Level.INFO, "Archivo nombres leido correctamente. Cantidad de Items [" + archivo.getItems().size() + "]");
    	
    	
		
    }
}
