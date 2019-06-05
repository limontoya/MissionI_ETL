/**
 * 
 */
package com.mission.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mission.app.model.Item;
import com.mission.app.util.ArchivoUtil;

/**
 * @author limon
 *
 */
public class TestAppCountDates {
	
	private static ArchivoUtil au;
	private static List<Item> listOfItems;
	
	/**
	 * Creacion de objetos antes para utilizarlos
	 */
	@BeforeClass
	public static void setUp() throws Exception {
				
		au = new ArchivoUtil();
		
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
				4L,"https://en.wikipedia.org/wiki/'Till_I_Collapse","'Till_I_Collapse","'Till I Collapse","27 April 2017"));
		
	}

	/**
	 * Utiliza el metodo isLastModifiedThisYear de ArchivoUtils para validar que la fecha de modificacion de la pagina es del año
	 */
	@Test
	public void testContadorPorFecha () {
		
		Iterator<Item> iter = listOfItems.iterator();
		Item item = new Item();
		int year = 2019;
		int contador = 0;
		String tres = "3";
		
		while( iter.hasNext() ){
	        
			item = (Item) iter.next();
			
			if (au.isLastModifiedThisYear(item.getLastModification(), year )) {
				contador++; 
			}			
		}
		
		assert(tres.equals(contador+""));
		
	}

}
