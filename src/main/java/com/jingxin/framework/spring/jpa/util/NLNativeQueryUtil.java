package com.jingxin.framework.spring.jpa.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class NLNativeQueryUtil {
	
	private static Pattern CACHE_PATTERN_FORM =  Pattern.compile(".*\\bfrom\\b", Pattern.CASE_INSENSITIVE);
	private static Pattern CACHE_PATTERN_WHERE =  Pattern.compile(".*\\bwhere\\b", Pattern.CASE_INSENSITIVE);
	private static Pattern CACHE_PATTERN_BY =  Pattern.compile("\\b(order by|group by)\\b.*$", Pattern.CASE_INSENSITIVE);

	public static String applySorting(String queryString, Sort sort){
		if (sort != null && sort.iterator().hasNext()) {
			StringBuilder wrapSQL = new StringBuilder(queryString);
			wrapSQL.append(" order by ");
			for (Iterator<Order> iterator = sort.iterator(); iterator.hasNext();) {
				Order order = iterator.next();
				wrapSQL.append(order.getProperty())
						.append(" ")
						.append(order.getDirection().toString())
						.append(" ");
				if (iterator.hasNext()) wrapSQL.append(", ");
			}
			return wrapSQL.toString();
		}
		return queryString;
	}
	
	public static long covertToLong(Object objectNum){
		if (objectNum == null) return 0;
		
		long countNum = 0;
		if (objectNum instanceof Number) {
			countNum = ((Number) objectNum).longValue();
		} else if (objectNum instanceof Long) {
			countNum = (Long) objectNum;
		}
		return countNum;
	}
	
	/**
	 * 设置名称参数
	 * @param query
	 * @param params
	 */
	public static void setParam(final Query query, final Map<String, Object> params) {
		if(params != null){
			Set<Entry<String, Object>> set = params.entrySet();
			Object obj;
			for (Entry<String, Object> entry : set) {
				obj = entry.getValue();
				if(obj instanceof Timestamp){
					query.setParameter(entry.getKey(), (Date)obj, TemporalType.TIMESTAMP);
				}else if(obj instanceof Date){
					query.setParameter(entry.getKey(), (Date)obj, TemporalType.DATE);
				}else{
					query.setParameter(entry.getKey(),obj);
				}
			}
		}
	}
	
	/**
	 * 生成计算查询语句总记录数据语句
	 * @param ql
	 * @return
	 */
	public static String generateCountQL(String ql){
		Matcher m = CACHE_PATTERN_FORM.matcher(ql);
		String totalJpql = m.replaceFirst("select count(*) from");
		Matcher m_groupby = CACHE_PATTERN_BY.matcher(totalJpql);
		totalJpql = m_groupby.replaceAll("");
		return totalJpql;
	}
	
	public static String combinePredicateSql (String sql, String predicateSql) {
		StringBuilder dSql = new StringBuilder(sql);
		if (!StringUtils.isEmpty(predicateSql)) {
			Matcher m = CACHE_PATTERN_WHERE.matcher(sql);
			if (m.matches()) {
				dSql.append(" and ");
			}else {
				dSql.append(" where  ");
			}
			dSql.append("(").append(predicateSql).append(")");
		}
		return dSql.toString();
	}
	
	public static String generateSeleteSql(Class<?> domainClass){
		Table tableAnnotation = AnnotationUtils.findAnnotation(domainClass, Table.class);
		if (tableAnnotation != null) {
			String tableName = (String) AnnotationUtils.getValue(tableAnnotation, "name");
			return "select * from " + tableName;
		}
		Assert.notNull(tableAnnotation,"can not auto generate select sql, because can not find @table param");
		return null;
	}
}
