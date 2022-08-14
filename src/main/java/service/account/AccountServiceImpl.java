package service.account;


import com.google.inject.Inject;
import dao.InMemoryDataBase;
import exceptions.AccountException;

public class
AccountServiceImpl implements AccountService{

    @Inject
    InMemoryDataBase inMemoryDataBase;

    @Override
    public Long createAccount(String accountName){
        validateAccountCreation(accountName);
        return inMemoryDataBase.createAccount(accountName);
    }

    @Override
    public Double balance(Long accountNumber){
        validateAccount(accountNumber);
        return inMemoryDataBase.getAccountBalance(accountNumber);
    }

    private void validateAccountCreation(String accountName){
        if(inMemoryDataBase.doesAccountExist(accountName))
            throw new AccountException.AccountAlreadyExistsException();
    }

    private void validateAccount(Long accountNumber){
        if(!inMemoryDataBase.doesAccountExist(accountNumber))
            throw new AccountException.AccountDoesNotExist();
    }

}
