package com.jingxin.framework.spring.jpa.repository.base.query.simple;

import org.springframework.data.repository.NoRepositoryBean;

import com.jingxin.framework.spring.jpa.repository.base.query.jql.EBaseJQLCUDRepository;
import com.jingxin.framework.spring.jpa.repository.base.query.jql.EBaseJQueryRepository;

@NoRepositoryBean
public interface FullJQueryRepository<T> 
			extends SimpleJQueryRepository<T>, EBaseJQueryRepository, EBaseJQLCUDRepository{

}
