package com.spring.knowhub.domain.specifications.message;

import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.specifications.Specification;
import lombok.Value;

@Value
public class MessageIsLatestPerPartnerSpec implements Specification<Message> {
    Long userId;

    @Override
    public boolean isSatisfiedBy(Message message) {
        return true;
    }
}
