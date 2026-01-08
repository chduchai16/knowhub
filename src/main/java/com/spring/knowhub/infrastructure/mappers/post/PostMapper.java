package com.spring.knowhub.infrastructure.mappers.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import com.spring.knowhub.infrastructure.exceptions.post.post.PostMapperException;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private TypeMap<Post, PostEntity> fromDomainToEntityTypeMap;
    private TypeMap<PostEntity, Post> fromEntityToDomainTypeMap;

    public PostEntity fromDomainToEntity(Post post) {
        try {
            if (post == null) {
                throw PostMapperException.fromDomainToEntityFailed("Đối tượng Post truyền vào là null");
            }
            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(Post.class, PostEntity.class);
                fromDomainToEntityTypeMap.getMappings().clear();
                fromDomainToEntityTypeMap.addMappings(mapper -> {
                    mapper.skip(PostEntity::setUser);
                });
                fromDomainToEntityTypeMap.implicitMappings();
            }

            PostEntity postEntity = fromDomainToEntityTypeMap.map(post);

            // map user
            if (post.getUser() != null) {
                postEntity.setUser(userMapper.fromDomainToEntity(post.getUser()));
            }
            return postEntity;
        } catch (Exception ex) {
            throw PostMapperException.fromDomainToEntityFailed(ex.getMessage());
        }
    }

    public Post fromEntityToDomain(PostEntity postEntity) {
        try {
            if (postEntity == null) {
                throw PostMapperException.fromEntityToDomainFailed("Đối tượng PostEntity truyền vào là null");
            }
            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(PostEntity.class, Post.class);
                fromEntityToDomainTypeMap.getMappings().clear();
                fromEntityToDomainTypeMap.addMappings(mapper -> {
                    mapper.skip(Post::setUser);
                });
                fromEntityToDomainTypeMap.implicitMappings();
            }

            Post post = fromEntityToDomainTypeMap.map(postEntity);

            // map user
            if (postEntity.getUser() != null) {
                post.setUser(userMapper.fromEntityToDomain(postEntity.getUser()));
            }
            return post;
        } catch (Exception ex) {
            throw PostMapperException.fromEntityToDomainFailed(ex.getMessage());
        }
    }

}
