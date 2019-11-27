package com.jingxin.framework.spring.jpa.repository.base.specification.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PredicateBuilder<T> {

	protected Root<T> root;
	protected CriteriaBuilder cb;
	protected Predicate predicate;
	
	protected List<Expression<Boolean>> expressions = new ArrayList<Expression<Boolean>>();
	
	private PredicateBuilder(){}
	
	protected PredicateBuilder(Root<T> root, CriteriaBuilder cb){
		this.root = root;
		this.cb = cb;
	}
	
	public static <T> PredicateBuilder<T> getInstance(Root<T> root, CriteriaBuilder cb){
		return new PredicateBuilder<T>(root, cb);
	}
	
	public PredicateBuilder<T> and(Predicate... predicates){
		predicate = cb.and(predicates);
		return this;
	}
	
	public PredicateBuilder<T> or(Predicate... predicates){
		predicate = cb.or(predicates);
		return this;
	}
	
	/**
	 * conjunction/disjunction 是针对Expression连接方式控制
	 * @return
	 */
	public PredicateBuilder<T> conjunction(){
		predicate = cb.conjunction();
		predicate.getExpressions().addAll(expressions);
		return this;
	}
	
	public PredicateBuilder<T> disjunction(){
		predicate = cb.disjunction();
		predicate.getExpressions().addAll(expressions);
		return this;
	}
	
	public Predicate getPredicate(){
		return this.predicate;
	}
	
	public <E extends Comparable<? super E>> PredicateBuilder<T> LT(String propertyName, E propertyValue){
		expressions.add(cb.lessThan(iteratePropertyName(propertyName), propertyValue));
		return this;
	}
	
	public <E extends Comparable<? super E>> PredicateBuilder<T> LTE(String propertyName, E propertyValue){
		expressions.add(cb.lessThanOrEqualTo(iteratePropertyName(propertyName), propertyValue));
		return this;
	}
	
	public <E extends Comparable<? super E>> PredicateBuilder<T> GT(String propertyName, E propertyValue){
		expressions.add(cb.greaterThan(iteratePropertyName(propertyName), propertyValue));
		return this;
	}
	
	public <E extends Comparable<? super E>> PredicateBuilder<T> GTE(String propertyName, E propertyValue){
		expressions.add(cb.greaterThanOrEqualTo(iteratePropertyName(propertyName), propertyValue));
		return this;
	}
	
	public PredicateBuilder<T> EQ(String propertyName, Object propertyValue){
		expressions.add(cb.equal(iteratePropertyName(propertyName), propertyValue));
		return this;
	}
	
	public PredicateBuilder<T> LIKE(String propertyName, String propertyValue){
		expressions.add(cb.like(iteratePropertyName(propertyName), "%"+propertyValue+"%"));
		return this;
	}
	
	public PredicateBuilder<T> START_WITH(String propertyName, String propertyValue){
		expressions.add(cb.like(iteratePropertyName(propertyName), propertyValue+"%"));
		return this;
	}
	
	public PredicateBuilder<T> END_WITH(String propertyName, String propertyValue){
		expressions.add(cb.like(iteratePropertyName(propertyName), "%"+propertyValue));
		return this;
	}
	
	public PredicateBuilder<T> IN(String propertyName, Object[] propertyValue){
		expressions.add(iteratePropertyName(propertyName).in(propertyValue));
		return this;
	}
	
	public PredicateBuilder<T> NOT_IN(String propertyName, Object[] propertyValue){
		expressions.add(iteratePropertyName(propertyName).in(propertyValue).not());
		return this;
	}
	
	public PredicateBuilder<T> NOTEQ(String propertyName, Object propertyValue){
		expressions.add(cb.notEqual(iteratePropertyName(propertyName), propertyValue));
		return this;
	}
	
	public PredicateBuilder<T> ISNULL(String propertyName){
		expressions.add(cb.isNull(iteratePropertyName(propertyName)));
		return this;
	}
	
	public PredicateBuilder<T> NOTNULL(String propertyName){
		expressions.add(cb.isNotNull(iteratePropertyName(propertyName)));
		return this;
	}
	
	public PredicateBuilder<T> NOTLILE(String propertyName, String propertyValue){
		expressions.add(cb.notLike(iteratePropertyName(propertyName), "%"+propertyValue+"%"));
		return this;
	}
	
	public PredicateBuilder<T> NOT(){
		predicate.not();
		return this;
	}
	
	public void clearExpression(){
		expressions.clear();
	}
	
	public Path iteratePropertyName(String propertyName){
		StringTokenizer tokenizer = new StringTokenizer(propertyName, ".");
		Path path = null;
		while (tokenizer.hasMoreTokens()) {
			if (path == null) {
				path = root.get(tokenizer.nextToken());
			}else{
				path = path.get(tokenizer.nextToken());
			}
		}
		return path;
	}
	
	public Root<T> getNativeRoot(){
		return this.root;
	}
	
	public CriteriaBuilder getNativeCriteriaBuilder() {
		return this.cb;
	}
}
