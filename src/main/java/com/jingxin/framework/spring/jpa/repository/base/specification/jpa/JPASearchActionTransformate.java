package com.jingxin.framework.spring.jpa.repository.base.specification.jpa;

import javax.persistence.criteria.Predicate;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSearchSpecification;

public interface JPASearchActionTransformate<T> {

	Predicate transform(BaseSearchSpecification baseSearch, PredicateBuilder<T> predicateBuilder);

}