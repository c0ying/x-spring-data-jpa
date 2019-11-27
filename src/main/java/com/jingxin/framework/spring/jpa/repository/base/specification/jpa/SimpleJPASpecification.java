package com.jingxin.framework.spring.jpa.repository.base.specification.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSearchSpecification;

public class SimpleJPASpecification<T> extends AbstractJPASpecification<T>{

	private BaseSearchSpecification baseJPASpecification;
	private JPASearchActionTransformate<T> transformate;
	private JPQueryAdapter jPQuery;
	
	public SimpleJPASpecification(BaseSearchSpecification baseJPASpecification, 
			JPASearchActionTransformate<T> transformate, JPQueryAdapter jPQuery) {
		this.baseJPASpecification = baseJPASpecification;
		this.transformate = transformate;
		this.jPQuery = jPQuery;
	}
	
	@Override
	protected Predicate customQuery(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		PredicateBuilder<T> predicateBuilder = PredicateBuilder.getInstance(root, cb);
		if(jPQuery != null) jPQuery.handler(query, predicateBuilder);
		return transformate.transform(baseJPASpecification, predicateBuilder);
	}
	
}
