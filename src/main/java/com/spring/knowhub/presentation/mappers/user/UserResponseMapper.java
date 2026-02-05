package com.spring.knowhub.presentation.mappers.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.post.PostRepository;
import com.spring.knowhub.domain.repositories.user.UserFollowRepository;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.presentation.exceptions.user.UserResponseMappingException;
import com.spring.knowhub.presentation.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserResponseMapper {
    private final RoleResponseMapper roleResponseMapper;
    private final ModelMapperConfiguration modelMapper;
    private final UserFollowRepository userFollowRepository;
    private final PostRepository postRepository;
    private TypeMap<User, UserResponse> fromUserToUserResponseTypeMap;

    public UserResponse fromUserToUserResponse(User user) {
        try {
            if (user == null) {
                throw UserResponseMappingException.objectNull();
            }
            if (fromUserToUserResponseTypeMap == null) {
                fromUserToUserResponseTypeMap = modelMapper.modelMapper().createTypeMap(User.class, UserResponse.class);
                fromUserToUserResponseTypeMap.addMappings(mapper -> {
                    mapper.skip(UserResponse::setRoleId);
                    mapper.skip(UserResponse::setRoleName);
                });
                fromUserToUserResponseTypeMap.implicitMappings();
            }
            UserResponse response = fromUserToUserResponseTypeMap.map(user);
            if (user.getRole() != null) {
                response.setRoleId(user.getRole().getId());
                response.setRoleName(user.getRole().getName());
            }

            // lấy dữ liệu follow và post
            response.setFollowerQuantity(userFollowRepository.countByUserId(user.getId()));
            response.setFollowingQuantity(userFollowRepository.countByFollowerId(user.getId()));
            response.setPostQuantity(postRepository.countByUserId(user.getId()));

            // check follow
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
                Long currentUserId = userDetails.getUserId();
                response.setIsFollowing(userFollowRepository.existsByUserIdAndFollowerId(user.getId(), currentUserId));
            } else {
                response.setIsFollowing(false);
            }

            return response;
        } catch (Exception exception) {
            throw UserResponseMappingException.errorMapping(exception);
        }
    }
}
