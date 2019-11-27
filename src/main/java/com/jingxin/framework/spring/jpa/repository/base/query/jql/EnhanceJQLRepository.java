package com.jingxin.framework.spring.jpa.repository.base.query.jql;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EnhanceJQLRepository<T> {

	EntityManager getEntityManager();
}
