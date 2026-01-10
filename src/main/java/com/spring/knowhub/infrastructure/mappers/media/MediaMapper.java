package com.spring.knowhub.infrastructure.mappers.media;

import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.infrastructure.entities.media.MediaEntity;
import com.spring.knowhub.infrastructure.exceptions.media.MediaMapperException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediaMapper {

    private final ModelMapperConfiguration modelMapper;
    private TypeMap<Media, MediaEntity> fromDomainToEntityTypeMap;
    private TypeMap<MediaEntity, Media> fromEntityToDomainTypeMap;

    public MediaEntity fromDomainToEntity(Media media) {
        if (media == null) {
            throw MediaMapperException.fromDomainToEntityFailed("Đối tượng Media là null");
        }
        try {
            if (fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.modelMapper().createTypeMap(Media.class, MediaEntity.class);
                fromDomainToEntityTypeMap.implicitMappings();
            }
            MediaEntity entity = fromDomainToEntityTypeMap.map(media);
            return entity;
        } catch (Exception e) {
            throw MediaMapperException.fromDomainToEntityFailed(e.getMessage());
        }
    }

    public Media fromEntityToDomain(MediaEntity entity) {
        if (entity == null) {
            throw MediaMapperException.fromEntityToDomainFailed("Đối tượng MediaEntity là null");
        }
        try {
            if (fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.modelMapper().createTypeMap(MediaEntity.class, Media.class);
                fromEntityToDomainTypeMap.implicitMappings();
            }
            return fromEntityToDomainTypeMap.map(entity);
        } catch (Exception e) {
            throw MediaMapperException.fromEntityToDomainFailed(e.getMessage());
        }
    }
}
