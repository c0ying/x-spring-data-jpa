package com.jingxin.framework.spring.jpa.repository.base.query.sql;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础操作Dao
 * @author cyh
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface EBaseSQLCUDRepository {

	/**
	 * 执行sql语句 的CUD操作
	 * @param sql
	 */
	public <E> void executeUpdateBySQL(String sql, Object... params);
	

}
