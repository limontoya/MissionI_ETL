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
	 * @return Objeto Archivo con las propiedades generales
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
	
	/**
	 * Metodo que lee cada linea del archivo para construir las URls
	 * @param archivo Objeto que contiene las propiedades del archivo de entrada
	 * @return objeto archivo que incluye la lista de items con las URLs
	 */
	public Archivo leerArchivoEntrada (Archivo archivo) {
		
		try {
			LOGGER.log(Level.INFO, "Set de lectura y manejo de los items del archivo de entrada.");
			archivo.setItems(
					archivoService.setNombresArchivo(
							archivo.getFileInput(), archivo.getUrlWikipedia()));
		
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "No se puede EstablecerNombresArchivo");
			e.printStackTrace();
		}
		
		return archivo;	
	}
	
	/**
	 * Buscar en Wikipedia: obtener el Titulo y Fecha de modificacion para cada Item
	 * @param archivo Objeto que contiene los items
	 * @return archivo Objeto ahora con los titulos y fechas de modificacion de cada Item
	 */
	public Archivo obtenerTituloFechaWikipedia ( Archivo archivo ) {
		
		try {
			archivo.setItems(archivoService.getTituloFechaWikipedia(archivo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return archivo;
	}
}
