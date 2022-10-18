package prr.core.exception;

public class NoClientException extends Exception{
    private static final String ERROR_MESSAGE = "O seguinte id de Cliente não existe: ";

    public NoClientException(String ClientId){
        super(ERROR_MESSAGE + ClientId);
    }
}
