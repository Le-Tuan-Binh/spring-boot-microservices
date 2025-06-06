package com.example.main.mapper;

import com.example.main.dto.external.CustomerDTO;
import com.example.main.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);

    void updateEntityFromDto(
        CustomerDTO dto,
        @MappingTarget
        Customer entity);

}
