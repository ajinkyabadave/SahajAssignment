package service.transfer;

import dao.InMemoryDataBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.deposit.DepositService;
import service.withdawal.WithdrawService;
import utils.Constants;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {
    @Mock
    DepositService depositService;
    @Mock
    WithdrawService withdrawService;
    @Mock
    InMemoryDataBase inMemoryDataBase;
    @InjectMocks
    TransferServiceImpl transferService;

    @Test
    public void verifyTransfer_withValidInput_returnsSuccessfulTransferMessage() throws Exception {
        Long sendersAccountNumber = 1000L;
        Long receiversAccountNumber = 1001L;
        Double transferAmount = 2000.0;
        String expectedResponse = Constants.TRANSFER_SUCCESSFUL_MESSAGE;
        when(withdrawService.withdraw(sendersAccountNumber,transferAmount)).thenReturn(5000.0);
        when(depositService.deposit(receiversAccountNumber,transferAmount)).thenReturn(7000.0);
        String actualResponse = transferService.transfer(sendersAccountNumber,receiversAccountNumber,transferAmount);
        assertEquals(expectedResponse,actualResponse);
    }
}