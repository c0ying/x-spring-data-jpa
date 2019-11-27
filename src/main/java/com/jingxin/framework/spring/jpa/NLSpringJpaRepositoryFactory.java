package com.jingxin.framework.spring.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import com.jingxin.framework.spring.jpa.annotation.NLSpringJPA;
import com.jingxin.framework.spring.jpa.repository.NLJPARepositoryImpl;

public class NLSpringJpaRepositoryFactory extends JpaRepositoryFactory{
	

	public NLSpringJpaRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
	}
	
	
	@Override
	protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
			RepositoryInformation information, EntityManager entityManager) {
		Class<?> repositoryInterface = information.getRepositoryInterface();
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
		if (repositoryInterface.isAnnotationPresent(NLSpringJPA.class)) {
			NLJPARepositoryImpl<?, ?> repo = 
					new NLJPARepositoryImpl(information.getDomainType(), entityManager);
			return repo;
		}else{
			return super.getTargetRepository(information, entityManager);
		}
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		if (metadata.getRepositoryInterface().isAnnotationPresent(NLSpringJPA.class)){
			return NLJPARepositoryImpl.class;
		}else{
			return SimpleJpaRepository.class;
		}
	}

}
