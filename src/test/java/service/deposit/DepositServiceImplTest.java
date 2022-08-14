package service.deposit;


import static org.mockito.Mockito.*;
import dao.InMemoryDataBase;
import exceptions.*;
import exceptions.DepositExceptions;
import models.Deposits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DepositServiceImplTest {
    @Mock
    InMemoryDataBase inMemoryDataBase;
    @InjectMocks
    DepositServiceImpl depositService;


    @Test
    public void verifyDeposit_withInvalidAccount_throwsAccountDoesNotExistError(){
        Long accountNumber = 2000L;
        Double depositAmount = 1000.0;
        mockCreateAccount(accountNumber,false);
        Assertions.assertThrows(AccountException.AccountDoesNotExist.class,
                ()->{
                    depositService.deposit(accountNumber,depositAmount);
                });
    }

    @Test
    public void verifyDeposit_withDepositAmtLessThanMinDepositAmt_throwsMinDepositAmtError(){
        Long accountNumber = 1000L;
        Double depositAmount = 400.0;
        mockCreateAccount(accountNumber,true);
        Assertions.assertThrows(DepositExceptions.MinimumDepositAmountException.class,
                ()->{
                    depositService.deposit(accountNumber,depositAmount);
                });
    }

    @Test
    public void verifyDeposit_withDepositAmtMoreThanMaxDepositAmt_throwsMaxDepositAmtError(){
        Long accountNumber = 1000L;
        Double depositAmount = 60000.0;
        mockCreateAccount(accountNumber,true);
        Assertions.assertThrows(DepositExceptions.MaximumDepositAmountException.class,
                ()->{
                    depositService.deposit(accountNumber,depositAmount);
                });
    }

    @Test
    public void verifyDeposit_withNoOfDepositsMoreThanMaxNoOfDeposits_throwsMaxNoOfDepositsError(){
        Long accountNumber = 1000L;
        Double depositAmount = 20000.0;
        mockCreateAccount(accountNumber,true);
        mockGetAccountDepositsMethod(accountNumber,3L);
        Assertions.assertThrows(DepositExceptions.MaximumNumberOfDepositsException.class,
                ()->{
                    depositService.deposit(accountNumber,depositAmount);
                });
    }
    
    @Test
    public void verifyDeposit_withAccountBalanceMoreThanMaxAccountBalance_throwsMaxAccountBalanceReachedError(){
        Long accountNumber = 1000L;
        Double depositAmount = 10000.0;
        mockCreateAccount(accountNumber,true);
        mockGetAccountBalance(accountNumber,95000.0);
        Assertions.assertThrows(DepositExceptions.MaximumAccountBalanceExceededException.class,
                ()->{
                    depositService.deposit(accountNumber,depositAmount);
                });
    }

    @Test
    public void verifyDeposit_withValidInput_returnsAccountBalance() throws Exception {
        Long accountNumber = 1000L;
        Double depositAmount = 2000.0;
        Double mockedAccountBalance = 20000.0;
        mockCreateAccount(accountNumber,true);
        mockGetAccountDepositsMethod(accountNumber,1L);
        mockGetAccountBalance(accountNumber,mockedAccountBalance);
        doNothing().when(inMemoryDataBase).depositAmount(accountNumber,depositAmount);
        depositService.deposit(accountNumber,depositAmount);
        verify(inMemoryDataBase,times(2)).getAccountBalance(accountNumber);
    }
    
    private void mockGetAccountDepositsMethod(Long accountNumber, Long numberOfDeposits){
        LocalDate transactionDate = LocalDate.now();
        Double transactionAmount = 2000.0;

        List<Deposits> userDeposits = new ArrayList<>();

        for(int i=0; i< numberOfDeposits; i++){
            Deposits deposit = new Deposits(accountNumber,transactionDate,transactionAmount);
            userDeposits.add(deposit);
        }
        when(inMemoryDataBase.getAccountDeposits(accountNumber)).thenReturn(userDeposits);
    }

    private void mockCreateAccount(Long accountNumber, boolean mockedValue){
        when(inMemoryDataBase.doesAccountExist(accountNumber)).thenReturn(mockedValue);
    }

    private void mockGetAccountBalance(Long accountNumber, Double mockedAccountBalance){
        when(inMemoryDataBase.getAccountBalance(accountNumber)).thenReturn(mockedAccountBalance);
    }
}