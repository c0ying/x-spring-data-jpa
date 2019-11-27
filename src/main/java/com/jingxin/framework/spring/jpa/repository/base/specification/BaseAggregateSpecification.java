package com.jingxin.framework.spring.jpa.repository.base.specification;

public class BaseAggregateSpecification {

	private boolean distinct = false;
	
	public BaseAggregateSpecification distinct() {
		this.distinct = true;
		return this;
	}
	public boolean isDistinct() {
		return distinct;
	}
}
