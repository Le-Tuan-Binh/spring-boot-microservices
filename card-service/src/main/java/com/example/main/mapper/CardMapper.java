package com.example.main.mapper;

import com.example.main.dto.external.CardDTO;
import com.example.main.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDTO toDto(Card account);

    Card toEntity(CardDTO accountDTO);

    void updateEntityFromDto(CardDTO dto, @MappingTarget Card entity);


}
