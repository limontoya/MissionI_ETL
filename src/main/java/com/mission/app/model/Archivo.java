/**
 * 
 */
package com.mission.app.model;

import java.util.HashSet;

/**
 * @author limon
 * Contiene los datos del archivo de Entrada
 */
public class Archivo {

	private String fileInput;
	private String fileOutput;
	private String headerCSV;
	private String urlWikipedia;
	private HashSet<Item> items;
	
	/**
	 * Constructor
	 */
	public Archivo () {
		
	}

	/**
	 * @return the fileInput
	 */
	public String getFileInput() {
		return fileInput;
	}

	/**
	 * @param fileInput the fileInput to set
	 */
	public void setFileInput(String fileInput) {
		this.fileInput = fileInput;
	}

	/**
	 * @return the fileOutput
	 */
	public String getFileOutput() {
		return fileOutput;
	}

	/**
	 * @param fileOutput the fileOutput to set
	 */
	public void setFileOutput(String fileOutput) {
		this.fileOutput = fileOutput;
	}

	/**
	 * @return the headerCSV
	 */
	public String getHeaderCSV() {
		return headerCSV;
	}

	/**
	 * @param headerCSV the headerCSV to set
	 */
	public void setHeaderCSV(String headerCSV) {
		this.headerCSV = headerCSV;
	}

	/**
	 * @return the urlWikipedia
	 */
	public String getUrlWikipedia() {
		return urlWikipedia;
	}

	/**
	 * @param urlWikipedia the urlWikipedia to set
	 */
	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}

	/**
	 * @return the items
	 */
	public HashSet<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(HashSet<Item> items) {
		this.items = items;
	}
	
}
