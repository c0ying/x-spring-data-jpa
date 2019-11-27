package com.jingxin.framework.spring.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.jingxin.framework.spring.jpa.repository.base.query.simple.SimpleJQueryRepository;

@NoRepositoryBean
public interface NLJQueryRepository<T, ID extends Serializable> 
					extends JpaRepository<T, ID>, SimpleJQueryRepository<T>{

}
