package com.spring.knowhub.infrastructure.mappers.post;

import com.spring.knowhub.domain.models.post.PostMedia;
import com.spring.knowhub.infrastructure.entities.post.PostMediaEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMediaMapper {

    private final ModelMapper modelMapper;
    private final ObjectProvider<PostMapper> postMapperProvider; // lazy injection để tránh vòng phụ thuộc

    private TypeMap<PostMedia, PostMediaEntity> fromModelToEntityTypeMap;
    private TypeMap<PostMediaEntity, PostMedia> fromEntityToModelTypeMap;

    public PostMediaEntity fromModelToEntity(PostMedia postMedia) {
        if (fromModelToEntityTypeMap == null) {
            fromModelToEntityTypeMap = modelMapper.createTypeMap(PostMedia.class, PostMediaEntity.class);
            fromModelToEntityTypeMap.getMappings().clear();
            fromModelToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(PostMediaEntity::setPost);
            });
            fromModelToEntityTypeMap.implicitMappings();
        }

        PostMediaEntity postMediaEntity = fromModelToEntityTypeMap.map(postMedia);
        // map post nếu có
        if (postMedia.getPost() != null) {
            PostMapper postMapper = postMapperProvider.getIfAvailable();
            if (postMapper != null) {
                postMediaEntity.setPost(postMapper.fromDomainToEntity(postMedia.getPost()));
            }
        }
        return postMediaEntity;
    }

    public PostMedia fromEntityToModel(PostMediaEntity postMediaEntity) {
        if (fromEntityToModelTypeMap == null) {
            fromEntityToModelTypeMap = modelMapper.createTypeMap(PostMediaEntity.class, PostMedia.class);
            fromEntityToModelTypeMap.getMappings().clear();
            fromEntityToModelTypeMap.addMappings(mapper -> {
                mapper.skip(PostMedia::setPost);
            });
            fromEntityToModelTypeMap.implicitMappings();
        }

        PostMedia postMedia = fromEntityToModelTypeMap.map(postMediaEntity);
        // map post nếu có
        if (postMediaEntity.getPost() != null) {
            PostMapper postMapper = postMapperProvider.getIfAvailable();
            if (postMapper != null) {
                postMedia.setPost(postMapper.fromEntityToDomain(postMediaEntity.getPost()));
            }
        }
        return postMedia;
    }
}
