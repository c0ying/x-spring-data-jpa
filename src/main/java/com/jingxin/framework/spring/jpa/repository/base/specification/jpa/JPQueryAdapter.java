package com.jingxin.framework.spring.jpa.repository.base.specification.jpa;

import javax.persistence.criteria.CriteriaQuery;

public interface JPQueryAdapter {

	void handler(CriteriaQuery<?> query, PredicateBuilder<?> predicateBuilder);
}
