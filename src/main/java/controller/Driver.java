package controller;

import com.google.inject.Inject;
import service.account.AccountService;
import service.transaction.TransactionService;

public class Driver {
    @Inject
    TransactionService transactionService;
    @Inject
    AccountService accountService;

    public void run(){
        System.out.println("Create account tests: "+ "\n");

        tryCreateAccount("Amit Dugal");         // valid -> returns 1001
        tryCreateAccount("Gauri Kalla");        // valid -> returns 1002
        tryCreateAccount("Ajinkya Badave");     // valid -> returns 1003
        tryCreateAccount("Sahaj Software");     // valid -> returns 1004
        tryCreateAccount("Gauri Kalla");        // error -> Account already exists

        System.out.println("\n" + "Deposit tests:" + "\n");

        tryDeposit(1001,500);      // valid -> returns 500
        tryDeposit(1001,1000);     // valid -> returns 1500
        tryDeposit(1003,15000);    // valid -> returns 15000
        tryDeposit(1001,100);      // error -> Minimum deposit amount is 500.0
        tryDeposit(1001,60000);    // error -> Maximum deposit amount is 50000.0
        tryDeposit(1001,10000);    // valid -> returns 11500.0
        tryDeposit(1001,10000);    // error -> Only 3 deposits are allowed per day
        tryDeposit(1000,10000);    // error -> Account does not exist

        System.out.println("\n" +"Balance tests:" +"\n");

        tryBalance(1001L);                      // valid -> returns 11500.0
        tryBalance(1000L);                      // error -> Account does not exist

        System.out.println("Withdrawal tests:" + "\n");

        tryWithdrawal(1001,500);    // error -> Minimum withdrawal amount is 1000.0
        tryWithdrawal(1001,20000);  // error -> Insufficient balance
        tryWithdrawal(1001,1000);   // valid -> returns 10500.0
        tryWithdrawal(1001,1900);   // valid -> returns 8600.0
        tryWithdrawal(1001,30000);  // error -> Maximum withdrawal amount is 25000.0
        tryWithdrawal(1001,1000);   // valid -> returns 7600.0
        tryWithdrawal(1001,5000);   // error -> Only 3 Withdraws are allowed per day
        tryWithdrawal(1000,5000);   // error -> Account does not exist

        System.out.println("\n" +"Transfer tests: "+"\n");

        tryTransfer(1003,1004,5000);    // valid -> returns Transfer Successful
        tryTransfer(1001,1002,500);     // error -> Minimum withdrawal amount is 1000.0
        tryTransfer(1001,1002,30000);   // error -> Maximum withdrawal amount is 25000.0
        tryTransfer(1003,1005,2000);    // error -> Account does not exist
        tryTransfer(1005,1002,2000);    // error -> Account does not exist

    }

    private void tryDeposit(Integer accountNumber, Integer depositAmount){
        try{
            System.out.println(transactionService.deposit(Long.valueOf(accountNumber),Double.valueOf(depositAmount)));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void tryCreateAccount(String accountName){
        try{
            System.out.println(accountService.createAccount(accountName));
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void tryBalance(Long accountNumber){
        try{
            System.out.println(accountService.balance(accountNumber));
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void tryWithdrawal(Integer accountNumber, Integer withdrawalAmount){
        try{
            System.out.println(transactionService.withDraw(Long.valueOf(accountNumber),Double.valueOf(withdrawalAmount)));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void tryTransfer(Integer sendersAccountNumber, Integer receiversAccountNumber, Integer transferAmount){
        try{
            System.out.println(transactionService.transfer(Long.valueOf(sendersAccountNumber),Long.valueOf(receiversAccountNumber),
                    Double.valueOf(transferAmount)));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
