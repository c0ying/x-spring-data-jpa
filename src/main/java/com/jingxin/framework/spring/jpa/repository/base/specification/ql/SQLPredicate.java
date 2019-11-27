package com.jingxin.framework.spring.jpa.repository.base.specification.ql;

import java.util.LinkedHashMap;
import java.util.Map;

public class SQLPredicate {

	private String sql;
	private Map<String, Object> params = new LinkedHashMap<String, Object>();
	
	public SQLPredicate(String sql, Map<String, Object> params) {
		super();
		this.sql = sql;
		this.params = params;
	}
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
}
