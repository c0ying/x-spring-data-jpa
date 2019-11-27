package com.jingxin.framework.spring.jpa.sqlBuilder;

import com.alibaba.druid.sql.builder.SQLSelectBuilder;

public interface JoinSQLBuilder extends SQLSelectBuilder{

	JoinSQLBuilder leftJoin(String table, String alias);
	JoinSQLBuilder rightJoin(String table, String alias);
	JoinSQLBuilder join(String table, String alias);
	JoinSQLBuilder on(String expr);
}
