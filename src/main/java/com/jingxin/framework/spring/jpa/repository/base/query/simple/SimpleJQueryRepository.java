package com.jingxin.framework.spring.jpa.repository.base.query.simple;

import org.springframework.data.repository.NoRepositoryBean;

import com.jingxin.framework.spring.jpa.repository.base.query.jql.BaseJQueryRepository;
import com.jingxin.framework.spring.jpa.repository.base.query.jql.EnhanceJQLRepository;

@NoRepositoryBean
public interface SimpleJQueryRepository<T> extends BaseJQueryRepository<T>, EnhanceJQLRepository<T> {

}
