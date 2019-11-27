package com.jingxin.framework.spring.jpa.repository.base.specification.ql;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSearchSpecification;
import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSpecification.SearchAction;
import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSpecification.SearchType;

public class SQLBaseSearchTransformate implements SQSearchActionTransformate {
	
	private String namespace = "_nl_";
	private int num = 0;
	
	/* (non-Javadoc)
	 * @see com.nenglong.framework.spring.jpa.repository.base.specification.ql.SQSearchActionTransformate#transform(com.nenglong.framework.spring.jpa.repository.base.specification.BaseSearchSpecification)
	 */
	public SQLPredicate transform(BaseSearchSpecification baseSearch) {
		SQLPredicate sqlPredicate = searchAction2SqlPredicate(baseSearch);
		if (baseSearch.hasCombine()) {
			for (BaseSearchSpecification baseSearchSpecification : baseSearch.getCombineActions()) {
				SQLPredicate combine_predicate = transform(baseSearchSpecification);
				if (baseSearch.getCombineType().equalsIgnoreCase("conjunction")) {
					if (!StringUtils.isEmpty(sqlPredicate.getSql())) {
						sqlPredicate.setSql(new StringBuilder(sqlPredicate.getSql())
								.append(" and ").append(combine_predicate.getSql()).toString());
					}else {
						sqlPredicate.setSql(new StringBuilder(combine_predicate.getSql()).toString());
					}
					sqlPredicate.getParams().putAll(combine_predicate.getParams());
				}else if (baseSearch.getCombineType().equalsIgnoreCase("disjunction")) {
					if (!StringUtils.isEmpty(sqlPredicate.getSql())) {
						sqlPredicate.setSql(new StringBuilder(sqlPredicate.getSql())
								.append(" or ").append(combine_predicate.getSql()).toString());
					}else {
						sqlPredicate.setSql(new StringBuilder(combine_predicate.getSql()).toString());
					}
					sqlPredicate.getParams().putAll(combine_predicate.getParams());
				}
			}
		}
		return sqlPredicate;
	}

	protected SQLPredicate searchAction2SqlPredicate(BaseSearchSpecification baseSearch){
		if (baseSearch.getActions().size() == 0) {
			return new SQLPredicate("", new LinkedHashMap<String, Object>());
		}
		StringBuilder dSql = null;
		if (baseSearch.isNegative()) {
			dSql = new StringBuilder("NOT(");
		}else {
			dSql = new StringBuilder("(");
		}
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		String operType = " and ";
		boolean first = true;
		if (baseSearch.isConjunction()) {
			operType = " and ";
		}else if (baseSearch.isDisjunction()) {
			operType = " or ";
		}
		for (SearchAction searchAction : baseSearch.getActions()) {
			if (!first) dSql.append(operType);
			StringBuilder propertyName = new StringBuilder();
			propertyName.append(searchAction.getPropertyName()).append(namespace).append(num);
			String propertyNameStr = propertyName.toString();
			
			if (searchAction.getSearchType() == SearchType.LT) {
				dSql.append(searchAction.getPropertyName()).append(" < :").append(propertyNameStr);
				params.put(propertyNameStr, searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.LTE) {
				dSql.append(searchAction.getPropertyName()).append(" <= :").append(propertyNameStr);
				params.put(propertyNameStr, searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.GT) {
				dSql.append(searchAction.getPropertyName()).append(" > :").append(propertyNameStr);
				params.put(propertyNameStr, searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.GTE) {
				dSql.append(searchAction.getPropertyName()).append(" >= :").append(propertyNameStr);
				params.put(propertyNameStr, searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.EQ) {
				dSql.append(searchAction.getPropertyName()).append(" = :").append(propertyNameStr);
				params.put(propertyNameStr, searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.LIKE) {
				dSql.append(searchAction.getPropertyName()).append(" like :").append(propertyNameStr);
				params.put(propertyNameStr, "%"+searchAction.getPropertyVal()+"%");
			}else if (searchAction.getSearchType() == SearchType.STARTW) {
				dSql.append(searchAction.getPropertyName()).append(" like :").append(propertyNameStr);
				params.put(propertyNameStr, searchAction.getPropertyVal()+"%");
			}else if (searchAction.getSearchType() == SearchType.ENDW) {
				dSql.append(searchAction.getPropertyName()).append(" like :").append(propertyNameStr);
				params.put(propertyNameStr, "%"+searchAction.getPropertyVal());
			}else if (searchAction.getSearchType() == SearchType.IN) {
				dSql.append(searchAction.getPropertyName()).append(" in ( ");
				if (searchAction.getPropertyVal() instanceof List) {
					List<?> vals = (List<?>) searchAction.getPropertyVal();
					StringBuilder inValStr = new StringBuilder();
					StringBuilder inPropertyStr = new StringBuilder();
					for (int i = 0; i < vals.size(); i++) {
						Object val = vals.get(i);
						inPropertyStr.append("IN_").append(propertyNameStr).append("_").append(i);
						inValStr.append(":").append(inPropertyStr);
						params.put(inPropertyStr.toString(), val);
						if (i+1 < vals.size()) inValStr.append(",");
					}
					dSql.append(inValStr);
				}else if (searchAction.getPropertyVal().getClass().isArray()) {
					Object[] vals = (Object[]) searchAction.getPropertyVal();
					StringBuilder inValStr = new StringBuilder();
					StringBuilder inPropertyStr = new StringBuilder();
					for (int i = 0; i < vals.length; i++) {
						inPropertyStr.append("IN_").append(propertyNameStr).append("_").append(i);
						inValStr.append(":").append(inPropertyStr);
						params.put(inPropertyStr.toString(), vals[i]);
						if (i+1 < vals.length) inValStr.append(",");
					}
					dSql.append(inValStr);
				}else{
					throw new IllegalArgumentException("in expression value Type noly support List or Array");
				}
				dSql.append(" )");
			}else if (searchAction.getSearchType() == SearchType.NOTIN) {
				dSql.append(searchAction.getPropertyName()).append(" not in ( ");
				if (searchAction.getPropertyVal() instanceof List) {
					List<?> vals = (List<?>) searchAction.getPropertyVal();
					StringBuilder inValStr = new StringBuilder();
					StringBuilder inPropertyStr = new StringBuilder();
					for (int i = 0; i < vals.size(); i++) {
						Object val = vals.get(i);
						inPropertyStr.append("NIN_").append(propertyNameStr).append("_").append(i);
						inValStr.append(":").append(inPropertyStr);
						params.put(inPropertyStr.toString(), val);
						if (i+1 < vals.size()) inValStr.append(",");
					}
					dSql.append(inValStr);
				}else if (searchAction.getPropertyVal().getClass().isArray()) {
					Object[] vals = (Object[]) searchAction.getPropertyVal();
					StringBuilder inValStr = new StringBuilder();
					StringBuilder inPropertyStr = new StringBuilder();
					for (int i = 0; i < vals.length; i++) {
						inPropertyStr.append("NIN_").append(propertyNameStr).append("_").append(i);
						inValStr.append(":").append(inPropertyStr);
						params.put(inPropertyStr.toString(), vals[i]);
						if (i+1 < vals.length) inValStr.append(",");
					}
					dSql.append(inValStr);
				}else{
					throw new IllegalArgumentException("not in expression value Type noly support List or Array");
				}
				dSql.append(" )");
			}
			first = false;
		}
		dSql.append(")");
		num += 1;
		return new SQLPredicate(dSql.toString(), params);
	}
	
}
