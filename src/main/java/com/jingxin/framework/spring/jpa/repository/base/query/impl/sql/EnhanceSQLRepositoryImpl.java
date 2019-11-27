package com.jingxin.framework.spring.jpa.repository.base.query.impl.sql;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jingxin.framework.spring.jpa.repository.base.query.impl.jql.FullJQueryRepositoryImpl;
import com.jingxin.framework.spring.jpa.repository.base.query.sql.EnhanceSQLRepository;
import com.jingxin.framework.spring.jpa.util.NLNativeQueryUtil;

public class EnhanceSQLRepositoryImpl<T, ID extends Serializable> extends FullJQueryRepositoryImpl<T, ID> implements EnhanceSQLRepository<T>{

	public EnhanceSQLRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public Page<T> findPageBySQL(String sql, Pageable pageable) {
		String countSql = NLNativeQueryUtil.generateCountQL(sql);
		return findPageBySQL(sql, countSql, pageable);
	}
	
	public Page<T> findPageBySQL(String sql, String totalSql, Pageable pageable) {
		Sort sort = pageable.getSort();
		String wrapSql = NLNativeQueryUtil.applySorting(sql, sort);
		Query nativeQuery = em.createNativeQuery(wrapSql, getDomainClass());
		nativeQuery.setFirstResult(pageable.getOffset());
		nativeQuery.setMaxResults(pageable.getPageSize());
		List<T> results = nativeQuery.getResultList();
		
		Query countQuery = em.createNativeQuery(totalSql);
		Object count = countQuery.getSingleResult();
		long countNum = NLNativeQueryUtil.covertToLong(count);
		Page<T> pageBean = new PageImpl<T>(results, pageable, countNum);
		return pageBean;
	}
	
	public Iterable<T> findScrollBySQL(String sql) {
		Query nativeQuery = em.createNativeQuery(sql, getDomainClass());
		return nativeQuery.getResultList();
	}
	
	public Iterable<T> findScrollBySQL(String sql, Sort sort) {
		String wrapSql =  NLNativeQueryUtil.applySorting(sql, sort);
		return findScrollBySQL(wrapSql);
	}
	
	public T findSingleBySQL(String sql){
		Query nativeQuery = em.createNativeQuery(sql, getDomainClass());
		nativeQuery.setMaxResults(1);
		return (T) nativeQuery.getSingleResult();
	}
	
	public T findSingleBySQL(String sql, Sort sort){
		String wrapSql =  NLNativeQueryUtil.applySorting(sql, sort);
		return findSingleBySQL(wrapSql);
	}
}
