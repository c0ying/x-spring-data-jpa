package com.jingxin.test.spring.junit.test;

import javax.persistence.criteria.CriteriaQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jingxin.framework.spring.jpa.repository.base.specification.jpa.JPQueryAdapter;
import com.jingxin.framework.spring.jpa.repository.base.specification.jpa.PredicateBuilder;
import com.jingxin.framework.spring.jpa.repository.base.specification.jpa.SpecificationBuilder;
import com.jingxin.test.dao.TestDao;
import com.jingxin.test.po.SMSSendReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class JPQueryCustomTest {

	@Autowired
	private TestDao dao;
	
	@Test
	public void distinct() {
		dao.findAll(SpecificationBuilder.getInstance(SMSSendReport.class).build(new JPQueryAdapter() {
			@Override
			public void handler(CriteriaQuery<?> query, PredicateBuilder<?> predicateBuilder) {
				query.distinct(true);
			}
		}));
	}
	
	@Test
	public void orderBy() {
		dao.findAll(SpecificationBuilder.getInstance(SMSSendReport.class).build(new JPQueryAdapter() {
			@Override
			public void handler(CriteriaQuery<?> query, PredicateBuilder<?> predicateBuilder) {
				query.orderBy(predicateBuilder.getNativeCriteriaBuilder()
								.asc(predicateBuilder.iteratePropertyName("reportTime")));
			}
		}));
	}
	/**
	 * 受Spring data JPA的控制，部分query修改无效
	 */
//	@Test
//	public void grouBy() {
//		dao.findAll(SpecificationBuilder.getInstance(SMSSendReport.class).build(new JPQueryAdapter() {
//			@Override
//			public void handler(CriteriaQuery<?> query, PredicateBuilder<?> predicateBuilder) {
//				
//				query.select(predicateBuilder.iteratePropertyName("sendPhone")).groupBy(predicateBuilder.iteratePropertyName("sendPhone"));
//			}
//		}));
//	}
}
