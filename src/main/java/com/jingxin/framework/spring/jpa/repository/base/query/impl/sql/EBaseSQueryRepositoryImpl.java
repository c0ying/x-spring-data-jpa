package com.jingxin.framework.spring.jpa.repository.base.query.impl.sql;

import com.jingxin.framework.spring.jpa.repository.base.query.sql.EBaseSQueryRepository;
import com.jingxin.framework.spring.jpa.util.NLNativeQueryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EBaseSQueryRepositoryImpl<T, ID extends Serializable> extends EnhanceSQLRepositoryImpl<T, ID> implements EBaseSQueryRepository{

	public EBaseSQueryRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findAllBySQL(String sql, Class<E> classType, Object... params) {
		Query query = em.createNativeQuery(sql, classType);
		setParam(query, params);
		return query.getResultList();
	}
	
	@SuppressWarnings("rawtypes")
	public List findAllBySQL(String sql, Object... params) {
		Query query = em.createNativeQuery(sql);
		setParam(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findAllBySQL(String sql, Class<E> classType, Map<String, Object> params) {
		Query query = em.createNativeQuery(sql, classType);
		setParam(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	public Object findSingleResultBySQL(String sql, Object... params){
		List data = findScrollResultBySQL(sql, 0, 1, params);
		if (data != null) {
			if (data.size() > 0) {
				return data.get(0);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public <E> E findSingleResultBySQL(String sql, Class<E> classType, Object... params){
		List<E> data = findScrollResultBySQL(sql, classType, 0, 1, params);
		return getSingleObjectFromList(data);
	}
	
	public <E> E findSingleResultBySQL(String sql, Class<E> classType, Map<String, Object> params) {
		List<E> data = findScrollResultBySQL(sql, classType, 0, 1, params);
		return getSingleObjectFromList(data);
	}
	
	public Long getCountBySQL(String sql, Object... params){
		Query query = em.createNativeQuery(sql);
		setParam(query, params);
		Object object = query.getSingleResult();
		Long sum = null;
		if(object instanceof Long)
			sum = (Long)object;
		else if(object instanceof Number)
			sum = ((Number)object).longValue();
		return sum==null?0:sum;
	}
	
	public Long getCountBySQL(String sql, Map<String, Object> params){
		Query query = em.createNativeQuery(sql);
		setParam(query, params);
		Object object = query.getSingleResult();
		Long sum = null;
		if(object instanceof Long)
			sum = (Long)object;
		else if(object instanceof Number)
			sum = ((Number)object).longValue();
		return sum==null?0:sum;
	}
	
	@SuppressWarnings("rawtypes")
	public List findScrollResultBySQL(String sql, int startPosition, int size, Object... params){
		Query query = em.createNativeQuery(sql);
		setParam(query, params);
		try {
			query.setFirstResult(startPosition);
			query.setMaxResults(size);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List findScrollResultBySQL(String sql, int startPosition, int size, Map<String, Object> params){
		Query query = em.createNativeQuery(sql);
        setParam(query, params);
		try {
			query.setFirstResult(startPosition);
			query.setMaxResults(size);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findScrollResultBySQL(String sql, Class<E> classType, int startPosition, int size, Object... params){
		Query query = em.createNativeQuery(sql, classType);
		setParam(query, params);
		query.setFirstResult(startPosition);
		query.setMaxResults(size);
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> findScrollResultBySQL(String sql, Class<E> classType, int startPosition, int size, Map<String, Object> params){
		Query query = em.createNativeQuery(sql, classType);
		setParam(query, params);
		query.setFirstResult(startPosition);
		query.setMaxResults(size);
		return query.getResultList();
	}
	
	public <E> Page<E> findPageViewBySQL(String sql, String totalSql, Class<E> classType, int startPosition, int size, Object... params){
		Long count = this.getCountBySQL(totalSql, params);
		if (count > 0) {
			List<E> rows = this.findScrollResultBySQL(sql, classType, startPosition, size, params);
//			PageView<E> pageView = new PageView<E>(startPosition, count, size, rows==null?new ArrayList<E>():rows);
//			return pageView;
			int num = startPosition / size;
			if (startPosition % size != 0) num++;
			
			return new PageImpl<E>(rows==null?new ArrayList<E>():rows, new PageRequest(num, size), count);
		}
//		return new PageView<E>(startPosition, count, size, new ArrayList<E>());
		return new PageImpl<E>(new ArrayList<E>(), new PageRequest(0, size), count);
	}
	
	public Page<T> findPageViewBySQL(String sql, int startPosition, int size, Object... params){
		return findPageViewBySQL(sql, getDomainClass(), startPosition, size, params);
	}

	@Override
	public <E> Page<E> findPageViewBySQL(String sql, Class<E> classType, int startPosition, int size, Object... params) {
		String totalSql = NLNativeQueryUtil.generateCountQL(sql);
		return this.findPageViewBySQL(sql, totalSql, classType, startPosition, size, params);
	}
	
	public <E> Page<E> findPageViewBySQL(String sql, Class<E> classType, int startPosition, int size, Map<String, Object> params) {
		String totalSql = NLNativeQueryUtil.generateCountQL(sql);
		Long count = this.getCountBySQL(totalSql, params);
		if (count > 0) {
			List<E> rows = this.findScrollResultBySQL(sql, classType, startPosition, size, params);
//			PageView<E> pageView = new PageView<E>(startPosition, count, size, rows==null?new ArrayList<E>():rows);
//			return pageView;
			int num = startPosition / size;
			if (startPosition % size != 0) num++;
			
			return new PageImpl<E>(rows==null?new ArrayList<E>():rows, new PageRequest(num, size), count);
		}
//		return new PageView<E>(startPosition, count, size, new ArrayList<E>());
		return new PageImpl<E>(new ArrayList<E>(), new PageRequest(0, size), count);
	}

	@Override
	public Page<?> findRawPageViewBySQL(String sql, String totalSql, int startPosition, int size, Object... params) {
		Long count = this.getCountBySQL(totalSql, params);
		if (count > 0) {
			List<?> rows = this.findScrollResultBySQL(sql, startPosition, size, params);
//			PageView<?> pageView = new PageView (startPosition, count, size, rows);
//			return pageView;
			int num = startPosition / size;
			if (startPosition % size != 0) num++;
			
			return new PageImpl(rows==null?new ArrayList():rows, new PageRequest(num, size), count);
		}
//		return new PageView (startPosition, count, size, Collections.EMPTY_LIST);
		return new PageImpl(new ArrayList(), new PageRequest(0, size), count);
	}

	@Override
	public Page<?> findRawPageViewBySQL(String sql, int startPosition, int size, Object... params) {
		return findRawPageViewBySQL(sql, NLNativeQueryUtil.generateCountQL(sql), startPosition, size, params);
	}

	@Override
	public Page<?> findRawPageViewBySQL(String sql, int startPosition, int size, Map<String, Object> params) {
		String totalSql = NLNativeQueryUtil.generateCountQL(sql);
		Long count = this.getCountBySQL(totalSql, params);
		if (count > 0) {
			List<?> rows = this.findScrollResultBySQL(sql, startPosition, size, params);
//			PageView<?> pageView = new PageView (startPosition, count, size, rows);
//			return pageView;
			int num = startPosition / size;
			if (startPosition % size != 0) num++;
			
			return new PageImpl(rows==null?new ArrayList():rows, new PageRequest(num, size), count);
		}
//		return new PageView (startPosition, count, size, Collections.EMPTY_LIST);
		return new PageImpl(new ArrayList(), new PageRequest(0, size), count);
	}

	
}
