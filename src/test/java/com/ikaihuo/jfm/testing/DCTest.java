package com.ikaihuo.jfm.testing;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ikaihuo.gp.storage.dc.api.idc.kits.Consts;
import com.ikaihuo.monkey.model.Profile;
import com.ikaihuo.monkey.model.User;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Model.Match;
import com.jfinal.plugin.activerecord.Model.QueryOut;

public class DCTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		MyDC dc = new MyDC();
		
		User u = new User();
		u.setId(new BigInteger("11"));
		u.setName("zcq");
		dc.delete(u);

		Profile p = new Profile();
		p.setId(new BigInteger("12"));
		p.setInfo("information");
		p.setUid(new BigInteger("1111"));
		dc.delete(p);

		p = new Profile();
		p.setId(new BigInteger("12"));
		p.setInfo("information");
		p.setUid(new BigInteger("1111"));
		dc.query(p);
		
		u = new User();
		u.setId(new BigInteger("11"));
		u.setName("zcq");
		dc.update(u);

		p = new Profile();
		p.setId(new BigInteger("12"));
		p.setInfo("information");
		dc.update(p);

		u = new User();
		u.setId(new BigInteger("11"));
		u.setName("zcq");

		p = new Profile();
		p.setInfo("information");
		dc.delete(u, p);

		User uu = new User();
		uu.setName("zcq");
		boolean ret = dc.insert(uu);
		System.out.println("😋Insert User =>"+uu +", result =>"+ret);
		
		uu = new User();
		uu.setName("zcq");
		List<Object> ids = dc.save(uu);
		//put id for yourself
		uu.setId(new BigInteger(ids.get(0).toString()));
		System.out.println("😋Saved User =>"+uu+", Ids =>"+ids);

		p = new Profile();
		p.setInfo("information");
		dc.save(p);

		uu = new User();
		uu.setId(new BigInteger("11"));
		uu.setName("zcq");
		dc.query(uu);

		uu = new User();
		uu.setId(new BigInteger("11"));
		uu.setName(Match.BW("a", "b"));
		dc.query(uu);

		uu = new User();
		uu.setId(Match.BW(new BigInteger("11"), new BigInteger("144")));
		dc.query(uu);

		// return User
		uu = new User();
		uu.setName("zcq");
		p = new Profile();
		p.setInfo("information");
		QueryOut queryOut = dc.query(uu, p);
		List<User> users = queryOut.getQueryData();
		for (int i = 0; i < users.size(); i++) {
			System.out.println("toJson=>" + JsonKit.toJson(users.get(i)));
			System.out.println("Model=>" + users.get(i));
			System.out.println("Class=>" + users.get(i).getClass());
		}
		System.out.println("QueryData Count=>"+queryOut.getQueryCount());

		// return Profile
		uu = new User();
		uu.setName("zcq");
		uu.setOutput("id", "cccccid");
		Map<?, ?> map = (Map<?, ?>)uu.dcExport().get(Consts.ATTR_KEY);
		System.out.println("SqlOutputColumn logic1=>"+map.get(Consts.OUTPUT_COLUMN_KEY)+"\nSqlOutputColumn logic2=>"+uu.getStr(Consts.OUTPUT_COLUMN_KEY));
		System.out.println("SqlAS logic1=>"+map.get(Consts.AS_KEY)+"\nSqlAS logic2=>"+uu.getStr(Consts.AS_KEY));
		p = new Profile();
		p.setInfo("information");
		queryOut = dc.query(p, uu);
		List<Profile> profiles = queryOut.getQueryData();
		for (int i = 0; i < profiles.size(); i++) {
			System.out.println("toJson=>" + JsonKit.toJson(profiles.get(i)));
			System.out.println("Model=>" + profiles.get(i));
			System.out.println("Class=>" + profiles.get(i).getClass());
		}
		System.out.println("QueryData Count=>"+queryOut.getQueryCount());
	}

}
