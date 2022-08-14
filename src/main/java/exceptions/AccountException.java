package exceptions;

import utils.Constants;

public class AccountException {

    public static class AccountAlreadyExistsException extends BaseException{
        public AccountAlreadyExistsException() {
            super(Constants.ACCOUNT_ALREADY_EXISTS_EXCEPTION);
        }
    }

    public static class AccountDoesNotExist extends BaseException{
        public AccountDoesNotExist() {
            super(Constants.ACCOUNT_DOES_NOT_EXISTS_EXCEPTION);
        }
    }
}
