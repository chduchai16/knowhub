package com.spring.knowhub.infrastructure.mappers.comment;

import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.infrastructure.entities.comment.CommentEntity;
import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.mappers.post.PostMapper;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    private TypeMap<Comment, CommentEntity> fromDomainToEntityTypeMap;
    private TypeMap<CommentEntity, Comment> fromEntityToDomainTypeMap;


    public Comment fromEntityToDomain(CommentEntity entity) {
        if (entity == null) return null;

        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap =
                    modelMapper.createTypeMap(CommentEntity.class, Comment.class);

            fromEntityToDomainTypeMap.getMappings().clear();
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(Comment::setPost);
                mapper.skip(Comment::setUser);
                mapper.skip(Comment::setParent);
            });
            fromEntityToDomainTypeMap.implicitMappings();
        }

        Comment comment = fromEntityToDomainTypeMap.map(entity);

        if (entity.getPost() != null) {
            comment.setPost(
                    postMapper.fromEntityToDomain(entity.getPost())
            );
        }

        // user
        if (entity.getUser() != null) {
            comment.setUser(
                    userMapper.fromEntityToDomain(entity.getUser())
            );
        }

        // parent (SHALLOW - chỉ ID)
        if (entity.getParent() != null) {
            Comment parent = new Comment();
            parent.setId(entity.getParent().getId());
            comment.setParent(parent);
        }

        return comment;
    }


    public CommentEntity fromDomainToEntity(Comment domain) {
        if (domain == null) return null;

        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap =
                    modelMapper.createTypeMap(Comment.class, CommentEntity.class);

            fromDomainToEntityTypeMap.getMappings().clear();
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(CommentEntity::setPost);
                mapper.skip(CommentEntity::setUser);
                mapper.skip(CommentEntity::setParent);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }

        CommentEntity entity = fromDomainToEntityTypeMap.map(domain);

        // post (reference by ID)
        if (domain.getPost() != null && domain.getPost().getId() != null) {
            PostEntity postRef = new PostEntity();
            postRef.setId(domain.getPost().getId());
            entity.setPost(postRef);
        }

        // user
        if (domain.getUser() != null && domain.getUser().getId() != null) {
            UserEntity userRef = new UserEntity();
            userRef.setId(domain.getUser().getId());
            entity.setUser(userRef);
        }

        // parent (self-reference by ID)
        if (domain.getParent() != null && domain.getParent().getId() != null) {
            CommentEntity parentRef = new CommentEntity();
            parentRef.setId(domain.getParent().getId());
            entity.setParent(parentRef);
        }

        return entity;
    }
}
