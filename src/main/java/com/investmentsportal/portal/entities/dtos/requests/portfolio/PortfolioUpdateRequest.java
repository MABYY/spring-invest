package com.investmentsportal.portal.entities.dtos.requests.portfolio;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
public class PortfolioUpdateRequest {
    @NotNull(message = "Provide valid id" )
    private Long id;
    @NotNull(message = "Provide valid name" )
    private String name;
    @NotNull(message = "Provide list of tickers or add assets first" )
    private Set<String> assets = new HashSet<>();

}
