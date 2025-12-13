package com.spring.knowhub.infrastructure.mappers.comment;

import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.models.comment.CommentLike;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.infrastructure.entities.comment.CommentEntity;
import com.spring.knowhub.infrastructure.entities.comment.CommentLikeEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentLikeMapper {

    private final ModelMapper modelMapper ;
    private final CommentMapper commentMapper ;
    private final UserMapper userMapper ;
    private TypeMap<CommentLike , CommentLikeEntity> fromDomainToEntityTypeMap ;
    private TypeMap<CommentLikeEntity , CommentLike> fromEntityToDomainTypeMap ;

    public CommentLike fromEntityToDomain(CommentLikeEntity commentLikeEntity) {
        if (commentLikeEntity == null) return null;
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(CommentLikeEntity.class, CommentLike.class);
            fromEntityToDomainTypeMap.getMappings().clear();
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(CommentLike::setComment);
                mapper.skip(CommentLike::setUser);
            });
            fromEntityToDomainTypeMap.implicitMappings();
        }

        CommentLike commentLike = fromEntityToDomainTypeMap.map(commentLikeEntity);

        // map comment
        if (commentLikeEntity.getComment() != null) {
            Comment comment = commentMapper.fromEntityToDomain(commentLikeEntity.getComment());
            commentLike.setComment(comment);
        }

        // map user
        if (commentLikeEntity.getUser() != null) {
            User user = userMapper.fromEntityToDomain(commentLikeEntity.getUser());
            commentLike.setUser(user);
        }

        return commentLike;
    }

    public CommentLikeEntity fromDomainToEntity(CommentLike commentLike) {
        if (commentLike == null) return null;
        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(CommentLike.class, CommentLikeEntity.class);
            fromDomainToEntityTypeMap.getMappings().clear();
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(CommentLikeEntity::setComment);
                mapper.skip(CommentLikeEntity::setUser);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }

        CommentLikeEntity commentLikeEntity = fromDomainToEntityTypeMap.map(commentLike);

        // map comment
        if (commentLike.getComment() != null) {
            CommentEntity commentEntity = commentMapper.fromDomainToEntity(commentLike.getComment());
            commentLikeEntity.setComment(commentEntity);
        }

        // map user
        if (commentLike.getUser() != null) {
            UserEntity userEntity = userMapper.fromDomainToEntity(commentLike.getUser());
            commentLikeEntity.setUser(userEntity);
        }

        return commentLikeEntity;
    }
}
