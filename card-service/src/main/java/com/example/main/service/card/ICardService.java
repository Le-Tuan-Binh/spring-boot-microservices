package com.example.main.service.card;

import com.example.main.dto.external.CardDTO;

public interface ICardService {

    void createCard(String mobileNumber);

    CardDTO getCardDetailByMobileNumber(String mobileNumber);

    boolean updateCard(CardDTO cardsDto);

    boolean deleteCard(String mobileNumber);

}