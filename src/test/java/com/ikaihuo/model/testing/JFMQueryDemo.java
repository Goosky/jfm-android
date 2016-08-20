package com.ikaihuo.model.testing;

import java.math.BigInteger;

import org.junit.Test;

import com.ikaihuo.gp.storage.dc.api.idc.kits.Consts;
import com.ikaihuo.monkey.model.User;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Model.Match;

public class JFMQueryDemo {

	@Test
	public void test() {

		User user = new User();
		
		BigInteger start = new BigInteger("1");
		BigInteger end = new BigInteger("111");
		
		user.setId(Match.BW(start, end));
		user.setName(Match.AND("zcq"));
		user.setName(Match.EQ("zcq"));
		System.out.println(user);
		System.out.println("DB=\n"+JsonKit.toJson(user.dcExport().get(Consts.ATTR_KEY))+"\n");
		System.out.println(user.efficientAttrs());
		
		
		
	}

}
