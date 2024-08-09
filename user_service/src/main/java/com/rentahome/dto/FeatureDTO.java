package com.rentahome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDTO {

    private Integer featureId;

    private String featureName;
    private String featureDescription;


}