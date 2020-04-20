package be.exceptions;

public class ValidationException extends Exception {

    private String errorCode;
    private String errorMessage;

    public ValidationException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }

}
