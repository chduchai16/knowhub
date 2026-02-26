package com.spring.knowhub.infrastructure.mappers.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import com.spring.knowhub.infrastructure.entities.post.PostTagEntity;
import com.spring.knowhub.infrastructure.exceptions.post.post.PostMapperException;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final TagMapper tagMapper;
    private TypeMap<Post, PostEntity> fromDomainToEntityTypeMap;
    private TypeMap<PostEntity, Post> fromEntityToDomainTypeMap;

    public PostEntity fromDomainToEntity(Post post) {
        try {
            if (post == null) {
                throw PostMapperException.fromDomainToEntityFailed("Đối tượng Post là null");
            }

            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(Post.class, PostEntity.class);
                fromDomainToEntityTypeMap.addMappings(mapper -> {
                    mapper.skip(PostEntity::setUser);
                    mapper.skip(PostEntity::setPostTags);
                });
                fromDomainToEntityTypeMap.implicitMappings();
            }

            PostEntity postEntity = fromDomainToEntityTypeMap.map(post);

            /* map user */
            if (post.getUser() != null) {
                postEntity.setUser(
                        userMapper.fromDomainToEntity(post.getUser()));
            }

            /* map postTags */
            if (post.getPostTags() != null && !post.getPostTags().isEmpty()) {
                if (postEntity.getPostTags() == null) {
                    postEntity.setPostTags(new HashSet<>());
                } else {
                    // Clear old tags to trigger orphanRemoval
                    postEntity.getPostTags().clear();
                }

                for (var postTag : post.getPostTags()) {
                    PostTagEntity postTagEntity = new PostTagEntity();
                    postTagEntity.setPost(postEntity);
                    postTagEntity.setTag(
                            tagMapper.fromDomainToEntity(postTag.getTag()));
                    postEntity.getPostTags().add(postTagEntity);
                }
            } else if (postEntity.getPostTags() != null) {
                postEntity.getPostTags().clear();
            }

            return postEntity;

        } catch (PostMapperException ex) {
            throw ex;
        } catch (Exception ex) {
            throw PostMapperException.fromDomainToEntityFailed(
                    ex.getClass().getSimpleName() + ": " +
                            (ex.getMessage() != null ? ex.getMessage() : "No message"));
        }
    }

    public Post fromEntityToDomain(PostEntity postEntity) {
        try {
            if (postEntity == null) {
                throw PostMapperException.fromEntityToDomainFailed("Thực thể PostEntity là null");
            }

            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(PostEntity.class, Post.class);
                fromEntityToDomainTypeMap.addMappings(mapper -> {
                    mapper.skip(Post::setUser);
                    mapper.skip(Post::setPostTags);
                });
                fromEntityToDomainTypeMap.implicitMappings();
            }

            Post post = modelMapper.map(postEntity, Post.class);

            /* map user */
            if (postEntity.getUser() != null) {
                post.setUser(
                        userMapper.fromEntityToDomain(postEntity.getUser()));
            }

            /* map postTags */
            if (postEntity.getPostTags() != null && !postEntity.getPostTags().isEmpty()) {
                post.setPostTags(
                        postEntity.getPostTags().stream()
                                .map(postTagEntity -> {
                                    com.spring.knowhub.domain.models.post.PostTag postTag = new com.spring.knowhub.domain.models.post.PostTag();

                                    postTag.setId(postTagEntity.getId());
                                    postTag.setPost(post);
                                    postTag.setTag(
                                            tagMapper.fromEntityToDomain(
                                                    postTagEntity.getTag()));

                                    return postTag;
                                })
                                .collect(java.util.stream.Collectors.toSet()));
            }

            return post;

        } catch (PostMapperException ex) {
            throw ex;
        } catch (Exception ex) {
            throw PostMapperException.fromEntityToDomainFailed(
                    ex.getClass().getSimpleName() + ": " +
                            (ex.getMessage() != null ? ex.getMessage() : "No message"));
        }
    }
}
