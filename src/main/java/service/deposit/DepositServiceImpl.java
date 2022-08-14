package service.deposit;

import com.google.inject.Inject;
import dao.InMemoryDataBase;
import exceptions.AccountException;
import exceptions.BaseException;
import exceptions.DepositExceptions;
import models.Deposits;
import utils.Constants;

import java.time.LocalDate;
import java.util.List;

public class DepositServiceImpl implements DepositService {
    @Inject
    InMemoryDataBase inMemoryDataBase;

    @Override
    public Double deposit(Long accountNumber, Double depositAmount) throws Exception {
        validateDeposit(accountNumber,depositAmount);
        inMemoryDataBase.depositAmount(accountNumber,depositAmount);
        return inMemoryDataBase.getAccountBalance(accountNumber);
    }

    public void validateDeposit(Long accountNumber, Double depositAmount) throws Exception{
        if(!inMemoryDataBase.doesAccountExist(accountNumber))
            throw new AccountException.AccountDoesNotExist();

        if(depositAmount < Constants.MIN_DEPOSIT_AMT)
            throw new DepositExceptions.MinimumDepositAmountException();

        if(depositAmount > Constants.MAX_DEPOSIT_AMT)
            throw new DepositExceptions.MaximumDepositAmountException();

        if(getCurrentDayDepositsCount(accountNumber))
            throw new DepositExceptions.MaximumNumberOfDepositsException();

        if(inMemoryDataBase.getAccountBalance(accountNumber) + depositAmount > Constants.MAX_ACCOUNT_BALANCE)
            throw new DepositExceptions.MaximumAccountBalanceExceededException();
    }

    private boolean getCurrentDayDepositsCount(Long accountNumber){
        List<Deposits> userDeposits= inMemoryDataBase.getAccountDeposits(accountNumber);
        long depositsCountOnCurrentDate = userDeposits.stream().
                filter(deposit -> deposit.getTransactionDate().isEqual(LocalDate.now())).count();

        return depositsCountOnCurrentDate >= Constants.MAX_DEPOSITS_PER_DAY;
    }
}
