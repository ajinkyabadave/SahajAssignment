package utils;

public class Constants {
    public static final Double MAX_DEPOSIT_AMT = 50000.0;
    public static final Double MIN_DEPOSIT_AMT = 500.0;
    public static final Double MAX_WITHDRAW_AMT = 25000.0;
    public static final Double MIN_WITHDRAW_AMT = 1000.0;
    public static final Integer MAX_DEPOSITS_PER_DAY = 3;
    public static final Integer MAX_WITHDRAWALS_PER_DAY = 3;
    public static final Double MAX_ACCOUNT_BALANCE = 100000.0;
    public static final Double MIN_ACCOUNT_BALANCE = 500.0;

    // Exception messages
    public static final String ACCOUNT_ALREADY_EXISTS_EXCEPTION = "Account already exists";
    public static final String ACCOUNT_DOES_NOT_EXISTS_EXCEPTION = "Account does not exist";

    public static final String MIN_DEPOSIT_AMT_EXCEPTION = "Minimum deposit amount is %1$s";
    public static final String MAX_DEPOSIT_AMT_EXCEPTION = "Maximum deposit amount is %1$s";
    public static final String MAX_NO_DEPOSITS_EXCEPTION = "Only %1$s deposits are allowed per day";
    public static final String MAX_BALANCE_EXCEEDED_EXCEPTION = "Maximum account balance exceeded";


    public static final String INSUFFICIENT_BALANCE_EXCEPTION = "Insufficient balance";
    public static final String MIN_BALANCE_REACHED_EXCEPTION = "Minimum account balance reached";
    public static final String MAX_NO_WITHDRAWALS_EXCEPTION = "Only %1$s Withdraws are allowed per day";
    public static final String MAX_WITHDRAW_AMT_EXCEPTION = "Maximum withdrawal amount is %1$s";
    public static final String MIN_WITHDRAW_AMT_EXCEPTION = "Minimum withdrawal amount is %1$s";



    // Output Strings
    public static final String TRANSFER_SUCCESSFUL_MESSAGE = "Transfer Successful";

}
