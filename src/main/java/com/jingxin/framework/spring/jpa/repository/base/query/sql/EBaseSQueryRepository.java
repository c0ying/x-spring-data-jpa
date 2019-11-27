package com.jingxin.framework.spring.jpa.repository.base.query.sql;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EBaseSQueryRepository {

	/**
	 * 使用sql语句全查
	 * @param sql 查询的sql语句
	 * @param classType 返回的类型
	 * @param params sql参数
	 * @return
	 */
	public <E> List<E> findAllBySQL(String sql, Class<E> classType, Object... params);
	
	/**
	 * 使用sql语句全查（不指定返回类型）
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findAllBySQL(String sql, Object... params);
	
	public <E> List<E> findAllBySQL(String sql, Class<E> classType, Map<String, Object> params);

	/**
	 * 使用sql语句查询一条记录
	 * @param sql sql语句
	 * @param classType 返回类型
	 * @param params sql参数
	 * @return
	 */
	public <E> E findSingleResultBySQL(String sql, Class<E> classType, Object... params);
	
	/**
	 * 使用sql语句查询一条记录（不指定返回类型）
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object findSingleResultBySQL(String sql, Object... params);
	
	public <E> E findSingleResultBySQL(String sql, Class<E> classType, Map<String, Object> params);

	/**
	 * 执行计算总记录数的sql
	 * @param sql 计算总数的sql语句
	 * @param params sql参数
	 * @return
	 */
	public Long getCountBySQL(String sql, Object... params);

	/**
	 * 执行计算总记录数的sql
	 * @param sql 计算总数的sql语句
	 * @param params sql参数
	 * @return
	 */
	public Long getCountBySQL(String sql,Map<String, Object> params);
	
	/**
	 * 使用sql查询指定一页的数据（不指定返回类型）
	 * @param sql sql语句
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params sql参数
	 * @return
	 */
	public List<?> findScrollResultBySQL(String sql, int startPosition, int size, Object... params);
	
	public List<?> findScrollResultBySQL(String sql, int startPosition, int size, Map<String, Object> params);

	/**
	 * 使用sql查询指定一页的数据
	 * @param sql sql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params sql参数
	 * @return
	 */
	public <E> List<E> findScrollResultBySQL(String sql, Class<E> classType, int startPosition, int size, Object... params);
	
	public <E> List<E> findScrollResultBySQL(String sql, Class<E> classType, int startPosition, int size, Map<String, Object> params);

	/**
	 * 使用sql进行分页查询
	 * @param sql sql语句
	 * @param totalSql 查询总记录数的sql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params sql参数
	 * @return PageView<E> 分页数据操作封装类
	 */
	public <E> Page<E> findPageViewBySQL(String sql, String totalSql, Class<E> classType, int startPosition, int size, Object... params);
	
	/**
	 * 使用sql进行分页查询（无需手动定义计算记录总数sql）
	 * @param sql sql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params sql参数
	 * @return PageView<E> 分页数据操作封装类
	 */
	public <E> Page<E> findPageViewBySQL(String sql, Class<E> classType, int startPosition, int size, Object... params);

	public <E> Page<E> findPageViewBySQL(String sql, Class<E> classType, int startPosition, int size, Map<String, Object> params);
	
	public Page<?> findRawPageViewBySQL(String sql, String totalSql, int startPosition, int size, Object... params);

	/**
	 * 使用sql进行分页查询（无需手动定义计算记录总数sql）
	 * @param sql sql语句
	 * @param classType 返回类型
	 * @param startPosition 开始显示记录索引
	 * @param size 一页的最大的记录数
	 * @param params sql参数
	 * @return PageView<E> 分页数据操作封装类
	 */
	public Page<?> findRawPageViewBySQL(String sql, int startPosition, int size, Object... params);
	
	public Page<?> findRawPageViewBySQL(String sql, int startPosition, int size, Map<String, Object> params);
}
