package prr.core.exception;

public class InvalidClientIDException extends Exception{

    private static final String ERROR_MESSAGE = "O seguinte id de Cliente não existe: ";

    public InvalidClientIDException(String ClientId){
        super(ERROR_MESSAGE + ClientId);
    }
}
