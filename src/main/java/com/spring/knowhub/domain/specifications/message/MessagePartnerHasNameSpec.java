package com.spring.knowhub.domain.specifications.message;

import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.domain.specifications.Specification;
import lombok.Value;

@Value
public class MessagePartnerHasNameSpec implements Specification<Message> {
    String keyword;

    @Override
    public boolean isSatisfiedBy(Message message) {
        if (keyword == null || keyword.isBlank())
            return true;
        String lowerKeyword = keyword.toLowerCase();
        return message.getSender().getFullName().toLowerCase().contains(lowerKeyword) ||
                message.getReceiver().getFullName().toLowerCase().contains(lowerKeyword);
    }
}
