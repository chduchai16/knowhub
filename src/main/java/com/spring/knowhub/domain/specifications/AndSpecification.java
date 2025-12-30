package com.spring.knowhub.domain.specifications;

public class AndSpecification <T> extends CompositeSpecification<T>{

    private final Specification<T> left;
    private final Specification<T> right;

    public AndSpecification(Specification<T> left, Specification<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfiedBy(T entity) {
        return left.isSatisfiedBy(entity) && right.isSatisfiedBy(entity);
    }

    public Specification<T> getLeft() {
        return left;
    }

    public Specification<T> getRight() {
        return right;
    }
}
