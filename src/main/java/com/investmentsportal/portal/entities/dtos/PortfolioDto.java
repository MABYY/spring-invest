package com.investmentsportal.portal.entities.dtos;

import com.investmentsportal.portal.entities.Asset;
import com.investmentsportal.portal.entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Data
public class PortfolioDto {
    private Long id;
    private String name;
    private Set<String> assets = new HashSet<>();
}
