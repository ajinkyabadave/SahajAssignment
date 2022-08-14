package exceptions;


public abstract class BaseException extends RuntimeException{

    public BaseException(String msg) {
        super(msg);
    }
}