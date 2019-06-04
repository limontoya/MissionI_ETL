/**
 * 
 */
package com.mission.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author limon
 *
 */
public class TestAppProperties extends TestCase {

	//Ubicacion del archivo de propiedades
	private static final String resourcesLocation = "/src/main/resources/";
	
	//Nombre del archivo .properties
	private static final String propertiesFile = "application.properties";
	
	/**
	 * Test lectura archivo de propiedades
	 * Que la propiedad "archivo-lectura" sea igual a la que se encuentra en el archivo .Properties
	 */
	@Test
    public void testReadPropertiesFile(){
		
		Properties properties = new Properties();		
		InputStream inputStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(propertiesFile);

		//Cargar los valores fijos del archivo de propiedades
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
				try {
					if(inputStream!=null) inputStream.close();
				} catch (IOException ignore) {	}
		}

		assertEquals("first10000.txt",properties.getProperty("archivo-lectura"));
		
    }
	
	/**
	 * Test lectura archivo de propiedades
	 * Que el Full Path no logre encontrar el archivo .properties
	 */
	@Test
    public void testFailReadPropertiesFile(){
		

		Properties properties = new Properties();		
		InputStream inputStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(resourcesLocation+propertiesFile);

		//Cargar los valores fijos del archivo de propiedades
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			
			//Capturar en la excepcion para que no salte la prueba
			assertNull(inputStream);
			
		} finally {
				try {
					if(inputStream!=null) inputStream.close();
				} catch (IOException ignore) {	}
		} 
    }

}
