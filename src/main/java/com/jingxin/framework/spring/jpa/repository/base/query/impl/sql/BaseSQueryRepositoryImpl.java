package com.jingxin.framework.spring.jpa.repository.base.query.impl.sql;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;

import com.jingxin.framework.spring.jpa.repository.base.query.sql.BaseSQueryRepository;

public class BaseSQueryRepositoryImpl<T, ID extends Serializable> extends EBaseSQLCUDRepositoryImpl<T, ID> implements BaseSQueryRepository<T>{

	public BaseSQueryRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public Page<T> findPageViewBySQL(String sql, String totalSql, int startPosition, int size, Object... params){
		return findPageViewBySQL(sql, totalSql, getDomainClass(), startPosition, size, params);
	}
	
	public Page<T> findPageViewBySQL(String sql, int startPosition, int size, Object... params){
		return findPageViewBySQL(sql, getDomainClass(), startPosition, size, params);
	}
	
}
