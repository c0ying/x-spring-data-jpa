package com.jingxin.framework.spring.jpa.repository.base.query.impl.jql;

import com.jingxin.framework.spring.jpa.repository.base.query.jql.EBaseJQueryRepository;
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

public class EBaseJQueryRepositoryImpl<T, ID extends Serializable> extends EnhanceJQLRepositoryImpl<T, ID> implements EBaseJQueryRepository{

	public EBaseJQueryRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Object... params){
		return findAllByJPQL(jpql, classType, null, params);
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Object... params){
		Query query = em.createQuery(jpql, classType);
		setParam(query, params);
		setHits(query, hits);
		return query.getResultList();
	}
	
	public <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Map<String, Object> params){
		return findAllByJPQL(jpql, classType, null, params);
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Map<String, Object> params){
		Query query = em.createQuery(jpql, classType);
		setParam(query, params);
		setHits(query, hits);
		return query.getResultList();
	}
	
	public <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Object... params){
		return findSingleResultByJPQL(jpql, classType, null, params);
	}
	
	public <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Object... params){
		List<E> data = findScrollResultByJPQL(jpql, classType, 0, 1, hits, params);
		return getSingleObjectFromList(data);
	}
	
	public <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Map<String, Object> params){
		return findSingleResultByJPQL(jpql, classType, null, params);
	}
	
	public <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Map<String, Object> params){
		List<E> data = findScrollResultByJPQL(jpql, classType, 0, 1, hits, params);
		return getSingleObjectFromList(data);
	}

	public Long getCountByJPQL(String jpql, Object... params){
		return getCountByJPQL(jpql, null, params);
	}
	
	public Long getCountByJPQL(String jpql, Map<String, Object> hits, Object... params){
		Long sum = this.findSingleResultByJPQL(jpql, Long.class, hits, params);
		return sum==null?0:sum;
	}

	public Long getCountByJPQL(String jpql,Map<String, Object> params){
		Long sum = this.findSingleResultByJPQL(jpql, Long.class, null, params);
		return sum==null?0:sum;
	}
	
	public <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Object... params){
		return findScrollResultByJPQL(jpql, classType, startPosition, size, null, params);
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params){
		Query query = em.createQuery(jpql, classType);
		setParam(query, params);
		setHits(query, hits);
		query.setFirstResult(startPosition);
		query.setMaxResults(size);
		return query.getResultList();
	}
	
	public <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> params){
		return findScrollResultByJPQL(jpql, classType, startPosition, size, null, params);
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Map<String, Object> params){
		Query query = em.createQuery(jpql, classType);
		setParam(query, params);
		setHits(query, hits);
		query.setFirstResult(startPosition);
		query.setMaxResults(size);
		return query.getResultList();
	}

	public <E> Page<E> findPageViewByJPQL(String jpql, String totalJqpl, Class<E> classType, int startPosition, int size, Object... params){
		return findPageViewByJPQL(jpql, totalJqpl, classType, startPosition, size, null, params);
	}
	
	public <E> Page<E> findPageViewByJPQL(String jpql, String totalJqpl, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params){
		Long count = this.getCountByJPQL(totalJqpl, hits, params);
		if (count > 0) {
			List<E> rows = this.findScrollResultByJPQL(jpql, classType, startPosition, size, hits, params);
//			PageView<E> pageView = new PageView<E>(startPosition, count, size, rows==null?new ArrayList<E>():rows);
//			return pageView;
			int num = startPosition / size;
			if (startPosition % size != 0) num++;
			
			return new PageImpl<E>(rows==null?new ArrayList<E>():rows, new PageRequest(num, size), count);
		}
		return new PageImpl<E>(new ArrayList<E>(), new PageRequest(0, size), count);
//		return new PageView<E>(startPosition, count, size, new ArrayList<E>());
	}
	
	public <E> Page<E> findPageViewByJPQL(String jpql, Class<E> classType, int startPosition, int size, Object... params){
		return findPageViewByJPQL(jpql, classType, startPosition, size, null, params);
	}
	
	public <E> Page<E> findPageViewByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params){
		String totalJpql = NLNativeQueryUtil.generateCountQL(jpql);
		return this.findPageViewByJPQL(jpql, totalJpql, classType, startPosition, size, hits, params);
	}
}
