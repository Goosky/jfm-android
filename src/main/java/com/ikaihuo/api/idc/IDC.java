package com.ikaihuo.api.idc;

import java.util.List;
import java.util.Map;

import com.ikaihuo.galaxy.rpc.api.Export;

/**
 * @author BruceZCQ
 */
@Export
public interface IDC {

    /**
     * Delete data
     * 
     * @param subSysName
     * @param datas
     * @return true: success; false: failure;
     */
    public boolean delete(String subSysName, Object... datas);

    /**
     * Update data, the data must contain PrimaryKey column value
     * 
     * @param subSysName
     * @param datas
     * @return true:success;false:failure
     */
    public boolean update(String subSysName, Object... datas);

    /**
     * Save Data
     * 
     * @param subSysName
     * @param datas
     * @return save data success return ids, otherwise return empty list.
     */
    public List<Object> save(String subSysName, Object... datas);

    /**
     * Query data,support query data from two database tables , and first query
     * the last table, and filter in first table data return this data.
     * 
     * @param subSysName
     * @param datas
     * @return query output, data into the map
     */
    public Map<String, Object> query(String subSysName, int page, int size, Object... datas);
}
