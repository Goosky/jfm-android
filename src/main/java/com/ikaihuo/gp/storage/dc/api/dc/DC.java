package com.ikaihuo.gp.storage.dc.api.dc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ikaihuo.gp.storage.dc.api.idc.IDC;
import com.ikaihuo.gp.storage.dc.api.idc.kits.Consts;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Model.QueryOut;

/**
 * @author BruceZCQ
 */
public abstract class DC {

    @Autowired
    protected IDC    idc;

    private SqlLogDC sqlLog = new SqlLogDC();

    protected abstract String subSysName();

    private Object[] toArray(Model<?>... datas) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < datas.length; i++) {
            list.add(datas[i].dcExport().get(Consts.ATTR_KEY));
        }
        return list.toArray();
    }

    private void checkDc() {
        if (null == idc) {
            throw new IllegalArgumentException("idc is nullpoint.");
        }
    }

    /**
     * Set show Sql
     */
    public void showSql(boolean shown) {
        this.sqlLog.showSql(shown);
    }

    /**
     * Delete Data
     * 
     * @param datas
     * @return true:success;false:failure
     */
    public boolean delete(Model<?>... datas) {
        this.checkDc();
        Object[] _datas = this.toArray(datas);
        this.sqlLog.delete(this.subSysName(), _datas);
        return this.idc.delete(this.subSysName(), _datas);
    }

    /**
     * Update Data, the data must contain PrimaryKey column value
     * 
     * @param datas
     * @return true:success;false:failure
     */
    public boolean update(Model<?>... datas) {
        this.checkDc();
        Object[] _datas = this.toArray(datas);
        this.sqlLog.update(this.subSysName(), _datas);
        return this.idc.update(this.subSysName(), _datas);
    }

    /**
     * Save Data, the PrimaryKey `id` default put to Model
     * 
     * @param datas
     * @return true:success;false:failure
     */
    public boolean insert(Model<?>... datas) {
        List<Object> ids = this.save(datas);
        boolean ret = null != ids;
        if (ret) {
            if (ids.size() != datas.length) {
                ids.clear();
                return false;
            }
            for (int i = 0; i < datas.length; i++) {
                Model<?> model = datas[i];
                model.put(Consts.DEFAULT_OUTPUT, ids.get(i));
            }
        }
        return ret;
    }

    /**
     * Save Data
     * 
     * @param datas
     * @return save data success return ids, otherwise return empty list.
     */
    public List<Object> save(Model<?>... datas) {
        this.checkDc();
        Object[] _datas = this.toArray(datas);
        this.sqlLog.save(this.subSysName(), _datas);
        return this.idc.save(this.subSysName(), _datas);
    }

    /**
     * Query data, Only Support query data from two databases tables , and first
     * query the last table, and filter in first table data return this data.
     * 
     * @param datas
     * @return query output that contains data and data count
     */
    public QueryOut query(Model<?>... datas) {
        return this.query(Consts.DEFAULT_PAGE, Consts.DEFALUT_SIZE, datas);
    }
    
    /**
     * Query data, Only Support query data from two databases tables , and first
     * query the last table, and filter in first table data return this data.
     * 
     * @param page: data page , the first page is zero
     * @param size: data size
     * @param datas
     * @return query output that contains data and data count
     */
    @SuppressWarnings("unchecked")
    public <T extends Model<T>> QueryOut query(int page, int size, Model<?>... datas) {
        this.checkDc();
        Object[] _datas = this.toArray(datas);
        this.sqlLog.query(this.subSysName(), page, size, _datas);

        Map<String, Object> idcRet = this.idc.query(this.subSysName(), page, size, _datas);
        List<Map<String, Object>> retData = (List<Map<String, Object>>) idcRet.get(Consts.QUERY_DATA);

        List<T> queryRetData = new ArrayList<T>();
        //use first class return data
        Class<? extends Model<?>> modelClass = (Class<? extends Model<?>>) datas[0].getClass();
        for (int index = 0; index < retData.size(); index++) {
            T instance = null;
            try {
                instance = (T) modelClass.newInstance();
                instance.put(retData.get(index));
            } catch (InstantiationException | IllegalAccessException e) {
                throw (new RuntimeException(e.getLocalizedMessage()));
            }
            queryRetData.add(instance);
        }

        //cover data
        idcRet.put(Consts.QUERY_DATA, queryRetData);
        return (new QueryOut(idcRet));
    }
}
