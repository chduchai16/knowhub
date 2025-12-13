package com.spring.knowhub.infrastructure.mappers.post;

import com.spring.knowhub.domain.models.post.PostLike;
import com.spring.knowhub.infrastructure.entities.post.PostLikeEntity;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostLikeMapper {
    private final ModelMapper modelMapper ;
    private final UserMapper userMapper ;
    private final PostMapper postMapper ;
    private TypeMap<PostLike , PostLikeEntity> fromDomainToEntityTypeMap ;
    private TypeMap<PostLikeEntity , PostLike> fromEntityToDomainTypeMap ;

    public PostLikeEntity fromDomainToEntity(PostLike postLike){
        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = modelMapper.createTypeMap(PostLike.class, PostLikeEntity.class);
            fromDomainToEntityTypeMap.getMappings().clear();
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(PostLikeEntity::setPost);
                mapper.skip(PostLikeEntity::setUser);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }
        PostLikeEntity postLikeEntity = fromDomainToEntityTypeMap.map(postLike);

        // map user
        if(postLike.getUser() != null){
            postLikeEntity.setUser(this.userMapper.fromDomainToEntity(postLike.getUser()));
        }

        // map post
        if(postLike.getPost() != null){
            postLikeEntity.setPost(this.postMapper.fromDomainToEntity(postLike.getPost()));
        }
        return postLikeEntity;
    }

    public PostLike fromEntityToDomain(PostLikeEntity postLikeEntity){
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = modelMapper.createTypeMap(PostLikeEntity.class, PostLike.class);
            fromEntityToDomainTypeMap.getMappings().clear();
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(PostLike::setPost);
                mapper.skip(PostLike::setUser);
            });
            fromEntityToDomainTypeMap.implicitMappings();
        }
        PostLike postLike = fromEntityToDomainTypeMap.map(postLikeEntity);

        // map user
        if(postLikeEntity.getUser() != null){
            postLike.setUser(this.userMapper.fromEntityToDomain(postLikeEntity.getUser()));
        }

        // map post
        if(postLikeEntity.getPost() != null){
            postLike.setPost(this.postMapper.fromEntityToDomain(postLikeEntity.getPost()));
        }
        return postLike;
    }
}
