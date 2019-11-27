package com.jingxin.framework.spring.jpa.repository.base.specification.ql;

import com.jingxin.framework.spring.jpa.repository.base.specification.AbstarctSpecificationBuilder;

public class SQLSpecificationBuilder extends AbstarctSpecificationBuilder<SQLBaseSearchSpecification>{

	public static SQLSpecificationBuilder getInstance() {
		return new SQLSpecificationBuilder();
	}
	
	@Override
	public SQLBaseSearchSpecification build() {
		return new SQLBaseSearchSpecification(baseSearchSpecification);
	}

	public SQLBaseSearchSpecification build(SQSearchActionTransformate transformate) {
		return new SQLBaseSearchSpecification(transformate, baseSearchSpecification);
	}

	@Override
	public AbstarctSpecificationBuilder<SQLBaseSearchSpecification> distinct() {
		throw new UnsupportedOperationException("SQL Specification unsupported this method!");
	}
}
