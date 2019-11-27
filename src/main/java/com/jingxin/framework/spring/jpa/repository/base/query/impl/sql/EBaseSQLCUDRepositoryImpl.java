package com.jingxin.framework.spring.jpa.repository.base.query.impl.sql;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jingxin.framework.spring.jpa.repository.base.query.sql.EBaseSQLCUDRepository;

public class EBaseSQLCUDRepositoryImpl<T, ID extends Serializable> extends SpefcSQueryRepositoryImpl<T, ID> implements EBaseSQLCUDRepository{

	public EBaseSQLCUDRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@Override
	public <E> void executeUpdateBySQL(String sql, Object... params) {
		Query query = em.createNativeQuery(sql);
		setParam(query, params);
		query.executeUpdate();
	}

}
