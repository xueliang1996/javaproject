package com.myjo.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactoryUtil {

	private String resource = "com/myjo/data/mybatis-config.xml";
	
	public SqlSession getSqlSession() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		System.out.println(factory);
		SqlSession session = factory.openSession();
		return session;
	}
	/*public static void main (String args[]) {
		SessionFactoryUtil u = new SessionFactoryUtil();
		try {
			System.out.println(u.getSqlSession());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
