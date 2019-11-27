package com.jingxin.framework.spring.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;


public class NLSpringJpaRepositoryFactoryBean <T extends Repository<S, ID>, S, ID extends Serializable>
										extends JpaRepositoryFactoryBean<T, S, ID>{

	public NLSpringJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new NLSpringJpaRepositoryFactory(entityManager);
	}
}
