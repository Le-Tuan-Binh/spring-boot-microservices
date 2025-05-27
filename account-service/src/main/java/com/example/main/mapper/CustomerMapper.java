package com.example.main.mapper;

import com.example.main.dto.external.CustomerDTO;
import com.example.main.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDto(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);

}
