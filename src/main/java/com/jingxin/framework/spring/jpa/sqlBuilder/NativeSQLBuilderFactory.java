package com.jingxin.framework.spring.jpa.sqlBuilder;

import com.alibaba.druid.sql.builder.SQLBuilderFactory;
import com.jingxin.framework.spring.jpa.sqlBuilder.impl.JoinSQLBuilderImpl;

public class NativeSQLBuilderFactory extends SQLBuilderFactory{

	public static JoinSQLBuilder createJoinSQLBuilder(String dbType) {
        return new JoinSQLBuilderImpl(dbType);
    }
    
    public static JoinSQLBuilder createJoinSQLBuilder(String sql, String dbType) {
        return new JoinSQLBuilderImpl(sql, dbType);
    }

}
