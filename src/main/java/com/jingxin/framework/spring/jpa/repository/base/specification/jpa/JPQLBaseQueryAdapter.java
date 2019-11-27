package com.jingxin.framework.spring.jpa.repository.base.specification.jpa;

import javax.persistence.criteria.CriteriaQuery;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseAggregateSpecification;

public class JPQLBaseQueryAdapter implements JPQueryAdapter{

	private BaseAggregateSpecification aggregateSpecification;
	
	public JPQLBaseQueryAdapter(BaseAggregateSpecification aggregateSpecification) {
		this.aggregateSpecification = aggregateSpecification;
	}

	@Override
	public void handler(CriteriaQuery<?> query, PredicateBuilder<?> predicateBuilder) {
		if (aggregateSpecification.isDistinct()) {
			query.distinct(true);
		}
	}

}
