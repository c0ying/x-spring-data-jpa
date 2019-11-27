package com.jingxin.framework.spring.jpa.repository.base.query.sql;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseSQueryRepository<T> {

	/**
	 * 使用sql进行分页查询（返回当前指定的实体类型）
	 * @param sql sql语句
	 * @param totalSql 查询总记录数的sql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params jpql参数
	 * @return PageView<T> 当前指定的实体类型的分页数据操作封装类
	 */
	public Page<T> findPageViewBySQL(String sql, String totalSql, int startPosition, int size, Object... params);
	
	/**
	 * 使用sql进行分页查询（无需手动定义计算记录总数sql）
	 * @param sql sql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params sql参数
	 * @return PageView<T> 当前指定的实体类型的分页数据操作封装类
	 */
	public Page<T> findPageViewBySQL(String sql, int startPosition, int size, Object... params);
}
