package com.spring.knowhub.infrastructure.mappers.boomark;

import com.spring.knowhub.domain.models.bookmark.Bookmark;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.infrastructure.entities.bookmark.BookmarkEntity;
import com.spring.knowhub.infrastructure.entities.post.PostEntity;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.mappers.post.PostMapper;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkMapper {

    private final ModelMapper mapper ;
    private final PostMapper postMapper ;
    private final UserMapper userMapper ;
    private TypeMap<Bookmark , BookmarkEntity> fromDomainToEntityTypeMap ;
    private TypeMap<BookmarkEntity , Bookmark> fromEntityToDomainTypeMap ;

    public BookmarkEntity fromDomainToEntity(Bookmark bookmark) {
        if (bookmark == null) return null;
        if (fromDomainToEntityTypeMap == null) {
            fromDomainToEntityTypeMap = mapper.createTypeMap(Bookmark.class, BookmarkEntity.class);
            fromDomainToEntityTypeMap.getMappings().clear();
            fromDomainToEntityTypeMap.addMappings(mapper -> {
                mapper.skip(BookmarkEntity::setUser);
                mapper.skip(BookmarkEntity::setPost);
            });
            fromDomainToEntityTypeMap.implicitMappings();
        }

        BookmarkEntity bookmarkEntity = fromDomainToEntityTypeMap.map(bookmark);

        // map user
        if(bookmark.getPost() != null ) {
            UserEntity userEntity = userMapper.fromDomainToEntity(bookmark.getUser());
            bookmarkEntity.setUser(userEntity);
        }

        // map post
        if(bookmark.getUser() != null ) {
            PostEntity postEntity = postMapper.fromDomainToEntity(bookmark.getPost());
            bookmarkEntity.setPost(postEntity);
        }

        return bookmarkEntity ;
    }

    public Bookmark fromEntityToDomain(BookmarkEntity bookmarkEntity) {
        if (bookmarkEntity == null) return null;
        if (fromEntityToDomainTypeMap == null) {
            fromEntityToDomainTypeMap = mapper.createTypeMap(BookmarkEntity.class, Bookmark.class);
            fromEntityToDomainTypeMap.getMappings().clear();
            fromEntityToDomainTypeMap.addMappings(mapper -> {
                mapper.skip(Bookmark::setUser);
                mapper.skip(Bookmark::setPost);
            }) ;
            fromEntityToDomainTypeMap.implicitMappings();
        }

        Bookmark bookmark = fromEntityToDomainTypeMap.map(bookmarkEntity);

        // map user
        if(bookmarkEntity.getUser() != null ) {
            User user = userMapper.fromEntityToDomain(bookmarkEntity.getUser());
            bookmark.setUser(user);
        }

        // map post
        if(bookmarkEntity.getPost() != null ) {
            Post post = postMapper.fromEntityToDomain(bookmarkEntity.getPost());
            bookmark.setPost(post);
        }

        return bookmark ;
    }
}
