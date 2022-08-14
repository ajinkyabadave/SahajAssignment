package service.transaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.deposit.DepositService;
import service.transfer.TransferService;
import service.withdawal.WithdrawService;
import utils.Constants;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionImplTest {
    @Mock
    WithdrawService withdrawService;
    @Mock
    DepositService depositService;
    @Mock
    TransferService transferService;
    @InjectMocks
    TransactionImpl transactionImpl;

    @Test
    public void verifyDeposit_withValidInput_ReturnsAccountBalance() throws Exception{
        Long accountNumber = 1000L;
        Double depositAmount = 2000.0;
        Double expectedAccountBalance = 4000.0;
        when(depositService.deposit(accountNumber,depositAmount)).thenReturn(expectedAccountBalance);
        Double actualAccountBalance = transactionImpl.deposit(accountNumber,depositAmount);
        assertEquals(expectedAccountBalance,actualAccountBalance);
    }

    @Test
    public void verifyWithdraw_withValidInput_ReturnsAccountBalance() throws Exception{
        Long accountNumber = 1000L;
        Double withdrawAmount = 2000.0;
        Double expectedAccountBalance = 4000.0;
        when(withdrawService.withdraw(accountNumber,withdrawAmount)).thenReturn(expectedAccountBalance);
        Double actualAccountBalance = transactionImpl.withDraw(accountNumber,withdrawAmount);
        assertEquals(expectedAccountBalance,actualAccountBalance);
    }

    @Test
    public void verifyTransfer_withValidInput_ReturnsAccountBalance() throws Exception{
        Long sendersAccountNumber = 1000L;
        Long receiversAccountNumber = 1000L;
        Double transferAmount = 2000.0;
        String expectedMessage = Constants.TRANSFER_SUCCESSFUL_MESSAGE;
        when(transferService.transfer(sendersAccountNumber,receiversAccountNumber,transferAmount)).thenReturn(expectedMessage);
        String actualMessage = transactionImpl.transfer(sendersAccountNumber,receiversAccountNumber,transferAmount);
        assertEquals(expectedMessage,actualMessage);
    }
}