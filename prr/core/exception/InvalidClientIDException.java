package prr.core.exception;

public class InvalidClientIDException extends Exception{

    private static final long serialVersionUID = 202110252107L;

    private String _key;

    public InvalidClientIDException(String key) {
        _key = key;
    }

    public String getID() {
        return _key;
    }
}
