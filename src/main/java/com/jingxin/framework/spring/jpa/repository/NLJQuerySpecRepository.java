package com.jingxin.framework.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NLJQuerySpecRepository<T> extends JpaSpecificationExecutor<T>{

}
