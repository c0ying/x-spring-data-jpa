package com.jingxin.framework.spring.jpa.repository.base.specification.ql;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSearchSpecification;

public class SQLBaseSearchSpecification{

	private SQSearchActionTransformate transformate;
	private BaseSearchSpecification baseSearchSpecification;
	
	public SQLBaseSearchSpecification(SQSearchActionTransformate transformate, BaseSearchSpecification baseSearchSpecification) {
		this.transformate = transformate;
		this.baseSearchSpecification = baseSearchSpecification;
	}
	
	public SQLBaseSearchSpecification(BaseSearchSpecification baseSearchSpecification) {
		this.transformate = new SQLBaseSearchTransformate();
		this.baseSearchSpecification = baseSearchSpecification;
	}
	
	public SQSearchActionTransformate getTransformate() {
		return transformate;
	}

	public SQLPredicate transformate(){
		return transformate.transform(baseSearchSpecification);
	}
}
