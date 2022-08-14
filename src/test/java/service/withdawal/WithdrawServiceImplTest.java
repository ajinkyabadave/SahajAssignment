package service.withdawal;

import static org.mockito.Mockito.*;

import dao.InMemoryDataBase;
import exceptions.AccountException;
import exceptions.WithdrawExceptions;
import models.Withdrawals;
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
class WithdrawServiceImplTest {
    @Mock
    InMemoryDataBase inMemoryDataBase;
    @InjectMocks
    WithdrawServiceImpl withdrawService;

    @Test
    public void verifyWithdraw_withInvalidAccount_throwsAccountDoesNotExistError(){
        Long accountNumber = 2000L;
        Double withdrawAmount = 1000.0;
        mockCreateAccount(accountNumber,false);
        Assertions.assertThrows(AccountException.AccountDoesNotExist.class,
                ()->{
                    withdrawService.withdraw(accountNumber,withdrawAmount);
                });
    }

    @Test
    public void verifyWithdraw_withWithdrawAmtLessThanMinWithdrawAmt_throwsMinWithdrawAmtError(){
        Long accountNumber = 1000L;
        Double withdrawAmount = 900.0;
        mockCreateAccount(accountNumber,true);
        Assertions.assertThrows(WithdrawExceptions.MinimumWithdrawAmountException.class,
                ()->{
                    withdrawService.withdraw(accountNumber,withdrawAmount);
                });
    }

    @Test
    public void verifyWithdraw_withWithdrawAmtMoreThanMaxWithdrawAmt_throwsMaxWithdrawAmtError(){
        Long accountNumber = 1000L;
        Double withdrawAmount = 30000.0;
        mockCreateAccount(accountNumber,true);
        Assertions.assertThrows(WithdrawExceptions.MaximumWithdrawAmountException.class,
                ()->{
                    withdrawService.withdraw(accountNumber,withdrawAmount);
                });
    }

    @Test
    public void verifyWithdraw_withRemainingAmountLessThanMinAccountBalance_throwsMinAccountBalanceReachedError(){
        Long accountNumber = 1000L;
        Double withdrawAmount = 19600.0;
        mockCreateAccount(accountNumber,true);
        mockGetAccountBalance(accountNumber,20000.0);
        Assertions.assertThrows(WithdrawExceptions.MinimumAccountBalanceReachedException.class,
                ()->{
                    withdrawService.withdraw(accountNumber,withdrawAmount);
                });
    }

    @Test
    public void verifyWithdraw_withRemainingAmountLessThanAccountBalance_throwsInsufficientBalanceError(){
        Double withdrawAmount = 22000.0;
        Long accountNumber = 1000L;
        mockCreateAccount(accountNumber,true);
        mockGetAccountBalance(accountNumber,20000.0);
        Assertions.assertThrows(WithdrawExceptions.InsufficientBalanceException.class,
                ()->{
                    withdrawService.withdraw(accountNumber,withdrawAmount);
                });
    }

    @Test
    public void verifyWithdraw_withValidInput_returnsRemainingAccountBalance() throws Exception {
        Long accountNumber = 1000L;
        Double withdrawAmount = 2000.0;
        Double mockedAccountBalance = 20000.0;
        mockCreateAccount(accountNumber,true);
        mockGetAccountWithdrawalsMethod(accountNumber,1L);
        mockGetAccountBalance(accountNumber,mockedAccountBalance);
        doNothing().when(inMemoryDataBase).withdrawAmount(accountNumber,withdrawAmount);
        withdrawService.withdraw(accountNumber,withdrawAmount);
        verify(inMemoryDataBase,times(2)).getAccountBalance(accountNumber);
    }

    @Test
    public void verifyWithdraw_withNoOfWithdrawalsMoreThanMaxNoOfWithdrawals_throwsMaxNoOfWithdrawalsError(){
        Long accountNumber = 1000L;
        Double withdrawAmount = 20000.0;
        mockCreateAccount(accountNumber,true);
        mockGetAccountWithdrawalsMethod(accountNumber,3L);
        Assertions.assertThrows(WithdrawExceptions.MaximumNumberOfWithdrawsException.class,
                ()->{
                    withdrawService.withdraw(accountNumber,withdrawAmount);
                });
    }

    private void mockGetAccountBalance(Long accountNumber, Double mockedAccountBalance){
        when(inMemoryDataBase.getAccountBalance(accountNumber)).thenReturn(mockedAccountBalance);
    }

    private void mockCreateAccount(Long accountNumber, boolean mockedValue){
        when(inMemoryDataBase.doesAccountExist(accountNumber)).thenReturn(mockedValue);
    }


    private void mockGetAccountWithdrawalsMethod(Long accountNumber, Long numberOfWithdrawals){
        LocalDate transactionDate = LocalDate.now();
        Double transactionAmount = 2000.0;

        List<Withdrawals> userWithdrawals = new ArrayList<>();

        for(int i=0; i< numberOfWithdrawals; i++){
            Withdrawals withdrawal = new Withdrawals(accountNumber,transactionDate,transactionAmount);
            userWithdrawals.add(withdrawal);
        }
        when(inMemoryDataBase.getAccountWithdrawals(accountNumber)).thenReturn(userWithdrawals);
    }

}