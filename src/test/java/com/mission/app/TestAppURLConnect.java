/**
 * 
 */
package com.mission.app;

import static org.junit.Assert.*;

import org.junit.Test;
import org.jsoup.nodes.Document;

import com.mission.app.service.ArchivoService;
import com.mission.app.util.ArchivoUtil;

/**
 * @author limon
 *
 */
public class TestAppURLConnect {

	ArchivoService as;
	ArchivoUtil au;
	
	/**
	 * Test de Jsoup conexion con Wikipedia
	 */
	@Test
	public void testJsoupConnectionOK () {
		
		String itemURL = "https://en.wikipedia.org/wiki/Hello";
		String userAgent = "Mozilla/5.0";
		String itemURLRedirect = "";
		
		as = new ArchivoService();
		
		Document doc = null;
		try {
			doc = as.getDocumentJsoupConnect (itemURL, userAgent, itemURLRedirect, "");
						
		} catch (Exception e) {
			fail("Error Test de Conexion con Jsoup");
		}
		    	
    	assertNotNull(doc);
	}
	
	/**
	 * Test obteniendo el Elemento por ID firstHeading
	 */
	@Test
	public void testJsoupElementIDTitulo () {
		
		String itemURL = "https://en.wikipedia.org/wiki/";
		String urlWikiTitle = "firstHeading";
		String userAgent = "Mozilla/5.0";
		String itemURLRedirect = "";
		String itemName = "!";
		String title = "";
		as = new ArchivoService();
		
		Document doc;
		try {
			doc = as.getDocumentJsoupConnect (itemURL+itemName, userAgent, itemURLRedirect, itemName);
			
			//HTML form id que contiene el Titulo
	    	title = doc.getElementById(urlWikiTitle).text();
			
		} catch (Exception e) {
			fail("Error Test de Conexion con Jsoup obteniendo el elemento DOM "+urlWikiTitle);
		}
		    	
    	assertEquals("Exclamation mark", title);
	}
	
	/**
	 * Test obteniendo el Elemento por ID footer-info-lastmod
	 */
	@Test
	public void testJsoupElementIDFecha () {
		
		String itemURL = "https://en.wikipedia.org/wiki/";
		String urlWikiDate = "footer-info-lastmod";
		String userAgent = "Mozilla/5.0";
		String itemURLRedirect = "";
		String itemName = "!Three_Amigos!";
		String date = "";
		as = new ArchivoService();
		
		Document doc;
		try {
			doc = as.getDocumentJsoupConnect (itemURL+itemName, userAgent, itemURLRedirect, itemName);
			
			//HTML form id que contiene el Titulo
			date = doc.getElementById(urlWikiDate).text();
			
		} catch (Exception e) {
			fail("Error Test de Conexion con Jsoup obteniendo el elemento DOM "+urlWikiDate);
		}
		    	
    	assertEquals("This page was last edited on 15 May 2019, at 07:46 (UTC).", date);
	}
	
	/**
	 * Test obteniendo el Elemento por ID footer-info-lastmod y cambiando el formato de la fecha
	 */
	@Test
	public void testJsoupElementIDFechaFormato () {
		
		String itemURL = "https://en.wikipedia.org/wiki/";
		String urlWikiDate = "footer-info-lastmod";
		String userAgent = "Mozilla/5.0";
		String itemURLRedirect = "";
		String itemName = "!Three_Amigos!";
		String date = "";
		String dateStr = "";
		as = new ArchivoService();
		au = new ArchivoUtil();
		
		Document doc;
		try {
			doc = as.getDocumentJsoupConnect (itemURL+itemName, userAgent, itemURLRedirect, itemName);
			
			//HTML form id que contiene el Titulo
			dateStr = doc.getElementById(urlWikiDate).text();
			
			date = au.utilSplitString(dateStr, "on|at|,");
			
		} catch (Exception e) {
			fail("Error Test de Conexion con Jsoup obteniendo el elemento DOM ["+urlWikiDate+"] cambiando el formato de fecha.");
		}
		    	
    	assertEquals("15 May 2019", date);
	}
	

}
