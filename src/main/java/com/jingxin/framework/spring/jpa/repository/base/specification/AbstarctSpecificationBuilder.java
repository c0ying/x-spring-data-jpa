package com.jingxin.framework.spring.jpa.repository.base.specification;

public abstract class AbstarctSpecificationBuilder<S>{

	protected BaseSearchSpecification baseSearchSpecification = BaseSearchSpecification.getInstance();
	
	public AbstarctSpecificationBuilder<S> lt(String property, Object value) {
		baseSearchSpecification.LT(property, value);
		return this;
	}
	
	public AbstarctSpecificationBuilder<S> lte(String property, Object value) {
		 baseSearchSpecification.LTE(property, value);
		 return this;
	}
	
	public AbstarctSpecificationBuilder<S> gt(String property, Object value) {
		 baseSearchSpecification.GT(property, value);
		 return this;
	}
	
	public AbstarctSpecificationBuilder<S> gte(String property, Object value) {
		 baseSearchSpecification.GTE(property, value);
		 return this;
	}

	public AbstarctSpecificationBuilder<S> like(String property, String value) {
		 baseSearchSpecification.LIKE(property, value);
		 return this;
	}
	
	public AbstarctSpecificationBuilder<S> eq(String property, Object value) {
		 baseSearchSpecification.EQ(property, value);
		 return this;
	}

	public AbstarctSpecificationBuilder<S> startWith(String property, String value) {
		 baseSearchSpecification.START_WITH(property, value);
		 return this;
	}
	
	public AbstarctSpecificationBuilder<S> endWith(String property, String value) {
		 baseSearchSpecification.END_WITH(property, value);
		 return this;
	}
	
	public AbstarctSpecificationBuilder<S> in(String property, Object[] value) {
		 baseSearchSpecification.IN(property, value);
		 return this;
	}

	public AbstarctSpecificationBuilder<S> notIn(String property, Object[] value) {
		 baseSearchSpecification.NOT_IN(property, value);
		 return this;
	}
	
	public AbstarctSpecificationBuilder<S> isNull(String property) {
		 baseSearchSpecification.ISNULL(property);
		 return this;
	}

	public AbstarctSpecificationBuilder<S> notNull(String property) {
		 baseSearchSpecification.NOTNULL(property);
		 return this;
	}

	public AbstarctSpecificationBuilder<S> notEq(String property, String value) {
		 baseSearchSpecification.NOTEQ(property, value);
		 return this;
	}

	public AbstarctSpecificationBuilder<S> notLike(String property, String value) {
		 baseSearchSpecification.NOTLIKE(property, value);
		 return this;
	}

	
	public AbstarctSpecificationBuilder<S> not() {
		 baseSearchSpecification.NOT();
		 return this;
	}

	public AbstarctSpecificationBuilder<S> and(BaseSearchSpecification... searchSpecification) {
		 baseSearchSpecification.and(searchSpecification);
		 return this;
	}
	
	public AbstarctSpecificationBuilder<S> or(BaseSearchSpecification... searchSpecification) {
		 baseSearchSpecification.or(searchSpecification);
		 return this;
	}
	
	public AbstarctSpecificationBuilder<S> distinct() {
		 baseSearchSpecification.distinct();
		 return this;
	}
	
	public abstract S build();
}
