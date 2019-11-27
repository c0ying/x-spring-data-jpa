package com.jingxin.framework.spring.jpa.repository.base.query.impl.jql;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.jingxin.framework.spring.jpa.repository.base.query.simple.FullJQueryRepository;

public class FullJQueryRepositoryImpl<T, ID extends Serializable> extends BaseJQueryRepositoryImpl<T, ID> implements FullJQueryRepository<T>{

	public FullJQueryRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

}
