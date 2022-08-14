package utils;

import com.google.inject.AbstractModule;
import dao.InMemoryDataBase;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.deposit.DepositService;
import service.deposit.DepositServiceImpl;
import service.transaction.TransactionImpl;
import service.transaction.TransactionService;
import service.transfer.TransferService;
import service.transfer.TransferServiceImpl;
import service.withdawal.WithdrawService;
import service.withdawal.WithdrawServiceImpl;

public class BindingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(InMemoryDataBase.class);
        bind(AccountService.class).to(AccountServiceImpl.class);

        bind(TransactionService.class).to(TransactionImpl.class);

        bind(TransferService.class).to(TransferServiceImpl.class);
        bind(WithdrawService.class).to(WithdrawServiceImpl.class);
        bind(DepositService.class).to(DepositServiceImpl.class);

    }

}
