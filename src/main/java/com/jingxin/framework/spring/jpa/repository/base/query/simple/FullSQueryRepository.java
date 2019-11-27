package com.jingxin.framework.spring.jpa.repository.base.query.simple;

import org.springframework.data.repository.NoRepositoryBean;

import com.jingxin.framework.spring.jpa.repository.base.query.sql.EBaseSQLCUDRepository;
import com.jingxin.framework.spring.jpa.repository.base.query.sql.EBaseSQueryRepository;

@NoRepositoryBean
public interface FullSQueryRepository<T> 
			extends SimpleSQueryRepository<T>, EBaseSQueryRepository, EBaseSQLCUDRepository{

}
