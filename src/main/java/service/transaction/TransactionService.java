package service.transaction;

public interface TransactionService {
    Double deposit(Long accountNumber, Double depositAmount) throws Exception;
    String transfer(Long sendersAccountNumber, Long receiversAccountNumber, Double transferAmount) throws Exception;
    Double withDraw(Long accountNumber, Double withdrawAmount) throws Exception;
}
