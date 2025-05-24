package com.investmentsportal.portal.entities.dtos.requests.users;

import com.investmentsportal.portal.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserUpdateRequest {
    @NotNull(message = "Email is required" )
    @Email(message = "Provide valid email" )
    @Lowercase( message = "Email much be in lowercase")
    private String email;
}
