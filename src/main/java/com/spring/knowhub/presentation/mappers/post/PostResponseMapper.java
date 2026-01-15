package com.spring.knowhub.presentation.mappers.post;

import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.presentation.exceptions.post.PostResponseMappingException;
import com.spring.knowhub.presentation.response.post.MediaResponse;
import com.spring.knowhub.presentation.response.post.PostResponse;
import com.spring.knowhub.presentation.response.post.TagResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostResponseMapper {
    private final ModelMapperConfiguration modelMapper;
    private TypeMap<Post, PostResponse> fromPostToPostResponseTypeMap;

    public PostResponse fromPostToPostResponse(Post post) {
        try {
            if (post == null) {
                throw PostResponseMappingException.objectNull();
            }

            if (fromPostToPostResponseTypeMap == null) {
                fromPostToPostResponseTypeMap = modelMapper.modelMapper().createTypeMap(Post.class, PostResponse.class);
                fromPostToPostResponseTypeMap.addMappings(mapper -> {
                    mapper.skip(PostResponse::setUserId);
                    mapper.skip(PostResponse::setUsername);
                    mapper.skip(PostResponse::setTags);
                    mapper.skip(PostResponse::setMedias);
                });
                fromPostToPostResponseTypeMap.implicitMappings();
            }

            PostResponse response = fromPostToPostResponseTypeMap.map(post);

            // map user
            if (post.getUser() != null) {
                response.setUserId(post.getUser().getId());
                response.setUsername(post.getUser().getUsername());
            }

            // map post tags
            if (post.getPostTags() != null) {
                response.setTags(
                        post.getPostTags().stream()
                                .map(postTag -> new TagResponse(
                                        postTag.getTag().getId(),
                                        postTag.getTag().getName()))
                                .collect(Collectors.toList()));
            }

            // map media
            if (post.getMedia() != null) {
                response.setMedias(
                        post.getMedia().stream()
                                .map(media -> new MediaResponse(
                                        media.getId(),
                                        media.getUrl(),
                                        media.getType() != null ? media.getType() : null,
                                        media.getOwnerType() != null ? media.getOwnerType() : null)
                                )
                                .collect(Collectors.toList()));
            }
            return response;
        } catch (Exception exception) {
            throw PostResponseMappingException.errorMapping(exception);
        }
    }
}
