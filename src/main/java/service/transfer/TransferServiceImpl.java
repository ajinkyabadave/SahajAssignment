package service.transfer;

import com.google.inject.Inject;
import service.deposit.DepositService;
import service.withdawal.WithdrawService;
import utils.Constants;

public class TransferServiceImpl implements TransferService  {
    @Inject
    DepositService depositService;
    @Inject
    WithdrawService withdrawService;

    @Override
    public String transfer(Long sendersAccountNumber, Long receiversAccountNumber, Double transferAmount) throws Exception {
        this.withdrawService.withdraw(sendersAccountNumber,transferAmount);
        this.depositService.deposit(receiversAccountNumber,transferAmount);
        return Constants.TRANSFER_SUCCESSFUL_MESSAGE;
    }

}
