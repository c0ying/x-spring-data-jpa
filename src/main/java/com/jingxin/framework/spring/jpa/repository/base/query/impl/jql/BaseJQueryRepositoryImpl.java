package com.jingxin.framework.spring.jpa.repository.base.query.impl.jql;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;

import com.jingxin.framework.spring.jpa.repository.base.query.jql.BaseJQueryRepository;

public class BaseJQueryRepositoryImpl<T, ID extends Serializable> extends EBaseJQLCUDRepositoryImpl<T, ID> implements BaseJQueryRepository<T>{

	public BaseJQueryRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public Page<T> findPageViewByJPQL(String jpql, String totalJqpl, int startPosition, int size, Object... params){
		return findPageViewByJPQL(jpql, totalJqpl, getDomainClass(), startPosition, size, params);
	}
	
	public Page<T> findPageViewByJPQL(String jpql, String totalJqpl, int startPosition, int size, Map<String, Object> hits, Object... params){
		return findPageViewByJPQL(jpql, totalJqpl, getDomainClass(), startPosition, size, hits, params);
	}
	
	public Page<T> findPageViewByJPQL(String jpql, int startPosition, int size, Object... params){
		return findPageViewByJPQL(jpql, getDomainClass(), startPosition, size, params);
	}
	
	public Page<T> findPageViewByJPQL(String jpql, int startPosition, int size, Map<String, Object> hits, Object... params){
		return findPageViewByJPQL(jpql, getDomainClass(), startPosition, size, hits, params);
	}
}
