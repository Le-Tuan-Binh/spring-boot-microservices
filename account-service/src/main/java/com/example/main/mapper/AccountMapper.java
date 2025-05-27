package com.example.main.mapper;

import com.example.main.dto.external.AccountDTO;
import com.example.main.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO toDto(Account account);

    Account toEntity(AccountDTO accountDTO);

    void updateEntityFromDto(AccountDTO dto, @MappingTarget Account entity);


}
