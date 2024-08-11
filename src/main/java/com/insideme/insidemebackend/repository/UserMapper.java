package com.insideme.insidemebackend.repository;

import com.insideme.insidemebackend.domain.User;
import com.insideme.insidemebackend.dto.user.UserInfoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void updateUserFromDto(UserInfoRequest dto, @MappingTarget User entity);
}
