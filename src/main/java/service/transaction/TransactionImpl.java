package service.transaction;


import com.google.inject.Inject;
import service.deposit.DepositService;
import service.transfer.TransferService;
import service.withdawal.WithdrawService;

public  class TransactionImpl implements TransactionService{
   @Inject
   DepositService depositService;
   @Inject
   WithdrawService withdrawService;
   @Inject
   TransferService transferService;

   @Override
   public Double deposit(Long accountNumber, Double depositAmount) throws Exception {
      return depositService.deposit(accountNumber, depositAmount);
   }

   @Override
   public String transfer(Long sendersAccountNumber, Long receiversAccountNumber, Double transferAmount) throws Exception {
      return this.transferService.transfer(sendersAccountNumber,receiversAccountNumber,transferAmount);
   }

   @Override
   public Double withDraw(Long accountNumber, Double withdrawAmount) throws Exception {
      return withdrawService.withdraw(accountNumber, withdrawAmount);
   }
}
