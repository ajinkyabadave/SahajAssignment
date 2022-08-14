package service.withdawal;

import com.google.inject.Inject;
import dao.InMemoryDataBase;
import exceptions.AccountException;
import exceptions.WithdrawExceptions;
import models.Withdrawals;
import utils.Constants;

import java.time.LocalDate;
import java.util.List;

public class WithdrawServiceImpl implements WithdrawService  {
    @Inject
    InMemoryDataBase inMemoryDataBase;

    @Override
    public Double withdraw(Long accountNumber, Double withdrawAmount) throws Exception{
        validateWithdraw(accountNumber,withdrawAmount);
        inMemoryDataBase.withdrawAmount(accountNumber, withdrawAmount);
        return inMemoryDataBase.getAccountBalance(accountNumber);
    }

    private void validateWithdraw(Long accountNumber, Double withdrawAmount) throws Exception{
            if(!inMemoryDataBase.doesAccountExist(accountNumber))
                throw new AccountException.AccountDoesNotExist();

            if(withdrawAmount < Constants.MIN_WITHDRAW_AMT)
                throw new WithdrawExceptions.MinimumWithdrawAmountException();

            if(withdrawAmount > Constants.MAX_WITHDRAW_AMT)
                throw new WithdrawExceptions.MaximumWithdrawAmountException();

            if(getCurrentDayWithdrawalsCount(accountNumber))
                throw new WithdrawExceptions.MaximumNumberOfWithdrawsException();

            double remainingAmount =inMemoryDataBase.getAccountBalance(accountNumber) - withdrawAmount;

            if(remainingAmount < 0)
                throw new WithdrawExceptions.InsufficientBalanceException();

            if(remainingAmount< Constants.MIN_ACCOUNT_BALANCE && remainingAmount > 0)
                throw new WithdrawExceptions.MinimumAccountBalanceReachedException();

    }
    private boolean getCurrentDayWithdrawalsCount(Long accountNumber){
        List<Withdrawals> userWithdrawals= inMemoryDataBase.getAccountWithdrawals(accountNumber);
        long withdrawalsCountOnCurrentDate = userWithdrawals.stream().
                filter(withdrawal -> withdrawal.getTransactionDate().isEqual(LocalDate.now())).count();

        return withdrawalsCountOnCurrentDate >= Constants.MAX_WITHDRAWALS_PER_DAY;
    }
}
