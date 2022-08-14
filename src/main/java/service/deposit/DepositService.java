package service.deposit;

public interface DepositService {
    Double deposit(Long accountNumber, Double depositAmount) throws Exception;
}
