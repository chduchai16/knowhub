package com.spring.knowhub.application.commands.post.post;

import com.spring.knowhub.application.buses.CommandHandler;
import com.spring.knowhub.application.validators.post.post.CreatePostValidator;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.enums.post.PostStatus;
import com.spring.knowhub.domain.enums.post.Privacy;
import com.spring.knowhub.domain.exceptions.post.post.InvalidPostException;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class CreatePostCommandHandler implements CommandHandler<CreatePostCommand, Long> {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;

    @Override
    public boolean supports(Object command) {
        return command instanceof CreatePostCommand;
    }

    @Override
    public Long handle(CreatePostCommand command) {
        CreatePostValidator.validate(command);
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> UserNotFoundException.byId(command.getUserId()));
        List<Tag> tags = tagRepository.findByIds(command.getTagIds());
        List<Media> medias = mediaRepository.findAllById(command.getMediaIds());

        String privacyUpper = command.getPrivacy().toUpperCase();
        if (!Privacy.contains(privacyUpper)) {
            throw InvalidPostException.privacyInvalid();
        }

        Post post = new Post(
                null,
                user,
                command.getContent(),
                Privacy.valueOf(command.getPrivacy().toUpperCase()),
                PostStatus.valueOf(command.getStatus().toUpperCase()),
                medias,
                new HashSet<>());

        tags.forEach(tag -> {
            PostTag postTag = new PostTag(null, post, tag);
            post.getPostTags().add(postTag);
        });

        Post savedPost = postRepository.save(post);

        medias.forEach(media -> {
            media.setOwnerType(OwnerType.POST);
            media.setOwnerId(savedPost.getId());
        });

        mediaRepository.saveAll(medias);
        return savedPost.getId();
    }
}
