package com.ikaihuo.idc.kits;


/**
 * Sort.DESC(column) <br/>
 * Sort.ASC(column)  <br/>
 * @author BruceZCQ
 */
public class Sort {

	private Sort() {}
	
	public static String DESC(String column) {
		return (column + Consts.ORDER_DESC);
	}
	
	public static String ASC(String column) {
		return (column + Consts.ORDER_ASC);
	}
}
