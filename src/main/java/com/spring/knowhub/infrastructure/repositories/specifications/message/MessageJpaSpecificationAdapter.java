package com.spring.knowhub.infrastructure.repositories.specifications.message;

import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.specifications.*;
import com.spring.knowhub.domain.specifications.message.*;
import com.spring.knowhub.infrastructure.entities.message.MessageEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public final class MessageJpaSpecificationAdapter {

    private MessageJpaSpecificationAdapter() {
    }

    public static Specification<MessageEntity> toJpaSpecification(
            com.spring.knowhub.domain.specifications.Specification<Message> spec) {

        if (spec instanceof AlwaysTrueSpecification) {
            return (root, query, cb) -> cb.conjunction();
        }

        // AND
        if (spec instanceof AndSpecification<Message> andSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(andSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(andSpec.getRight()).toPredicate(root, query, cb);
                return cb.and(left, right);
            };
        }

        // OR
        if (spec instanceof OrSpecification<Message> orSpec) {
            return (root, query, cb) -> {
                Predicate left = toJpaSpecification(orSpec.getLeft()).toPredicate(root, query, cb);
                Predicate right = toJpaSpecification(orSpec.getRight()).toPredicate(root, query, cb);
                return cb.or(left, right);
            };
        }

        // NOT
        if (spec instanceof NotSpecification<Message> notSpec) {
            return (root, query, cb) -> cb.not(toJpaSpecification(notSpec.getWrapped()).toPredicate(root, query, cb));
        }

        // tìm kiếm theo tên 
        if (spec instanceof MessagePartnerHasNameSpec nameSpec) {
            String keyword = nameSpec.getKeyword();
            String likePattern = "%" + keyword.toLowerCase() + "%";

            return (root, query, cb) -> {
                Join<MessageEntity, UserEntity> sender = root.join("sender", JoinType.INNER);
                Join<MessageEntity, UserEntity> receiver = root.join("receiver", JoinType.INNER);

                return cb.or(
                        cb.like(cb.lower(sender.get("fullName")), likePattern),
                        cb.like(cb.lower(sender.get("username")), likePattern),
                        cb.like(cb.lower(receiver.get("fullName")), likePattern),
                        cb.like(cb.lower(receiver.get("username")), likePattern));
            };
        }

        // tìm kiếm tin nhắn mới nhất theo từng đối tác
        if (spec instanceof MessageIsLatestPerPartnerSpec latestSpec) {
            Long userId = latestSpec.getUserId();
            return (root, query, cb) -> {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<MessageEntity> subRoot = subquery.from(MessageEntity.class);

                Expression<Long> partnerId = cb.selectCase()
                        .when(cb.equal(root.get("sender").get("id"), userId), root.get("receiver").get("id"))
                        .otherwise(root.get("sender").get("id"))
                        .as(Long.class);

                subquery.select(cb.max(subRoot.get("id")));
                subquery.where(cb.or(
                        cb.and(cb.equal(subRoot.get("sender").get("id"), userId),
                                cb.equal(subRoot.get("receiver").get("id"), partnerId)),
                        cb.and(cb.equal(subRoot.get("receiver").get("id"), userId),
                                cb.equal(subRoot.get("sender").get("id"), partnerId))));

                return cb.and(
                        cb.or(cb.equal(root.get("sender").get("id"), userId),
                                cb.equal(root.get("receiver").get("id"), userId)),
                        cb.equal(root.get("id"), subquery));
            };
        }

        throw new IllegalArgumentException("Không thể chuyển đổi Message Specification: " + spec.getClass().getName());
    }
}
