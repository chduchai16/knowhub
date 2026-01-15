package com.spring.knowhub.application.commands.post.post;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.post.post.UpdatePostValidator;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.enums.post.PostStatus;
import com.spring.knowhub.domain.enums.post.Privacy;
import com.spring.knowhub.domain.exceptions.post.post.InvalidPostException;
import com.spring.knowhub.domain.exceptions.post.post.PostNotFoundException;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.media.Media;
import com.spring.knowhub.domain.models.post.Post;
import com.spring.knowhub.domain.models.post.PostTag;
import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.media.MediaRepository;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.post.TagRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.exceptions.post.post.PostRepositoryException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class UpdatePostCommandHandler implements CommandHandler<UpdatePostCommand, Long> {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof UpdatePostCommand;
    }

    @Override
    public Long handle(UpdatePostCommand command) {
        UpdatePostValidator.validate(command);
        Post existingPost = postRepository.findById(command.getId())
                .orElseThrow(() -> PostNotFoundException.withId(command.getId()));
        List<Tag> tags = tagRepository.findByIds(command.getTagIds());

        List<Media> medias = mediaRepository.findAllById(command.getMediaIds());
        User existingUser = userRepository.findById(command.getUserId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getUserId()));
        if (!Privacy.contains(command.getPrivacy().toUpperCase())) {
            throw InvalidPostException.privacyInvalid();
        }

        Set<PostTag> newPostTags = new HashSet<>();
        for (Tag tag : tags) {
            PostTag postTag = new PostTag();
            postTag.setPost(existingPost);
            postTag.setTag(tag);
            newPostTags.add(postTag);
        }

        existingPost.setContent(command.getContent());
        existingPost.setPrivacy(Privacy.valueOf(command.getPrivacy().toUpperCase()));
        existingPost.setMedia(medias);
        existingPost.setStatus(PostStatus.valueOf(command.getStatus().toUpperCase()));
        existingPost.setUser(existingUser);
        existingPost.setPostTags(newPostTags);
        Post savedPost = postRepository.save(existingPost)
                .orElseThrow(() -> PostRepositoryException.saveFailed("Không thể cập nhật bài viết"));
        medias.forEach(media -> {
            media.setOwnerId(savedPost.getId());
            media.setOwnerType(OwnerType.POST);
        });
        mediaRepository.saveAll(medias);
        return savedPost.getId();
    }
}
