/**
 * 
 */
package com.mission.app.model;

import java.util.List;

/**
 * @author limon
 * Contiene los datos del archivo de Entrada
 */
public class Archivo {

	private String fileInput;
	private String fileOutput;
	private String headerCSV;
	private String urlWikipedia;
	private List<Item> items;
	private String urlUserAgent;
	private String urlWikiTitle;
	private String urlWikiDate;
	private String urlWikipediaRedirect;
	
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
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * @return the urlUserAgent
	 */
	public String getUrlUserAgent() {
		return urlUserAgent;
	}

	/**
	 * @param urlUserAgent the urlUserAgent to set
	 */
	public void setUrlUserAgent(String urlUserAgent) {
		this.urlUserAgent = urlUserAgent;
	}

	/**
	 * @return the urlWikiTitle
	 */
	public String getUrlWikiTitle() {
		return urlWikiTitle;
	}

	/**
	 * @param urlWikiTitle the urlWikiTitle to set
	 */
	public void setUrlWikiTitle(String urlWikiTitle) {
		this.urlWikiTitle = urlWikiTitle;
	}

	/**
	 * @return the urlWikiDate
	 */
	public String getUrlWikiDate() {
		return urlWikiDate;
	}

	/**
	 * @param urlWikiDate the urlWikiDate to set
	 */
	public void setUrlWikiDate(String urlWikiDate) {
		this.urlWikiDate = urlWikiDate;
	}

	/**
	 * @return the urlWikipediaRedirect
	 */
	public String getUrlWikipediaRedirect() {
		return urlWikipediaRedirect;
	}

	/**
	 * @param urlWikipediaRedirect the urlWikipediaRedirect to set
	 */
	public void setUrlWikipediaRedirect(String urlWikipediaRedirect) {
		this.urlWikipediaRedirect = urlWikipediaRedirect;
	}
	
}
