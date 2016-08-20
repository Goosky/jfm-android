package com.ikaihuo.model.testing;

import java.math.BigInteger;

import org.junit.Test;

import com.ikaihuo.jfm.testing.MyDC;
import com.ikaihuo.monkey.model.User;
import com.jfinal.plugin.activerecord.Model.Match;

public class NewDcLogic {

	@Test
	public void test() {
		
		MyDC dc = new MyDC();
		
		User user = new User();
		
		user.setId(Match.LIKE(new BigInteger("123")));
		user.setName(Match.BW("a", "b"));
		
		dc.delete(user);
		dc.save(user);
		
		user.setAddr(Match.OR("addr"));
		dc.query(user);
		
		user = new User();
		
		user.setId(new BigInteger("123"));
		user.setName("newname");
		user.setAddr("aa");
		dc.update(user);
	}

}
