package service.transfer;

import service.withdawal.WithdrawService;

public interface TransferService {
    String transfer(Long sendersAccountNumber, Long receiversAccountNumber, Double transferAmount) throws Exception;
}
