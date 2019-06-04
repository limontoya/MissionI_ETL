/**
 * 
 */
package com.mission.app.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mission.app.model.Archivo;
import com.mission.app.service.ArchivoService;

/**
 * @author limon
 * Maneja las excepciones del Servicio de Archivo de entrada
 */
public class ArchivoController {
	
	private final static Logger LOGGER = Logger.getLogger("com.mission.app.ArchivoController");
	private final ArchivoService archivoService ;

	/**
	 * Constructor
	 */
	public ArchivoController () {
		archivoService = new ArchivoService();
	}
	
	/**
	 * Metodo que establece las variables fijas del archivo Properties
	 * @return Objeto Archivo
	 */
	public Archivo leerPropiedades () {
		
		Archivo archivo = null;
		
		try {
			
			LOGGER.log(Level.INFO, "Leo archivo de propiedades");
			archivo = archivoService.setPropiedadesArchivo();			
			
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, "No se ha podido encontrar el archivo de Propiedades. "
					+ "\nPor favor ubicarlo en el folder de Recursos del proyecto.\n" + ioe.getMessage());
		} catch (Exception e) {
			// Algo fatal ocurre
			e.printStackTrace();
		}
		
		return archivo;		
	}
}
