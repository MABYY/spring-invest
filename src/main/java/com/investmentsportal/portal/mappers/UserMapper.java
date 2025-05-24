package com.investmentsportal.portal.mappers;

import com.investmentsportal.portal.entities.Users;
import com.investmentsportal.portal.entities.dtos.UserDto;
import com.investmentsportal.portal.entities.dtos.requests.users.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users user);
    Users toEntity(UserRequest request);

    @Mapping(target = "id", ignore = true )
    @Mapping(target = "email", ignore = true )
    Users update(UserRequest request, @MappingTarget Users user);
}
