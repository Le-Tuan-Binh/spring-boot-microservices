package com.example.main.mapper;

import com.example.main.dto.external.LoanDTO;
import com.example.main.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanDTO toDto(Loan loan);

    Loan toEntity(LoanDTO loanDTO);

    void updateEntityFromDto(LoanDTO dto, @MappingTarget Loan entity);

}
