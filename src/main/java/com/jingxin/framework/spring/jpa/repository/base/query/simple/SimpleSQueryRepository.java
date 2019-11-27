package com.jingxin.framework.spring.jpa.repository.base.query.simple;

import org.springframework.data.repository.NoRepositoryBean;

import com.jingxin.framework.spring.jpa.repository.base.query.sql.BaseSQueryRepository;
import com.jingxin.framework.spring.jpa.repository.base.query.sql.EnhanceSQLRepository;

@NoRepositoryBean
public interface SimpleSQueryRepository<T> 
					extends BaseSQueryRepository<T>, EnhanceSQLRepository<T>{

}
