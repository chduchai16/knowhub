package com.spring.knowhub.presentation.mappers.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.infrastructure.configurations.ModelMapperConfiguration;
import com.spring.knowhub.presentation.exceptions.user.UserResponseMappingException;
import com.spring.knowhub.presentation.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserResponseMapper {
    private final RoleResponseMapper roleResponseMapper;
    private final ModelMapperConfiguration modelMapper ;
    private TypeMap<User , UserResponse> fromUserToUserResponseTypeMap ;

    public UserResponse fromUserToUserResponse(User user){
        try {
            if(user == null) {
                throw UserResponseMappingException.objectNull();
            }
            if(fromUserToUserResponseTypeMap == null) {
                fromUserToUserResponseTypeMap = modelMapper.modelMapper().createTypeMap(User.class , UserResponse.class);
                fromUserToUserResponseTypeMap.addMappings(mapper -> {
                    mapper.skip(UserResponse::setRoleId);
                    mapper.skip(UserResponse::setRoleName);
                });
                fromUserToUserResponseTypeMap.implicitMappings();
            }
            UserResponse response = fromUserToUserResponseTypeMap.map(user);
            if(user.getRole() != null) {
                response.setRoleId(user.getRole().getId());
                response.setRoleName(user.getRole().getName());
            }
            return response ;
        } catch (Exception exception) {
            throw UserResponseMappingException.errorMapping(exception) ;
        }
    }
}
