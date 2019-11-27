package com.jingxin.framework.spring.jpa.sqlBuilder.impl;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource.JoinType;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.builder.impl.SQLSelectBuilderImpl;
import com.alibaba.druid.util.StringUtils;
import com.jingxin.framework.spring.jpa.sqlBuilder.JoinSQLBuilder;

public class JoinSQLBuilderImpl extends SQLSelectBuilderImpl implements JoinSQLBuilder{

	public JoinSQLBuilderImpl(String dbType) {
		super(dbType);
	}
	
	public JoinSQLBuilderImpl(String sql, String dbType){
		super(sql, dbType);
	}

	@Override
	public JoinSQLBuilder leftJoin(String table, String alias) {
		join(table, alias, JoinType.LEFT_OUTER_JOIN);
		return this;
	}

	@Override
	public JoinSQLBuilder rightJoin(String table, String alias) {
		join(table, alias, JoinType.RIGHT_OUTER_JOIN);
		return this;
	}

	@Override
	public JoinSQLBuilder join(String table, String alias) {
		join(table, alias, JoinType.JOIN);
		return this;
	}

	@Override
	public JoinSQLBuilder on(String expr) {
		SQLTableSource sqlTableSource = getQueryBlock().getFrom();
		if (sqlTableSource != null && sqlTableSource instanceof SQLJoinTableSource) {
			((SQLJoinTableSource)sqlTableSource).setCondition(SQLUtils.toSQLExpr(expr));
		}
		return this;
	}

	protected JoinSQLBuilder join(String table, String alias, JoinType joinType) {
		SQLJoinTableSource join = new SQLJoinTableSource();
		join.setJoinType(joinType);
		join.setLeft(getQueryBlock().getFrom());
		SQLExprTableSource tableReference = new SQLExprTableSource();
		tableReference.setExpr(new SQLIdentifierExpr(table));
		if (!StringUtils.isEmpty(alias)) {
			tableReference.setAlias(alias);
		}
		join.setRight(tableReference);
		getQueryBlock().setFrom(join);
		return this;
	}
}
