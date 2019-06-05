/**
 * 
 */
package com.mission.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * @author limon
 *
 */
public class ArchivoUtil {
	
	public ArchivoUtil () {
		
	}
	
	/**
	 * Carga las propiedades de application.properties
	 * @return
	 */
	public Properties utilLoadPropertiesFile () {
		
		Properties properties = new Properties();		
		InputStream inputStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("application.properties");

		//Cargar los valores fijos del archivo de propiedades
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally { //Obligo a cerrar el inputStream
			try { if(inputStream!=null) inputStream.close(); } catch (Exception ignore) { }
		}
		
		return properties;
	}
	
	/**
	 * Separador de cadenas, elimina tambien los espacios en blanco:trim
	 * @param strToSplit
	 * @param args
	 * @return
	 */
	public String utilSplitString (String strToSplit, String args) {
		
		String strSplitted = "";
		
		//Separacion de caracteres que no se necesitan
    	String [] arrOfString = strToSplit.split(args); 
    	strSplitted = arrOfString[1].trim();
				
		return strSplitted;
	}
	
	/**
	 * Metodo que codifica un String a codificacion `UTF-8`
	 * @param strToEncode
	 * @return
	 */
    public String encodeStringUTF8 (String strToEncode ) {
        try {
            return URLEncoder.encode(strToEncode, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
    
    /**
     * Compara una fecha con formato dd MMMM yyyy con el año especificado
     * @param strDateLastModification
     * @param thisYear
     * @return
     */
	public boolean isLastModifiedThisYear( String strDate, int thisYear ) {

    	try {
    		//Formato de fecha de USA
    		DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);	
    		
    		Date date = formatter.parse(strDate);
		        
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(date);
    		
    		//Comparo el año usando Calendar
    		if (cal.get(Calendar.YEAR)==thisYear) {
	        	return true;
	        } else return false;
	        
    	} catch (Exception ex) {
            return false;
        }
    }

}
