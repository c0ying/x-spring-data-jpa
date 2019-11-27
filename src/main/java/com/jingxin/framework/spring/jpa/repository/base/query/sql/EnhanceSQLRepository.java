package com.jingxin.framework.spring.jpa.repository.base.query.sql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EnhanceSQLRepository<T> {

	Page<T> findPageBySQL(String sql, Pageable pageable);

	Page<T> findPageBySQL(String sql, String totalSql, Pageable pageable);

	Iterable<T> findScrollBySQL(String sql);

	Iterable<T> findScrollBySQL(String sql, Sort sort);

	T findSingleBySQL(String sql);

	T findSingleBySQL(String sql, Sort sort);

}