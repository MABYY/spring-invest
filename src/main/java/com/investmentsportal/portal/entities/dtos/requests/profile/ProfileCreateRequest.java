package com.investmentsportal.portal.entities.dtos.requests.profile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProfileCreateRequest {

    @NotNull(message = "First name is required" )
    @Size(min = 2 , message = "Min length 2 characters " )
    private String first_name;

    @NotNull(message = "Last name is required" )
    @Size(min = 2 , message = "Min length 2 characters " )
    private String last_name;

    @NotNull(message = "Company name is required" )
    @Size(min = 2 , message = "Min length 2 characters " )
    private String company;
}
