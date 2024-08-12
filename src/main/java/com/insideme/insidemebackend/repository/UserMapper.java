package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.user.InitUserRequest;
import com.insideme.insidemebackend.dto.user.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    void initUserFromDto(InitUserRequest dto, @MappingTarget User entity);
    void updateUserFromDto(UserInfo dto, @MappingTarget User entity);
    UserInfo initUserRequestToUserResponse(InitUserRequest dto);

}
