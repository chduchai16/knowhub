package com.spring.knowhub.application.commands.post.postlike;

import org.springframework.stereotype.Component;
import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.post.postlike.CreatePostLikeValidator;
import com.spring.knowhub.domain.exceptions.post.post.PostNotFoundException;
import com.spring.knowhub.domain.exceptions.post.postlike.PostAlreadyLikedException;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.post.PostLike;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.post.PostLikeRepository;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePostLikeCommandHandler implements CommandHandler<CreatePostLikeCommand, Long> {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreatePostLikeCommand;
    }

    @Override
    public Long handle(CreatePostLikeCommand command) {
        CreatePostLikeValidator.validate(command);

        // kiểm tra xem đã like chưa
        if (postLikeRepository.findByPostIdAndUserId(command.getPostId(), command.getUserId()).isPresent()) {
            throw PostAlreadyLikedException.of(command.getPostId(), command.getUserId());
        }

        Post existingPost = postRepository.findById(command.getPostId())
                .orElseThrow(() -> PostNotFoundException.withId(command.getPostId()));
        User existingUser = userRepository.findById(command.getUserId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getUserId()));

        PostLike postLike = new PostLike(
                null,
                existingPost,
                existingUser);
        PostLike savedPostLike = postLikeRepository.save(postLike);
        return savedPostLike.getId();
    }
}