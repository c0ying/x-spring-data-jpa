package com.jingxin.framework.spring.jpa.repository.base.query.impl.jql;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.jingxin.framework.spring.jpa.repository.base.query.impl.BaseRepositoryImpl;
import com.jingxin.framework.spring.jpa.repository.base.query.jql.EnhanceJQLRepository;

public class EnhanceJQLRepositoryImpl<T, ID extends Serializable> extends BaseRepositoryImpl<T, ID> implements EnhanceJQLRepository<T>{

	public EnhanceJQLRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

}
