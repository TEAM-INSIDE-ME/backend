package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.user.InitUserRequest;
import com.insideme.insidemebackend.dto.user.UserInfoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    void updateUserFromDto(UserInfoRequest dto, @MappingTarget User entity);
    void initUserFromDto(InitUserRequest dto, @MappingTarget User entity);

}
