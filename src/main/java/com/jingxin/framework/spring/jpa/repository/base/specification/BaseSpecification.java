package com.jingxin.framework.spring.jpa.repository.base.specification;

public class BaseSpecification {

	public static enum SearchType{
		LT, LTE, GT, GTE, EQ, LIKE, STARTW, ENDW, 
		IN, NOTIN, ISNULL, NOTNULL, NOTLIKE, NOTEQ, NOT;
	}
	
	public static enum AggregateType{
		ORDERBY, GROUPBY, HAVING, DISTINCT
	}
	
	public static class SearchAction{
		private SearchType searchType;
		private String propertyName;
		private Object propertyVal;
		
		public SearchAction(SearchType type, String property, Object val) {
			searchType = type;
			propertyName = property;
			propertyVal = val;
		}

		public SearchType getSearchType() {
			return searchType;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public Object getPropertyVal() {
			return propertyVal;
		}
	}
}
