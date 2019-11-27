package com.jingxin.framework.spring.jpa.repository.base.query.impl.sql;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jingxin.framework.spring.jpa.repository.base.query.sql.SpefcSQueryRepository;
import com.jingxin.framework.spring.jpa.repository.base.specification.ql.SQLBaseSearchSpecification;
import com.jingxin.framework.spring.jpa.repository.base.specification.ql.SQLPredicate;
import com.jingxin.framework.spring.jpa.util.NLNativeQueryUtil;

public class SpefcSQueryRepositoryImpl<T, ID extends Serializable> extends EBaseSQueryRepositoryImpl<T, ID> implements SpefcSQueryRepository<T>{

	public SpefcSQueryRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public List<T> findAllBySQLSpefc(SQLBaseSearchSpecification searchSpecification){
		return findAllBySQLSpefc(NLNativeQueryUtil.generateSeleteSql(getDomainClass()), searchSpecification, null);
	}
	public List<T> findAllBySQLSpefc(SQLBaseSearchSpecification searchSpecification, Sort sort){
		return findAllBySQLSpefc(NLNativeQueryUtil.generateSeleteSql(getDomainClass()), searchSpecification, sort);
	}
	
	public List<T> findAllBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification){
		return findAllBySQLSpefc(sql, searchSpecification, null);
	}
	
	public List<T> findAllBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification, Sort sort){
		return findAllBySQLSpefc(sql, getDomainClass(), searchSpecification, sort);
	}
	
	public <E> List<E> findAllBySQLSpefc(String sql, Class<E> result, SQLBaseSearchSpecification searchSpecification, Sort sort){
		SQLPredicate sqlPredicate = searchSpecification.transformate();
		String csql = NLNativeQueryUtil.combinePredicateSql(sql, sqlPredicate.getSql());
		return findAllBySQL(NLNativeQueryUtil.applySorting(csql, sort), result, sqlPredicate.getParams());
	}
	
	public T findOneBySQLSpefc(SQLBaseSearchSpecification searchSpecification){
		return findOneBySQLSpefc(NLNativeQueryUtil.generateSeleteSql(getDomainClass()), searchSpecification, null);
	}
	
	public T findOneBySQLSpefc(SQLBaseSearchSpecification searchSpecification, Sort sort){
		return findOneBySQLSpefc(NLNativeQueryUtil.generateSeleteSql(getDomainClass()), searchSpecification, sort);
	}
	
	public T findOneBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification){
		return findOneBySQLSpefc(sql, searchSpecification, null);
	}
	
	public T findOneBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification, Sort sort){
		return findOneBySQLSpefc(sql, getDomainClass(), searchSpecification, sort);
	}
	
	public <E> E findOneBySQLSpefc(String sql,  Class<E> result, SQLBaseSearchSpecification searchSpecification, Sort sort){
		SQLPredicate sqlPredicate =  searchSpecification.transformate();
		String csql = NLNativeQueryUtil.combinePredicateSql(sql, sqlPredicate.getSql());
		return findSingleResultBySQL(NLNativeQueryUtil.applySorting(csql, sort), result, sqlPredicate.getParams());
	}
	
	public Page<T> findPageViewBySQLSpefc(SQLBaseSearchSpecification searchSpecification, Pageable pageable){
		return findPageViewBySQLSpefc(NLNativeQueryUtil.generateSeleteSql(getDomainClass()), getDomainClass(), searchSpecification, pageable);
	}
	
	public Page<T> findPageViewBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification, Pageable pageable){
		return findPageViewBySQLSpefc(sql, getDomainClass(), searchSpecification, pageable);
	}
	
	public <E> Page<E> findPageViewBySQLSpefc(String sql, Class<E> result, SQLBaseSearchSpecification searchSpecification, Pageable pageable){
		SQLPredicate sqlPredicate =  searchSpecification.transformate();
		String csql = NLNativeQueryUtil.combinePredicateSql(sql, sqlPredicate.getSql());
		return findPageViewBySQL(NLNativeQueryUtil.applySorting(csql, pageable.getSort()), result, pageable.getOffset(), pageable.getPageSize(), sqlPredicate.getParams());
	}
	
}
