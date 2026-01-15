package com.spring.knowhub.infrastructure.mappers.post;

import com.spring.knowhub.domain.models.post.PostTag;
import com.spring.knowhub.infrastructure.entities.post.PostTagEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostTagMapper {
    private final ModelMapper modelMapper;
    private final TagMapper tagMapper;
    private TypeMap<PostTag, PostTagEntity> fromDomainToEntityTypeMap;
    private TypeMap<PostTagEntity, PostTag> fromEntityToDomainTypeMap;

    public PostTagEntity fromDomainToEntity(PostTag postTag) {
        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(PostTag.class, PostTagEntity.class);
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(PostTagEntity::setPost);
                mapper.skip(PostTagEntity::setTag);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }

        PostTagEntity postTagEntity = fromDomainToEntityTypeMap.map(postTag);

        // map tag
        if (postTag.getTag() != null) {
            postTagEntity.setTag(
                    tagMapper.fromDomainToEntity(postTag.getTag()));
        }

        return postTagEntity;
    }

    public PostTag fromEntityToDomain(PostTagEntity postTagEntity) {
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(PostTagEntity.class, PostTag.class);
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(PostTag::setPost);
                mapper.skip(PostTag::setTag);
            });
            fromEntityToDomainTypeMap.implicitMappings();
        }

        PostTag postTag = fromEntityToDomainTypeMap.map(postTagEntity);

        // map tag only
        if (postTagEntity.getTag() != null) {
            postTag.setTag(
                    tagMapper.fromEntityToDomain(postTagEntity.getTag()));
        }

        return postTag;
    }
}
