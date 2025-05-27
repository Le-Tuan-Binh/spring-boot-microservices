package com.example.main.mapper;

import com.example.main.dto.external.AccountDTO;
import com.example.main.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO toDto(Account account);

    Account toEntity(AccountDTO accountDTO);

}
