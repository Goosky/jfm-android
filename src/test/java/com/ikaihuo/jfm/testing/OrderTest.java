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
		c.setOrders(Sort.DESC(FireCity.AREA_CODE), Sort.ASC(FireCity.CREATE_DATE));
		
		Sql sql = SqlKit.query(c.dcExport().get(Consts.ATTR_KEY));
		String[] orders = (String[]) c.dcExport().get(Consts.ORDER_KEY);
		System.out.println("sql ==" +sql.orderBy(orders));
	}
	
	@Test
	public void testParserOrder() {
		String order = Sort.ASC(FireCity.CREATE_DATE);
		
		System.out.println(order);
		
		String[] orders = order.split("ASC");
		String column = "";
		if (orders.length == 2) {
			column = orders[0].trim();
		}
		System.out.println(column);
	}

}
