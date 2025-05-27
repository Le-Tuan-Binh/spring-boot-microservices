package com.example.main.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountDTO {

    @JsonProperty("account_number")
    private Long accountNumber;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("branch_address")
    private String branchAddress;

}
