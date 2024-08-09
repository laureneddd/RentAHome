package com.rentahome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerMailDTO {

    private Integer ownerMailId;

    private Integer reservationId;

    private Integer ownerId;
}