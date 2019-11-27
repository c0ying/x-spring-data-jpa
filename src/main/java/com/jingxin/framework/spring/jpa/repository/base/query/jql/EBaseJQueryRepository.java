package com.jingxin.framework.spring.jpa.repository.base.query.jql;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface EBaseJQueryRepository{

	public <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Object... params);
	public <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Object... params);
	
	public <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Map<String, Object> params);
	public <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Map<String, Object> params);
	
	public <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Object... params);
	public <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Object... params);
	
	public <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Map<String, Object> params);
	public <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Map<String, Object> params);
	
	/**
	 * 执行计算总记录数据的jpql
	 * @param jqpl 计算总数的jpql语句
	 * @param params jpql参数
	 * @return
	 */
	public Long getCountByJPQL(String jqpl, Object... params);
	public Long getCountByJPQL(String jqpl, Map<String, Object> hits, Object... params);
	public Long getCountByJPQL(String jpql,Map<String, Object> params);
	/**
	 * 使用jpql查询指定一页的数据
	 * @param jpql jpql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params sql参数
	 * @return
	 */
	public <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Object... params);
	public <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params);
	
	public <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> params);
	public <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Map<String, Object> params);

	/**
	 * 使用jpql进行分页查询
	 * @param jpql jpql语句
	 * @param totalJqpl 查询总计记录数的jpql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params jpql参数
	 * @return PageView<E> 分页数据操作封装类
	 */
	public <E> Page<E> findPageViewByJPQL(String jpql, String totalJqpl, Class<E> classType, int startPosition, int size, Object... params);
	public <E> Page<E> findPageViewByJPQL(String jpql, String totalJqpl, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params);
	
	/**
	 * 使用jpql进行分页查询（无需手动定义计算记录总数jpql）
	 * @param jpql jpql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params jpql参数
	 * @return PageView<E> 分页数据操作封装类
	 */
	public <E> Page<E> findPageViewByJPQL(String jpql, Class<E> classType, int startPosition, int size, Object... params);
	public <E> Page<E> findPageViewByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params);
	
}
