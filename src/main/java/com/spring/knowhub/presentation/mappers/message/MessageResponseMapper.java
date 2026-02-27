package com.spring.knowhub.presentation.mappers.message;

import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.presentation.exceptions.message.MessageResponseMappingException;
import com.spring.knowhub.presentation.response.message.MessageResponse;
import com.spring.knowhub.presentation.response.post.MediaResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageResponseMapper {
    private final ModelMapper modelMapper;
    private TypeMap<Message, MessageResponse> fromMessageToMessageResponseTypeMap;

    @PostConstruct
    public void init() {
        fromMessageToMessageResponseTypeMap = modelMapper.createTypeMap(Message.class, MessageResponse.class);
        fromMessageToMessageResponseTypeMap.addMappings(mapper -> {
            mapper.skip(MessageResponse::setSenderId);
            mapper.skip(MessageResponse::setSenderName);
            mapper.skip(MessageResponse::setSenderAvatarUrl);
            mapper.skip(MessageResponse::setReceiverId);
            mapper.skip(MessageResponse::setReceiverName);
            mapper.skip(MessageResponse::setReceiverAvatarUrl);
            mapper.skip(MessageResponse::setPartnerId);
            mapper.skip(MessageResponse::setPartnerName);
            mapper.skip(MessageResponse::setPartnerAvatarUrl);
            mapper.skip(MessageResponse::setMedias);
        });
        fromMessageToMessageResponseTypeMap.implicitMappings();
    }

    public MessageResponse fromMessageToMessageResponse(Message message) {
        try {
            if (message == null) {
                throw MessageResponseMappingException.objectNull();
            }

            MessageResponse response = fromMessageToMessageResponseTypeMap.map(message);

            if (message.getSender() != null) {
                response.setSenderId(message.getSender().getId());
                response.setSenderName(message.getSender().getUsername());
                response.setSenderAvatarUrl(message.getSender().getAvatarUrl());
            }

            if (message.getReceiver() != null) {
                response.setReceiverId(message.getReceiver().getId());
                response.setReceiverName(message.getReceiver().getUsername());
                response.setReceiverAvatarUrl(message.getReceiver().getAvatarUrl());
            }

            if (message.getMedia() != null) {
                response.setMedias(
                        message.getMedia().stream()
                                .map(m -> new MediaResponse(m.getId(), m.getUrl(), m.getType(), m.getOwnerType()))
                                .collect(Collectors.toList()));
            }

            return response;
        } catch (Exception exception) {
            throw MessageResponseMappingException.errorMapping(exception);
        }
    }

    public MessageResponse fromMessageToMessageResponse(Message message, Long currentUserId) {
        MessageResponse response = fromMessageToMessageResponse(message);

        if (currentUserId != null && message.getSender() != null && message.getReceiver() != null) {
            boolean isSender = currentUserId.equals(message.getSender().getId());
            if (isSender) {
                response.setPartnerId(message.getReceiver().getId());
                response.setPartnerName(message.getReceiver().getUsername());
                response.setPartnerAvatarUrl(message.getReceiver().getAvatarUrl());
            } else {
                response.setPartnerId(message.getSender().getId());
                response.setPartnerName(message.getSender().getUsername());
                response.setPartnerAvatarUrl(message.getSender().getAvatarUrl());
            }
        }

        return response;
    }
}
