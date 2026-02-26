package com.spring.knowhub.infrastructure.mappers.message;

import com.spring.knowhub.domain.models.message.Message;
import com.spring.knowhub.infrastructure.entities.message.MessageEntity;
import com.spring.knowhub.infrastructure.exceptions.message.MessageMapperException;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private TypeMap<Message, MessageEntity> fromDomainToEntityTypeMap;
    private TypeMap<MessageEntity, Message> fromEntityToDomainTypeMap;

    public MessageEntity fromDomainToEntity(Message message) {
        try {
            if (message == null) {
                throw MessageMapperException.errorMapping("Đối tượng Message là null");
            }

            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(Message.class, MessageEntity.class);
                fromDomainToEntityTypeMap.addMappings(mapper -> {
                    mapper.skip(MessageEntity::setSender);
                    mapper.skip(MessageEntity::setReceiver);
                });
                fromDomainToEntityTypeMap.implicitMappings();
            }

            MessageEntity entity = fromDomainToEntityTypeMap.map(message);

            if (message.getSender() != null) {
                entity.setSender(userMapper.fromDomainToEntity(message.getSender()));
            }

            if (message.getReceiver() != null) {
                entity.setReceiver(userMapper.fromDomainToEntity(message.getReceiver()));
            }

            return entity;
        } catch (Exception ex) {
            throw MessageMapperException.errorMapping(ex.getMessage());
        }
    }

    public Message fromEntityToDomain(MessageEntity entity) {
        try {
            if (entity == null) {
                throw MessageMapperException.errorMapping("Thực thể MessageEntity là null");
            }

            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(MessageEntity.class, Message.class);
                fromEntityToDomainTypeMap.addMappings(mapper -> {
                    mapper.skip(Message::setSender);
                    mapper.skip(Message::setReceiver);
                    mapper.skip(Message::setMedia);
                });
                fromEntityToDomainTypeMap.implicitMappings();
            }

            Message message = modelMapper.map(entity, Message.class);

            if (entity.getSender() != null) {
                message.setSender(userMapper.fromEntityToDomain(entity.getSender()));
            }

            if (entity.getReceiver() != null) {
                message.setReceiver(userMapper.fromEntityToDomain(entity.getReceiver()));
            }

            return message;
        } catch (Exception ex) {
            throw MessageMapperException.errorMapping(ex.getMessage());
        }
    }
}
