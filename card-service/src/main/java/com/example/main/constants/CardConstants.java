package com.example.main.constants;

public final class CardConstants {

    public static final String CARD_TYPE_CREDIT = "Credit";

    public static final int NEW_CARD_LIMIT = 1_00_000;

    public static final String CARD_TYPE_DEBIT = "Debit";

    public static final int STATUS_CREATED = 201;

    public static final int STATUS_OK = 200;

    public static final int STATUS_EXPECTATION_FAILED = 417;

    public static final String MESSAGE_CARD_CREATED_SUCCESS = "Card created successfully";

    public static final String MESSAGE_CARD_UPDATE_SUCCESS = "Card updated successfully";

    public static final String MESSAGE_CARD_UPDATE_FAILED = "Card update failed. Please try again or contact support";

    public static final String MESSAGE_CARD_DELETE_FAILED = "Card delete failed. Please try again or contact support";

    private CardConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
