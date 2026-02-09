package com.spring.knowhub.presentation.mappers.comment;

import com.spring.knowhub.domain.models.comment.Comment;
import com.spring.knowhub.domain.repositories.comment.CommentRepository;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.presentation.exceptions.comment.CommentResponseMappingException;
import com.spring.knowhub.presentation.response.comment.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentResponseMapper {
    private final ModelMapperConfiguration modelMapper;
    private final CommentRepository commentRepository;
    private TypeMap<Comment, CommentResponse> fromCommentToCommentResponseTypeMap;

    public CommentResponse fromCommentToCommentResponse(Comment comment) {
        try {
            if (comment == null) {
                throw CommentResponseMappingException.objectNull();
            }

            if (fromCommentToCommentResponseTypeMap == null) {
                fromCommentToCommentResponseTypeMap = modelMapper.modelMapper().createTypeMap(Comment.class,
                        CommentResponse.class);
                fromCommentToCommentResponseTypeMap.addMappings(mapper -> {
                    mapper.skip(CommentResponse::setUserId);
                    mapper.skip(CommentResponse::setUsername);
                    mapper.skip(CommentResponse::setUserAvatarUrl);
                    mapper.skip(CommentResponse::setPostId);
                    mapper.skip(CommentResponse::setParentId);
                    mapper.skip(CommentResponse::setReplyQuantity);
                });
                fromCommentToCommentResponseTypeMap.implicitMappings();
            }

            CommentResponse response = fromCommentToCommentResponseTypeMap.map(comment);

            // map user
            if (comment.getUser() != null) {
                response.setUserId(comment.getUser().getId());
                response.setUsername(comment.getUser().getUsername());
                response.setUserAvatarUrl(comment.getUser().getAvatarUrl());
            }

            // map post
            if (comment.getPost() != null) {
                response.setPostId(comment.getPost().getId());
            }

            // map parent
            if (comment.getParent() != null) {
                response.setParentId(comment.getParent().getId());
            }

            // map reply quantity (chỉ cho root comment)
            if (comment.getRootId() == null && comment.getParent() == null) {
                response.setReplyQuantity(commentRepository.countByRootId(comment.getId()));
            } else {
                response.setReplyQuantity(0L);
            }

            return response;
        } catch (Exception exception) {
            throw CommentResponseMappingException.errorMapping(exception);
        }
    }
}
