package exceptions;

import utils.Constants;

public class DepositExceptions {

    public static class MinimumDepositAmountException extends BaseException{
        public MinimumDepositAmountException() {
            super(String.format(Constants.MIN_DEPOSIT_AMT_EXCEPTION,Constants.MIN_DEPOSIT_AMT));
        }
    }
    public static class MaximumDepositAmountException extends BaseException{
        public MaximumDepositAmountException() {
            super(String.format(Constants.MAX_DEPOSIT_AMT_EXCEPTION,Constants.MAX_DEPOSIT_AMT));
        }
    }
    public static class MaximumNumberOfDepositsException extends BaseException{
        public MaximumNumberOfDepositsException() {
            super(String.format(Constants.MAX_NO_DEPOSITS_EXCEPTION, Constants.MAX_DEPOSITS_PER_DAY));
        }
    }
    public static class MaximumAccountBalanceExceededException extends BaseException{
        public MaximumAccountBalanceExceededException() {
            super(Constants.MAX_BALANCE_EXCEEDED_EXCEPTION);
        }
    }

}
