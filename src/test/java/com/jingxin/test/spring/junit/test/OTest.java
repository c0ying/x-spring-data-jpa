package com.jingxin.test.spring.junit.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSearchSpecification;
import com.jingxin.framework.spring.jpa.repository.base.specification.jpa.SpecificationBuilder;
import com.jingxin.framework.spring.jpa.repository.base.specification.ql.SQLBaseSearchSpecification;
import com.jingxin.framework.spring.jpa.repository.base.specification.ql.SQLSpecificationBuilder;
import com.jingxin.test.dao.TestDao;
import com.jingxin.test.po.SMSSendReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class OTest {

	@Autowired
	private TestDao dao;
	
	@Test
	public void test(){
		int size = dao.findAll().size();
		System.out.println(size);
		int ssize = dao.findByStatus(1).size();
		System.out.println("special size:"+size);
	}
	
	@Test
	public void testSQLSearch(){
		Sort sort = new Sort(new Order(Direction.DESC, "sendTime"),new Order(Direction.DESC, "reportTime"));
		Pageable page = new PageRequest(1, 1, sort);
		Page<SMSSendReport> testPages = dao.findPageBySQL("select * from user_sms_report", page);
		for (SMSSendReport smsSendReport : testPages) {
			System.out.println(smsSendReport.getRequestId());
		}
	}
	
	@Test
	public void testRawSQLSearch(){
		List<?> data = dao.findAllBySQL("select requestId from user_sms_report");
		System.out.println(data.size());
	}
	
	@Test
	public void testAnnotationSQLQuery(){
		List<SMSSendReport> reports = dao.findOO();
		System.out.println(reports.size());
	}
	
	@Test
	public void testAnnotationJPQLQuery(){
		List<SMSSendReport> reports = dao.findO2();
		System.out.println(reports.size());
	}
	
	@Test
	public void testJPSpecificationQuery() {
		BaseSearchSpecification baseSearchSpecification = BaseSearchSpecification.getInstance()
				.EQ("status", 1).EQ("receivePhone", "18938718960");
		BaseSearchSpecification combineSearch = BaseSearchSpecification.getInstance().GTE("receiveTime", new Date());
		dao.findAll(SpecificationBuilder.getInstance(SMSSendReport.class).or(baseSearchSpecification, combineSearch).build());
	}
	
	@Test
	public void testSQLPredicateQuery(){
		BaseSearchSpecification baseSearchSpecification = BaseSearchSpecification.getInstance()
													.EQ("status", 1).EQ("receivePhone", "18938718960");
		BaseSearchSpecification combineSearch = BaseSearchSpecification.getInstance().GTE("receiveTime", "2017-07-01");
		baseSearchSpecification.or(combineSearch);
		List<SMSSendReport> reports = dao.findAllBySQLSpefc(new SQLBaseSearchSpecification(baseSearchSpecification));
		System.out.println("testSQLPredicateQuery:"+reports.size());
	}
	
	@Test
	public void testSQLPredicateSortQuery(){
		Sort sort = new Sort(new Order(Direction.DESC, "sendTime"),new Order(Direction.DESC, "reportTime"));
		BaseSearchSpecification baseSearchSpecification = BaseSearchSpecification.getInstance()
				.EQ("status", 1).EQ("receivePhone", "18938718960");
		BaseSearchSpecification combineSearch = BaseSearchSpecification.getInstance().GTE("receiveTime", "2017-07-01");
		List<SMSSendReport> reports = dao.findAllBySQLSpefc(
				SQLSpecificationBuilder.getInstance().or(baseSearchSpecification, combineSearch).build(), sort);
		System.out.println("testSQLPredicateQuery:"+reports.size());
	}
	
	@Test
	public void testCustomSQLPredicateQuery(){
		BaseSearchSpecification baseSearchSpecification = BaseSearchSpecification.getInstance()
				.EQ("status", 1).EQ("receivePhone", "18938718960");
		BaseSearchSpecification combineSearch = BaseSearchSpecification.getInstance().GTE("receiveTime", "2017-07-01");
		baseSearchSpecification.or(combineSearch);
		List<SMSSendReport> reports = dao.findAllBySQLSpefc("select * from user_sms_report", new SQLBaseSearchSpecification(baseSearchSpecification));
		System.out.println("testCustomSQLPredicateQuery:"+reports.size());
	}
	
	@Test
	public void testPageView2Page(){
		BaseSearchSpecification baseSearchSpecification = BaseSearchSpecification.getInstance()
				.EQ("status", 1).EQ("receivePhone", "18938718960");
		BaseSearchSpecification combineSearch = BaseSearchSpecification.getInstance().GTE("receiveTime", "2017-07-01");
		baseSearchSpecification.or(combineSearch);
		Sort sort = new Sort(new Order(Direction.DESC, "sendTime"),new Order(Direction.DESC, "reportTime"));
		Pageable requestPage = new PageRequest(1, 1, sort);
		Page<SMSSendReport> reports = dao.findPageViewBySQLSpefc("select * from user_sms_report", new SQLBaseSearchSpecification(baseSearchSpecification), requestPage);
		System.out.println("testPageView2Page:"+reports.getSize()+":"+reports.getTotalElements());
	}
	
	@Test
	public void testInSQLQuery(){
		BaseSearchSpecification baseSearchSpecification = BaseSearchSpecification.getInstance().IN("status", new Object[]{1,2,3,4});
		List<SMSSendReport> reports = dao.findAllBySQLSpefc(new SQLBaseSearchSpecification(baseSearchSpecification));
		System.out.println("testInSQLQuery:"+ reports.size());
	}
	
	@Test
	public void testNInSQLQuery(){
		BaseSearchSpecification baseSearchSpecification = BaseSearchSpecification.getInstance().NOT_IN("status", new Object[]{1,2,3,4});
		List<SMSSendReport> reports = dao.findAllBySQLSpefc(new SQLBaseSearchSpecification(baseSearchSpecification));
		System.out.println("testNInSQLQuery:"+ reports.size());
	}
}
