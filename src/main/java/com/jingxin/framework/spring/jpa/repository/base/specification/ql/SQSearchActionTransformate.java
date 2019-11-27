package com.jingxin.framework.spring.jpa.repository.base.specification.ql;

import com.jingxin.framework.spring.jpa.repository.base.specification.BaseSearchSpecification;

public interface SQSearchActionTransformate {

	SQLPredicate transform(BaseSearchSpecification baseSearch);

}