package com.example.main.dto.response;

import com.example.main.dto.external.AccountDTO;
import com.example.main.dto.external.CustomerDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Information of customer and account details")
public class CustomerAccountDTO {

    @Valid
    @Schema(description = "Customer personal information")
    private CustomerDTO customer;

    @Valid
    @Schema(description = "Customer's bank account details")
    private AccountDTO account;

}
