package com.spring.knowhub.infrastructure.mappers.post;

import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.infrastructure.entities.post.TagEntity;
import com.spring.knowhub.infrastructure.exceptions.post.tag.TagMapperException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagMapper {
    private final ModelMapper modelMapper;
    private TypeMap<Tag, TagEntity> fromDomainToEntityTypeMap;
    private TypeMap<TagEntity, Tag> fromEntityToDomainTypeMap;

    public TagEntity fromDomainToEntity(Tag tag) {
        try {
            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(Tag.class, TagEntity.class);
                fromDomainToEntityTypeMap.implicitMappings();
            }
            return fromDomainToEntityTypeMap.map(tag);
        } catch (Exception ex) {
            throw TagMapperException.fromDomainToEntityMappingError(ex.getMessage());
        }
    }

    public Tag fromEntityToDomain(TagEntity tagEntity) {
        try {
            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(TagEntity.class, Tag.class);
                fromEntityToDomainTypeMap.implicitMappings();
            }
            return fromEntityToDomainTypeMap.map(tagEntity);
        } catch (Exception ex) {
            throw TagMapperException.fromEntityToDomainMappingError(ex.getMessage());
        }
    }
}
