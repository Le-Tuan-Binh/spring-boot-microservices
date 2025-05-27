package com.example.main.mapper;

import com.example.main.dto.external.CustomerDTO;
import com.example.main.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);

}
