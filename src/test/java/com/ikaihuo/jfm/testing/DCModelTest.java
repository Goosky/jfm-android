package com.ikaihuo.jfm.testing;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ikaihuo.monkey.model.Profile;
import com.ikaihuo.monkey.model.User;
import com.jfinal.plugin.activerecord.Model.Match;

public class DCModelTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		MyDC idc = new MyDC();

		User u = new User();
		u.setId(new BigInteger("11"));
		u.setName("zcq");
		u.selectDC(idc);
		u.delete();
		
		Profile p = new Profile();
		p.setId(new BigInteger("12"));
		p.setInfo("information");
		p.setUid( new BigInteger("1111"));
		p.selectDC(idc);
		p.delete();

		p = new Profile();
		p.setId( new BigInteger("12"));
		p.setInfo("information");
		p.setUid( new BigInteger("1111"));
		p.selectDC(idc);
		p.query();

		u = new User();
		u.setId(new BigInteger("11"));
		u.setName("zcq");
		u.selectDC(idc);
		u.update();

		p = new Profile();
		p.setId(new BigInteger("12"));
		p.setInfo("information");
		p.selectDC(idc);
		p.update();

		u = new User();
		u.setId(new BigInteger("11"));
		u.setName("zcq");
		u.selectDC(idc);
		u.delete();

		p = new Profile();
		p.setInfo("information");
		p.selectDC(idc);
		p.delete();

		User uu = new User();
		uu.setName("zcq");
		uu.selectDC(idc);
		boolean ret = uu.insert();
		System.out.println("😋Insert User =>" + uu + ", result =>" + ret);

		uu = new User();
		uu.setName("zcq");
		uu.selectDC(idc);
		Object id = uu.save();
		// put id for yourself
		uu.setId(new BigInteger(id.toString()));
		System.out.println("😋Saved User =>" + uu + ", Id =>" + id);

		p = new Profile();
		p.setInfo("information");
		p.selectDC(idc);
		p.save();

		uu = new User();
		uu.setId(new BigInteger("11"));
		uu.setName("zcq");
		uu.selectDC(idc);
		uu.query();

		uu = new User();
		uu.setId(new BigInteger("11"));
		uu.setName("zcq");
		uu.setName(Match.BW("a", "b"));
		uu.selectDC(idc);
		uu.query();

		uu = new User();
		uu.setId(Match.BW(new BigInteger("11"), new BigInteger("144")));
		uu.selectDC(idc);
		uu.query();
	}

}
