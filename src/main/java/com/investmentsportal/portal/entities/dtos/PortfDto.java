package com.investmentsportal.portal.entities.dtos;

import com.investmentsportal.portal.entities.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.catalina.User;

@AllArgsConstructor
@Data
public class PortfDto {
    private Long id;

    @NotNull(message = "Name is required" )
    private String name;

    private Users users_id ;
}
