package com.example.main.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private String name;

    private String email;

    @JsonProperty("mobile_number")
    private String mobileNumber;

}
