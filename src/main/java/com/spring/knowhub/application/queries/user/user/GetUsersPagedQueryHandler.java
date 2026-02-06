package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.domain.specifications.user.UserSpecifications;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUsersPagedQueryHandler implements QueryHandler<GetUsersPagedQuery, Page<User>> {

    private final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;
    private final PostRepository postRepository;

    @Override
    public Page<User> handle(GetUsersPagedQuery query) {
        Specification<User> spec = new AlwaysTrueSpecification<>();
        spec = spec.and(UserSpecifications.hasRole(query.getRoleId()));
        spec = spec.and(UserSpecifications.hasStatus(query.getStatus()));
        spec = spec.and(UserSpecifications.hasKeyword(query.getKeyword()));

        Page<User> users = userRepository.findUsersPaged(spec, query.getPageable());

        // Get current user for follow status check
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = (authentication != null
                && authentication.getPrincipal() instanceof CustomUserDetails userDetails)
                        ? userDetails.getUserId()
                        : null;

        return users.map(user -> enrichUser(user, currentUserId));
    }

    private User enrichUser(User user, Long currentUserId) {
        user.setFollowerQuantity(userFollowRepository.countByUserId(user.getId()));
        user.setFollowingQuantity(userFollowRepository.countByFollowerId(user.getId()));
        user.setPostQuantity(postRepository.countByUserId(user.getId()));

        if (currentUserId != null) {
            user.setIsFollowing(userFollowRepository.existsByUserIdAndFollowerId(user.getId(), currentUserId));
        } else {
            user.setIsFollowing(false);
        }
        return user;
    }

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUsersPagedQuery;
    }
}
