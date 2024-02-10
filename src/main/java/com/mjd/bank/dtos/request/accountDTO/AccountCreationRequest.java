package com.mjd.bank.dtos.request.accountDTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountCreationRequest {
    private Long ownerId;
    private String type;
}
