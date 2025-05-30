package com.example.main.service.card;

import com.example.main.constants.CardConstants;
import com.example.main.dto.external.CardDTO;
import com.example.main.entity.Card;
import com.example.main.exception.CardAlreadyExistsException;
import com.example.main.exception.ResourceNotFoundException;
import com.example.main.mapper.CardMapper;
import com.example.main.repository.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class CardServiceImpl implements ICardService {

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    private final Random random = new Random();

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }


    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CARD_TYPE_CREDIT);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }


    @Override
    public CardDTO getCardDetailByMobileNumber(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", mobileNumber));
        CardDTO cardDTO = cardMapper.toDto(cards);
        return cardDTO;
    }

    @Override
    public boolean updateCard(CardDTO cardDTO) {
        Card cards = cardRepository.findByCardNumber(cardDTO.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "card number", cardDTO.getCardNumber()));
        cardMapper.updateEntityFromDto(cardDTO, cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", mobileNumber));
        cardRepository.deleteById(cards.getCardId());
        return true;
    }

}
