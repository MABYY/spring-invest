package com.investmentsportal.portal.entities.dtos;

import com.investmentsportal.portal.validation.Lowercase;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.pl.NIP;

@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    @NotNull(message = "Email is required" )
    @Email(message = "Provide valid email" )
    @Lowercase( message = "Email much be in lowercase")
    private String email;

    @NotNull(message = "Password is required" )
    @Size(min = 8 , max = 16 , message = "Min length 8 - Max length 16 characters" )
    private String password;
}
