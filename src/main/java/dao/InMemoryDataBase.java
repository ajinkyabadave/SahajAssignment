package dao;

import models.Account;
import models.Deposits;
import models.Withdrawals;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class InMemoryDataBase {
    static Long accountNumberCounter = 1001L;
    static ConcurrentHashMap<Long, Account> accountsTable = new ConcurrentHashMap<>();
    static ConcurrentHashMap<Long, List<Deposits>> depositsTable = new ConcurrentHashMap<>();
    static ConcurrentHashMap<Long, List<Withdrawals>> withdrawalsTable = new ConcurrentHashMap<>();

    public Long createAccount(String accountHolderName){
        Long accountNumber;
        synchronized (accountNumberCounter){
            accountNumber = accountNumberCounter++;
        }
        Account newAccount = new Account(accountNumber,accountHolderName);
        accountsTable.put(accountNumber,newAccount);
        return accountNumber;
    }

    public boolean doesAccountExist(String accountName){
        return accountsTable.values().stream().anyMatch(account -> account.getAccountHolderName().equals(accountName));
    }

    public boolean doesAccountExist(Long accountNumber){
        return accountsTable.containsKey(accountNumber);
    }

    public List<Deposits> getAccountDeposits(Long accountNumber){
        return depositsTable.getOrDefault(accountNumber, Collections.emptyList());
    }

    public void depositAmount(Long accountNumber,Double depositAmount){
        Account account = accountsTable.get(accountNumber);
        account.setAccountBalance(account.getAccountBalance() + depositAmount);
        accountsTable.put(accountNumber,account);

        Deposits deposit = new Deposits(account.getAccountNumber(), LocalDate.now(),depositAmount);
        List<Deposits> userDeposits = depositsTable.getOrDefault(accountNumber, new ArrayList<>());
        userDeposits.add(deposit);
        depositsTable.put(accountNumber,userDeposits);
    }

    public Double getAccountBalance(Long accountNumber){
        return accountsTable.get(accountNumber).getAccountBalance();
    }

    public List<Withdrawals> getAccountWithdrawals(Long accountNumber){
        return withdrawalsTable.getOrDefault(accountNumber,Collections.emptyList());
    }

    public void withdrawAmount(Long accountNumber, Double withdrawalAmount){
        Account account = accountsTable.get(accountNumber);
        account.setAccountBalance(account.getAccountBalance() - withdrawalAmount);
        accountsTable.put(accountNumber,account);

        Withdrawals withdrawal = new Withdrawals(account.getAccountNumber(),LocalDate.now(),withdrawalAmount);
        List<Withdrawals> userWithdrawals = withdrawalsTable.getOrDefault(accountNumber, new ArrayList<>());
        userWithdrawals.add(withdrawal);
        withdrawalsTable.put(accountNumber,userWithdrawals);
    }

}
