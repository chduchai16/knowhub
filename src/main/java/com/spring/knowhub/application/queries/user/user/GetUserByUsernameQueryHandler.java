package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.validators.user.user.GetUserByUsernameValidator;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;
import org.springframework.stereotype.Component;
import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetUserByUsernameQueryHandler implements QueryHandler<GetUserByUsernameQuery, User> {

    private final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;
    private final PostRepository postRepository;

    @Override
    public User handle(GetUserByUsernameQuery query) {
        GetUserByUsernameValidator.validate(query);
        User user = userRepository.findByUsername(query.getUsername())
                .orElseThrow(() -> UserNotFoundException.byUsername(query.getUsername()));

        // Enrich with counts
        user.setFollowerQuantity(userFollowRepository.countByUserId(user.getId()));
        user.setFollowingQuantity(userFollowRepository.countByFollowerId(user.getId()));
        user.setPostQuantity(postRepository.countByUserId(user.getId()));

        // Enrich with follow status
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            user.setIsFollowing(
                    userFollowRepository.existsByUserIdAndFollowerId(user.getId(), userDetails.getUserId()));
        } else {
            user.setIsFollowing(false);
        }

        return user;
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUserByUsernameQuery;
    }
}
