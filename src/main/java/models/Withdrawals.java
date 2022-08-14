package models;

import java.time.LocalDate;

public class Withdrawals {
    Long accountNumber;
    LocalDate transactionDate;
    Double withdrawalAmount;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public Withdrawals(Long accountNumber, LocalDate transactionDate, Double withdrawalAmount) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.withdrawalAmount = withdrawalAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
