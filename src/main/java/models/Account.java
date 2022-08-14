package models;

public class Account {
    public Long getAccountNumber() {
        return accountNumber;
    }

    private Long accountNumber;
    private final String accountHolderName;
    private Double accountBalance;
    private static final Double initialAccountBalance = 0.0;

    public Account(Long accountNumber,String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountBalance = initialAccountBalance;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getAccountHolderName() { return accountHolderName; }
}
