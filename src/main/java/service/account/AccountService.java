package service.account;

public interface AccountService {
    Long createAccount(String accountName);
    Double balance(Long accountNumber);
}
