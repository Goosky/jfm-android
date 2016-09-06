package com.ikaihuo.jfm.testing;

import org.junit.Test;

import com.ikaihuo.gp.subsystem.api.model.FireCity;
import com.ikaihuo.idc.kits.Consts;
import com.ikaihuo.idc.kits.Sort;
import com.ikaihuo.idc.kits.SqlKit;
import com.ikaihuo.idc.kits.SqlKit.Sql;

public class OrderTest {

	@Test
	public void test() {
		FireCity c = new FireCity();
		c.setAreaCode("code");
		c.setOrders(Sort.DESC(FireCity.AREA_CODE), Sort.DESC(FireCity.CREATE_DATE));
		
		Sql sql = SqlKit.query(c.dcExport().get(Consts.ATTR_KEY));
		String[] orders = (String[]) c.dcExport().get(Consts.ORDER_KEY);
		System.out.println("sql ==" +sql.orderBy(orders));
	}

}
