/**
 * 
 */
package com.mission.app;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mission.app.model.Item;
import com.mission.app.service.ArchivoService;
import com.opencsv.CSVReader;

/**
 * @author limon
 *
 */
public class TestAppCSVWriter {

	private static ArchivoService as;
	private static String archivoSalida;
	private static List<Item> listOfItems;
	
	@BeforeClass
	public static void onlyOnce() {
		
		as = new ArchivoService();
		
		archivoSalida = "testArchivo.csv";
		
		listOfItems = new ArrayList<Item>();
		
		listOfItems.add(new Item(
				0L,"https://en.wikipedia.org/wiki/!Tierra_y_Libertad!","!Tierra_y_Libertad!","Land and liberty (slogan)","5 April 2019" ));
		listOfItems.add(new Item(
				1L,"https://en.wikipedia.org/wiki/$20K_House","$20K_House","Rural Studio","8 October 2018" ));
		listOfItems.add(new Item( 
				2L,"https://en.wikipedia.org/wiki/!","!","Exclamation mark","28 May 2019"));
		listOfItems.add(new Item( 
				3L,"https://en.wikipedia.org/wiki/'NSync","'NSync","NSYNC","25 May 2019"));
		listOfItems.add(new Item( 
				4L,"https://en.wikipedia.org/wiki/'Till_I_Collapse","'Till_I_Collapse","'Till I Collapse","27 April 2019"));
	}
	
	@Test
	public void testCSVWriterFileExists () {
		
		try {
			as.setItemsArchivoCSV(archivoSalida, listOfItems);
		} catch (FileNotFoundException e) {
			fail("Error Test FileNotFoundException escribiendo el archivo de salida [testArchivo.csv]");
		} catch (IOException e) {
			fail("Error Test IOException escribiendo el archivo de salida [testArchivo.csv]");
		} catch (Exception e) {
			fail("Error Test escribiendo el archivo de salida [testArchivo.csv]");
		}
		
		File file = new File(archivoSalida);		
		assertTrue(file.exists());
		
	}
	
	@Test
	public void testCSVWriterContent () {
			
		String dataReader = "";
		CSVReader reader;
		List<String[]> listReader = null;
		
		try {
			reader = new CSVReader(new FileReader(archivoSalida));
			
			listReader = reader.readAll();
			
			//Land and liberty (slogan)
			dataReader = listReader.get(0)[2];
			
		} catch (FileNotFoundException e) {
			fail("Error Test leyendo el archivo de salida [testArchivo.csv]");
		} catch (IOException e) {
			fail("Error Test leyendo el archivo de salida [testArchivo.csv]");
		}
		
		assert(dataReader.equals("Land and liberty (slogan)"));
	}
}
