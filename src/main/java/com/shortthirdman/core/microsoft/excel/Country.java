package com.shortthirdman.core.microsoft.excel;

/**
 * @author Swetank Mohanty
 *
 */
public class Country {

	/**
	 * name: Stores String for Country.java
	 */
	private String name;
	
	/**
	 * shortCode: Stores String for Country.java
	 */
	private String shortCode;
	
	/**
	 * @param n
	 * @param c
	 */
	public Country(String n, String c) {
		this.name = n;
		this.shortCode = c;
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return
	 */
	public String getShortCode() {
		return shortCode;
	}
	
	/**
	 * @param shortCode
	 */
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "::" + shortCode;
	}	
}