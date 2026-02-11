package com.spring.knowhub.presentation.mappers.message;

import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.presentation.exceptions.message.MessageResponseMappingException;
import com.spring.knowhub.presentation.response.message.MessageResponse;
import com.spring.knowhub.presentation.response.post.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageResponseMapper {
    private final ModelMapperConfiguration modelMapper;
    private TypeMap<Message, MessageResponse> fromMessageToMessageResponseTypeMap;

    public MessageResponse fromMessageToMessageResponse(Message message) {
        try {
            if (message == null) {
                throw MessageResponseMappingException.objectNull();
            }

            if (fromMessageToMessageResponseTypeMap == null) {
                fromMessageToMessageResponseTypeMap = modelMapper.modelMapper().createTypeMap(Message.class,
                        MessageResponse.class);
                fromMessageToMessageResponseTypeMap.addMappings(mapper -> {
                    mapper.skip(MessageResponse::setSenderId);
                    mapper.skip(MessageResponse::setSenderName);
                    mapper.skip(MessageResponse::setSenderAvatarUrl);
                    mapper.skip(MessageResponse::setReceiverId);
                    mapper.skip(MessageResponse::setReceiverName);
                    mapper.skip(MessageResponse::setReceiverAvatarUrl);
                    mapper.skip(MessageResponse::setMedias);
                });
                fromMessageToMessageResponseTypeMap.implicitMappings();
            }

            MessageResponse response = fromMessageToMessageResponseTypeMap.map(message);

            // map sender
            if (message.getSender() != null) {
                response.setSenderId(message.getSender().getId());
                response.setSenderName(message.getSender().getUsername());
                response.setSenderAvatarUrl(message.getSender().getAvatarUrl());
            }

            // map receiver
            if (message.getReceiver() != null) {
                response.setReceiverId(message.getReceiver().getId());
                response.setReceiverName(message.getReceiver().getUsername());
                response.setReceiverAvatarUrl(message.getReceiver().getAvatarUrl());
            }

            // map media
            if (message.getMedia() != null) {
                response.setMedias(
                        message.getMedia().stream()
                                .map(m -> new MediaResponse(
                                        m.getId(),
                                        m.getUrl(),
                                        m.getType() != null ? m.getType() : null,
                                        m.getOwnerType() != null ? m.getOwnerType() : null))
                                .collect(Collectors.toList()));
            }

            return response;
        } catch (Exception exception) {
            throw MessageResponseMappingException.errorMapping(exception);
        }
    }
}
