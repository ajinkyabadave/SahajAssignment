package exceptions;

import utils.Constants;

public class WithdrawExceptions {

    public static class MinimumWithdrawAmountException extends BaseException{
        public MinimumWithdrawAmountException() {
            super(String.format(Constants.MIN_WITHDRAW_AMT_EXCEPTION,Constants.MIN_WITHDRAW_AMT));
        }
    }
    public static class MaximumWithdrawAmountException extends BaseException{
        public MaximumWithdrawAmountException() {
            super(String.format(Constants.MAX_WITHDRAW_AMT_EXCEPTION,Constants.MAX_WITHDRAW_AMT));
        }
    }
    public static class MaximumNumberOfWithdrawsException extends BaseException{
        public MaximumNumberOfWithdrawsException() {
            super(String.format(Constants.MAX_NO_WITHDRAWALS_EXCEPTION, Constants.MAX_WITHDRAWALS_PER_DAY));
        }
    }
    public static class MinimumAccountBalanceReachedException extends BaseException{
        public MinimumAccountBalanceReachedException() {
            super(Constants.MIN_BALANCE_REACHED_EXCEPTION);
        }
    }
    public static class InsufficientBalanceException extends BaseException{
        public InsufficientBalanceException() {
            super(Constants.INSUFFICIENT_BALANCE_EXCEPTION);
        }
    }

}
