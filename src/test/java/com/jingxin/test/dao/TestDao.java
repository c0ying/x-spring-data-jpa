package com.jingxin.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jingxin.framework.spring.jpa.annotation.NLSpringJPA;
import com.jingxin.framework.spring.jpa.repository.NLJPARepository;
import com.jingxin.framework.spring.jpa.repository.NLJQuerySpecRepository;
import com.jingxin.framework.spring.jpa.repository.base.query.simple.SimpleSQueryRepository;
import com.jingxin.framework.spring.jpa.repository.base.query.sql.SpefcSQueryRepository;
import com.jingxin.test.po.SMSSendReport;

@NLSpringJPA
public interface TestDao extends NLJPARepository<SMSSendReport, String>, 
									SimpleSQueryRepository<SMSSendReport>, 
									NLJQuerySpecRepository<SMSSendReport>,
									SpefcSQueryRepository<SMSSendReport> {
	
	List<SMSSendReport> findByStatus(int Status);
	
	@Query(value = "from SMSSendReport")
	List<SMSSendReport> findO2();
	
	@Query(value = "select * from user_sms_report", nativeQuery = true)
	List<SMSSendReport> findOO();
	
}
