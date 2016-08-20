package com.ikaihuo.idc.kits;

/**
 * Sort.DESC(column) <br/>
 * Sort.ASC(column)  <br/>
 * @author BruceZCQ
 */
public class Sort {

	private Sort() {}
	
	private static final String DESC = "+";
	private static final String ASC = "-";
	
	public static String DESC(String column) {
		return (column + Sort.DESC);
	}
	
	public static String ASC(String column) {
		return (column + Sort.ASC);
	}
}
