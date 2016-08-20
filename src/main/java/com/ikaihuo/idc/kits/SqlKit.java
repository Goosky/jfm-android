package com.ikaihuo.idc.kits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqlKit
 * 
 * @author BruceZCQ
 */
public class SqlKit {

    public static class Sql {

        private String   sql    = "";
        private Object[] params = new Object[] {};

        public Sql() {
        }

        public Sql(String sql, Object[] params) {
            this.sql = sql;
            this.params = params;
        }

        public String sql() {
            return this.sql;
        }

        public Object[] params() {
            return this.params;
        }
        
        public Sql page(int page, int size) {
        	this.sql = String.format(" %s LIMIT ?, ?", this.sql);
        	List<Object> paramlst = new ArrayList<Object>();
        	Collections.addAll(paramlst, this.params);
        	paramlst.add(page * size);
        	paramlst.add(size);
        	this.params = paramlst.toArray();
        	return this;
        }

        @Override
        public String toString() {
            return "{ " + sql + " } params=" + Arrays.toString(params);
        }
    }

    private static void removeFields(Map<?, ?> map, Object... fields) {
    	if (null == fields) {
			return;
		}
    	
    	for (int i = 0; i < fields.length; i++) {
    		Object key = fields[i];
    		if (map.containsKey(key)) {
                map.remove(key);
            }
		}
    }
    
    private static void fillValues(List<Object> vals, Object params) {
    	if (!params.getClass().isArray()) {
    		vals.add(params);
    		return;
		}

    	Object[] objs = (Object[]) params;
        for (int i = 0; i < objs.length; i++) {
            vals.add(objs[i]);
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<?, ?> cloneMap(Object map) {
        Map<String, Object> clonedMap = new HashMap<String, Object>();
        clonedMap.putAll((Map<String, Object>) map);
        return clonedMap;
    }

    /**
     * Make Delete data Sql
     * 
     * @param data
     * @return delete sql
     */
    @SuppressWarnings("unchecked")
    public static Sql delete(Object data) {
        if (!(data instanceof Map)) {
            return (new Sql());
        }
        
        Map<?, ?> map = SqlKit.cloneMap(data);
        Map<String, Object> params = (Map<String, Object>) map.get(Consts.PARAMS_KEY);
        if (params.size() == 0) {
            return (new Sql());
		}
        
        SqlKit.removeFields(map, Consts.OUTPUT_KEY, Consts.AS_KEY, Consts.OUTPUT_COLUMN_KEY, Consts.ORDER_KEY);

        StringBuilder sbr = new StringBuilder();
        sbr.append("DELETE FROM `").append(map.get(Consts.TABLE_KEY)).append("` WHERE ");
        SqlKit.removeFields(map, Consts.TABLE_KEY, Consts.PARAMS_KEY);

        Map<String, Object> ops = (Map<String, Object>) map.get(Consts.OPS_KEY);
        SqlKit.removeFields(map, Consts.OPS_KEY);

        List<Object> vals = new ArrayList<Object>();
        String suffix = " AND ";

        for (Object key : map.keySet()) {
            if (!ops.containsKey(key)) {
                sbr.append("`").append(key).append("`").append(map.get(key)).append(suffix);
                SqlKit.fillValues(vals, params.get(key));
            }
        }
        sbr.delete(sbr.length() - suffix.length(), sbr.length());

        for (Object opsKey : ops.keySet()) {
            suffix = ops.get(opsKey).toString();
            sbr.append(suffix).append("`").append(opsKey).append("` ").append(map.get(opsKey));
            SqlKit.fillValues(vals, params.get(opsKey));
        }

        return (new Sql(sbr.toString(), vals.toArray()));
    }

    /**
     * Make Update data Sql
     * 
     * @param data
     * @return update sql
     */
    @SuppressWarnings("unchecked")
    public static Sql update(Object data) {
        if (!(data instanceof Map)) {
            return (new Sql());
        }

        Map<?, ?> map = SqlKit.cloneMap(data); 
        Map<String, Object> params = (Map<String, Object>) map.get(Consts.PARAMS_KEY);
        if (params.size() == 0) {
            return (new Sql());
		}
        
        SqlKit.removeFields(map, Consts.OUTPUT_KEY, Consts.AS_KEY, Consts.OUTPUT_COLUMN_KEY, Consts.ORDER_KEY);

        if (!map.containsKey(Consts.DEFAULT_OUTPUT)) {
            throw new IllegalArgumentException("Not Found PrimaryKey!");
        }
        StringBuilder sbr = new StringBuilder();
        sbr.append("UPDATE `").append(map.get(Consts.TABLE_KEY)).append("` SET ");
        map.remove(Consts.TABLE_KEY);
        Object id = map.get(Consts.DEFAULT_OUTPUT);
        map.remove(Consts.DEFAULT_OUTPUT);

        SqlKit.removeFields(map, Consts.PARAMS_KEY, Consts.OPS_KEY);

        List<Object> vals = new ArrayList<Object>();
        String suffix = ", ";

        for (Object key : map.keySet()) {
            sbr.append("`").append(key).append("`").append(map.get(key)).append(suffix);
            SqlKit.fillValues(vals, params.get(key));
        }
        sbr.delete(sbr.length() - suffix.length(), sbr.length());

        sbr.append(" WHERE `").append(Consts.DEFAULT_OUTPUT).append("`").append(id);
        vals.add(params.get(Consts.DEFAULT_OUTPUT));
        return (new Sql(sbr.toString(), vals.toArray()));
    }

    /**
     * Make Save data Sql
     * 
     * @param data
     * @return insert sql
     */
    @SuppressWarnings("unchecked")
    public static Sql save(Object data) {
        if (!(data instanceof Map)) {
            return (new Sql());
        }

        Map<?, ?> map = SqlKit.cloneMap(data);
        Map<String, Object> params = (Map<String, Object>) map.get(Consts.PARAMS_KEY);
        if (params.size() == 0) {
            return (new Sql());
		}
        
        SqlKit.removeFields(map, Consts.OUTPUT_KEY, Consts.AS_KEY, Consts.OUTPUT_COLUMN_KEY, Consts.ORDER_KEY, Consts.PARAMS_KEY, Consts.OPS_KEY);

        StringBuilder sbr = new StringBuilder();
        sbr.append("INSERT INTO `").append(map.get(Consts.TABLE_KEY)).append("` ");
        map.remove(Consts.TABLE_KEY);

        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        List<Object> vals = new ArrayList<Object>();
        String suffix = ", ";

        for (Object key : map.keySet()) {
            columns.append("`").append(key).append("`").append(suffix);
            values.append("?").append(suffix);
            SqlKit.fillValues(vals, params.get(key));
        }
        columns.delete(columns.length() - suffix.length(), columns.length());
        values.delete(values.length() - suffix.length(), values.length());

        columns.append(")");
        values.append(")");
        sbr.append(columns).append(" VALUES ").append(values);
        return (new Sql(sbr.toString(), vals.toArray()));
    }

    /**
     * Make Query data Sql
     * 
     * @param data
     * @return select sql
     */
    @SuppressWarnings("unchecked")
    public static Sql query(Object data) {
        if (!(data instanceof Map)) {
            return (new Sql());
        }

        Map<?, ?> map = SqlKit.cloneMap(data);
        Map<String, Object> params = (Map<String, Object>) map.get(Consts.PARAMS_KEY);
        if (params.size() == 0) {
            return (new Sql());
		}
        
        String output = map.get(Consts.OUTPUT_KEY).toString();
        SqlKit.removeFields(map, Consts.OUTPUT_KEY, Consts.AS_KEY, Consts.OUTPUT_COLUMN_KEY, Consts.ORDER_KEY, Consts.PARAMS_KEY);

        Map<String, Object> ops = (Map<String, Object>) map.get(Consts.OPS_KEY);
        SqlKit.removeFields(map, Consts.OPS_KEY);

        StringBuilder sbr = new StringBuilder();
        sbr.append("SELECT ").append(output).append(" FROM `").append(map.get(Consts.TABLE_KEY)).append("` WHERE ");
        map.remove(Consts.TABLE_KEY);

        List<Object> vals = new ArrayList<Object>();
        String suffix = " AND ";

        for (Object key : map.keySet()) {
            if (!ops.containsKey(key)) {
                sbr.append("`").append(key).append("`").append(map.get(key)).append(suffix);
                SqlKit.fillValues(vals, params.get(key));
            }
        }
        sbr.delete(sbr.length() - suffix.length(), sbr.length());

        for (Object opsKey : ops.keySet()) {
            suffix = ops.get(opsKey).toString();
            sbr.append(suffix).append("`").append(opsKey).append("`").append(map.get(opsKey));
            SqlKit.fillValues(vals, params.get(opsKey));
        }

        return (new Sql(sbr.toString(), vals.toArray()));
    }
}
