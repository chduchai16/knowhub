package com.spring.knowhub.domain.specifications.message;

import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;

public class MessageSpecification {

    public static Specification<Message> isLatestPerPartner(Long userId) {
        return new MessageIsLatestPerPartnerSpec(userId);
    }

    public static Specification<Message> partnerHasName(String keyword) {
        return (keyword == null || keyword.isBlank())
                ? new AlwaysTrueSpecification<>()
                : new MessagePartnerHasNameSpec(keyword);
    }
}
