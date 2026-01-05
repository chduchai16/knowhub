package com.spring.knowhub.infrastructure.mappers.media;

import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.infrastructure.entities.media.MediaEntity;
import com.spring.knowhub.infrastructure.exceptions.media.MediaMapperException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediaMapper {

    private final ModelMapper modelMapper ;
    private TypeMap<MediaEntity , Media> fromEntityToDomainTypeMap ;
    private TypeMap<Media , MediaEntity> fromDomainToEntityTypeMap ;

    public Media fromEntityToDomain(MediaEntity entity) {
        try {
            if (entity == null) {
                throw MediaMapperException.fromEntityToDomainFailed("Đối tượng MediaEntity truyền vào là null");
            }
            if(fromEntityToDomainTypeMap == null) {
                fromEntityToDomainTypeMap = modelMapper.createTypeMap(MediaEntity.class , Media.class) ;
                fromEntityToDomainTypeMap.implicitMappings();
            }
            return fromEntityToDomainTypeMap.map(entity);
        } catch (MediaMapperException ex) {
            throw ex;
        } catch (Exception e) {
            throw MediaMapperException.fromEntityToDomainFailed(e.getMessage());
        }
    }

    public MediaEntity fromDomainToEntity(Media media) {
        try {
            if (media == null) {
                throw MediaMapperException.fromDomainToEntityFailed("Đối tượng Media truyền vào là null");
            }
            if(fromDomainToEntityTypeMap == null) {
                fromDomainToEntityTypeMap = modelMapper.createTypeMap(Media.class , MediaEntity.class) ;
                fromDomainToEntityTypeMap.implicitMappings();
            }
            return fromDomainToEntityTypeMap.map(media);
        } catch (MediaMapperException ex) {
            throw ex;
        } catch (Exception e) {
            throw MediaMapperException.fromDomainToEntityFailed(e.getMessage());
        }

    }
}
