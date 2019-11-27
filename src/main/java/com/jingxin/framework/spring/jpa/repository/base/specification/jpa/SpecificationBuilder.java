package com.jingxin.framework.spring.jpa.repository.base.specification.jpa;

import com.jingxin.framework.spring.jpa.repository.base.specification.AbstarctSpecificationBuilder;

public class SpecificationBuilder<T> extends AbstarctSpecificationBuilder<SimpleJPASpecification<T>>{

	public static <T> SpecificationBuilder<T> getInstance(Class<T> clz){
		return new SpecificationBuilder<T>();
	}
	
	public SimpleJPASpecification<T> build(){
		return build(new JQLBaseSearchTransformate<T>(), new JPQLBaseQueryAdapter(baseSearchSpecification));
	}
	
	public SimpleJPASpecification<T> build(JPQueryAdapter jpQueryAdapter){
		return build(new JQLBaseSearchTransformate<T>(), jpQueryAdapter);
	}
	
	public SimpleJPASpecification<T> build(JPASearchActionTransformate<T> transformate, JPQueryAdapter jpQueryAdapter){
		return new SimpleJPASpecification<T>(baseSearchSpecification, transformate, jpQueryAdapter);
	}
	
}
