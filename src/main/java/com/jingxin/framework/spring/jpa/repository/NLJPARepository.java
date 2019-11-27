package com.jingxin.framework.spring.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.jingxin.framework.spring.jpa.repository.base.query.simple.FullJQueryRepository;
import com.jingxin.framework.spring.jpa.repository.base.query.simple.FullSQueryRepository;

@NoRepositoryBean
public interface NLJPARepository<T, ID extends Serializable> 
			extends JpaRepository<T, ID>, FullJQueryRepository<T>, FullSQueryRepository<T>{

}
