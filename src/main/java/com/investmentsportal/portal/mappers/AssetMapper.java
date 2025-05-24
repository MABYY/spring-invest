package com.investmentsportal.portal.mappers;

import com.investmentsportal.portal.entities.Asset;
import com.investmentsportal.portal.entities.dtos.AssetDto;
import com.investmentsportal.portal.entities.dtos.requests.AssetRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AssetMapper {

    // @Mapping(target = "type", source = "type")
    AssetDto toDto(Asset asset);

    Asset toEntity(AssetRequest asset);

    @Mapping(target = "id", ignore = true)
    void update(AssetRequest request, @MappingTarget Asset asset);

}
