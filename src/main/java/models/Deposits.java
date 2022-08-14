package models;

import java.time.LocalDate;

public class Deposits {
    Long accountNumber;
    LocalDate transactionDate;
    Double depositAmount;

    public Deposits( Long accountNumber, LocalDate transactionDate, Double depositAmount) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.depositAmount = depositAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
