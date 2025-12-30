package com.spring.knowhub.domain.specifications;

public class NotSpecification <T> extends CompositeSpecification<T> {

    private final Specification<T> wrapped;

    public NotSpecification(Specification<T> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public boolean isSatisfiedBy(T entity) {
        return !wrapped.isSatisfiedBy(entity);
    }

    public Specification<T> getWrapped() {
        return wrapped;
    }
}
