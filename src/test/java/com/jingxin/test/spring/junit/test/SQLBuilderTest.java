package com.jingxin.test.spring.junit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jingxin.framework.spring.jpa.sqlBuilder.JoinSQLBuilder;
import com.jingxin.framework.spring.jpa.sqlBuilder.impl.JoinSQLBuilderImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SQLBuilderTest {

	@Test
	public void join() {
		JoinSQLBuilder sqlBuilder = new JoinSQLBuilderImpl("mysql");
		sqlBuilder.from("test");
		sqlBuilder.join("b","").on("a.id=b.id");
		System.out.println(sqlBuilder.toString());
	}
}
