package com.rentahome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTypeDTO {
    private Integer propertyTypeId;
    private String propertyTypeName;
}
