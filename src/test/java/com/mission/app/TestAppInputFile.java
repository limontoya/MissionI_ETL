/**
 * 
 */
package com.mission.app;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

/**
 * @author limon
 *
 */
public class TestAppInputFile {

	//Nombre del archivo entrada
	private static final String nameInputFile = "first10000.txt";
	
	/**
	 * Test existencia del archivo
	 */
	@Test
	public void testExistsInputFile() {

		File file = new File(nameInputFile);
		
		assertTrue(file.exists());
	}	
	
	/**
	 * Test para leer el archivo de entrada
	 */
	@Test
	public void testReadFirstLineInputFile() {

		File file = null;
		String line = "!";
		LineIterator it = null;
		
		try {
			file = new File(nameInputFile);
			it = FileUtils.lineIterator(file, "UTF-8");
		
			//Saltar primer linea
			it.nextLine();
			
			assertEquals(line, it.nextLine());
			
		} catch (IOException e) { }	
		finally { try { if(it!=null) it.close(); } catch (IOException ignore) { } }
	}
	
	/**
	 * Test provoca null porque no existe el archivo FileNotFoundException/ NullPointerException
	 */
	@Test
	public void testFailReadInputFile() {
		
		//Con ubicacion diferente a la original del archivo ocurre FileNotFoundException 
		// y el objeto IT seguira siendo null
		String otherLocation = "/src/";
		LineIterator it = null;
		
		try {
			it = FileUtils.lineIterator(new File(otherLocation+nameInputFile), "UTF-8");
		} catch (Exception e) {
			//IOExc o NullPointer
			assertNull(it);
		}
		finally { try { if(it!=null) it.close(); } catch (IOException ignore) { } }
	}
	
}
