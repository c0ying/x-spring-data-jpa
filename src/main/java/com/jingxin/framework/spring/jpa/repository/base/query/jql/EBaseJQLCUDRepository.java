package com.jingxin.framework.spring.jpa.repository.base.query.jql;

import java.util.Map;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础操作Dao
 * @author cyh
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface EBaseJQLCUDRepository{

	/**
	 * 执行jpql语句的CUD操作
	 * @param jpql
	 */
	public <E> void executeUpdateByJPQL(String jpql, Object... params);
	
	public <E> void executeUpdateByJPQL(String jpql, Map<String, Object> hits, Object... params);

}
