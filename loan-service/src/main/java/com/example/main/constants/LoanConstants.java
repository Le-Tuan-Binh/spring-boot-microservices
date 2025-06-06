package com.example.main.constants;

public final class LoanConstants {

    public static final String LOAN_TYPE_HOME = "Home Loan";

    public static final String LOAN_TYPE_PERSONAL = "Personal Loan";

    public static final String LOAN_TYPE_AUTO = "Auto Loan";

    public static final int NEW_LOAN_LIMIT = 1_00_000;

    public static final String LOAN_PROVIDER_ADDRESS = "Wall Street, New York";

    public static final int STATUS_CREATED = 201;

    public static final int STATUS_OK = 200;

    public static final int STATUS_EXPECTATION_FAILED = 417;

    public static final String MESSAGE_LOAN_CREATED_SUCCESS = "Loan application created successfully";

    public static final String MESSAGE_LOAN_UPDATE_SUCCESS = "Loan details updated successfully";

    public static final String MESSAGE_UPDATE_FAILED = "Update operation failed. Please try again or contact support";

    public static final String MESSAGE_DELETE_FAILED = "Delete operation failed. Please try again or contact support";

    private LoanConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
