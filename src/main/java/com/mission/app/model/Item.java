/**
 * 
 */
package com.mission.app.model;

/**
 * @author limon
 * Lista de Items que se escriben en el archivo de salida CSV
 */
public class Item {

	private int id;
	private String url;
	private String name;
	private String title;
	private String lastModification;
	
	/**
	 * Constructor
	 */
	public Item () {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the lastModification
	 */
	public String getLastModification() {
		return lastModification;
	}

	/**
	 * @param lastModification the lastModification to set
	 */
	public void setLastModification(String lastModification) {
		this.lastModification = lastModification;
	}
	
}
