package com.ikaihuo.model.testing;

import java.math.BigInteger;

import org.junit.Test;

import com.jfinal.plugin.activerecord.Model.Match;

public class QueryUUIDDemo {

	@Test
	public void test() {
		
		BigInteger start = new BigInteger("1");
		BigInteger end = new BigInteger("111");
		
		BigInteger mq = Match.BW(start, end);
		
		Match q = Match.getQuery(mq);
		
		
		int i = Match.EQ(12);
		
		q = Match.getQuery(i);
		
		System.out.println(q);
	}

}
