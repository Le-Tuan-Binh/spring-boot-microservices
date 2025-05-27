package com.example.main.constants;

public final class AccountConstants {

    public static final String ACCOUNT_TYPE_SAVINGS = "Savings";

    public static final String ACCOUNT_ADDRESS = "Main Street, New York";

    public static final int STATUS_CREATED = 201;

    public static final int STATUS_OK = 200;

    public static final int STATUS_EXPECTATION_FAILED = 417;

    public static final String MESSAGE_ACCOUNT_CREATED_SUCCESS = "Account created successfully";

    public static final String MESSAGE_REQUEST_PROCESSED_SUCCESSFULLY = "Request processed successfully";

    public static final String MESSAGE_UPDATE_FAILED = "Update operation failed. Please try again or contact team";

    public static final String MESSAGE_DELETE_FAILED = "Delete operation failed. Please try again or contact team";

    private AccountConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
