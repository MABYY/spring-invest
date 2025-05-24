package com.investmentsportal.portal.entities.dtos;
import com.investmentsportal.portal.entities.enums.AssetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AssetDto {
    private Long id;

    @NotNull(message = "Field is required" )
    @Size(min = 1 , message = "Min length required 2 characters" )
    private String isin;

    @NotNull(message = "Field is required" )
    @Size(min = 1 , message = "Min length required" )
    private String ticker;

    @NotNull(message = "Field is required" )
    @Size(min = 2 , message = "Min length required 2 characters" )
    private String name;

    @NotNull(message = "Field is required" )
    private AssetType type;

    private String description;
}
