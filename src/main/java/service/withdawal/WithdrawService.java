package service.withdawal;

public interface WithdrawService {
    Double withdraw(Long accountNumber, Double withdrawAmount) throws Exception;
}
