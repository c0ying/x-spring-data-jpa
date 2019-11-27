package com.jingxin.framework.spring.jpa.repository.base.query.sql;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import com.jingxin.framework.spring.jpa.repository.base.specification.ql.SQLBaseSearchSpecification;

@NoRepositoryBean
public interface SpefcSQueryRepository<T> {

	List<T> findAllBySQLSpefc(SQLBaseSearchSpecification searchSpecification);
	
	List<T> findAllBySQLSpefc(SQLBaseSearchSpecification searchSpecification, Sort sort);

	List<T> findAllBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification);

	List<T> findAllBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification, Sort sort);

	<E> List<E> findAllBySQLSpefc(String sql, Class<E> result, SQLBaseSearchSpecification searchSpecification, Sort sort);

	T findOneBySQLSpefc(SQLBaseSearchSpecification searchSpecification);
	
	T findOneBySQLSpefc(SQLBaseSearchSpecification searchSpecification, Sort sort);

	T findOneBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification);

	T findOneBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification, Sort sort);

	<E> E findOneBySQLSpefc(String sql, Class<E> result, SQLBaseSearchSpecification searchSpecification, Sort sort);

	Page<T> findPageViewBySQLSpefc(SQLBaseSearchSpecification searchSpecification, Pageable pageable);

	Page<T> findPageViewBySQLSpefc(String sql, SQLBaseSearchSpecification searchSpecification, Pageable pageable);

	<E> Page<E> findPageViewBySQLSpefc(String sql, Class<E> result, SQLBaseSearchSpecification searchSpecification,
			Pageable pageable);

}