package com.jingxin.framework.spring.jpa.repository.base.query.impl.jql;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jingxin.framework.spring.jpa.repository.base.query.jql.EBaseJQLCUDRepository;

public class EBaseJQLCUDRepositoryImpl<T, ID extends Serializable> extends EBaseJQueryRepositoryImpl<T, ID> implements EBaseJQLCUDRepository{

	public EBaseJQLCUDRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@Override
	public <E> void executeUpdateByJPQL(String jpql, Object... params) {
		executeUpdateByJPQL(jpql, null, params);
	}

	public <E> void executeUpdateByJPQL(String jpql, Map<String, Object> hits, Object... params) {
		Query query = em.createQuery(jpql);
		setHits(query, hits);
		setParam(query, params);
		query.executeUpdate();
	}
}
