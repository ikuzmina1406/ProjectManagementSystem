package ua.GoIt.exeptions;


public class SqlReturnedItemException extends RuntimeException {
    public SqlReturnedItemException(String message) {
        super(message);
    }
}