/**
 * 
 */
package com.mission.app.model;

/**
 * @author limon
 * Lista de Items que se escriben en el archivo de salida CSV
 */
public class Item {

	private Long id;
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
	 * @param id
	 * @param url
	 * @param name
	 * @param title
	 * @param lastModification
	 */
	public Item(Long id, String url, String name, String title, String lastModification) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
		this.title = title;
		this.lastModification = lastModification;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [id=" + id + ", url=" + url + ", name=" + name + ", title=" + title + ", lastModification="
				+ lastModification + "]";
	}
	
}
