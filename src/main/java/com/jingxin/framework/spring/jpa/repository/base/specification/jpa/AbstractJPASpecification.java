package com.jingxin.framework.spring.jpa.repository.base.specification.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractJPASpecification<T> implements Specification<T>{

	protected CriteriaBuilder cb;
	protected Root<T> root;
	
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		this.cb = cb;
		this.root = root;
		return customQuery(root, query, cb);
	}
	
	protected abstract Predicate customQuery(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);

}
