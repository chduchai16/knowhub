package com.spring.knowhub.application.queries.user.user;

import com.spring.knowhub.application.buses.QueryHandler;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.domain.specifications.AlwaysTrueSpecification;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.domain.specifications.user.UserHasNameSpec;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class GetUsersByNameQueryHandler implements QueryHandler<GetUsersByNameQuery, List<User>> {

    private final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;
    private final PostRepository postRepository;

    @Override
    public boolean supports(Object query) {
        return query instanceof GetUsersByNameQuery;
    }

    @Override
    public List<User> handle(GetUsersByNameQuery query) {
        Specification<User> specification = new AlwaysTrueSpecification<>();
        specification = specification.and(new UserHasNameSpec(query.getName()));

        List<User> users = userRepository.findUsersPaged(specification, query.getPageable()).getContent();

        // Get current user for follow status check
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = (authentication != null
                && authentication.getPrincipal() instanceof CustomUserDetails userDetails)
                        ? userDetails.getUserId()
                        : null;

        return users.stream().map(user -> enrichUser(user, currentUserId)).collect(Collectors.toList());
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
}
