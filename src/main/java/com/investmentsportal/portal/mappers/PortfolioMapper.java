package com.investmentsportal.portal.mappers;

import com.investmentsportal.portal.entities.Portfolio;
import com.investmentsportal.portal.entities.dtos.PortfDto;
import com.investmentsportal.portal.entities.dtos.PortfolioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {
    @Mapping(target = "assets", ignore = true)
    PortfolioDto toDto(Portfolio portfolio);

//    Portfolio toEntity(PortfolioRequest request);

//    @Mapping(target = "id", ignore = true)
//    void update(PortfolioRequest request, @MappingTarget Portfolio portfolio);


}
