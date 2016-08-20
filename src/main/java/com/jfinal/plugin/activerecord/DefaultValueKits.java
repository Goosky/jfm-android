package com.jfinal.plugin.activerecord;

/**
 * @author BruceZCQ
 */
public class DefaultValueKits {
	
	private DefaultValueKits() {}

	@SuppressWarnings("unchecked")
	public static <T> T getDefaultValue(T t) {
		T _t = (T) new Object();
		if (t instanceof String) {
			_t = (T) new String(); 
		} else if (t instanceof java.math.BigInteger) {
			_t = (T) new java.math.BigInteger("0");
		} else if (t instanceof java.util.Date){
			_t = (T) new java.util.Date();
		} else if (t instanceof byte[]) {
			_t = (T) new byte[0];
		} else if (t instanceof Integer) {
			_t = (T) new Integer(0);
		} else if (t instanceof Long) {
			_t = (T) new Long(0);
		} else if (t instanceof Float) {
			_t = (T) new Float(0.0);
		} else if (t instanceof Double) {
			_t = (T) new Double(0.0);
		} else if (t instanceof Boolean) {
			_t = (T) new Boolean(true);
		} else if (t instanceof java.math.BigDecimal) {
			_t = (T) new java.math.BigDecimal("");
		} else if (t instanceof Number) {
			_t = (T) new Integer(0);
		}
		return _t;
	}
}
