package dao;

import models.Deposits;
import models.Withdrawals;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InMemoryDataBaseTest {

    @InjectMocks
    InMemoryDataBase inMemoryDataBase;

    Long testAccountNumber;
    String testAccountName = "Test Account 1";
    Double testDepositAmount = 5000.0;

    @BeforeEach
    void setUp(){
        this.testAccountNumber = inMemoryDataBase.createAccount(testAccountName);
        inMemoryDataBase.depositAmount(testAccountNumber,testDepositAmount);
    }

    @Test
    @Order(1)
    void verifyCreateAccount_withValidInput_returnsAccountNumber() {
        String accountName = "Test Account 2";
        Long expectedAccountNumber = 1002L;
        Long actualAccountNumber = inMemoryDataBase.createAccount(accountName);
        assertEquals(expectedAccountNumber,actualAccountNumber);
    }

    @Test
    void verifyDoesAccountExist_withValidAccountNumberAsInput_returnsBoolean() {
        boolean actualResponse = inMemoryDataBase.doesAccountExist(testAccountNumber);
        assertTrue(actualResponse);
    }

    @Test
    void verifyDoesAccountExist_withInvalidAccountNumberAsInput_returnsBoolean() {
        Long inValidAccountNumber = 2000L;
        boolean actualResponse = inMemoryDataBase.doesAccountExist(inValidAccountNumber);
        assertFalse(actualResponse);
    }

    @Test
    void verifyDoesAccountExist_withValidAccountNameAsInput_returnsBoolean() {
        boolean actualResponse = inMemoryDataBase.doesAccountExist(testAccountName);
        assertTrue(actualResponse);
    }

    @Test
    void verifyDoesAccountExist_withInvalidAccountNameAsInput_returnsBoolean() {
        String inValidAccountName = "Test Account 10";
        boolean actualResponse = inMemoryDataBase.doesAccountExist(inValidAccountName);
        assertFalse(actualResponse);
    }

    @Test
    void verifyGetAccountDeposits_withAccountHavingZeroDeposits_returnsEmptyList() {
        Long accountNumber = inMemoryDataBase.createAccount("Test Account 3");
        List<Deposits> actualDepositsList = inMemoryDataBase.getAccountDeposits(accountNumber);
        assertEquals(Collections.emptyList(),actualDepositsList);
    }

    @Test
    void verifyGetAccountDeposits_withAccountHavingOneDeposit_returnsDepositList() {
        List<Deposits> actualDepositsList = inMemoryDataBase.getAccountDeposits(testAccountNumber);
        assertFalse(actualDepositsList.isEmpty());
        assertEquals(1,actualDepositsList.size());
        assertEquals(testAccountNumber,actualDepositsList.get(0).getAccountNumber());
        assertEquals(testDepositAmount, actualDepositsList.get(0).getDepositAmount());
        assertEquals(LocalDate.now(),actualDepositsList.get(0).getTransactionDate());
    }

    @Test
    void verifyGetAccountBalance_withValidAccountNumber_returnsAccountBalance() {
        Double actualAccountBalance = inMemoryDataBase.getAccountBalance(testAccountNumber);
        assertEquals(testDepositAmount,actualAccountBalance);
    }

    @Test
    void verifyGetAccountWithdrawals_withAccountHavingZeroWithdrawals_returnsEmptyList() {
        Long accountNumber = inMemoryDataBase.createAccount("Test Account 4");
        List<Withdrawals> actualWithdrawalsList = inMemoryDataBase.getAccountWithdrawals(accountNumber);
        assertEquals(Collections.emptyList(),actualWithdrawalsList);
    }

    @Test
    void verifyGetAccountWithdrawals_withAccountHavingOneWithdrawals_returnsWithdrawalsList() {
        Double testWithdrawalAmount = 1500.0;
        inMemoryDataBase.withdrawAmount(testAccountNumber,testWithdrawalAmount);
        List<Withdrawals> actualWithdrawalsList = inMemoryDataBase.getAccountWithdrawals(testAccountNumber);
        assertFalse(actualWithdrawalsList.isEmpty());
        assertEquals(1,actualWithdrawalsList.size());
        assertEquals(testAccountNumber,actualWithdrawalsList.get(0).getAccountNumber());
        assertEquals(testWithdrawalAmount, actualWithdrawalsList.get(0).getWithdrawalAmount());
        assertEquals(LocalDate.now(),actualWithdrawalsList.get(0).getTransactionDate());
    }
//
//    @Test
//    void getAccountWithdrawals() {
//    }
//
//    @Test
//    void withdrawAmount() {
//    }
}