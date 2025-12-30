package com.spring.knowhub.domain.specifications;

public class AlwaysTrueSpecification<T> implements Specification<T>{
    @Override
    public boolean isSatisfiedBy(T candidate) {
        return true;
    }
}
