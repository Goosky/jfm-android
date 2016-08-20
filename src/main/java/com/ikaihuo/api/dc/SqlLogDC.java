package com.ikaihuo.api.dc;

import java.util.List;
import java.util.Map;

import com.ikaihuo.api.idc.IDC;
import com.ikaihuo.idc.kits.SqlKit;
import com.ikaihuo.idc.kits.SqlKit.Sql;

/**
 * @author BruceZCQ
 */
public class SqlLogDC implements IDC {

    private boolean shown = false;

    public SqlLogDC() {
    }

    public void showSql(boolean shown) {
        this.shown = shown;
    }

    private void printSql(int idx, String type, String subSysName, Sql sql) {
        System.out.println("\nSubSysName=>" + subSysName + "️\nSQL Index=>" + idx + "\nSQL Type=>" + type + "\nSQL=>️ " + sql + "️");
    }

    @Override
    public boolean delete(String subSysName, Object... datas) {
        if (!shown) {
            return false;
        }
        for (int i = 0; i < datas.length; i++) {
            this.printSql(i, "DELETE", subSysName, SqlKit.delete(datas[i]));
        }
        return false;
    }

    @Override
    public boolean update(String subSysName, Object... datas) {
        if (!shown) {
            return false;
        }
        for (int i = 0; i < datas.length; i++) {
            this.printSql(i, "UPDATE", subSysName, SqlKit.update(datas[i]));
        }
        return false;
    }

    @Override
    public List<Object> save(String subSysName, Object... datas) {
        if (!shown) {
            return null;
        }
        for (int i = 0; i < datas.length; i++) {
            this.printSql(i, "SAVE | INSERT", subSysName, SqlKit.save(datas[i]));
        }
        return null;
    }

    @Override
    public Map<String, Object> query(String subSysName, int page, int size, Object... datas) {
        if (!shown) {
            return null;
        }
        for (int i = datas.length - 1; i >= 0; i--) {
            this.printSql(i, "QUERY | SELECT", subSysName, SqlKit.query(datas[i]));
        }
        
        return null;
    }

}
