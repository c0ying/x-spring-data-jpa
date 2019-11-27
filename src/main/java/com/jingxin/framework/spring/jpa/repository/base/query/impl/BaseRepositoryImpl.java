package com.jingxin.framework.spring.jpa.repository.base.query.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>{

	protected final EntityManager em;
	
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}
	
	/**
	 * 从List中固定只取出第一个元素
	 * @param data
	 * @return
	 */
	public <E> E getSingleObjectFromList(List<E> data){
		if (data != null) {
			if (data.size() > 0) {
				return data.get(0);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 设置参数
	 * @param query
	 * @param params
	 */
	protected void setParam(Query query, Object... params){
		if(params != null){
			Object obj;
			for(int i=0; i< params.length; i++){
				obj = params[i];
				if(obj instanceof Timestamp){
					query.setParameter(i+1, (Date)obj, TemporalType.TIMESTAMP);
				}else if(obj instanceof Date){
					query.setParameter(i+1, (Date)obj, TemporalType.DATE);
				}else{
					query.setParameter(i+1,obj);
				}
			}
		}
	}
	
	/**
	 * 设置名称参数
	 * @param query
	 * @param params
	 */
	protected void setParam(final Query query, final Map<String, Object> params) {
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

	
	protected void setHits(final Query query, final Map<String, Object> hits) {
		if (hits != null) {
			Set<Entry<String, Object>> set = hits.entrySet();
			for (Entry<String, Object> entry : set) {
				query.setHint(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
}
