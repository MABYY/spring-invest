package com.investmentsportal.portal.entities.dtos.requests.users;

import com.investmentsportal.portal.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRequest {
    private Long id;
    @NotNull(message = "Email is required" )
    @Email(message = "Provide valid email" )
    @Lowercase( message = "Email much be in lowercase")
    private String email;

    @NotNull(message = "Password is required" )
    @Size(min = 8 , max = 16 , message = "Min length 8 - Max length 16 characters" )
    private String password;
}
