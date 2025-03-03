package com.tien4112004.auth_service.mapper;

import com.tien4112004.auth_service.dto.user.CreateUserDto;
import com.tien4112004.auth_service.dto.user.UpdateUserDto;
import com.tien4112004.auth_service.dto.user.UserDto;
import com.tien4112004.auth_service.dto.user.UserMinimalDto;
import com.tien4112004.auth_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    UserMinimalDto toInfoDto(User user);

    User toEntity(UserDto userDto);

    User toEntity(UserMinimalDto userMinimalDto);

    @Mapping(target = "id", ignore = true)
    User toEntity(CreateUserDto createUserDto);

    User toEntity(UpdateUserDto updateUserDto);
}