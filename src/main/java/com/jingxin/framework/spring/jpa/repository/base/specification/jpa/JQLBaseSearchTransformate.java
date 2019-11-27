package com.jingxin.framework.spring.jpa.repository.base.specification.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSearchSpecification;
import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSpecification.SearchAction;
import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSpecification.SearchType;

public class JQLBaseSearchTransformate<T> implements JPASearchActionTransformate<T> {

	
	public Predicate transform(BaseSearchSpecification baseSearch, PredicateBuilder<T> predicateBuilder){
//		predicateBuilder.clearExpression();
//		Predicate predicate = searchAction2Predicate(baseSearch, predicateBuilder);
		Predicate predicate = searchAction2Predicate(baseSearch, predicateBuilder);
		if (baseSearch.hasCombine()) {
			List<Predicate> combined_predicates = new ArrayList<Predicate>();
			for (BaseSearchSpecification baseSearchSpecification : baseSearch.getCombineActions()) {
				Predicate combined_predicate = transform(baseSearchSpecification, 
						PredicateBuilder.getInstance(predicateBuilder.getNativeRoot(), predicateBuilder.getNativeCriteriaBuilder()));
				combined_predicates.add(combined_predicate);
			}
			combined_predicates.add(0, predicate);
			if (baseSearch.getCombineType().equalsIgnoreCase("conjunction")) {
				predicateBuilder.and(combined_predicates.toArray(new Predicate[0]));
			}else if (baseSearch.getCombineType().equalsIgnoreCase("disjunction")) {
				predicateBuilder.or(combined_predicates.toArray(new Predicate[0]));
			}
		}
		return predicateBuilder.getPredicate();
	}
	
	protected Predicate searchAction2Predicate(BaseSearchSpecification baseSearch, PredicateBuilder<T> predicateBuilder){
		List<SearchAction> searchActions =  baseSearch.getActions();
		for (SearchAction searchAction : searchActions) {
			if (searchAction.getSearchType() == SearchType.LT) {
				predicateBuilder.LT(searchAction.getPropertyName(), (Comparable)searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.LTE) {
				predicateBuilder.LTE(searchAction.getPropertyName(), (Comparable)searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.GT) {
				predicateBuilder.GT(searchAction.getPropertyName(), (Comparable)searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.GTE) {
				predicateBuilder.GTE(searchAction.getPropertyName(), (Comparable)searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.EQ) {
				predicateBuilder.EQ(searchAction.getPropertyName(), searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.LIKE) {
				predicateBuilder.LIKE(searchAction.getPropertyName(), (String) searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.STARTW) {
				predicateBuilder.START_WITH(searchAction.getPropertyName(), (String) searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.ENDW) {
				predicateBuilder.END_WITH(searchAction.getPropertyName(), (String) searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.IN) {
				predicateBuilder.IN(searchAction.getPropertyName(), (Object[]) searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.NOTIN) {
				predicateBuilder.NOT_IN(searchAction.getPropertyName(), (Object[]) searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.ISNULL) {
				predicateBuilder.ISNULL(searchAction.getPropertyName());
			}else if (searchAction.getSearchType() == SearchType.NOTNULL) {
				predicateBuilder.NOTNULL(searchAction.getPropertyName());
			}else if (searchAction.getSearchType() == SearchType.NOTEQ) {
				predicateBuilder.NOTEQ(searchAction.getPropertyName(), searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.NOTLIKE) {
				predicateBuilder.NOTLILE(searchAction.getPropertyName(), (String) searchAction.getPropertyVal());
			}
		}
		if (baseSearch.isConjunction()) {
			predicateBuilder.conjunction();
		}else if (baseSearch.isDisjunction()) {
			predicateBuilder.disjunction();
		}
		return predicateBuilder.getPredicate();
	}
}
