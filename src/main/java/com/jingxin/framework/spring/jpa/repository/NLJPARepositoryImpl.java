package com.jingxin.framework.spring.jpa.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.jingxin.framework.spring.jpa.repository.base.query.impl.sql.FullSQueryRepositoryImpl;

public class NLJPARepositoryImpl <T, ID extends Serializable>
		extends FullSQueryRepositoryImpl<T, ID> implements NLJPARepository<T, ID>{
	
	public NLJPARepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

}
