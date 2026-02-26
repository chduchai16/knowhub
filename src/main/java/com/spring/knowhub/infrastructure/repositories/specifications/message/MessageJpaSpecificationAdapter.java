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

        return (root, query, cb) -> {
            // Eager fetch sender và receiver để tránh LazyInitializationException và tăng
            // hiệu năng
            if (query != null && query.getResultType() != Long.class) {
                root.fetch("sender", JoinType.LEFT);
                root.fetch("receiver", JoinType.LEFT);
            }
            return convertLogic(spec, root, query, cb);
        };
    }

    private static Predicate convertLogic(com.spring.knowhub.domain.specifications.Specification<Message> spec,
            Root<MessageEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (spec instanceof AlwaysTrueSpecification) {
            return cb.conjunction();
        }

        if (spec instanceof AndSpecification<Message> andSpec) {
            return cb.and(convertLogic(andSpec.getLeft(), root, query, cb),
                    convertLogic(andSpec.getRight(), root, query, cb));
        }

        if (spec instanceof OrSpecification<Message> orSpec) {
            return cb.or(convertLogic(orSpec.getLeft(), root, query, cb),
                    convertLogic(orSpec.getRight(), root, query, cb));
        }

        if (spec instanceof NotSpecification<Message> notSpec) {
            return cb.not(convertLogic(notSpec.getWrapped(), root, query, cb));
        }

        // Tìm kiếm theo tên đối tác
        if (spec instanceof MessagePartnerHasNameSpec nameSpec) {
            String keyword = nameSpec.getKeyword();
            String likePattern = "%" + keyword.toLowerCase() + "%";

            // JOIN để lọc (Fetch dùng để lấy dữ liệu, Join dùng để filter)
            Join<MessageEntity, UserEntity> sender = root.join("sender", JoinType.LEFT);
            Join<MessageEntity, UserEntity> receiver = root.join("receiver", JoinType.LEFT);

            return cb.or(
                    cb.like(cb.lower(sender.get("fullName")), likePattern),
                    cb.like(cb.lower(sender.get("username")), likePattern),
                    cb.like(cb.lower(receiver.get("fullName")), likePattern),
                    cb.like(cb.lower(receiver.get("username")), likePattern));
        }

        // Inbox logic: Lấy tin nhắn cuối cùng của mỗi cặp hội thoại
        if (spec instanceof MessageIsLatestPerPartnerSpec latestSpec) {
            Long userId = latestSpec.getUserId();

            if (query == null)
                return cb.conjunction();

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<MessageEntity> subRoot = subquery.from(MessageEntity.class);

            // Xác định partnerId của row hiện tại
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
        }

        return cb.conjunction();
    }
}
