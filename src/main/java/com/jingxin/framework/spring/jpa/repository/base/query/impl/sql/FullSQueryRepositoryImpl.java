package com.jingxin.framework.spring.jpa.repository.base.query.impl.sql;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.jingxin.framework.spring.jpa.repository.base.query.simple.FullSQueryRepository;

public class FullSQueryRepositoryImpl<T, ID extends Serializable> extends BaseSQueryRepositoryImpl<T, ID> implements FullSQueryRepository<T>{

	public FullSQueryRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

}
