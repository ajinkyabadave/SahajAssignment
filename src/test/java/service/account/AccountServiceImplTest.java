package service.account;

import dao.InMemoryDataBase;
import exceptions.AccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    InMemoryDataBase inMemoryDataBase;
    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    public void verifyCreateAccount_withValidInput_returnsAccountNumber(){
        String accountName = "TestAccountName";
        Long accountNumber = 1000L;
        when(inMemoryDataBase.doesAccountExist(accountName)).thenReturn(false);
        when(inMemoryDataBase.createAccount(accountName)).thenReturn(accountNumber);
        Long actualResponse= accountService.createAccount(accountName);
        assertEquals(accountNumber,actualResponse);
    }

    @Test
    public void verifyCreateAccount_withExistingAccountInput_throwsError(){
        String accountName = "TestAccountName";
        when(inMemoryDataBase.doesAccountExist(accountName)).thenReturn(true);
        Assertions.assertThrows(AccountException.AccountAlreadyExistsException.class,
                ()->{
                    accountService.createAccount(accountName);
                });
    }

    @Test
    public void verifyBalance_withValidAccountNumber_returnsAccountBalance(){
        Long accountNumber = 1000L;
        Double expectedAccountBalance = 2000.0;
        when((inMemoryDataBase.doesAccountExist(accountNumber))).thenReturn(true);
        when(inMemoryDataBase.getAccountBalance(accountNumber)).thenReturn(expectedAccountBalance);
        Double actualAccountBalance = accountService.balance(accountNumber);
        assertEquals(expectedAccountBalance,actualAccountBalance);

    }

    @Test
    public void verifyBalance_withInvalidAccountNumber_throwsAccountDoesNotExistError(){
        Long accountNumber = 1000L;
        when((inMemoryDataBase.doesAccountExist(accountNumber))).thenReturn(false);
        Assertions.assertThrows(AccountException.AccountDoesNotExist.class,
                ()->{
                    accountService.balance(accountNumber);
                });

    }
}