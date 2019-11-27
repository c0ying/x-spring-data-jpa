package com.jingxin.framework.spring.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.jingxin.framework.spring.jpa.repository.base.query.simple.SimpleSQueryRepository;

@NoRepositoryBean
public interface NLSQueryRepository<T, ID extends Serializable> 
						extends JpaRepository<T, ID>, SimpleSQueryRepository<T>{

}
