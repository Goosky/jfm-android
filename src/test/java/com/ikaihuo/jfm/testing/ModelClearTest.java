package com.ikaihuo.jfm.testing;


import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ikaihuo.gp.subsystem.api.model.FireCity;

public class ModelClearTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		FireCity city = new FireCity();
		city.setAreaCode("areaCode");
		city.setIconImage("iconImage");
		city.setDescription("description");
		
		FireCity ccity = city.clear(FireCity.ICON_IMAGE);
		
		System.out.println(city.efficientAttrs());
		System.out.println("old=>\n"+city+"\ncleared=>\n"+ccity);
		
		FireCity cccity = city.clear(FireCity.ICON_IMAGE, FireCity.AREA_CODE);
		System.out.println("old=>\n"+city+"\ncleared=>\n"+cccity);
		
		System.out.println(new Date());
	}

}
