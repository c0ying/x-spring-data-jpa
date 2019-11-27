package com.jingxin.framework.spring.jpa.repository.base.query.jql;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJQueryRepository<T>{

	/**
	 * 使用jpql进行分页查询（返回当前指定的实体类型）
	 * @param jpql jpql语句
	 * @param totalJqpl 查询总计记录数的jpql语句
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params jpql参数
	 * @return PageView<T> 当前指定的实体类型的分页数据操作封装类
	 */
	public Page<T> findPageViewByJPQL(String jpql, String totalJqpl, int startPosition, int size, Object... params);
	public Page<T> findPageViewByJPQL(String jpql, String totalJqpl, int startPosition, int size, Map<String, Object> hits, Object... params);
	
	/**
	 * 使用jpql进行分页查询（无需手动定义计算记录总数jpql）
	 * @param jpql jpql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params jpql参数
	 * @return PageView<T> 当前指定的实体类型的分页数据操作封装类
	 */
	public Page<T> findPageViewByJPQL(String jpql, int startPosition, int size, Object... params);
	public Page<T> findPageViewByJPQL(String jpql, int startPosition, int size, Map<String, Object> hits, Object... params);
}
