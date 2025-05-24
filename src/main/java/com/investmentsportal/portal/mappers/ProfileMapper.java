package com.investmentsportal.portal.mappers;

import com.investmentsportal.portal.entities.Profile;
import com.investmentsportal.portal.entities.dtos.ProfileDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto toDto(Profile profile);
    Profile toEntity( ProfileDto dto);
//    @Mapping(target = "id", ignore = true )
//    @Mapping(target = "email", ignore = true )
//    Users update(UserRequest request, @MappingTarget Users user);
}
