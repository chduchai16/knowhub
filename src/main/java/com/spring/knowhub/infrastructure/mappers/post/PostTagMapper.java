package com.spring.knowhub.infrastructure.mappers.post;

import com.spring.knowhub.domain.models.post.PostTag;
import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import com.spring.knowhub.infrastructure.entities.post.PostTagEntity;
import com.spring.knowhub.infrastructure.entities.post.TagEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostTagMapper {
    private final ModelMapper modelMapper ;
    private final TagMapper tagMapper ;
    private final PostMapper postMapper ;
    private TypeMap<PostTag , PostTagEntity> fromDomainToEntityTypeMap;
    private TypeMap<PostTagEntity , PostTag> fromEntityToDomainTypeMap;

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

        // map post
        if(postTag.getPost() != null) {
            PostEntity postEntity = postMapper.fromDomainToEntity(postTag.getPost());
            postTagEntity.setPost(postEntity);
        }

        // map tag
        if(postTag.getTag() != null) {
            TagEntity tagEntity = tagMapper.fromDomainToEntity(postTag.getTag());
            postTagEntity.setTag(tagEntity);
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

        // map post
        if(postTagEntity.getPost() != null) {
            PostEntity postEntity = postTagEntity.getPost();
            postTag.setPost( postMapper.fromEntityToDomain(postEntity) );
        }

        // map tag
        if(postTagEntity.getTag() != null) {
            TagEntity tagEntity = postTagEntity.getTag();
            postTag.setTag( tagMapper.fromEntityToDomain(tagEntity) );
        }

        return postTag;
    }
}
