/**
 * 
 */
package com.mission.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mission.app.model.Archivo;

/**
 * @author limon
 * Realiza todos los servicios para el Archivo
 */
public class ArchivoService {

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
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");

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
}
